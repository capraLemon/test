import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import classNames from "classnames";

const Chat = ({ byWhat, id, handleMouseOver }) => {
  const [messages, setMessages] = useState([]);

  useEffect(() => {
    let handleMessaesList = messagesList => {
      setMessages(messagesList);
    };

    (async () => {
      const data = await fetch(`/api/message/${byWhat}${id}`);
      const jsonedData = await data.json();
      handleMessaesList(jsonedData);
    })();

    const pollingInterval = setInterval(async () => {
      const data = await fetch(`/api/message/${byWhat}${id}`);
      const jsonedData = await data.json();
      handleMessaesList(jsonedData);
    }, 1000);
    return () => {
      clearInterval(pollingInterval);
      handleMessaesList = () => {};
    };
  }, [byWhat, id]);

  const showDate = timestamp => {
    const options = {
      hour: "numeric",
      minute: "numeric",
      year: "numeric",
      month: "long",
      day: "numeric",
    };
    let date = new Date();
    date.setTime(timestamp);
    return date.toLocaleString("ru", options);
  };

  return (
    <div className="messenger-chat" onMouseOver={handleMouseOver}>
      {messages.map((message, index) => (
        <div
          key={index}
          className={classNames("messenger-chat__message_to-user", {
            "messenger-chat__message_from-user": message.senderIsUser,
          })}
        >
          {message.messageText}
          <p className="messenger-chat__message-date">
            {showDate(message.creationDate)}
          </p>
        </div>
      ))}
    </div>
  );
};

Chat.propTypes = {
  byWhat: PropTypes.string.isRequired,
  id: PropTypes.number.isRequired,
  handleMouseOver: PropTypes.func.isRequired,
};

export default Chat;
