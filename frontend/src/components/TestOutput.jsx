import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import Textarea from "bloko/blocks/textarea/index.jsx";

const TestOutput = ({
  type,
  title,
  placeholder,
  editMode = false,
  testText,
  inputEditCallback,
  rows = 6,
}) => {
  const [temporaryText, setTemporaryText] = useState(testText);
  useEffect(() => {
    setTemporaryText(testText);
  }, [editMode, testText]);
  const onChange = event => {
    setTemporaryText(event.target.value);
    inputEditCallback(type, event.target.value);
  };

  return (
    <div className="test-output">
      <div className="test-output__title">{title}</div>
      <Textarea
        rows={rows}
        placeholder={placeholder}
        readOnly={!editMode}
        value={editMode ? temporaryText : testText}
        onChange={onChange}
      />
    </div>
  );
};

TestOutput.propTypes = {
  type: PropTypes.string,
  title: PropTypes.string,
  placeholder: PropTypes.string,
  editMode: PropTypes.bool,
  testText: PropTypes.string,
  inputEditCallback: PropTypes.func,
  rows: PropTypes.number,
};

export default TestOutput;
