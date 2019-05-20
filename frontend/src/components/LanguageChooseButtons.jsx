import React from "react";
import PropTypes from "prop-types";
import Button from "bloko/blocks/button/index.jsx";
import { mapIdToButtonName } from "../constants";

const LanguageChooseButtons = ({ languageId, onClick }) => {
  return (
    <div className="code-lang-choose-wrapper">
      {Object.keys(mapIdToButtonName).map(id => (
        <Button
          key={id}
          onClick={() => {
            onClick(id);
          }}
          kind={id === languageId ? "primary" : "primary-minor"}
          stretched
        >
          {mapIdToButtonName[id]}
        </Button>
      ))}
    </div>
  );
};

LanguageChooseButtons.propTypes = {
  languageId: PropTypes.string,
  onClick: PropTypes.func.isRequired,
};

export default LanguageChooseButtons;
