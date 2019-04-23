import React from "react";
import { ColumnsWrapper } from "bloko/blocks/column/index.jsx";
import Gap from "bloko/blocks/gap/index.jsx";
import Button from "bloko/blocks/button/index.jsx";
import PageHeader from "../containers/PageHeader";

const UserAfter = () => (
  <React.Fragment>
    <PageHeader
      tasksVisibility={false}
      chatVisibility={false}
      userInfoVisibility
    />
    <ColumnsWrapper>
      <div className="centering-wrapper">
        <Gap top bottom>
          <h2 className="title">
            Вступительные испытания продлятся
            <br />
            до 30 октября 2019 года.
            <br />
            На выполнение заданий отводится 2 недели c момента нажатия кнопки
            &quot;Начать&quot;.
          </h2>
        </Gap>
        <Button kind={Button.kinds.primary} scale="large">
          Начать
        </Button>
      </div>
    </ColumnsWrapper>
  </React.Fragment>
);

export default UserAfter;
