import React, { useCallback, useRef, useState } from "react";
import Button from "bloko/blocks/button/index.jsx";
import Column from "bloko/blocks/column/index.jsx";
import Gap from "bloko/blocks/gap/index.jsx";
import Input from "bloko/blocks/input/index.jsx";

const SignInForm = () => {
  const [show, toggleShow] = useState(false);

  const timerRef = useRef();

  const click = useCallback(() => {
    clearTimeout(timerRef.current);
    toggleShow(true);
    timerRef.current = setTimeout(toggleShow, 3000, false);
  }, []);

  return (
    <div className="form-wrapper">
      <Column m="4">
        <Gap bottom top>
          <h4 className="form__text">Логин</h4>
          <Input />
          <Gap bottom />
          <h4 className="form__text">Пароль</h4>
          <Input type={Input.types.password} />
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

export default SignInForm;
