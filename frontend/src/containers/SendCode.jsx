import React, { Component } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { sendCode } from "../signals";
import { setCode, setLanguage } from "../reducers/enteredCode";
import LanguageChooseButtons from "../components/LanguageChooseButtons";
import SendCodeButtonCounter from "../components/SendCodeButtonCounter";
import Textarea from "bloko/blocks/textarea/index.jsx";
import Gap from "bloko/blocks/gap/index.jsx";

class SendCode extends Component {
  handleSendClick = () => {
    if (!this.props.isFetching) {
      this.props.sendCode();
    }
  };

  handleInputChange = event => {
    const enteredCode = event.target.value;
    this.props.setCode(enteredCode, this.props.pageId);
  };

  handleLangChooseClick = id => {
    this.props.setLanguage(id, this.props.pageId);
  };

  render() {
    const { isFetching, code, language, pageId } = this.props;
    return (
      <React.Fragment>
        <LanguageChooseButtons
          languageId={language[pageId]}
          onClick={this.handleLangChooseClick}
        />
        <Gap bottom />
        <Textarea
          onChange={this.handleInputChange}
          value={code[pageId] === undefined ? "" : code[pageId]}
          placeholder="...Введите свой код"
          rows={30}
          disabled={isFetching}
        />
        <Gap bottom />
        <SendCodeButtonCounter
          isFetching={this.props.isFetching}
          onClick={this.handleSendClick}
          triesLeft={this.props.sendCounter}
        />
      </React.Fragment>
    );
  }
}

SendCode.propTypes = {
  setCode: PropTypes.func.isRequired,
  setLanguage: PropTypes.func.isRequired,
  sendCode: PropTypes.func.isRequired,
  isFetching: PropTypes.bool.isRequired,
  code: PropTypes.object.isRequired,
  language: PropTypes.object.isRequired,
  sendCounter: PropTypes.number,
  pageId: PropTypes.string.isRequired,
};

const mapStateToProps = ({
  codeStatus: { isFetching },
  enteredCode: { code, language },
  sendCounter,
  pageId,
}) => {
  return { isFetching, code, language, sendCounter, pageId };
};

const mapDispatchToProps = {
  setCode,
  setLanguage,
  sendCode,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SendCode);
