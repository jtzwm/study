define(["utils"], function (Utils) {
  "use strict";
  var msg = $$('.video-message');

  function render(params) {
    Utils.bindEvents(params.bindings);
  }

  function renderMessage(params) {
    switch (params) {
      case 1:
        $$(msg[0]).text('等待坐席接通，请稍等……');
        $$(msg[1]).hide();
        break;
      case 2:
        $$(msg[0]).text('视频通话中');
        $$(msg[1]).hide();
        break;
      case 3:
        $$(msg[0]).hide();
        $$(msg[1]).find('span').text('视频验证失败');
        $$(msg[1]).find('.video-button').text('重新预约');
        break;
      case 4:
        $$(msg[0]).hide();
        $$(msg[1]).find('span').text('网络异常，请重新发起视频');
        $$(msg[1]).find('.video-button').text('重新视频');
        break;
    }
  }

  return {
    render: render,
    renderMessage: renderMessage
  };
});
