import { mapResponseToColor } from "../constants";
import {
  setTestStatus,
  setTestInputOutput,
  setTestDataSendStatus,
} from "../reducers/tests";

const startTest = async (dispatch, getState) => {
  try {
    const pageId = getState().pageId;
    const activeId = getState().tests.activeId[pageId];
    dispatch(setTestDataSendStatus(pageId, activeId, true));
    if (activeId !== -1) {
      // dispatch(gettingTests()); //????????????????????????????
      const response = await fetch(
        `/api/test/run/${getState().tests.activeId[pageId]}`,
        {
          method: "POST",
          headers: {
            Accept: "application/json", // eslint-disable-line prettier/prettier
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            solutionText: getState().enteredCode.code[pageId],
            progLanguage: getState().enteredCode.language[pageId],
          }),
        }
      );
      if (!response.ok) {
        // console.log("oshibka pri zapuske testa");
        dispatch(setTestDataSendStatus(pageId, activeId, false));
        return;
      }
      dispatch(setTestInputOutput(pageId, activeId, "output", ""));
      const jsonedResponse = await response.json();
      const polling = () => {
        setTimeout(async () => {
          const response = await fetch(
            `/api/test/run/${jsonedResponse.testQueueId}`
          ); //oshibki ne lovlu poka
          if (
            response.status !== "IN_QUEUE" &&
            response.status !== "PROCESSING"
          ) {
            const testStatus = await response.json();
            dispatch(
              setTestStatus(
                pageId,
                jsonedResponse.testId,
                mapResponseToColor[testStatus.status]
              )
            );
            dispatch(
              setTestInputOutput(
                pageId,
                jsonedResponse.testId,
                "output",
                testStatus.testOutput
              )
            );
            dispatch(setTestDataSendStatus(pageId, activeId, false));
            // clearTimeout(timer);
          } else {
            // console.log("eshe response ne ok ot proverki");
            polling(); //esli v otvet prishla 404 (skazhem ne bil vibran yazik potomuchto) to zapros beskonechniy
          }
        }, 1000);
      };
      polling();
    } else {
      dispatch(setTestDataSendStatus(pageId, "allTests", true));
      const response = await fetch(`/api/test/${pageId}/runAll`, {
        method: "POST",
        headers: {
          Accept: "application/json", // eslint-disable-line prettier/prettier
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          solutionText: getState().enteredCode.code[pageId],
          progLanguage: getState().enteredCode.language[pageId],
        }),
      });
      if (!response.ok) {
        // console.log("oshibka pri zapuske vseh testov");
        dispatch(setTestDataSendStatus(pageId, "allTests", false));
        return;
      }

      const jsonedResponse = await response.json();
      // console.log("response after All test start", jsonedResponse);
      const testsIds = getState().tests.tests[pageId];
      Object.keys(testsIds).forEach(test => {
        if (test === "testsList") return;
        dispatch(setTestStatus(pageId, test, "neutral"));
        dispatch(setTestDataSendStatus(pageId, test, true));
        dispatch(setTestInputOutput(pageId, test, "output", ""));
      });

      const polling = (testsStatuses, startNumber) => {
        setTimeout(async () => {
          let testsResponses = await Promise.all(
            testsStatuses.map(async test => {
              if (test.hasOwnProperty("testOutput")) {
                return test;
              } else {
                let response = await fetch(`/api/test/run/${test.testQueueId}`); //oshibki ne lovlu poka
                let jsonedResponse = await response.json();
                return jsonedResponse;
              }
            })
          );

          let newTestsStatuses = [];
          // console.log(testsResponses);
          let canWriteOutput = true;
          let someTestsLeft = false;
          let inQueueNumbers = [];
          testsResponses.forEach((test, index) => {
            if (index < startNumber) {
              newTestsStatuses.push({
                testId: test.testId,
                status: test.status,
                testOutput: test.testOutput,
              });
              return;
            }
            if (test.status === "IN_QUEUE" || test.status === "PROCESSING") {
              newTestsStatuses.push({
                testId: test.testId,
                testQueueId: jsonedResponse.testsStatuses[index].testQueueId,
              });
              canWriteOutput = false;
              someTestsLeft = true;
              inQueueNumbers.push(index);
            } else {
              newTestsStatuses.push({
                testId: test.testId,
                status: test.status,
                testOutput: test.testOutput,
              });
              dispatch(
                setTestInputOutput(
                  pageId,
                  test.testId,
                  "output",
                  test.testOutput
                )
              );
              dispatch(
                setTestStatus(
                  pageId,
                  test.testId,
                  mapResponseToColor[test.status]
                )
              );
              dispatch(setTestDataSendStatus(pageId, test.testId, false));
            }
            if (canWriteOutput) {
              let output =
                getState().tests.tests[pageId].allTests.output === undefined
                  ? ""
                  : getState().tests.tests[pageId].allTests.output;
              // console.log(test.testOutput);
              if (test.status !== "ACCEPTED") {
                dispatch(setTestStatus(pageId, "allTests", "fail"));
                dispatch(
                  setTestInputOutput(
                    pageId,
                    "allTests",
                    "output",
                    `${output}\n${
                      getState().tests.tests[pageId][test.testId].text
                    }\n\nВвод\n\n${
                      getState().tests.tests[pageId][test.testId].input
                    }\n\nОжидаемый вывод\n\n${
                      getState().tests.tests[pageId][test.testId].expectedOutput
                    }\n\nВывод\n\n${
                      test.testOutput
                    }\nСтатус: Неверный ответ\n_________________________________`
                  )
                );
              } else {
                dispatch(
                  setTestInputOutput(
                    pageId,
                    "allTests",
                    "output",
                    `${output}\n${
                      getState().tests.tests[pageId][test.testId].text
                    } - Пройден\n_________________________________`
                  )
                );
              }
            }
          });
          if (someTestsLeft) {
            polling(newTestsStatuses, inQueueNumbers[0]); //esli v otvet prishla 404 (skazhem ne bil vibran yazik potomuchto) to zapros beskonechniy
          } else {
            dispatch(setTestDataSendStatus(pageId, "allTests", false));
            getState().tests.tests[pageId].allTests.status === "neutral" &&
              dispatch(setTestStatus(pageId, "allTests", "pass"));
          }
        }, 1000);
      };
      polling(jsonedResponse.testsStatuses, 0);
    }
  } catch (err) {
    //
  }
};

export default startTest;
