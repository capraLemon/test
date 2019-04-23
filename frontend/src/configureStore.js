import { createStore, applyMiddleware } from "redux";
import thunkMiddleware from "redux-thunk";
import rootReducer from "./reducers";
import signalsMiddleware from "./signalsMiddleware";

let middleware;
if (process.env.NODE_ENV === "development") {
  const logger = store => next => action => {
    let result = next(action);
    console.log("state", store.getState()); // eslint-disable-line no-console
    return result;
  };
  middleware = applyMiddleware(thunkMiddleware, logger, signalsMiddleware);
} else {
  middleware = applyMiddleware(thunkMiddleware, signalsMiddleware);
}

export default function configureStore(preloadedState) {
  return createStore(rootReducer, preloadedState, middleware);
}
