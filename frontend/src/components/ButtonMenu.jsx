import React, { Component } from "react";
import PropTypes from "prop-types";
import onClickOutside from "react-onclickoutside";
import HeaderButton from "./HeaderButton";
import MenuClickList from "./MenuClickList";

class ButtonMenu extends Component {
  state = {
    menuVisibility: false,
  };

  handleClick = () => {
    this.setState({ menuVisibility: true });
  };

  handleClickOutside = () => {
    this.setState({ menuVisibility: false });
  };

  render() {
    if (!this.props.visibility) {
      return null;
    }
    return (
      <div>
        <HeaderButton
          text="Задачи"
          click={this.handleClick}
          visibility
          signVisibility={false}
        />
        <MenuClickList
          // texts={[
          //   "Задача 1. Минимальное пропущенное число",
          //   "Задача 2. Проклятый остров",
          //   "Задача 3. Разрезание пирога",
          // ]}
          texts={this.props.tasks}
          menuVisibility={this.state.menuVisibility}
        />
      </div>
    );
  }
}

ButtonMenu.propTypes = {
  visibility: PropTypes.bool.isRequired,
  tasks: PropTypes.array,
};

export default onClickOutside(ButtonMenu);
