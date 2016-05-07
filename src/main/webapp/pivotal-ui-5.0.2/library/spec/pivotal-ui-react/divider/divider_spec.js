require('../spec_helper');

import {itPropagatesAttributes} from '../support/shared_examples';

describe('Divider', function() {
  var Divider;
  beforeEach(function() {
    Divider = require('../../../src/pivotal-ui-react/dividers/dividers').Divider;
    ReactDOM.render(<Divider className='test-class' id='test-id' style={{opacity: '0.5'}}/>, root);
  });

  afterEach(function() {
    ReactDOM.unmountComponentAtNode(root);
  });

  it('creates a divider', function() {
    expect('hr').toHaveClass('divider-alternate-1');
  });

  itPropagatesAttributes('hr', {className: 'test-class', id: 'test-id', style: {opacity: '0.5'}});
  describe('when large is set to true', function() {
    beforeEach(function() {
      ReactDOM.render(<Divider size="large"/>, root);
    });

    it('creates a divider with -2 appended to the classname', function() {
      expect('hr').toHaveClass('divider-alternate-2');
    });
  });

  describe('when the divider goes on a dark background, inverse: true', function() {
    beforeEach(function() {
      ReactDOM.render(<Divider inverse={true}/>, root);
    });

    it('creates a divider without the -alternate in the class', function() {
      expect('hr').toHaveClass('divider-1');
    });
  });

  describe('when a large divider goes on a dark background, inverse: true', function() {
    beforeEach(function() {
      ReactDOM.render(<Divider inverse={true} size="large"/>, root);
    });

    it('creates a divider without the -alternate in the class', function() {
      expect('hr').toHaveClass('divider-2');
    });
  });

  describe('setting a custom className', function() {
    beforeEach(function() {
      ReactDOM.render(<Divider inverse={true} className="myClass"/>, root);
    });

    it('passes the class through to the divider', function() {
      expect('hr').toHaveClass('divider-1');
      expect('hr').toHaveClass('myClass');
    });
  });

  describe('setting a custom data attribute', function() {
    beforeEach(function() {
      ReactDOM.render(<Divider data-behavior="myAttr"/>, root);
    });

    it('passes the data attribute through to the divider', function() {
      expect('hr').toHaveAttr('data-behavior', 'myAttr');
    });
  });
});
