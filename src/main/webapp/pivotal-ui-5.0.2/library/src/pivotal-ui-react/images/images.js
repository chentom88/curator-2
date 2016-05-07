var React = require('react');
var types = React.PropTypes;
import {mergeProps} from 'pui-react-helpers';
require('pui-css-images');

class Image extends React.Component {
  static propTypes = {
    responsive: types.bool,
    href: types.string,
    alt: types.string,
    src: types.string.isRequired
  };

  render() {
    let {responsive, href, children, ...props} = this.props;
    if (responsive) {
      props = mergeProps(props, {className: 'img-responsive'});
    }

    const image = <img {...props}>{children}</img>;
    return href ? <a {...{href}}>{image}</a> : image;
  }
}

module.exports = {Image};
