export const SET_PAGE_ID = "SET_PAGE_ID";

export const setPageId = pageId => ({
  type: SET_PAGE_ID,
  pageId,
});

export const pageId = (state = "", action) => {
  switch (action.type) {
    case SET_PAGE_ID:
      return action.pageId;
    default:
      return state;
  }
};
