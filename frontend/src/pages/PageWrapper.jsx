import React, { useEffect } from "react";
import { connect } from "react-redux";
import { Route, Redirect } from "react-router-dom";
import PropTypes from "prop-types";
import {
  setLoggedUser,
  startContest,
  setUserName,
} from "../reducers/backEndMock";

const PageWrapper = ({
  setLoggedUser,
  startContest,
  setUserName,
  logged,
  component: Component,
  ...rest
}) => {
  useEffect(() => {
    const fetchUserData = async () => {
      const response = await fetch("/api/userData");
      const jsonedResponse = await response.json();
      if (jsonedResponse.isAuthenticated) {
        setLoggedUser(true);
        setUserName(jsonedResponse.name);
      }
    };
    fetchUserData();
  }, [setLoggedUser, setUserName]);

  useEffect(() => {
    const fetchContestStart = async () => {
      const response = await fetch("/api/contest");
      const jsonedResponse = await response.json();
      startContest(jsonedResponse.userParticipationIsStarted);
    };
    fetchContestStart();
  }, [startContest]);

  return (
    <Route
      {...rest}
      render={props =>
        logged ? (
          <Component {...props} />
        ) : (
          <Redirect
            to={{
              pathname: "/login",
              state: "anon",
            }}
          />
        )
      }
    />
  );
};

PageWrapper.propTypes = {
  component: PropTypes.func,
  logged: PropTypes.bool,
  setLoggedUser: PropTypes.func,
  startContest: PropTypes.func,
  setUserName: PropTypes.func,
};

const mapStateToProps = ({ backEndMock: { logged } }) => {
  return { logged };
};

const mapDispatchToProps = {
  setLoggedUser,
  startContest,
  setUserName,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PageWrapper);
