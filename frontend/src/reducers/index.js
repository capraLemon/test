import { combineReducers } from "redux";
import { sendCounter } from "./sendCounter";
import { codeStatus } from "./codeStatus";
import { enteredCode } from "./enteredCode";
import { pageToGive } from "./pageToGive";

const rootReducer = combineReducers({
  sendCounter,
  codeStatus,
  enteredCode,
  pageToGive,
});

export default rootReducer;
