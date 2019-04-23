import React from "react";
import { ColumnsWrapper } from "bloko/blocks/column/index.jsx";
import Gap from "bloko/blocks/gap/index.jsx";
import Link from "bloko/blocks/link/index.jsx";
import PageHeader from "../containers/PageHeader";
import SignInForm from "../components/SignInForm";

const AnonBefore = () => (
  <React.Fragment>
    <PageHeader
      tasksVisibility={false}
      chatVisibility={false}
      userInfoVisibility={false}
    />
    <ColumnsWrapper>
      <div className="centering-wrapper">
        <Gap top bottom>
          <Gap top>
            <h2 className="title">
              Набор в&nbsp;
              <Link href="https://school.hh.ru/">
                Школу программистов HeadHunter
              </Link>
              <br />
              откроется с 30 сентября 2019 года.
            </h2>
          </Gap>
        </Gap>
        <SignInForm />
      </div>
    </ColumnsWrapper>
  </React.Fragment>
);

export default AnonBefore;
