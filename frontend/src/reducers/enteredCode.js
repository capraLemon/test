export const SET_CODE = "SET_CODE";
export const SET_LANGUAGE = "SET_LANGUAGE";

export const setCode = enteredCode => ({
  type: SET_CODE,
  enteredCode,
});

export const setLanguage = choosedLanguage => ({
  type: SET_LANGUAGE,
  choosedLanguage,
});

export const enteredCode = (
  state = { code: "", language: "PYTHON" },
  action
) => {
  switch (action.type) {
    case SET_CODE:
      return { ...state, code: action.enteredCode };
    case SET_LANGUAGE:
      return { ...state, language: action.choosedLanguage };
    default:
      return state;
  }
};
