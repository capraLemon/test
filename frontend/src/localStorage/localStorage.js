export const loadState = () => {
  try {
    const serializedState = localStorage.getItem("checkUpState");
    if (serializedState === null) {
      return undefined;
    }
    return JSON.parse(serializedState);
  } catch (err) {
    return undefined;
  }
};

export const saveState = state => {
  try {
    const serializedState = JSON.stringify(state);
    localStorage.setItem("checkUpState", serializedState);
  } catch (err) {
    //can log some info here if needed
  }
};
