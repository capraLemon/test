import React, { useState, useEffect } from "react";
import Button from "bloko/blocks/button/index.jsx";
import Column, { ColumnsWrapper } from "bloko/blocks/column/index.jsx";
import Gap from "bloko/blocks/gap/index.jsx";
import Textarea from "bloko/blocks/textarea/index.jsx";
import Chat from "../components/Chat";
import ContentWrapper from "../components/ContentWrapper";
import PageHeader from "../containers/PageHeader";
import SendCode from "../containers/SendCode";
import TaskCondition from "../components/TaskCondition";
import HistoryOfSending from "../containers/HistoryOfSending";

const Tasks = () => {
  const [task, setTask] = useState({});

  useEffect(() => {
    const initialFetchHistory = async () => {
      const data = await fetch("http://checkup.space:9999/api/task/1000");
      const jsonedData = await data.json();
      setTask(jsonedData);
      console.log("fetched task text"); // eslint-disable-line no-console
    };
    initialFetchHistory();
  }, []);

  const [solutions, setSolutions] = useState([]);

  useEffect(() => {
    const initialFetchHistory = async () => {
      const solutions = await fetch(
        "http://checkup.space:9999/api/code/history/1000"
      );
      const newSolutions = await solutions.json();
      setSolutions(newSolutions);
      console.log("history update initial"); // eslint-disable-line no-console
    };

    initialFetchHistory();
  }, []);

  useEffect(() => {
    const fetchHistory = () => {
      const timeout = setTimeout(async () => {
        const solutions = await fetch(
          "http://checkup.space:9999/api/code/history/1000"
        );
        const newSolutions = await solutions.json();
        setSolutions(newSolutions);
        console.log("history update with timeout"); // eslint-disable-line no-console
      }, 2000);
      return timeout;
    };

    let timeout = fetchHistory();
    return () => clearTimeout(timeout);
  }, [solutions]);

  return (
    <React.Fragment>
      <PageHeader tasksVisibility chatVisibility userInfoVisibility />
      <ColumnsWrapper>
        <Column m="12" l="16">
          <Gap top bottom>
            <p className="timer">
              До окончания тестирования осталось: 10 дней, 5 часов, 20 минут
            </p>
          </Gap>
          <h4>Задача 1</h4>
          <h1>{task.title}</h1>
        </Column>
        <div className="centering-wrapper">
          <Gap top bottom>
            <Column m="12" l="16">
              <ContentWrapper
                title={"Условие задачи"}
                collapsible
                opened={false}
              >
                <TaskCondition task={task} />
              </ContentWrapper>
            </Column>
          </Gap>
          <Gap top bottom>
            <Column m="12" l="16">
              <ContentWrapper title={"Ваше решение"}>
                <SendCode />
              </ContentWrapper>
            </Column>
          </Gap>
          <Gap top bottom>
            <Column m="12" l="16">
              <ContentWrapper
                title={"История отправки решений"}
                collapsible
                opened={true}
              >
                <HistoryOfSending solutions={solutions} />
              </ContentWrapper>
            </Column>
          </Gap>
          <Gap top bottom>
            <Column m="12" l="16">
              <ContentWrapper title={"Чат"} collapsible>
                <Chat />
                <Gap top bottom>
                  <Textarea placeholder="Введите ваше сообщение" rows={5} />
                </Gap>
                <Button kind={Button.kinds.primary}>Отправить сообщение</Button>
              </ContentWrapper>
            </Column>
          </Gap>
        </div>
      </ColumnsWrapper>
    </React.Fragment>
  );
};

export default Tasks;
