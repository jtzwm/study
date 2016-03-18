define([], function () {
  "use strict";

  function init() {
    setTimeout(start, 1500);
  }


  function start() {
    $$('.prepare .wait-time').addClass('start').find('.wast-time').text("开始视频");
    $$('.countdown').hide();
    $$('.wait-time').click(function () {
      mainView.loadPage('video.html');
    });
  }

  return {
    init: init
  };
});
