import {
  setTestList,
  setTestText,
  setTestInputOutput,
  setTestDataSendStatus,
} from "../reducers/tests";

const getTests = async (dispatch, getState) => {
  // console.log("I am in getTests");
  try {
    const pageId = getState().pageId;
    // dispatch(gettingTests()); //????????????????????????????
    const response = await fetch(`/api/test/${pageId}/list`);
    const tests = await response.json();
    dispatch(setTestList(pageId, tests.adminTests, tests.userTests));

    const allTests = [];
    tests.adminTests.forEach(element => {
      allTests.push(element);
    });
    tests.userTests.forEach(element => {
      allTests.push(element);
    });
    let testsInputs = await Promise.all(
      allTests.map(async test => {
        let response = await fetch(`/api/test/${test.testId}`);
        let jsonedResponse = await response.json();
        return jsonedResponse;
      })
    );
    testsInputs.forEach(test => {
      dispatch(setTestText(pageId, test.testId, test.testTitle));
      dispatch(
        setTestInputOutput(pageId, test.testId, "input", test.testInput)
      );
      dispatch(
        setTestInputOutput(
          pageId,
          test.testId,
          "expectedOutput",
          test.testExpectedOutput
        )
      );
      dispatch(setTestDataSendStatus(pageId, test.testId, false));
    });

    dispatch(setTestText(pageId, "allTests", "Запустить все тесты"));
    dispatch(setTestDataSendStatus(pageId, "allTests", false));
    if (!getState().tests.tests[pageId].allTests.output) {
      dispatch(setTestInputOutput(pageId, "allTests", "output", ""));
    }
    // console.log("I finished getTests");
  } catch (err) {
    //
  }
};

export default getTests;
