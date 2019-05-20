import React, { useState, useCallback, useEffect, useRef } from "react";
import PropTypes from "prop-types";
import Icon from "bloko/blocks/icon/Icon.jsx";
import Modal from "bloko/blocks/modal/index.jsx";
import Column, { ColumnsWrapper } from "bloko/blocks/column/index.jsx";
import Gap from "bloko/blocks/gap/index.jsx";
import ContentWrapper from "./ContentWrapper";
import Textarea from "bloko/blocks/textarea/index.jsx";
import { mapIdToButtonName, mapStatusToText } from "../constants";

const HistoryInfoButton = ({ id }) => {
  const [show, toggleShow] = useState(false);
  const [userData, setUserData] = useState({});
  const mounted = useRef();
  const [fetchSignal, setFetchSignal] = useState(true);

  const openSendedCode = useCallback(() => {
    toggleShow(true);
    setFetchSignal(!fetchSignal);
  }, [fetchSignal]);

  const closeSendedCode = useCallback(() => {
    toggleShow(false);
  }, []);

  useEffect(() => {
    let handleDetails = details => {
      setUserData(details);
    };
    const fetchDetails = async () => {
      const details = await fetch(`/api/code/history/details/${id}`);
      const jsonedDetails = await details.json();
      handleDetails(jsonedDetails);
    };
    if (!mounted.current) {
      mounted.current = true;
    } else {
      fetchDetails();
    }
    return () => {
      handleDetails = () => {};
    };
  }, [id, fetchSignal]);

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
              <Textarea value={userData.solutionText} rows={18} readOnly />
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
