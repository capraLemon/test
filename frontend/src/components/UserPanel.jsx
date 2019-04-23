import React from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

const UserPanel = ({ userName, visibility }) => {
  if (!visibility) {
    return null;
  }
  return (
    <div className="header-user-info">
      {userName}
      {/* <Link className="header-user-info__link" to="/AnonAfter_or_AnonBefore"> */}
      <Link className="header-user-info__link" to="/tasks">
        Выход
      </Link>
    </div>
  );
};

UserPanel.propTypes = {
  userName: PropTypes.string,
  visibility: PropTypes.bool.isRequired,
};

export default UserPanel;
