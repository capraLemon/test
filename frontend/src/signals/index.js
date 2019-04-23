export const SIGNAL = "SIGNAL";

export const sendCode = () => ({
  type: SIGNAL,
  signal: "sendCodeReceiveStatus",
});
