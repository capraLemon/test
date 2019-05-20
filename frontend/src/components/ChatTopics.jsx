import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import classNames from "classnames";

const ChatTopics = ({ id, handleClick }) => {
  const [topics, setTopics] = useState([]);

  useEffect(() => {
    let handleTopicsList = topicsList => {
      setTopics(topicsList);
    };

    (async () => {
      const data = await fetch("/api/message/topics");
      const jsonedData = await data.json();
      handleTopicsList(jsonedData);
    })();

    const pollingInterval = setInterval(async () => {
      const data = await fetch("/api/message/topics");
      const jsonedData = await data.json();
      handleTopicsList(jsonedData);
    }, 1000);
    return () => {
      clearInterval(pollingInterval);
      handleTopicsList = () => {};
    };
  }, []);

  return (
    <div className="messenger-topic-list">
      {topics.map(topic => (
        <button
          key={topic.topicId}
          className={classNames("messenger-topic-list__button", {
            "messenger-topic-list__button_chosen": topic.topicId === id,
          })}
          onClick={() => {
            handleClick(topic.topicId);
          }}
        >
          <p className="messenger-topic-list__button-text">{topic.title}</p>
          <span
            className={classNames(
              "messenger-topic-list__button_no-new-message",
              {
                "messenger-topic-list__button_new-message": topic.quantity,
              }
            )}
          >
            {topic.quantity}
          </span>
        </button>
      ))}
    </div>
  );
};

ChatTopics.propTypes = {
  id: PropTypes.number.isRequired,
  handleClick: PropTypes.func.isRequired,
};

export default ChatTopics;
