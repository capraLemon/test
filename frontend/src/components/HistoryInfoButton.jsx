import React, { useState, useCallback, useEffect, useRef } from "react";
import PropTypes from "prop-types";
import Icon from "bloko/blocks/icon/Icon.jsx";
import Modal from "bloko/blocks/modal/index.jsx";
// import Modal from "./Modal";
import Column, { ColumnsWrapper } from "bloko/blocks/column/index.jsx";
import Gap from "bloko/blocks/gap/index.jsx";
import ContentWrapper from "./ContentWrapper";
import Textarea from "bloko/blocks/textarea/index.jsx";

const mapStatusToText = {
  //vnimanie - uzhe bilo v HistoryOfSendings
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

const mapIdToButtonName = {
  //vnimanie - uzhe bilo v HistoryOfSendings
  PYTHON: "Python",
  JAVA: "Java",
  JAVA_SCRIPT: "JavaScript",
};

const HistoryInfoButton = ({ id }) => {
  const [show, toggleShow] = useState(false);
  const [userData, setUserData] = useState({});

  const mounted = useRef();
  const [crunch, setCrunch] = useState(0);

  const openSendedCode = useCallback(() => {
    console.log("opened"); // eslint-disable-line no-console
    toggleShow(true);
    setCrunch(crunch + 1);
  }, [crunch]);

  const closeSendedCode = useCallback(() => {
    toggleShow(false);
  }, []);

  useEffect(() => {
    const initialFetchHistory = async () => {
      const data = await fetch(
        `http://checkup.space:9999/api/code/history/details/${id}`
      );
      const jsonedData = await data.json();
      setUserData(jsonedData);
      console.log("fetched", jsonedData); // eslint-disable-line no-console
    };
    if (!mounted.current) {
      mounted.current = true;
    } else {
      initialFetchHistory();
    }
  }, [id, crunch]);

  return (
    <React.Fragment>
      <button onClick={openSendedCode}>
        <Icon view={Icon.views.info} initial={Icon.kinds.impact} scale="24" />
      </button>
      <Modal visible={show} onClose={closeSendedCode}>
        <ColumnsWrapper>
          <Column m="12" l="16">
            <ContentWrapper title={"Информация об отправке"}>
              <table className="history-info__restriction">
                <tbody>
                  <tr>
                    <td>Время отправки:</td>
                    <td>
                      {new Date(userData.creationDate).toLocaleString("ru-RU", {
                        timeZone: "Europe/Moscow",
                        day: "numeric",
                        month: "long",
                        hour: "2-digit",
                        minute: "2-digit",
                      })}
                    </td>
                  </tr>
                  <tr>
                    <td>Язык программирования:</td>
                    <td>{mapIdToButtonName[userData.progLanguage]}</td>
                  </tr>
                  <tr>
                    <td>Статус:</td>
                    <td>{mapStatusToText[userData.statusId]}</td>
                  </tr>
                </tbody>
              </table>
              <Gap bottom />
              <Textarea value={userData.solutionText} rows={30} readOnly />
            </ContentWrapper>
          </Column>
        </ColumnsWrapper>
      </Modal>
    </React.Fragment>
  );
};

HistoryInfoButton.propTypes = {
  id: PropTypes.number.isRequired,
};

export default HistoryInfoButton;
