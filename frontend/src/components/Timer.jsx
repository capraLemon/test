import React, { useState, useEffect } from "react";

const Timer = () => {
  const timer = time => {
    let delta = time / 1000;
    const days = Math.floor(delta / 86400);
    delta -= days * 86400;
    const hours = Math.floor(delta / 3600) % 24;
    delta -= hours * 3600;
    const minutes = Math.floor(delta / 60) % 60;
    delta -= minutes * 60;
    return { days, hours, minutes };
  };

  const [time, setTime] = useState(0);
  const [left, setLeftTime] = useState({});

  useEffect(() => {
    let handleTime = time => {
      setTime(time.timeLeftMilliSecs);
      setLeftTime(timer(time.timeLeftMilliSecs));
    };
    const fetchTime = async () => {
      const time = await fetch("/api/contest/timeLeft");
      const jsonedTime = await time.json();
      handleTime(jsonedTime);
    };
    fetchTime();
    return () => {
      handleTime = () => {};
    };
  }, []);

  useEffect(() => {
    const refreshTime = setTimeout(() => {
      setTime(time - 60000);
      setLeftTime(timer(time - 60000));
    }, 60000);
    return () => {
      clearTimeout(refreshTime);
    };
  }, [time]);

  return (
    <React.Fragment>
      <p className="timer">До окончания тестирования осталось</p>
      <p className="timer">
        дни: {left.days}, часы: {left.hours}, минуты: {left.minutes}
      </p>
    </React.Fragment>
  );
};

export default Timer;
