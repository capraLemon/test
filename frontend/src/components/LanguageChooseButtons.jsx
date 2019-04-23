import React, { Component } from "react";
import PropTypes from "prop-types";
import Button from "bloko/blocks/button/index.jsx";

const mapIdToButtonName = {
  PYTHON: "Python",
  JAVA: "Java",
  JAVA_SCRIPT: "JavaScript",
};

class LanguageChooseButtons extends Component {
  static propTypes = {
    languageId: PropTypes.string.isRequired,
    onClick: PropTypes.func.isRequired,
  };

  state = {
    currentId: this.props.languageId,
  };

  setCurrentId = id => {
    this.setState(state => {
      if (state.currentId === id) {
        return null;
      }
      return {
        currentId: id,
      };
    });
  };

  render() {
    return (
      <div className="code-lang-choose-wrapper">
        {Object.keys(mapIdToButtonName).map(id => (
          <Button
            key={id}
            onClick={() => {
              this.setCurrentId(id);
              this.props.onClick(id);
            }}
            kind={id === this.state.currentId ? "primary" : "primary-minor"}
            stretched
          >
            {mapIdToButtonName[id]}
          </Button>
        ))}
      </div>
    );
  }
}

export default LanguageChooseButtons;
