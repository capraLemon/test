export const DECREASE_COUNTER = "DECREASE_COUNTER";

export const decreaseCounter = () => ({
  type: DECREASE_COUNTER,
});

const initialCounter = 100;

export const sendCounter = (state = initialCounter, action) => {
  switch (action.type) {
    case DECREASE_COUNTER:
      return state - 1;
    default:
      return state;
  }
};
