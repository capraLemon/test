import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { withRouter } from "react-router";
import Column, { ColumnsWrapper } from "bloko/blocks/column/index.jsx";
import ButtonMenu from "../components/ButtonMenu";
import HeaderButton from "../components/HeaderButton";
import UserPanel from "../components/UserPanel";
import Logo from "../components/Logo";

const PageHeader = ({
  history,
  tasksVisibility,
  chatVisibility,
  userInfoVisibility,
  userName,
}) => {
  const [messageNumber, setMessageNumber] = useState(0);
  const [tasksList, setTasksList] = useState([]);

  useEffect(() => {
    let handleTaskList = taskList => {
      setTasksList(taskList);
    };
    const initialFetchTaskList = async () => {
      const taskList = await fetch("/api/task/list");
      const jsonedTaskList = await taskList.json();
      handleTaskList(jsonedTaskList);
    };
    tasksVisibility && initialFetchTaskList();
    return () => {
      handleTaskList = () => {};
    };
  }, [tasksVisibility]);

  useEffect(() => {
    if (chatVisibility) {
      let handleMessageNumber = quantity => {
        setMessageNumber(quantity);
      };

      (async () => {
        const data = await fetch("/api/message/unread");
        const jsonedData = await data.json();
        handleMessageNumber(jsonedData.quantity);
      })();

      const pollingInterval = setInterval(async () => {
        const data = await fetch("/api/message/unread");
        const jsonedData = await data.json();
        handleMessageNumber(jsonedData.quantity);
      }, 1000);
      return () => {
        clearInterval(pollingInterval);
        handleMessageNumber = () => {};
      };
    }
  }, [chatVisibility]);

  const chatClick = () => {
    history.push("/messenger");
  };

  return (
    <div className="header">
      <ColumnsWrapper>
        <Column m="12" l="16">
          <div className="header-content">
            <div className="header-logo-and-buttons">
              <Logo />
              <ButtonMenu tasks={tasksList} visibility={tasksVisibility} />
              <HeaderButton
                text="Чат"
                click={chatClick}
                visibility={chatVisibility}
                signVisibility={messageNumber > 0}
                messageNumber={messageNumber}
              />
            </div>
            <UserPanel userName={userName} visibility={userInfoVisibility} />
          </div>
        </Column>
      </ColumnsWrapper>
    </div>
  );
};

PageHeader.propTypes = {
  history: PropTypes.object.isRequired,
  tasksVisibility: PropTypes.bool.isRequired,
  chatVisibility: PropTypes.bool.isRequired,
  userInfoVisibility: PropTypes.bool.isRequired,
  userName: PropTypes.string,
};

const mapStateToProps = ({ backEndMock: { userName } }) => {
  return { userName };
};

export default withRouter(connect(mapStateToProps)(PageHeader));
