export const SET_LOGIN_PASSWORD = "SET_LOGIN_PASSWORD";
export const START_CONTEST = "START_CONTEST";
export const SET_USER_NAME = "SET_USER_NAME";

export const setLoggedUser = status => ({
  type: SET_LOGIN_PASSWORD,
  status,
});

export const startContest = flag => ({
  type: START_CONTEST,
  flag,
});

export const setUserName = userName => ({
  type: SET_USER_NAME,
  userName,
});

export const backEndMock = (
  state = {
    logged: false,
    contestStarted: false,
    userName: "",
  },
  action
) => {
  switch (action.type) {
    case SET_LOGIN_PASSWORD:
      return {
        ...state,
        logged: action.status,
      };
    case START_CONTEST:
      return { ...state, contestStarted: action.flag };
    case SET_USER_NAME:
      return { ...state, userName: action.userName };
    default:
      return state;
  }
};
