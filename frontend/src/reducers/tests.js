export const SET_EDIT_MODE = "SET_EDIT_MODE";
export const SET_ACTIVE_ID = "SET_ACTIVE_ID";
export const SET_TEST_LIST = "SET_TEST_LIST";
export const SET_TEST_TEXT = "SET_TEST_TEXT";
export const SET_TEST_STATUS = "SET_TEST_STATUS";
export const SET_TEST_DATA_SEND_STATUS = "SET_TEST_DATA_SEND_STATUS";
export const SET_TEST_INPUT_OUTPUT = "SET_TEST_INPUT_OUTPUT";

export const setEditMode = status => ({
  type: SET_EDIT_MODE,
  status,
});

export const setActiveId = (pageId, testId) => ({
  type: SET_ACTIVE_ID,
  pageId,
  testId,
});

export const setTestList = (pageId, adminTests, userTests) => ({
  type: SET_TEST_LIST,
  pageId,
  adminTests,
  userTests,
});

export const setTestText = (pageId, testId, text) => ({
  type: SET_TEST_TEXT,
  pageId,
  testId,
  text,
});

export const setTestStatus = (pageId, testId, status) => ({
  type: SET_TEST_STATUS,
  pageId,
  testId,
  status,
});

export const setTestDataSendStatus = (pageId, testId, status) => ({
  type: SET_TEST_DATA_SEND_STATUS,
  pageId,
  testId,
  status,
});

export const setTestInputOutput = (pageId, testId, inputKind, input) => ({
  type: SET_TEST_INPUT_OUTPUT,
  pageId,
  testId,
  inputKind,
  input,
});

export const tests = (
  state = { editMode: false, activeId: {}, tests: {} },
  action
) => {
  switch (action.type) {
    case SET_EDIT_MODE:
      return {
        ...state,
        editMode: action.status,
      };
    case SET_ACTIVE_ID:
      return {
        ...state,
        activeId: {
          ...state.activeId,
          [action.pageId]: action.testId,
        },
      };
    case SET_TEST_LIST:
      return {
        ...state,
        tests: {
          ...state.tests,
          [action.pageId]: {
            ...state.tests[action.pageId],
            testsList: {
              adminTests: action.adminTests,
              userTests: action.userTests,
            },
          },
        },
      };
    case SET_TEST_TEXT:
      return {
        ...state,
        tests: {
          ...state.tests,
          [action.pageId]: {
            ...state.tests[action.pageId],
            [action.testId]: {
              ...state.tests[action.pageId][action.testId],
              text: action.text,
            },
          },
        },
      };
    case SET_TEST_STATUS:
      return {
        ...state,
        tests: {
          ...state.tests,
          [action.pageId]: {
            ...state.tests[action.pageId],
            [action.testId]: {
              ...state.tests[action.pageId][action.testId],
              status: action.status,
            },
          },
        },
      };
    case SET_TEST_DATA_SEND_STATUS:
      return {
        ...state,
        tests: {
          ...state.tests,
          [action.pageId]: {
            ...state.tests[action.pageId],
            [action.testId]: {
              ...state.tests[action.pageId][action.testId],
              dataSendStatus: action.status,
            },
          },
        },
      };
    case SET_TEST_INPUT_OUTPUT:
      if (
        state.tests[action.pageId] &&
        state.tests[action.pageId][action.testId]
      ) {
        return {
          ...state,
          tests: {
            ...state.tests,
            [action.pageId]: {
              ...state.tests[action.pageId],
              [action.testId]: {
                ...state.tests[action.pageId][action.testId],
                [action.inputKind]: action.input,
              },
            },
          },
        };
      }
      return {
        ...state,
        tests: {
          ...state.tests,
          [action.pageId]: {
            ...state.tests[action.pageId],
            [action.testId]: {
              [action.inputKind]: action.input,
            },
          },
        },
      };
    default:
      return state;
  }
};
