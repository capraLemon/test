import React from "react";
import PropTypes from "prop-types";
import classNames from "classnames";
import Gap from "bloko/blocks/gap/index.jsx";
import Button from "bloko/blocks/button/index.jsx";
import Icon from "bloko/blocks/icon/Icon.jsx";

const SendCodeButtonCounter = ({ isFetching = false, onClick, triesLeft }) => {
  return (
    <div className="code-send-wrapper">
      <div className="code-send__button">
        <Button
          kind={Button.kinds.primary}
          onClick={onClick}
          stretched
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
          Отправить
        </Button>
      </div>
      <Gap right />
      <p className={classNames({ "code-send__counter": !isFetching })}>
        Оставшиеся попытки: {triesLeft}
      </p>
    </div>
  );
};

SendCodeButtonCounter.propTypes = {
  isFetching: PropTypes.bool,
  onClick: PropTypes.func,
  triesLeft: PropTypes.number,
};

export default SendCodeButtonCounter;
