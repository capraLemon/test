import React from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";

const Counter = ({ sendCounter }) => (
  <div style={{ color: `white` }}>tries left: {sendCounter}</div>
);

Counter.propTypes = {
  sendCounter: PropTypes.number,
};

const mapStateToProps = state => {
  return { sendCounter: state.sendCounter };
};

export default withRouter(connect(mapStateToProps)(Counter));
