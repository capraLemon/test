import React, { useState } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import Button from "bloko/blocks/button/index.jsx";
import Icon from "bloko/blocks/icon/Icon.jsx";
import {
  setEditMode,
  setActiveId,
  setTestInputOutput,
  setTestText,
} from "../reducers/tests";
import Test from "./Test";
import TestOutput from "./TestOutput";
import { addTest, startTest, changeTest } from "../signals";
import Gap from "bloko/blocks/gap/index.jsx";

const Testing = ({
  editMode,
  activeId,
  tests,
  pageId,
  setEditMode,
  setActiveId,
  setTestInputOutput,
  setTestText,
  addTest,
  startTest,
  changeTest,
}) => {
  const [temporaryText, setTemporaryText] = useState({});
  const [temporaryInput, setTemporaryInput] = useState({});

  const setEditStatus = status => {
    setEditMode(status);
  };
  const giveActiveId = id => {
    setActiveId(pageId, id);
  };
  const setUserInput = (inputKind, inputText) => {
    setTemporaryInput({ ...temporaryInput, [inputKind]: inputText });
  };

  const setUserTestText = text => {
    setTemporaryText({ text });
  };

  const editControl = confirmed => {
    if (confirmed) {
      if (temporaryInput.hasOwnProperty("input")) {
        setTestInputOutput(
          pageId,
          activeId[pageId],
          "input",
          temporaryInput.input
        );
      }
      if (temporaryInput.hasOwnProperty("expectedOutput")) {
        setTestInputOutput(
          pageId,
          activeId[pageId],
          "expectedOutput",
          temporaryInput.expectedOutput
        );
      }
      if (temporaryText.hasOwnProperty("text")) {
        setTestText(pageId, activeId[pageId], temporaryText.text);
      }
      if (
        Object.entries(temporaryText).length !== 0 ||
        Object.entries(temporaryInput).length !== 0
      ) {
        changeTest();
      }
    }
    setTemporaryInput({});
    setTemporaryText({});
  };

  const testStarted = () => {
    startTest();
  };

  return (
    <div className="testing-wrapper">
      <div className="testing-part_left-wrapper">
        <div className="testing-part_left">
          <Gap bottom>
            <Test
              text="Запустить все тесты"
              status={
                tests[pageId] &&
                tests[pageId].allTests &&
                tests[pageId].allTests.status
              }
              id={-1}
              activeId={activeId[pageId]}
              activeIdCallback={giveActiveId}
              editMode={editMode}
              testStartCallback={testStarted}
              dataSendInProcess={
                tests[pageId] &&
                tests[pageId].allTests &&
                tests[pageId].allTests.dataSendStatus
              }
            />
          </Gap>
          <h3>Встроенные тесты</h3>
          <Gap bottom>
            <div className="test-block__line" />
            {tests[pageId] && //mb izbavitsya ot massiva testsList, i zhdat poka vse testi i inputi zagruzytsya i vivod delat iz mesta gde vsya infa o testah hranitsya?
            tests[pageId].testsList &&
            tests[pageId].testsList.adminTests && //!!!!!!!!!!!!!!!!!!!!!!!!
              tests[pageId].testsList.adminTests.map(test => {
                return (
                  <Test
                    text={test.testTitle}
                    status={
                      tests[pageId][test.testId] &&
                      tests[pageId][test.testId].status
                    }
                    id={test.testId}
                    key={test.testId} //dublirovanie, key == id
                    activeId={activeId[pageId]}
                    activeIdCallback={giveActiveId}
                    editMode={editMode}
                    testStartCallback={testStarted}
                    dataSendInProcess={
                      tests[pageId] &&
                      tests[pageId][test.testId] &&
                      tests[pageId][test.testId].dataSendStatus
                    }
                  />
                );
              })}
          </Gap>
          <h3>Пользовательские тесты</h3>
          <Gap bottom>
            <div className="test-block__line" />
            {tests[pageId] &&
            tests[pageId].testsList &&
            tests[pageId].testsList.userTests && //!!!!!!!!!!!!!!!!!!!!!!!!!
              tests[pageId].testsList.userTests.map(test => {
                return (
                  <Test
                    text={test.testTitle}
                    status={
                      tests[pageId][test.testId] &&
                      tests[pageId][test.testId].status
                    }
                    id={test.testId}
                    key={test.testId} //dublirovanie, key == id
                    activeId={activeId[pageId]}
                    canEdit
                    editCallback={setEditStatus}
                    activeIdCallback={giveActiveId}
                    setUserTestTextCallback={setUserTestText}
                    editControlCallback={editControl}
                    editMode={editMode}
                    testStartCallback={testStarted}
                    dataSendInProcess={
                      tests[pageId] &&
                      tests[pageId][test.testId] &&
                      tests[pageId][test.testId].dataSendStatus
                    }
                  />
                );
              })}
          </Gap>
          <Button
            kind={Button.kinds.minor}
            icon={<Icon view={Icon.views.plus} initial={Icon.kinds.default} />}
            iconPosition="left"
            onClick={addTest}
          >
            Добавить тест
          </Button>
        </div>
      </div>
      <div className="testing-part_right">
        {activeId[pageId] ? (
          activeId[pageId] === -1 ? (
            <React.Fragment>
              <TestOutput
                title="Вывод"
                testText={
                  tests[pageId] === undefined
                    ? ""
                    : tests[pageId].allTests === undefined
                    ? ""
                    : tests[pageId].allTests.output
                }
                rows={27}
              />
            </React.Fragment>
          ) : (
            <React.Fragment>
              <TestOutput
                type="input"
                title="Ввод"
                placeholder="...Введите свои входные данные"
                editMode={editMode}
                testText={
                  tests[pageId] === undefined
                    ? ""
                    : tests[pageId][activeId[pageId]] === undefined
                    ? ""
                    : tests[pageId][activeId[pageId]].input
                }
                inputEditCallback={setUserInput}
              />
              <TestOutput
                type="expectedOutput"
                title="Ожидаемый вывод"
                placeholder="...Введите свои ожидаемые входные данные"
                editMode={editMode}
                testText={
                  tests[pageId] === undefined
                    ? ""
                    : tests[pageId][activeId[pageId]] === undefined
                    ? ""
                    : tests[pageId][activeId[pageId]].expectedOutput
                }
                inputEditCallback={setUserInput}
              />
              <TestOutput
                title="Вывод"
                testText={
                  tests[pageId] === undefined
                    ? ""
                    : tests[pageId][activeId[pageId]] === undefined
                    ? ""
                    : tests[pageId][activeId[pageId]].output === undefined
                    ? ""
                    : tests[pageId][activeId[pageId]].output
                }
              />
            </React.Fragment>
          )
        ) : (
          <React.Fragment />
        )}
      </div>
    </div>
  );
};

Testing.propTypes = {
  editMode: PropTypes.bool.isRequired,
  activeId: PropTypes.object,
  // tests: PropTypes.object.isRequired,
  tests: PropTypes.object,
  pageId: PropTypes.string.isRequired,
  setEditMode: PropTypes.func.isRequired,
  setActiveId: PropTypes.func.isRequired,
  setTestInputOutput: PropTypes.func.isRequired,
  setTestText: PropTypes.func.isRequired,
  addTest: PropTypes.func.isRequired,
  startTest: PropTypes.func.isRequired,
  changeTest: PropTypes.func.isRequired,
};

const mapStateToProps = ({ tests: { editMode, activeId, tests }, pageId }) => {
  return { editMode, activeId, tests, pageId };
};

const mapDispatchToProps = {
  setEditMode,
  setActiveId,
  setTestInputOutput,
  setTestText,
  addTest,
  startTest,
  changeTest,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Testing);
