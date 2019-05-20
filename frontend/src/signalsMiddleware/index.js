import sendCodeReceiveStatus from "./sendCodeReceiveStatus";
import sendMessageClearInput from "./sendMessageClearInput";
import getTests from "./getTests";
import addTest from "./addTest";
import startTest from "./startTest";
import changeTest from "./changeTest";

const mapSignalToFunction = {
  sendCodeReceiveStatus,
  sendMessageClearInput,
  getTests,
  addTest,
  startTest,
  changeTest,
};

const signalsMiddleware = store => next => action => {
  if (mapSignalToFunction[action.signal]) {
    mapSignalToFunction[action.signal](store.dispatch, store.getState);
  } else {
    return next(action);
  }
};

export default signalsMiddleware;
