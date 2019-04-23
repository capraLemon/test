import React, { useState } from "react";
import classNames from "classnames";

const ChatTopicButton = () => {
  const topicId = [0, 1, 2, 3];

  const mapIdToTopicName = {
    0: "Общие вопросы",
    1: "Задача 1. Минимальное пропущенное число",
    2: "Задача 2. Проклятый остров",
    3: "Задача 3. Разрезание пирога",
  };

  const [newMessages, setNewMessages] = useState([0, 1, 2, 0]);
  const [currentId, setCurrentId] = useState(0);

  const click = id => {
    let newState = newMessages.slice();
    newState[id] = 0;
    setNewMessages(newState);
    setCurrentId(id);
  };

  return (
    <div className="messenger-topic-list">
      {topicId.map(id => (
        <button
          key={id}
          className={classNames("messenger-topic-list__button", {
            "messenger-topic-list__button_chosen": id === currentId,
          })}
          onClick={() => {
            click(id);
          }}
        >
          <p className="messenger-topic-list__button-text">
            {mapIdToTopicName[id]}
          </p>
          <span
            className={classNames(
              "messenger-topic-list__button_no-new-message",
              {
                "messenger-topic-list__button_new-message": newMessages[id],
              }
            )}
          >
            {newMessages[id]}
          </span>
        </button>
      ))}
    </div>
  );
};

export default ChatTopicButton;
