import sendCodeReceiveStatus from "./sendCodeReceiveStatus";

const mapSignalToFunction = {
  sendCodeReceiveStatus,
};

const signalsMiddleware = store => next => action => {
  if (mapSignalToFunction[action.signal]) {
    mapSignalToFunction[action.signal](store.dispatch, store.getState);
  } else {
    return next(action);
  }
};

export default signalsMiddleware;
