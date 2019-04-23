import React from "react";
import Button from "bloko/blocks/button/index.jsx";
import Column, { ColumnsWrapper } from "bloko/blocks/column/index.jsx";
import Gap from "bloko/blocks/gap/index.jsx";
import Textarea from "bloko/blocks/textarea/index.jsx";
import Chat from "../components/Chat";
import ChatTopicButton from "../components/ChatTopicButton";
import PageHeader from "../containers/PageHeader";

const Messenger = () => (
  <React.Fragment>
    <PageHeader tasksVisibility chatVisibility userInfoVisibility />
    <ColumnsWrapper>
      <Column m="12" l="16">
        <h1 className="messenger-title">Связь с организаторами</h1>
      </Column>
      <Gap top bottom>
        <Column m="12" l="16">
          <div className="messenger-modal">
            <div className="messenger-topic-and-chat">
              <ChatTopicButton />
              <Chat />
            </div>
            <Gap top bottom>
              <Textarea placeholder="Введите ваше сообщение" rows={5} />
            </Gap>
            <Button kind={Button.kinds.primary}>Отправить сообщение</Button>
          </div>
        </Column>
      </Gap>
    </ColumnsWrapper>
  </React.Fragment>
);

export default Messenger;
