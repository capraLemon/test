import React from "react";
import PropTypes from "prop-types";

const TaskCondition = ({ task }) => {
  return (
    <React.Fragment>
      <table className="task-condition__restriction">
        <tbody>
          <tr>
            <td>Ограничение времени, с</td>
            <td>{task.timeLimit}</td>
          </tr>
          <tr>
            <td>Ограничение памяти, Мб</td>
            <td>{task.memoryLimit}</td>
          </tr>
          <tr>
            <td>Ввод</td>
            <td>стандартный ввод или input.txt</td>
          </tr>
          <tr>
            <td>Вывод</td>
            <td>стандартный вывод или output.txt</td>
          </tr>
          <tr>
            <td>Общее число попыток отправки</td>
            <td>{task.triesLimit}</td>
          </tr>
        </tbody>
      </table>
      <br />
      <p>{task.taskText}</p>
      <br />
      <h3>Формат ввода</h3>
      <p>{task.sampleInputOutput && task.sampleInputOutput[0].input}</p>
      <br />
      <h3>Формат вывода</h3>
      <p>{task.sampleInputOutput && task.sampleInputOutput[0].output}</p>
      <br />
      <h3>Примечание</h3>
      <p>{task.sampleInputOutput && task.sampleInputOutput[0].notes}</p>
    </React.Fragment>
  );
};

TaskCondition.propTypes = {
  task: PropTypes.object.isRequired,
};

export default TaskCondition;
