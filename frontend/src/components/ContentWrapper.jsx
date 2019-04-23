import React, { PureComponent } from "react";
import classNames from "classnames";
import PropTypes from "prop-types";
import Gap from "bloko/blocks/gap/index.jsx";

class ContentWrapper extends PureComponent {
  static propTypes = {
    title: PropTypes.string.isRequired,
    collapsible: PropTypes.bool,
    opened: PropTypes.bool,
    children: PropTypes.node,
  };

  static defaultProps = {
    collapsible: false,
    opened: false,
  };

  state = {
    opened: this.props.opened,
  };

  switchState = () => {
    this.setState({ opened: !this.state.opened });
  };

  render() {
    const { collapsible, title } = this.props;
    const opened = this.state.opened;

    return (
      <div className="content-wrapper">
        <div className="content-wrapper-header">
          <h2>{title}</h2>
          {collapsible && (
            <button
              className={classNames("content-wrapper-header__switch", {
                "content-wrapper-header__switch_opened": opened,
              })}
              onClick={this.switchState}
            />
          )}
        </div>
        {(!collapsible || opened) && <Gap top>{this.props.children}</Gap>}
      </div>
    );
  }
}

export default ContentWrapper;
