define([], function () {
  "use strict";

  function init() {
    //时间小于20分钟，隐藏修改按钮
    setTimeout(noModify, 1000);
    //时间小于15分钟，直接跳到风险测评页面
    setTimeout(start, 2000);
  }

  function noModify() {
    $$('.prepare .modify').hide();
  }

  function start() {
    mainView.loadPage('risk.html');
  }

  return {
    init: init
  };
});
