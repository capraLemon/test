import React from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import AnonAfter from "./AnonAfter";
import UserAfter from "./UserAfter";
import { Route, Redirect } from "react-router-dom";

const MainPage = ({ location, contestStarted }) => {
  if (location.state === "anon") {
    return <AnonAfter />; //Should be just Anon in the future (because we will GET data from backend )
  }
  if (!contestStarted) {
    return <UserAfter />;
  }
  return <Route render={() => <Redirect to={{ pathname: "/tasks/1000" }} />} />;
};

MainPage.propTypes = {
  location: PropTypes.object,
  contestStarted: PropTypes.bool,
};

const mapStateToProps = ({ backEndMock: { contestStarted } }) => {
  return { contestStarted };
};

export default connect(mapStateToProps)(MainPage);
