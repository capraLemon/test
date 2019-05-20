import React from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { ColumnsWrapper } from "bloko/blocks/column/index.jsx";
import Gap from "bloko/blocks/gap/index.jsx";
import Button from "bloko/blocks/button/index.jsx";
import PageHeader from "../containers/PageHeader";
import { startContest } from "../reducers/backEndMock";

const UserAfter = ({ startContest }) => {
  const handleClick = async () => {
    const response = await fetch("/api/contest/start", {
      method: "PUT",
    });
    if (response.ok) {
      startContest(true);
    }
  };

  return (
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
          <Button
            kind={Button.kinds.primary}
            scale="large"
            onClick={handleClick}
          >
            Начать
          </Button>
        </div>
      </ColumnsWrapper>
    </React.Fragment>
  );
};

UserAfter.propTypes = {
  startContest: PropTypes.func,
};

const mapDispatchToProps = {
  startContest,
};

export default connect(
  null,
  mapDispatchToProps
)(UserAfter);
