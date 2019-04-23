import React from "react";
import { ColumnsWrapper } from "bloko/blocks/column/index.jsx";
import Gap from "bloko/blocks/gap/index.jsx";
import Link from "bloko/blocks/link/index.jsx";
import PageHeader from "../containers/PageHeader";
import SignInForm from "../components/SignInForm";

const AnonAfter = () => (
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
            Набор в&nbsp;
            <Link href="https://school.hh.ru/">
              Школу программистов HeadHunter
            </Link>
            <br />
            уже начался. Вступительные испытания продлятся до 30 октября 2019
            года.
          </h2>
        </Gap>
        <SignInForm />
      </div>
    </ColumnsWrapper>
  </React.Fragment>
);

export default AnonAfter;
