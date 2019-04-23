import React from "react";
import PropTypes from "prop-types";
import Icon from "bloko/blocks/icon/Icon.jsx";
import HistoryInfoButton from "../components/HistoryInfoButton";

const mapStatusToText = {
  IN_QUEUE: "В очереди на проверку",
  PROCESSING: "! В процессе проверки",
  ACCEPTED: "! Решение принято",
  WRONG_ANSWER: "Неправильный ответ",
  TIME_LIMIT_EXCEEDED: "Превышение лимита времени выполнения",
  COMPILATION_ERROR: "Ошибка компиляции",
  RUNTIME_ERROR_SIGSEGV: "! Ошибка во время выполнения (SIGSEGV)",
  RUNTIME_ERROR_SIGXFSZ: "! Ошибка во время выполнения (SIGXFSZ)",
  RUNTIME_ERROR_SIGFPE: "! Ошибка во время выполнения (SIGFPE)",
  RUNTIME_ERROR_SIGABRT: "! Ошибка во время выполнения (SIGABRT)",
  RUNTIME_ERROR_NZEC: "! Ошибка во время выполнения (NZEC)",
  RUNTIME_ERROR_OTHER: "! Ошибка во время выполнения (Other)",
  INTERNAL_ERROR: "! Внутренняя ошибка",
};

const HistoryOfSending = ({ solutions }) => {
  return (
    <React.Fragment>
      <table className="history-of-sending">
        <tbody>
          <tr className="history-of-sending__tr">
            <td className="history-of-sending__td_time">Время отправки</td>
            <td className="history-of-sending__td">Статус</td>
            <td />
          </tr>
          {solutions
            .slice()
            .reverse()
            .map(solution => {
              return (
                <tr
                  className="history-of-sending__tr"
                  key={solution.solutionId}
                >
                  <td>
                    {new Date(solution.creationTime).toLocaleString("ru-RU", {
                      timeZone: "Europe/Moscow",
                      day: "numeric",
                      month: "long",
                      hour: "2-digit",
                      minute: "2-digit",
                    })}
                  </td>
                  <td className="history-of-sending__td">
                    {mapStatusToText[solution.statusId]}
                  </td>
                  <td className="history-of-sending__td">
                    {(!solution.isChecking && (
                      <HistoryInfoButton id={solution.solutionId} />
                    )) || (
                      <Icon
                        view={Icon.views.loading}
                        initial={Icon.kinds.primary}
                        scale="24"
                      />
                    )}
                  </td>
                </tr>
              );
            })}
        </tbody>
      </table>
    </React.Fragment>
  );
};

HistoryOfSending.propTypes = {
  solutions: PropTypes.array.isRequired,
};

export default HistoryOfSending;
