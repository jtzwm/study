/**
 * Created by qi on 2015/9/9.
 */
$(function () {
  'use strict';

  //menu 切换
  function changeMenu(titles, contents, isHide) {
    $(titles).each(function (index, el) {
      $(el).mouseover(function (e) {
        e.preventDefault();
        e.stopPropagation();
        $(titles).removeClass('active').eq(index).addClass('active');
        $(contents).removeClass('active').eq(index).addClass('active');
      }).mouseleave(function (e) {
        e.preventDefault();
        e.stopPropagation();
        if (isHide) {
          $(el).removeClass('active');
        }
      });
    });
    $(contents).each(function (index, el) {
      $(el).mouseover(function (e) {
        e.preventDefault();
        e.stopPropagation();
      });
    });
    if (isHide) {
      $(document).mouseover(function () {
        $(titles).removeClass('active');
        $(contents).removeClass('active');
      });
    }
  }

  (function () {
    var titleLists = $('.cate-title-list .cate-m'),
      contentLists = $('.cate-content-list .cate-mc');
    changeMenu(titleLists, contentLists, true);
  })();
  (function () {
    var changeList = $('.floor');
    changeList.each(function (index, el) {
      var titleLists = changeList.eq(index).find('.floor-side li'),
        contentLists = changeList.eq(index).find('.floor-content .floor-section');
      changeMenu(titleLists, contentLists);
    });
    changeMenu();
  })();
});
