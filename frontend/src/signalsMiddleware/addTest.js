import {
  setTestList,
  setTestStatus,
  setTestInputOutput,
} from "../reducers/tests";

const addTest = async (dispatch, getState) => {
  try {
    const pageId = getState().pageId;
    dispatch(setTestStatus(pageId, "allTests", "neutral"));
    dispatch(setTestInputOutput(pageId, "allTests", "output", ""));

    const response = await fetch(`/api/test/${pageId}`, {
      method: "POST",
      headers: {
        Accept: "application/json", // eslint-disable-line prettier/prettier
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        testInput: "",
        testExpectedOutput: "",
        testTitle: "Тест",
      }),
    });
    const test = await response.json();
    const newTests = getState().tests.tests[pageId].testsList;
    newTests.userTests.push(test);
    dispatch(setTestList(pageId, newTests.adminTests, newTests.userTests));
  } catch (err) {
    //
  }
};

export default addTest;
