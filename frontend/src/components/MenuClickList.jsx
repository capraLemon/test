import React from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

const MenuClickList = ({ menuVisibility, texts }) => {
  if (!menuVisibility) {
    return null;
  }
  return (
    <ul className="header-button-drop-menu-list">
      {texts.map((text, key) => (
        <li className="header-button-drop-menu-list__task" key={key}>
          <Link
            // to={text.title}
            // to={{ pathname: `tasks/${text.title}` }}
            to={`tasks${text.taskId}`}
            className="header-button-drop-menu-list__task-link"
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
};

export default MenuClickList;
