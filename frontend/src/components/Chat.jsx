import React from "react";
import classNames from "classnames";

const messages = [
  {
    id: 0,
    from: false,
    text: "Здравствуй, здесь вы можете задать свои вопросы.",
    date: "23:03 10.10.2019",
    unread: false,
  },
  {
    id: 1,
    from: true,
    text: "Здравствуй, считается ли 0 натуральным числом?",
    date: "4:18 15.10.2019",
    unread: false,
  },
  {
    id: 2,
    from: false,
    text: "Читайте условия задачи.",
    date: "14:05 25.10.2019",
    unread: false,
  },
];

const Chat = () => {
  return (
    <div className="messenger-chat">
      {messages.map(message => (
        <div
          key={message.id}
          className={classNames("messenger-chat__message_to-user", {
            "messenger-chat__message_from-user": message.from,
          })}
        >
          <p className="messenger-chat__message-text">{message.text}</p>
          {message.date}
        </div>
      ))}
    </div>
  );
};

export default Chat;
