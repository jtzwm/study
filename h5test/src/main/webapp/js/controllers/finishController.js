define(['views/finishView'], function (Utils) {
  "use strict";

  var code = 2;//0成功，1待审核，2失败
  var isSuccess = ['success', 'wait', 'fail'];

  function render() {
    switch (isSuccess[code]) {
      case 'success':
        //更改提示图标
        $$('.info i')[0].className = 'icon-right';
        //更改提示文字
        $$('.text-hint').text('签约完成');
        //更改错误提示信息状态
        $$('.wrong-message').hide();
        //更改重做按钮状态
        $$('.reve').hide();
        break;
      case 'wait':
        $$('.info i')[0].className = 'icon-tip';
        $$('.text-hint').text('视频面签已结束，谢谢合作！');
        $$('.wrong-message').html('我们会在3个工作日内完成审核');
        $$('.wrong-message').show();
        $$('.reve').show().text('刷新');
        break;
      case 'fail':
        $$('.info i')[0].className = 'icon-wrong';
        $$('.text-hint').text('面签失败');
        $$('.wrong-message').html('错误提示错误提示错提示错误提示错误提示错误提示');
        $$('.wrong-message').show();
        $$('.reve').show();
        break;
    }
  }

  function init() {
    Utils.render();
    render();
  }

  return {
    init: init
  };
});
