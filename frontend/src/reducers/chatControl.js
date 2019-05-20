export const SET_MESSAGE = "SET_MESSAGE";
export const SENDING_MESSAGE = "SENDING_MESSAGE";
export const SET_ADDRESS = "SET_ADDRESS";
export const FINISH_SENDING = "FINISH_SENDING";

export const setMessage = (enteredMessage, topicId) => ({
  type: SET_MESSAGE,
  enteredMessage,
  topicId,
});

export const sendingMessage = () => ({
  type: SENDING_MESSAGE,
});

export const setAddress = (address, id) => ({
  type: SET_ADDRESS,
  address,
  id,
});

export const finishSending = () => ({
  type: FINISH_SENDING,
});

export const chatControl = (
  state = { message: {}, isFetching: false, byWhat: "byTask/", currentId: 0 },
  action
) => {
  switch (action.type) {
    case SET_MESSAGE:
      return {
        ...state,
        message: { ...state.message, [action.topicId]: action.enteredMessage },
      };
    case SENDING_MESSAGE:
      return { ...state, isFetching: true };
    case SET_ADDRESS:
      return { ...state, byWhat: action.address, currentId: action.id };
    case FINISH_SENDING:
      return { ...state, isFetching: false };
    default:
      return state;
  }
};
