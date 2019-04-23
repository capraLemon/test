import React from "react";
import { ColumnsWrapper } from "bloko/blocks/column/index.jsx";
import Gap from "bloko/blocks/gap/index.jsx";
import Link from "bloko/blocks/link/index.jsx";
import PageHeader from "../containers/PageHeader";

const UserBefore = () => (
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
            Привет, Маргарита!
            <br />
            <br />
            Набор в&nbsp;
            <Link href="https://school.hh.ru/">
              Школу программистов HeadHunter
            </Link>
            <br />
            откроется с 30 сентября 2019 года.
            <br />
            Вы получите уведомление на Ваш e-mail.
          </h2>
        </Gap>
      </div>
    </ColumnsWrapper>
  </React.Fragment>
);

export default UserBefore;
