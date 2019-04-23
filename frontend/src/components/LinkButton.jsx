import React from "react";
import PropTypes from "prop-types";
import { withRouter } from "react-router";

const LinkButton = ({ history, to, text }) => {
  const onClick = () => {
    history.push(to);
  };

  return <button onClick={onClick}>{text}</button>;
};

LinkButton.propTypes = {
  history: PropTypes.object.isRequired,
  to: PropTypes.string.isRequired,
  text: PropTypes.string.isRequired,
};

export default withRouter(LinkButton);
