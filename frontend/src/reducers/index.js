import { combineReducers } from "redux";
import { sendCounter } from "./sendCounter";
import { codeStatus } from "./codeStatus";
import { enteredCode } from "./enteredCode";
import { pageToGive } from "./pageToGive";
import { pageId } from "./pageId";
import { backEndMock } from "./backEndMock";
import { chatControl } from "./chatControl";
import { tests } from "./tests";

const rootReducer = combineReducers({
  sendCounter,
  codeStatus,
  enteredCode,
  pageToGive,
  pageId,
  backEndMock,
  chatControl,
  tests,
});

export default rootReducer;
