import React, { useState, useCallback } from "react";
import PropTypes from "prop-types";
import onClickOutside from "react-onclickoutside";
import HeaderButton from "./HeaderButton";
import MenuClickList from "./MenuClickList";

const ButtonMenu = ({ visibility, tasks }) => {
  const [menuVisibility, setMenuVisibility] = useState(false);

  const handleClick = useCallback(() => setMenuVisibility(true), []);
  const handleListClick = useCallback(() => setMenuVisibility(false), []);
  ButtonMenu.handleClickOutside = useCallback(
    () => setMenuVisibility(false),
    []
  );

  return (
    visibility && (
      <div>
        <HeaderButton
          text="Задачи"
          click={handleClick}
          visibility
          signVisibility={false}
          focus={menuVisibility}
        />
        <MenuClickList
          texts={tasks}
          menuVisibility={menuVisibility}
          handleListClick={handleListClick}
        />
      </div>
    )
  );
};

const clickOutsideConfig = {
  handleClickOutside: () => ButtonMenu.handleClickOutside,
};

ButtonMenu.propTypes = {
  visibility: PropTypes.bool.isRequired,
  tasks: PropTypes.array,
};

export default onClickOutside(ButtonMenu, clickOutsideConfig);
