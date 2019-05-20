import React, { useCallback, useRef, useState, useEffect } from "react";
import Button from "bloko/blocks/button/index.jsx";
import Column from "bloko/blocks/column/index.jsx";
import Gap from "bloko/blocks/gap/index.jsx";
import Input from "bloko/blocks/input/index.jsx";

import { connect } from "react-redux";
import PropTypes from "prop-types";
import { withRouter } from "react-router";
import { setLoggedUser } from "../reducers/backEndMock";

const SignInForm = ({ setLoggedUser, history }) => {
  const [show, toggleShow] = useState(false);

  const timerRef = useRef();

  const click = () => {
    setUserData([enteredLogin, enteredPassword]);
  };

  const [userData, setUserData] = useState(["", ""]);

  const [enteredLogin, setEnteredLogin] = useState("");
  const [enteredPassword, setEnteredPassword] = useState("");
  const onChangeLogin = useCallback(() => {
    setEnteredLogin(event.target.value);
  }, []);
  const onChangePassword = useCallback(() => {
    setEnteredPassword(event.target.value);
  }, []);

  const mounted = useRef();
  useEffect(() => {
    const sendUserData = async () => {
      const response = await fetch("/api/login", {
        method: "POST",
        headers: {
          Accept: "application/json", // eslint-disable-line prettier/prettier
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          login: userData[0],
          password: userData[1],
        }),
      });
      if (response.ok) {
        setLoggedUser(true);
        history.replace("/");
      } else {
        clearTimeout(timerRef.current);
        toggleShow(true);
        timerRef.current = setTimeout(toggleShow, 3000, false);
      }
    };

    if (!mounted.current) {
      mounted.current = true;
    } else {
      sendUserData();
    }
  }, [userData, setLoggedUser, history]);

  return (
    <div className="form-wrapper">
      <Column m="4">
        <Gap bottom top>
          <h4 className="form__text">Логин</h4>
          <Input onChange={onChangeLogin} />
          <Gap bottom />
          <h4 className="form__text">Пароль</h4>
          <Input type={Input.types.password} onChange={onChangePassword} />
          <div className="form__error-wrapper">
            {show && (
              <p className="form__error">Неправильный логин или пароль</p>
            )}
          </div>
          <div className="form__button-wrapper">
            <Button kind={Button.kinds.primary} stretched onClick={click}>
              Войти
            </Button>
          </div>
        </Gap>
      </Column>
    </div>
  );
};

SignInForm.propTypes = {
  setLoggedUser: PropTypes.func,
  history: PropTypes.object,
};

const mapStateToProps = ({ backEndMock: { login, password } }) => {
  return { login, password };
};

const mapDispatchToProps = {
  setLoggedUser,
};

export default withRouter(
  connect(
    mapStateToProps,
    mapDispatchToProps
  )(SignInForm)
);
