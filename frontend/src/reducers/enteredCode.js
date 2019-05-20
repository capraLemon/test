export const SET_CODE = "SET_CODE";
export const SET_LANGUAGE = "SET_LANGUAGE";

export const setCode = (enteredCode, pageId) => ({
  type: SET_CODE,
  enteredCode,
  pageId,
});

export const setLanguage = (choosedLanguage, pageId) => ({
  type: SET_LANGUAGE,
  choosedLanguage,
  pageId,
});

export const enteredCode = (state = { code: {}, language: {} }, action) => {
  switch (action.type) {
    case SET_CODE:
      return {
        ...state,
        code: { ...state.code, [action.pageId]: action.enteredCode },
      };
    case SET_LANGUAGE:
      return {
        ...state,
        language: {
          ...state.language,
          [action.pageId]: action.choosedLanguage,
        },
      };
    default:
      return state;
  }
};
