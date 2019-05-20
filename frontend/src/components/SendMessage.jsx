import React, { useEffect } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import Button from "bloko/blocks/button/index.jsx";
import Chat from "../components/Chat";
import ChatTopics from "../components/ChatTopics";
import Gap from "bloko/blocks/gap/index.jsx";
import Icon from "bloko/blocks/icon/Icon.jsx";
import Textarea from "bloko/blocks/textarea/index.jsx";
import { setMessage, setAddress } from "../reducers/chatControl";
import { sendMessage } from "../signals";

const SendMessage = ({
  by,
  id,
  message,
  isFetching,
  byWhat,
  currentId,
  setMessage,
  setAddress,
  sendMessage,
}) => {
  useEffect(() => {
    setAddress(by, id);
  }, [by, id, setAddress]);

  const changeTopic = newTopic => {
    setAddress("byTopic/", newTopic);
    readNewMessages();
  };

  const readNewMessages = async () => {
    await fetch(`/api/message/read/${currentId}`, {
      method: "PUT",
    });
  };

  const handleClick = () => {
    if (!isFetching && message[currentId] !== "") {
      sendMessage();
    }
  };

  const handleInputChange = event => {
    const enteredMessage = event.target.value;
    setMessage(enteredMessage, currentId);
  };

  return (
    <React.Fragment>
      <div className="messenger-topic-and-chat">
        {byWhat === "byTopic/" && (
          <ChatTopics id={currentId} handleClick={changeTopic} />
        )}
        <Chat
          byWhat={byWhat}
          id={currentId}
          handleMouseOver={readNewMessages}
        />
      </div>
      <Gap top bottom>
        <Textarea
          onChange={handleInputChange}
          onClick={readNewMessages}
          value={message[currentId] === undefined ? "" : message[currentId]}
          placeholder="Введите ваше сообщение"
          rows={5}
        />
      </Gap>
      <Button
        kind={Button.kinds.primary}
        onClick={handleClick}
        loading={
          isFetching && (
            <Icon
              view={Icon.views.loading}
              initial={Icon.kinds.inverted}
              scale="24"
            />
          )
        }
      >
        Отправить сообщение
      </Button>
    </React.Fragment>
  );
};

SendMessage.propTypes = {
  by: PropTypes.string.isRequired,
  id: PropTypes.number.isRequired,
  message: PropTypes.object.isRequired,
  isFetching: PropTypes.bool.isRequired,
  byWhat: PropTypes.string.isRequired,
  currentId: PropTypes.number.isRequired,
  setMessage: PropTypes.func.isRequired,
  setAddress: PropTypes.func.isRequired,
  sendMessage: PropTypes.func.isRequired,
};

const mapStateToProps = ({
  chatControl: { message, isFetching, byWhat, currentId },
}) => {
  return { message, isFetching, byWhat, currentId };
};

const mapDispatchToProps = {
  setMessage,
  setAddress,
  sendMessage,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SendMessage);
