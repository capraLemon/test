export const SIGNAL = "SIGNAL";

export const sendCode = () => ({
  type: SIGNAL,
  signal: "sendCodeReceiveStatus",
});

export const sendMessage = () => ({
  type: SIGNAL,
  signal: "sendMessageClearInput",
});

export const getTests = () => ({
  type: SIGNAL,
  signal: "getTests",
});

export const addTest = () => ({
  type: SIGNAL,
  signal: "addTest",
});

export const startTest = () => ({
  type: SIGNAL,
  signal: "startTest",
});

export const changeTest = () => ({
  type: SIGNAL,
  signal: "changeTest",
});
