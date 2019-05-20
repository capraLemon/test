import React from "react";
import PropTypes from "prop-types";
import classNames from "classnames";

const HeaderButton = ({
  text,
  click,
  visibility,
  signVisibility,
  messageNumber,
  focus,
}) => {
  if (!visibility) {
    return null;
  }
  return (
    <button
      className={classNames("header-button", {
        "header-button_focused": focus,
      })}
      onClick={click}
    >
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
  messageNumber: PropTypes.number,
  focus: PropTypes.bool,
};

export default HeaderButton;
