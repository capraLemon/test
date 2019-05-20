export const mapIdToButtonName = {
  PYTHON: "Python",
  JAVA: "Java",
  JAVA_SCRIPT: "JavaScript",
};

export const mapStatusToText = {
  IN_QUEUE: "В очереди на проверку",
  PROCESSING: "В процессе проверки",
  ACCEPTED: "Решение принято",
  WRONG_ANSWER: "Неправильный ответ",
  TIME_LIMIT_EXCEEDED: "Превышение лимита времени выполнения",
  COMPILATION_ERROR: "Ошибка компиляции",
  RUNTIME_ERROR_SIGSEGV: "Ошибка во время выполнения (SIGSEGV)",
  RUNTIME_ERROR_SIGXFSZ: "Ошибка во время выполнения (SIGXFSZ)",
  RUNTIME_ERROR_SIGFPE: "Ошибка во время выполнения (SIGFPE)",
  RUNTIME_ERROR_SIGABRT: "Ошибка во время выполнения (SIGABRT)",
  RUNTIME_ERROR_NZEC: "Ошибка во время выполнения (NZEC)",
  RUNTIME_ERROR_OTHER: "Ошибка во время выполнения (Other)",
  INTERNAL_ERROR: "Внутренняя ошибка",
};

export const mapStatusToColor = {
  pass: "test-wrapper_pass",
  neutral: "test-wrapper_neutral",
  fail: "test-wrapper_fail",
};

export const mapResponseToColor = {
  IN_QUEUE: "neutral",
  PROCESSING: "neutral",
  ACCEPTED: "pass",
  WRONG_ANSWER: "fail",
  TIME_LIMIT_EXCEEDED: "fail",
  COMPILATION_ERROR: "fail",
  RUNTIME_ERROR_SIGSEGV: "fail",
  RUNTIME_ERROR_SIGXFSZ: "fail",
  RUNTIME_ERROR_SIGFPE: "fail",
  RUNTIME_ERROR_SIGABRT: "fail",
  RUNTIME_ERROR_NZEC: "fail",
  RUNTIME_ERROR_OTHER: "fail",
  INTERNAL_ERROR: "fail",
};
