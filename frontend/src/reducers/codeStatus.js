export const SENDING_CODE = "SENDING_CODE";
export const RECEIVING_CODE = "RECEIVING_CODE";

export const sendingCode = () => ({
  type: SENDING_CODE,
});

export const receivingCode = status => ({
  type: RECEIVING_CODE,
  status,
});

export const codeStatus = (
  state = { isFetching: false, status: false },
  action
) => {
  switch (action.type) {
    case SENDING_CODE:
      return { ...state, isFetching: true, status: false };
    case RECEIVING_CODE:
      return { ...state, isFetching: false, status: action.status };
    default:
      return state;
  }
};
