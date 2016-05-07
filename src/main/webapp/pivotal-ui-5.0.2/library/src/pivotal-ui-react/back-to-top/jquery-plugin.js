var $ = global.jQuery || require('jquery');
var throttle = require('lodash.throttle');
var {getScrollTop} = require('./scroll-top');

/**
 * @component BackToTopJquery
 *
 * @description
 * A component for scrolling to the top of a page using jQuery
 * Included in your JavaScript with `require('pui-react-back-to-top/jquery-plugin')`
 *
 * @example ```html
 * <a class="back-to-top" data-position="back-to-top" href="#" target="_blank" style="display: inline;"></a>
 * ```
 *
 */


var backToTop = throttle(function() {
  if (getScrollTop() > 400) {
    $('[data-position="back-to-top"]').fadeIn();
  } else {
    $('[data-position="back-to-top"]').fadeOut();
  }
}, 200);

$(function() {
  $(window).scroll(backToTop);
  $(document).on('click.pui.back-to-top.data-api', '[data-position="back-to-top"]', function(e) {
    e.preventDefault();
    $('html, body').animate({scrollTop: 0}, 800);
  });
});
