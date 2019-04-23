import React from "react";
import PropTypes from "prop-types";
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
  tasksList,
}) => {
  const chatClick = () => {
    history.push("messenger");
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
                signVisibility
                messageNumber="1"
              />
            </div>
            <UserPanel userName="Маргарита" visibility={userInfoVisibility} />
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
  tasksList: PropTypes.array,
};

export default withRouter(PageHeader);
