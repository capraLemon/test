import React from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import AnonBefore from "./AnonBefore";
import UserBefore from "./UserBefore";
import AnonAfter from "./AnonAfter";
import UserAfter from "./UserAfter";

const mapPageToComponent = {
  AnonBefore,
  UserBefore,
  AnonAfter,
  UserAfter,
};

const MainPage = ({ Page }) => <Page />;

MainPage.propTypes = {
  Page: PropTypes.func.isRequired,
};

const mapStateToProps = ({ pageToGive: { mainPage } }) => {
  return { Page: mapPageToComponent[mainPage] };
};

export default connect(mapStateToProps)(MainPage);
