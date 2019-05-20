import React, { useState, useEffect } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import Column, { ColumnsWrapper } from "bloko/blocks/column/index.jsx";
import Gap from "bloko/blocks/gap/index.jsx";
import ContentWrapper from "../components/ContentWrapper";
import PageHeader from "../containers/PageHeader";
import SendCode from "../containers/SendCode";
import SendMessage from "../components/SendMessage";
import TaskCondition from "../components/TaskCondition";
import HistoryOfSending from "../containers/HistoryOfSending";
import Timer from "../components/Timer";
import Testing from "../components/Testing";
import { setPageId } from "../reducers/pageId";
import { setEditMode } from "../reducers/tests";
import { getTests } from "../signals";

const Tasks = ({ match, setPageId, getTests, setEditMode }) => {
  useEffect(() => {
    setPageId(match.params.id);
    setEditMode(false);
    getTests();
  }, [setPageId, match.params.id, getTests, setEditMode]);

  const [task, setTask] = useState({});
  useEffect(() => {
    let handleTaskList = taskParams => {
      setTask(taskParams);
    };
    const initialFetchText = async () => {
      const taskParams = await fetch(`/api/task/${match.params.id}`);
      const jsonedTaskParams = await taskParams.json();
      handleTaskList(jsonedTaskParams);
    };
    initialFetchText();
    return () => {
      handleTaskList = () => {};
    };
  }, [match.params.id]);

  const [history, setHistory] = useState([]);
  useEffect(() => {
    let handleHistory = history => {
      setHistory(history);
    };
    const initialFetchHistory = async () => {
      const history = await fetch(`/api/code/history/${match.params.id}`);
      const jsonedHistory = await history.json();
      handleHistory(jsonedHistory);
    };

    initialFetchHistory();
    const refreshTime = setInterval(async () => {
      initialFetchHistory();
    }, 1000);
    return () => {
      clearInterval(refreshTime);
      handleHistory = () => {};
    };
  }, [match.params.id]);

  return (
    <React.Fragment>
      <PageHeader tasksVisibility chatVisibility userInfoVisibility />
      <ColumnsWrapper>
        <Column m="12" l="16">
          <Gap top bottom>
            <Timer />
          </Gap>
          <h1>{task.title}</h1>
        </Column>
        <div className="centering-wrapper">
          <Gap top bottom>
            <Column m="12" l="16">
              <ContentWrapper title="Условие задачи" collapsible>
                <TaskCondition task={task} />
              </ContentWrapper>
            </Column>
          </Gap>
          <Gap top bottom>
            <Column m="12" l="16">
              <ContentWrapper title="Ваше решение">
                <SendCode />
              </ContentWrapper>
            </Column>
          </Gap>
          <Gap top bottom>
            <Column m="12" l="16">
              <ContentWrapper title="Тестирование" collapsible>
                <Testing />
              </ContentWrapper>
            </Column>
          </Gap>
          <Gap top bottom>
            <Column m="12" l="16">
              <ContentWrapper title="История отправки решений" collapsible>
                <HistoryOfSending history={history} />
              </ContentWrapper>
            </Column>
          </Gap>
          <Gap top bottom>
            <Column m="12" l="16">
              <ContentWrapper title="Чат" collapsible>
                <SendMessage by="byTask/" id={match.params.id - 0} />
              </ContentWrapper>
            </Column>
          </Gap>
        </div>
      </ColumnsWrapper>
    </React.Fragment>
  );
};

Tasks.propTypes = {
  match: PropTypes.shape({
    params: PropTypes.shape({
      id: PropTypes.string,
    }),
  }),
  setPageId: PropTypes.func.isRequired,
  setEditMode: PropTypes.func.isRequired,
  getTests: PropTypes.func.isRequired,
};

const mapDispatchToProps = {
  setPageId,
  getTests,
  setEditMode,
};

export default connect(
  null,
  mapDispatchToProps
)(React.memo(Tasks));
