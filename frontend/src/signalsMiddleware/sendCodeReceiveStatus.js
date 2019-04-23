import { sendingCode, receivingCode } from "../reducers/codeStatus";
import { decreaseCounter } from "../reducers/sendCounter";
// import { backendURL } from "../constants";

const sendCodeReceiveStatus = async (dispatch, getState) => {
  try {
    dispatch(sendingCode());
    const response = await fetch(
      "http://checkup.space:9999/api/code/send/1000",
      {
        method: "POST",
        headers: {
          Accept: "application/json", // eslint-disable-line prettier/prettier
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          solutionText: getState().enteredCode.code,
          progLanguage: getState().enteredCode.language,
        }),
      }
    );
    // const response = await fetch("api/todo/list");
    const status = await response.json();
    dispatch(receivingCode(status));
    dispatch(decreaseCounter());
  } catch (err) {
    const status = false;
    dispatch(receivingCode(status));
  }
};

export default sendCodeReceiveStatus;
