export const GIVE_PAGE = "GIVE_PAGE";

export const givePage = page => ({
  type: GIVE_PAGE,
  page,
});

// const mainPage = "AnonBefore";
// const mainPage = "UserBefore";
const mainPage = "AnonAfter";
// const mainPage = "UserAfter";

export const pageToGive = (state = { mainPage }, action) => {
  switch (action.type) {
    case GIVE_PAGE:
      return { ...state, mainPage: action.page };
    default:
      return state;
  }
};
