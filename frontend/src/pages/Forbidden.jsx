import React from "react";
import { ColumnsWrapper } from "bloko/blocks/column/index.jsx";
import Gap from "bloko/blocks/gap/index.jsx";
import PageHeader from "../containers/PageHeader";

const UserBefore = () => (
  <React.Fragment>
    <PageHeader
      tasksVisibility={false}
      chatVisibility={false}
      userInfoVisibility={false}
    />
    <ColumnsWrapper>
      <div className="centering-wrapper">
        <Gap top bottom>
          <h2 className="title">
            Вам пока что сюда нельзя
            <br />
            Error 403
          </h2>
        </Gap>
      </div>
    </ColumnsWrapper>
  </React.Fragment>
);

export default UserBefore;
