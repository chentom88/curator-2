import React from 'react';
import {Collapsible} from 'pui-react-collapsible';
import classnames from 'classnames';

const types = React.PropTypes;

class SmallTab extends React.Component {
  static propTypes = {
    expanded: types.bool,
    header: types.node,
    onClick: types.func,
    paneId: types.string
  };

  render() {
    const {children, expanded, header, onClick, paneId} = this.props;

    return (
      <div>
        <div className="tab-heading">
          <h4 className="tab-title" role="presentation">
            <a aria-expanded={expanded} aria-controls={paneId} aria-selected={expanded} role="tab"
               onClick={onClick}>{header}</a>
          </h4>
        </div>
        <Collapsible expanded={expanded} role="tabpanel">
          {children}
        </Collapsible>
      </div>
    );
  }
}

class SmallTabs extends React.Component {
  static propTypes = {
    actions: types.node,
    activeKey: types.number,
    id: types.string,
    handleClick: types.func,
    onSelect: types.func,
    smallScreenClassName: types.string,
    tabType: types.string
  };

  render() {
    const {
      actions,
      activeKey,
      children,
      className,
      id,
      handleClick,
      onSelect,
      smallScreenClassName,
      tabType
      } = this.props;
    const smallScreenClasses = classnames([`tab-${tabType}-small-screen`, 'panel-group', smallScreenClassName, className]);
    const childArray = React.Children.toArray(children);
    const childrenAsPanels = childArray.map((child, key) => {
      const {title, eventKey, children} = child.props;
      const paneId = `${id}-pane-${key}`;
      const myProps = {
        expanded: eventKey === activeKey,
        header: title,
        key,
        onClick: (e) => handleClick(e, eventKey, onSelect),
        paneId
      };
      return <SmallTab {...myProps}>{children}</SmallTab>;
    });

    const actionsNode = actions ? <div className="tabs-action">{actions}</div> : null;

    return (
      <div className={smallScreenClasses}>
        {actionsNode}
        {childrenAsPanels}
      </div>
    );
  }
}

module.exports = {SmallTabs};
