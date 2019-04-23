import React from "react";
import ReactDOM from "react-dom";
import { Provider } from "react-redux";
import throttle from "lodash/throttle";
import Root from "./pages";
import "./styles/app.less";
import configureStore from "./configureStore";
import { loadState, saveState } from "./localStorage/localStorage";

const stateFromLocalStorage = loadState();
const store = configureStore(stateFromLocalStorage);
store.subscribe(
  throttle(() => {
    saveState(store.getState());
  }, 1000)
);

ReactDOM.render(
  <Provider store={store}>
    <Root />
  </Provider>,
  document.getElementById("app")
);
