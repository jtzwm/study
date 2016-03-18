define(['views/appointView'], function (View) {
  "use strict";

  function cal() {
    var monthNames = ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'];
    var dayNamesShort = ['日', '一', '二', '三', '四', '五', '六'];
    var calendarInline = App.calendar({
      container: '#calendar-inline-container',
      value: [new Date()],
      dayNamesShort: dayNamesShort,
      toolbarTemplate: '<div class="toolbar calendar-custom-toolbar">' +
      '<div class="toolbar-inner">' +
      '<div class="left">' +
      '<a href="#" class="link icon-only"><i class="icon icon-back"></i></a>' +
      '</div>' +
      '<div class="center"></div>' +
      '<div class="right">' +
      '<a href="#" class="link icon-only"><i class="icon icon-forward"></i></a>' +
      '</div>' +
      '</div>' +
      '</div>',
      onOpen: function (p) {
        $$('.calendar-custom-toolbar .center').text(monthNames[p.currentMonth] + ', ' + p.currentYear);
        $$('.calendar-custom-toolbar .left .link').on('click', function () {
          calendarInline.prevMonth();
        });
        $$('.calendar-custom-toolbar .right .link').on('click', function () {
          calendarInline.nextMonth();
        });
      },
      onMonthYearChangeStart: function (p) {
        $$('.calendar-custom-toolbar .center').text(monthNames[p.currentMonth] + ', ' + p.currentYear);
      }
    });

    $$('#picker-time').on('click', function () {
      app.pickerModal('.picker-time');
    });

    $$('.close-picker').on('click', function () {
      var pickedTime = document.forms[0].appointment.value;
      $$('#picker-time').val(pickedTime);
    });
  }

  function choose() {
    var items = $$('.choose-time .item');
    $$(items).click(function () {
      if (!$$(this).hasClass('disable')) {
        $$(items).removeClass('active');
        $$(this).addClass('active');
      }
    });
  }

  function init() {
    View.render();
    cal();
    choose();
  }

  return {
    init: init
  };
});
