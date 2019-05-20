import {
  sendingMessage,
  setMessage,
  finishSending,
} from "../reducers/chatControl";

const sendMessageClearInput = async (dispatch, getState) => {
  try {
    dispatch(sendingMessage());
    const byWhat = getState().chatControl.byWhat;
    const id = getState().chatControl.currentId;
    const response = await fetch(`/api/message/${byWhat}${id}`, {
      method: "POST",
      headers: {
        Accept: "application/json", // eslint-disable-line prettier/prettier
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        messageText: getState().chatControl.message[
          getState().chatControl.currentId
        ],
      }),
    });
    if (response.ok) {
      dispatch(setMessage("", getState().chatControl.currentId));
    }
    dispatch(finishSending());
  } catch (err) {
    dispatch(finishSending());
  }
};

export default sendMessageClearInput;
