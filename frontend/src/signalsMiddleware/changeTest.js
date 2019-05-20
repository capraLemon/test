import {
  setTestList,
  setTestStatus,
  setTestDataSendStatus,
  setTestInputOutput,
} from "../reducers/tests";

const changeTest = async (dispatch, getState) => {
  try {
    // console.log("I am in change test");
    const pageId = getState().pageId;
    const activeId = getState().tests.activeId[pageId];

    dispatch(setTestStatus(pageId, "allTests", "neutral"));
    dispatch(setTestInputOutput(pageId, "allTests", "output", ""));

    dispatch(setTestStatus(pageId, activeId, "neutral"));
    dispatch(setTestInputOutput(pageId, activeId, "output", ""));
    dispatch(setTestDataSendStatus(pageId, activeId, true));

    await fetch(`/api/test/${activeId}`, {
      method: "PUT",
      headers: {
        Accept: "application/json", // eslint-disable-line prettier/prettier
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        testInput: getState().tests.tests[pageId][activeId].input,
        testExpectedOutput: getState().tests.tests[pageId][activeId]
          .expectedOutput,
        testTitle: getState().tests.tests[pageId][activeId].text,
      }),
    });
    dispatch(setTestDataSendStatus(pageId, activeId, false));

    let userTests = getState().tests.tests[pageId].testsList.userTests;
    let newUserTests = userTests.map(test => {
      if (test.testId === activeId) {
        return {
          testTitle: getState().tests.tests[pageId][activeId].text,
          testId: test.testId,
        };
      }
      return test;
    });
    dispatch(
      setTestList(
        pageId,
        getState().tests.tests[pageId].testsList.adminTests,
        newUserTests
      )
    );
  } catch (err) {
    //
  }
};

export default changeTest;
