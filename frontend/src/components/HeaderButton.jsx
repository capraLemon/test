import React from "react";
import PropTypes from "prop-types";

const HeaderButton = ({
  text,
  click,
  visibility,
  signVisibility,
  messageNumber,
}) => {
  if (!visibility) {
    return null;
  }
  return (
    <button className="header-button" onClick={click}>
      <span className="header-button__text">{text}</span>
      {signVisibility && (
        <span className="header-button__sign">{messageNumber}</span>
      )}
    </button>
  );
};

HeaderButton.propTypes = {
  text: PropTypes.string,
  click: PropTypes.func,
  visibility: PropTypes.bool.isRequired,
  signVisibility: PropTypes.bool.isRequired,
  messageNumber: PropTypes.string,
};

export default HeaderButton;
