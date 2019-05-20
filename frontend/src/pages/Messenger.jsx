import React from "react";
import Column, { ColumnsWrapper } from "bloko/blocks/column/index.jsx";
import Gap from "bloko/blocks/gap/index.jsx";
import PageHeader from "../containers/PageHeader";
import SendMessage from "../components/SendMessage";

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
            <SendMessage by="byTopic/" id={0} />
          </div>
        </Column>
      </Gap>
    </ColumnsWrapper>
  </React.Fragment>
);

export default Messenger;
