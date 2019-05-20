import React from "react";
import PropTypes from "prop-types";
import Icon from "bloko/blocks/icon/Icon.jsx";
import HistoryInfoButton from "../components/HistoryInfoButton";
import { mapStatusToText } from "../constants";

const HistoryOfSending = ({ history }) => {
  return (
    <React.Fragment>
      <table className="history-of-sending">
        <tbody>
          <tr className="history-of-sending__tr">
            <td className="history-of-sending__td_time">Время отправки</td>
            <td className="history-of-sending__td">Статус</td>
            <td />
          </tr>
          {history.map(solution => {
            return (
              <tr className="history-of-sending__tr" key={solution.solutionId}>
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
  history: PropTypes.array,
};

export default HistoryOfSending;
