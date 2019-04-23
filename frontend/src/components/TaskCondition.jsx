import React from "react";
import PropTypes from "prop-types";

const TaskCondition = ({ task }) => {
  return (
    <React.Fragment>
      <table className="task-condition__restriction">
        <tbody>
          <tr>
            <td>Ограничение времени</td>
            <td>{task.timeLimit}</td>
          </tr>
          <tr>
            <td>Ограничение памяти</td>
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
            <td>Начальное число попыток отправки</td>
            <td>{task.triesLimit}</td>
          </tr>
        </tbody>
      </table>
      <br />
      <p>
        {/* Необходимо найти минимальное натуральное число, которого нет во входном
        массиве.
        <br />
        На вход подается строка, содержащая не более 10^6 целых чисел в
        диапазоне от -10^9 до 10^9, разделенные пробелом.
        <br />
        На выходе ожидается одно число, удовлетворяющее условию задачи. */}
        {task.taskText}
      </p>
      <br />
      <h3>Формат ввода</h3>
      <p>1 3 6 4 1 2</p>
      <br />
      <h3>Формат вывода</h3>
      <p>5</p>
      <br />
      <h3>Примечание</h3>
      <p>
        Цифра 0 не является натуральным числом.
        <br />В выводе ожидается одно число без пробельных символов.
      </p>
    </React.Fragment>
  );
};

TaskCondition.propTypes = {
  task: PropTypes.object.isRequired,
};

export default TaskCondition;
