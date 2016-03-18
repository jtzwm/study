/**
 * Created by qi on 2015/7/14.
 */
define(['views/indexView'], function (View) {
  var bindings = [{
    element: '.index .item',
    event: 'click',
    handler: go
  }];

  function go() {
    mainView.router.loadPage('appoint.html');
  }

  function sub() {
    $$('.select').click(function (e) {
      e.stopPropagation();
      $$('.submenu').show();
    });
    $$('.submenu li').click(function (e) {
      e.stopPropagation();
      $$('.selected').text($$(this).text());
      $$('.submenu').hide();
    });
    $$('document').click(function () {
      $$('.submenu').hide();
    });
  }

  function init() {
    View.render({
      bindings: bindings
    });
    sub();
  }

  return {
    init: init
  };
});
