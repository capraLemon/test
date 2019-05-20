import { sendingCode, receivingCode } from "../reducers/codeStatus";
import { decreaseCounter } from "../reducers/sendCounter";

const sendCodeReceiveStatus = async (dispatch, getState) => {
  try {
    const pageId = getState().pageId;
    dispatch(sendingCode());
    const response = await fetch(`/api/code/send/${pageId}`, {
      method: "POST",
      headers: {
        Accept: "application/json", // eslint-disable-line prettier/prettier
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        solutionText: getState().enteredCode.code[pageId],
        progLanguage: getState().enteredCode.language[pageId],
      }),
    });
    const status = await response.json();
    dispatch(receivingCode(status));
    dispatch(decreaseCounter());
  } catch (err) {
    dispatch(receivingCode(false));
  }
};

export default sendCodeReceiveStatus;
