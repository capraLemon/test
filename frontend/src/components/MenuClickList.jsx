import React from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import { withRouter } from "react-router";

const MenuClickList = ({
  menuVisibility,
  texts,
  handleListClick,
  location,
}) => {
  const handleClick = () => {
    handleListClick();
  };

  if (!menuVisibility) {
    return null;
  }
  return (
    <ul className="header-button-drop-menu-list">
      {texts.map((text, key) => (
        <li className="header-button-drop-menu-list__task" key={key}>
          <Link
            to={
              location.pathname === "/messenger"
                ? `/tasks/${text.taskId}`
                : `${text.taskId}`
            }
            className="header-button-drop-menu-list__task-link"
            onClick={handleClick}
          >
            {text.title}
          </Link>
        </li>
      ))}
    </ul>
  );
};

MenuClickList.propTypes = {
  menuVisibility: PropTypes.bool.isRequired,
  texts: PropTypes.array,
  location: PropTypes.object,
  handleListClick: PropTypes.func,
};

export default withRouter(MenuClickList);
