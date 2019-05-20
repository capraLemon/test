import React from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import { setLoggedUser, setUserName } from "../reducers/backEndMock";

const UserPanel = ({ userName, visibility, setLoggedUser, setUserName }) => {
  const onClick = async () => {
    const response = await fetch("/api/logout", {
      method: "POST",
      headers: {
        Accept: "application/json", // eslint-disable-line prettier/prettier
        "Content-Type": "application/json",
      },
    });
    if (response.ok) {
      setLoggedUser(false);
      setUserName("");
      localStorage.clear();
    }
  };

  if (!visibility) {
    return null;
  }
  return (
    <div className="header-user-info">
      {userName}
      <Link className="header-user-info__link" to="/" onClick={onClick}>
        Выход
      </Link>
    </div>
  );
};

UserPanel.propTypes = {
  userName: PropTypes.string,
  visibility: PropTypes.bool.isRequired,
  setLoggedUser: PropTypes.func,
  setUserName: PropTypes.func,
};

const mapDispatchToProps = {
  setLoggedUser,
  setUserName,
};

export default connect(
  null,
  mapDispatchToProps
)(UserPanel);
