define(['views/videoView'], function (View) {
  "use strict";
  /**
   * code:
   *    1 等待
   *    2 通话中
   *    3 验证失败
   *    4 网络异常
   */

  var code = 4;

  function init() {
    View.renderMessage(code);
  }

  return {
    init: init
  };
});
