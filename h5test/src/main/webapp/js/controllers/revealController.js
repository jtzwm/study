define(['views/revealView'], function (Utils) {
  var timer;
  var agree = $$('#agree');
  function init() {
    Utils.render();
    var setTime = 10;
    var curTime = 0;
    countDown(setTime, curTime);
    iAgree();
  }

  function countDown(setTime, curTime) {
    var leftTime;

    if (timer) {
      clearTimeout(timer);
    }

    curTime++;
    leftTime = setTime - curTime;
    reRender(leftTime);

    if (leftTime > 0) {
      timer = setTimeout(function () {
        countDown(setTime, curTime);
      }, 1000);
    } else {
      reset();
    }
  }

  function iAgree() {
    $$('.hint').click(function () {
      var isChecked = $$(agree).prop('checked');
      if (isChecked) {
        clearTimeout(timer);
        reset();
      } else {
        $$('#revealNext').addClass('disabled');
      }
    });
  }
  function reRender(leftTime) {
    $$('#countdown').html('（' + leftTime + 's）');
  }

  function reset() {
    if ($$(agree).prop('checked')) {
      $$('#revealNext').removeClass('disabled');
    }
    $$('#countdown').remove();
  }

  return {
    init: init
  };
});
