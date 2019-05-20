import React, { useState } from "react";
import PropTypes from "prop-types";
import classNames from "classnames";
import LinkSwitch from "bloko/blocks/linkSwitch/index.jsx";
import Icon from "bloko/blocks/icon/Icon.jsx";
import IconLink from "bloko/blocks/icon/IconLink.jsx";
import { mapStatusToColor } from "../constants";

const Test = ({
  text,
  status = "neutral",
  id,
  activeId,
  editCallback = () => {},
  activeIdCallback = () => {},
  canEdit = false,
  editMode = false,
  editControlCallback = () => {},
  setUserTestTextCallback = () => {},
  testStartCallback = () => {},
  dataSendInProcess = false,
}) => {
  const onTextClick = () => {
    if (!editMode) {
      activeIdCallback(id);
    }
  };

  const onPlayClick = () => {
    if (editMode) return;
    if (id !== activeId) {
      activeIdCallback(id);
    }
    testStartCallback();
  };

  const [localEditMode, setLocalEditMode] = useState(false);

  const edit = () => {
    if (editMode) return;
    if (id !== activeId) {
      activeIdCallback(id);
    }
    setLocalEditMode(true);
    editCallback(true);
  };

  const confirm = () => {
    setLocalEditMode(false);
    setFinalText(changedText);
    editCallback(false);
    editControlCallback(true);
  };

  const cancel = () => {
    setLocalEditMode(false);
    setChangedText(finalText);
    editCallback(false);
  };

  const [changedText, setChangedText] = useState(text);
  const [finalText, setFinalText] = useState(text);

  const inputChange = event => {
    setUserTestTextCallback(event.target.textContent);
    setChangedText(event.target.textContent);
  };

  return (
    <div
      className={classNames("test-wrapper", {
        [mapStatusToColor[status]]: status,
      })}
      key={id} //????id already created while map
    >
      <div
        className={classNames("test", {
          test_disabled: dataSendInProcess, // eslint-disable-line prettier/prettier
        })}
      >
        <button className="test__start-sign" onClick={onPlayClick} />
        <div
          className={classNames("test__text", {
            test__text_active: activeId === id, // eslint-disable-line prettier/prettier
          })}
        >
          {!localEditMode && (
            <LinkSwitch kind="inherited" onClick={onTextClick}>
              {finalText}
            </LinkSwitch>
          )}
          {localEditMode && (
            <div
              className="test__text_edit"
              contentEditable={true}
              suppressContentEditableWarning={true} //!!!!!!!!!!!!!!
              onBlur={inputChange}
            >
              {changedText}
            </div>
          )}
        </div>
        <div
          className={classNames(
            { "test__edit-sign_hidden": canEdit === false },
            { "test__edit-sign": canEdit === true },
            {
              "test__edit-sign_hidden": localEditMode || dataSendInProcess,
            }
          )}
        >
          <IconLink Element="button" onClick={edit}>
            <Icon view={Icon.views.edit} />
          </IconLink>
        </div>
        <div
          className={classNames(
            { "test__edit-sign_hidden": dataSendInProcess === false },
            { "test__loading-sign": dataSendInProcess === true }
          )}
        >
          <Icon
            view={Icon.views.loading}
            initial={Icon.kinds.primary}
            scale="24"
          />
        </div>
        <div
          className={classNames("test__cancel-confirm_hidden", {
            "test__cancel-confirm": localEditMode,
          })}
        >
          <IconLink Element="button" onClick={cancel}>
            <Icon view={Icon.views.cancel} />
          </IconLink>
          <IconLink Element="button" onClick={confirm}>
            <Icon view={Icon.views.done} />
          </IconLink>
        </div>
      </div>
    </div>
  );
};

Test.propTypes = {
  text: PropTypes.string,
  status: PropTypes.string,
  id: PropTypes.number,
  // id: PropTypes.string.isRequired,
  activeId: PropTypes.number,
  editCallback: PropTypes.func,
  activeIdCallback: PropTypes.func,
  editControlCallback: PropTypes.func,
  setUserTestTextCallback: PropTypes.func,
  testStartCallback: PropTypes.func,
  canEdit: PropTypes.bool,
  editMode: PropTypes.bool,
  dataSendInProcess: PropTypes.bool,
};

export default Test;
