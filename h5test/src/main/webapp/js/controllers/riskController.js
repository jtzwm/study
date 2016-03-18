define(['views/riskView'], function (Utils) {
  "use strict";

  function init() {
    Utils.render();
    choose();
  }

  function choose() {
    var items = $$('.select .item');
    $$(items).click(function () {
      if ($$(this).parent('.select').hasClass('multiple')) {
        if ($$(this).hasClass('checked')) {
          $$(this).removeClass('checked');
        } else {
          $$(this).addClass('checked');
        }
      } else {
        $$(this).parent('.select').find('.item').removeClass('checked');
        $$(this).addClass(('checked'));
      }
    });
  }

  return {
    init: init
  };
});
