if (!String.prototype.trim) {
  String.prototype.trim = function () {
    // Make sure we trim BOM and NBSP
    rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
    return this.replace(rtrim, "");
  }
}

/**
 * @authors yuk (yukan@cairenhui.com)
 */
var B = (function (ua) {
  var b = {
    msie: /\b(?:msie |ie |trident)/.test(ua) && !/opera/.test(ua),
    opera: /opera/.test(ua),
    safari: /webkit/.test(ua) && !/chrome/.test(ua),
    firefox: /firefox/.test(ua),
    chrome: /chrome/.test(ua)
  };
  var vMark = "";
  for (var i in b) {
    if (b[i]) {
      vMark = "safari" == i ? "version" : i;
      break;
    }
  }
  b.version = vMark && RegExp("(?:" + vMark + ")[\\/: ]([\\d.]+)").test(ua) ? RegExp.$1 : "0";

  b.ie = b.msie;
  b.ie6 = b.msie && parseInt(b.version, 10) == 6;
  b.ie7 = b.msie && parseInt(b.version, 10) == 7;
  b.ie8 = b.msie && parseInt(b.version, 10) == 8;
  b.ie9 = b.msie && parseInt(b.version, 10) == 9;
  b.ie10 = b.msie && parseInt(b.version, 10) == 10;

  b.win2000 = ua.indexOf('windows nt 5.0') > 1;
  b.winxp = ua.indexOf('windows nt 5.1') > 1;
  b.win2003 = ua.indexOf('windows nt 5.2') > 1;
  b.winvista = ua.indexOf('windows nt 6.0') > 1;
  b.win7 = ua.indexOf('windows nt 6.1') > 1;
  b.win8 = ua.indexOf('windows nt 6.2') > 1;

  return b;
})(window.navigator.userAgent.toLowerCase());
var isIE6 = B.ie6
var DiySelect = function (select, options) {
  options = $.extend({}, $.fn.diySelect.defaults, options);

  this.options = options;
  this.hasInit = false;

  this.init(select);
};

DiySelect.prototype = {

  constructor: DiySelect,

  init: function (select) {
    // 是否初始化且目标元素是否为 `select`
    if (this.hasInit && select.tagName.toLowerCase !== 'select') {
      return;
    }

    var id = select.id;
    this.unique(id); // 去重

    this.selectSpan = $('<span/>').addClass(this.options.selectClass).attr('id', id + '-select');
    this.optionDiv = $('<div/>').addClass(this.options.optionClass).attr('id', id + '-option');
    if (this.options.isSearch) {
      this.optionUl = $('<ul style="max-height:120px;overflow:auto;"></ul>');
    } else {
      this.optionUl = $('<ul/>');
    }
    this.option = $(select).find('option');
    this.optionSize = this.option.length;
    this.arrowHtml = '<i class="' + this.options.arrowClass + '"></i>';
    this.bgIframe = $('<iframe/>').addClass('bgiframe').attr('frameborder', '0');

    // 组装模拟元件
    this.setupOption();
    this.setupSelect();

    // DOM 操作：
    // 隐藏原 `select`，再其后加入模拟的 `HTMLElement`
    $(select).hide();
    this.selectSpan.insertAfter($(select));
    this.optionDiv.insertAfter(this.selectSpan).hide();

    // 初始化完成标记
    this.hasInit = true;

    var that = this;
    var active = -1;
    var list = this.optionDiv.find('li');

    // 事件绑定
    list.on('click', function (e) {
      var text = $(this).text();

      e.preventDefault();
      e.stopPropagation();

      that.chooseOption(text);
      $(select).trigger('change');
    });

    list.on('mouseenter', function () {
      list.removeClass('active');
      $(this).addClass('active');
      active = list.filter(':visible').index(this);
    });

    this.selectSpan.on('click', function (e) {
      e.preventDefault();
      e.stopPropagation();

      // 自定义事件
      if (that.options.beforeSelect) {
        that.options.beforeSelect.apply(this);
      }
      ;

      $('.' + that.options.optionClass).hide();

      // if (that.optionVisible) {
      //  that.hideOption();
      // } else {
      //  that.showOption();
      // }
      that.showOption();
    });

    $(select).on('choose', function (e, text) {
      e.stopPropagation();
      that.chooseOption(text);
    });

    $(select).on('revert', function (e) {
      e.stopPropagation();
      that.revert();
    });

    // 窗口变化时调整位置
    $(window).on('resize', $.proxy(this.setPosition, this));

    // 模拟 `select` 失焦
    $(document).on('click', function (e) {
      if (that.optionVisible) {
        if (e.target == that.optionUl.siblings('.searchDiv').find('input')[0] || e.target == that.optionUl.siblings('.searchDiv')[0] || e.target == that.optionUl.siblings('.searchDiv').find('.search_btn')[0]) {
          return;
        } else {
          that.hideOption();
        }
      }
    });

    // 键盘事件
    $(document).on('keydown', function (e) {
      if (that.optionDiv.is(':visible')) {
        switch (e.keyCode) {
          case 13: // enter
            if (active !== -1) {
              var text = list.slice(active, active + 1).text();
              that.chooseOption(text);
            }

            that.hideOption();
            $(select).trigger('change');
            break;
          case 27: // esc
            that.hideOption();
            break;
          case 38: // up
            e.preventDefault();
            moveSelect(-1);
            break;
          case 40: // down
            e.preventDefault();
            moveSelect(1);
            break;
        }
      }
    });

    /**
     * 焦点位移
     * @param  {float} step 位移步长
     */
    function moveSelect(step) {
      var count = list.length;

      active += step;

      if (active < 0) {
        active = 0;
      } else if (active >= count) {
        active = count - 1;
      } else {
        list.removeClass('active');
        list.slice(active, active + 1).addClass('active');

        // 出现滚动条的情况
        if (active >= (that.options.maxSize / 2) && count > that.options.maxSize) {
          that.optionDiv.scrollTop(list.height() * (active - (that.options.maxSize / 2)));
        }
      }
    }
  },

  setupOption: function () {
    var fragment = document.createDocumentFragment();

    for (var i = 0; i < this.optionSize; i++) {
      var li = document.createElement('li');
      var value = this.option[i].value;
      var text = this.option[i].text;

      li.innerHTML = text;

      if (value) {
        li.setAttribute('data-' + this.options.valueAttr, value);
      }

      fragment.appendChild(li);
    }
    if (this.options.isSearch) {
      this.optionDiv.append('<div class="searchDiv" id="' + this.options.searchId + '" style="margin:10px 0;"><input style="width:60%" class="input searchBranch"><input class="search_btn right_btn"></div>')
    }

    this.optionDiv.append(this.optionUl);
    this.optionUl.get(0).appendChild(fragment);
    this.optionVisible = false;
  },

  setupSelect: function () {
    var checkText = this.options.checkText || this.option.filter(':selected').text();

    // 初始选择（可设置）
    this.chooseOption(checkText);

    if (this.options.width) {
      this.selectSpan.width(this.options.width);
    }

    var spanStyle = this.selectSpan.attr('style');

    if (spanStyle) {
      spanStyle += ';' + this.options.style;
    }

    this.selectSpan.attr('style', spanStyle);
  },

  setPosition: function () {
    // 可视窗口顶部到 `selectSpan` 的距离
    var top_height = this.selectSpan.offset().top + this.selectSpan.outerHeight() - $(window).scrollTop();

    // 可视窗口剩余空间与 `optionDiv` 高度的差值
    var diff = $(window).height() - top_height - this.optionDiv.height();

    // 差值大于零，说明剩余空间还可容纳 `optionDiv`
    // `optionDiv` 就位居 `selectSpan` 正下方展示
    // 反之亦然
    if (diff > 0) {
      if (this.options.isSearch) {
        this.optionDiv.pin({
          base: this.selectSpan,
          baseXY: [0, '100%-3px']
        });
        this.optionDiv.css({'borderColor': '#f99f42', 'borderTop': '0 none', 'borderBottom': '1px solid #f99f42'})
      }
      else {
        this.optionDiv.pin({
          base: this.selectSpan,
          baseXY: [0, '100%-1px']
        });
      }
    } else {
      if (this.options.isSearch) {
        this.optionDiv.pin({
          base: this.selectSpan,
          selfXY: [0, '100%-3px']
        });
        this.optionDiv.css({'borderColor': '#f99f42', 'borderBottom': '0 none', 'borderTop': '1px solid #f99f42'})
      }
      else {
        this.optionDiv.pin({
          base: this.selectSpan,
          selfXY: [0, '100%-1px']
        });
      }
    }
  },

  chooseOption: function (text) {
    this.hideOption();
    this.selectSpan.html(text + this.arrowHtml);
    // this.option.attr('selected', false);

    for (var i = 0; i < this.optionSize; i++) {
      if (text === this.option[i].text) {
        // 原生 `select` 跟随选择
        this.option[i].selected = true;
        break;
      }
    }

    if (this.options.afterChoose) {
      this.options.afterChoose.apply(this, [text]);
    }
  },

  showOption: function () {
    this.optionDiv.show();

    this.optionDiv.height(Math.min(this.optionDiv.height(),
      this.optionDiv.find('li').height() * this.options.maxSize));

    this.optionDiv.css({
      'min-width': this.selectSpan.outerWidth() - 2,
      'z-index': this.options.zIndex
    });

    if (isIE6) {
      this.optionDiv.css('zoom', 1);
    }
    if (this.options.isSearch) {
      this.selectSpan.css({'borderColor': '#f99f42'});
    }
    this.setPosition();
    this.optionVisible = true;
  },

  hideOption: function () {
    this.optionDiv.hide();
    this.optionVisible = false;
  },

  unique: function (id) {
    if ($('#' + id + '-select')) {
      $('#' + id + '-select').remove();
    }
    if ($('#' + id + '-option')) {
      $('#' + id + '-option').remove();
    }
  },

  revert: function () {
    this.selectSpan.remove();
    this.optionDiv.remove();
  }
};

// 注册插件
$.fn.diySelect = function (options) {
  return this.each(function () {
    new DiySelect(this, options);
  });
};

$.fn.chooseSelect = function (value) {
  return this.trigger('choose', [value]);
};

$.fn.revertSelect = function () {
  return this.trigger('revert');
};

// 默认设置
$.fn.diySelect.defaults = {
  selectClass: 'js-select',
  optionClass: 'js-option',
  arrowClass: 'arrow',
  valueAttr: 'select-val',
  zIndex: '10000',
  offsetY: 1,
  maxSize: 6
};

$.fn.Constructor = DiySelect;


// 定义 Overlay 类
var Overlay = function (element, options) {
  options = $.extend({}, $.fn.overlay.defaults, options);

  this.options = options;
  this.overlay = $(element);

  this.init(options);
};

Overlay.prototype = {

  constructor: Overlay,

  init: function (options) {
    this.overlay.addClass(this.options.className);

    // 添加外部自定义的样式
    this.overlay.attr('style', this.options.style);

    // 基本 CSS
    this.overlay.css({
      width: this.options.width,
      height: this.options.height,
      zIndex: this.options.zIndex
    });

    // 将浮出层插入 DOM 并进行定位
    this.overlay.appendTo($(this.options.parent));
    this.setPosition();

    // 窗口变化重新定位
    $(window).on('resize', $.proxy(this.setPosition, this));
  },

  show: function () {
    this.overlay.show();
  },

  hide: function () {
    this.overlay.hide();
  },

  remove: function () {
    this.overlay.remove();
  },

  blurHide: function () {
    $(document).on('click', $.proxy(this.hide, this));
    this.overlay.on('click', function (e) {
      e.stopPropagation();
    });
  },

  setPosition: function () {
    this.overlay.pin({
      // 基准定位元素，默认为当前可视区域
      base: this.options.align.base,
      // element 的定位点，默认为中心
      selfXY: this.options.align.selfXY,
      // 基准定位元素的定位点，默认为中心
      baseXY: this.options.align.baseXY
    });
  }
};

// 注册插件
$.fn.overlay = function (options) {
  return this.each(function () {
    new Overlay(this, options);
  });
};

// 默认设置
$.fn.overlay.defaults = {
  className: '',
  style: '',
  width: 'auto',
  height: 'auto',
  zIndex: 999,
  parent: 'body',
  align: {
    base: null,
    selfXY: ['50%', '50%'],
    baseXY: ['50%', '50%']
  }
};

$.fn.overlay.Constructor = Overlay;
/**
 * 名称: pin.js
 * 描述: 通过两个对象分别描述定位元素及其定位点，然后将其定位点重合
 * 属性: 工具
 * 版本: 0.9.0
 * 依赖: jQuery ~> 1.7.2
 * 开发: wuwj
 */

$.fn.pin = function (options, fixed) {
  options = $.extend({
    base: null,
    selfXY: [0, 0],
    baseXY: [0, 0]
  }, options || {});

  // 是否相对于当前可视区域（Window）进行定位
  var isViewport = !options.base,

  // 定位 fixed 元素的标志位，表示需要特殊处理
    isPinFixed = false,

    parent = this.offsetParent(),

  // 基准元素的偏移量
    offsetLeft, offsetTop,

  // 基准元素根据定位点坐标 `baseXY` 分别获得纵横两个方向上的 size
    baseX, baseY,

  // 同上，根据定位点坐标 `selfXY` 获取的横纵两个方向上的 size
    selfX, selfY,

  // 定位元素位置
    left, top;

  // 设定目标元素的 position 为绝对定位
  // 若元素的初始 position 不为 absolute，会影响元素的 display、宽高等属性
  if (this.css('position') !== 'fixed' || isIE6) {
    this.css('position', 'absolute');
    isPinFixed = false;
  } else {
    isPinFixed = true;
  }

  // 修正 ie6 下 absolute 定位不准的 bug
  if (isIE6) {
    this.css('zoom', 1);
    parent.css('zoom', 1);
  }

  // 如果不定义基准元素，则相对于当前可视区域进行定位
  if (isViewport) {
    offsetLeft = $(document).scrollLeft();
    offsetTop = $(document).scrollTop();

    baseX = getSize($(window), options.baseXY[0], 'outerWidth');
    baseY = getSize($(window), options.baseXY[1], 'outerHeight');
  } else {
    // 判断定位元素的祖先是否被定位过，是的话用 `$.position()`，否则用 `$.offset()`
    var offsetFixed = (parent[0] === document.documentElement) ?
      options.base.offset() :
      options.base.position();

    offsetLeft = offsetFixed.left;
    offsetTop = offsetFixed.top;

    baseX = getSize(options.base, options.baseXY[0], 'outerWidth');
    baseY = getSize(options.base, options.baseXY[1], 'outerHeight');
  }

  selfX = getSize(this, options.selfXY[0], 'outerWidth');
  selfY = getSize(this, options.selfXY[1], 'outerHeight');

  // 计算定位元素位置
  // 若定位 fixed 元素，则父元素的 offset 没有意义
  left = (isPinFixed ? 0 : offsetLeft) + baseX - selfX;
  top = (isPinFixed ? 0 : offsetTop) + baseY - selfY;

  // 进行定位
  this.css({left: left, top: top});
};

// 扩展：相对于当前可视区域页面上某一元素的居中定位
$.fn.pinCenter = function (options) {
  this.pin({
    base: (options) ? options.base : null,
    selfXY: ['50%', '50%'],
    baseXY: ['50%', '50%']
  });
};

/**
 * 根据坐标点获取对应尺寸值
 * @param  {jquery} object 被获取尺寸的元素
 * @param  {array}  coord  坐标点
 * @param  {string} type   尺寸类型
 * @return {number}
 */
function getSize(object, coord, type) {
  // 参考 `https://github.com/aralejs/position/blob/master/src/position.js`
  // 中的 `xyConverter` 方法
  // 先将坐标值转成字符串
  var x = coord + '';

  // 处理 alias，此处正则表达式内的 `:?` 表示此括号为非捕获型括号
  if (/\D/.test(x)) {
    x = x.replace(/(?:top|left)/gi, '0%')
      .replace(/center/gi, '50%')
      .replace(/(?:bottom|right)/gi, '100%');
  }

  // 处理 `px`
  if (x.indexOf('px') !== -1) {
    x = x.replace(/px/gi, '');
  }

  // 将百分比转为像素值
  if (x.indexOf('%') !== -1) {
    // 支持小数
    x = x.replace(/(\d+(?:\.\d+)?)%/gi, function (m, d) {
      return object[type]() * (d / 100.0);
    });
  }

  // 处理类似 100%+20px 的情况
  if (/[+\-*\/]/.test(x)) {
    try {
      x = (new Function('return ' + x))();
    } catch (e) {
      throw new Error('Invalid position value: ' + x);
    }
  }

  // 转回为数字
  return parseFloat(x, 10);
}

// 定义 Dialog 类
var Dialog = function (element, options) {
  options = $.extend({}, $.fn.dialog.defaults, options);

  this.options = options;
  this.hasInit = false;

  this.setup(element);
  this.element = element;
};

Dialog.prototype = {

  constructor: Dialog,

  init: function () {
    // 创建弹出层
    this.dialog = new Overlay(this.options.template, {
      className: this.options.dialogClass,
      width: this.options.width,
      height: this.options.height,
      zIndex: this.options.zIndex
    });

    if (this.options.hasMask) {
      var $mask = $('.' + this.options.maskClass);

      if ($mask.length === 0) {
        // 创建遮罩
        this.mask = new Overlay(document.createElement('div'), {
          className: this.options.maskClass,
          width: isIE6 ? $(document).outerWidth() : '100%',
          height: isIE6 ? $(document).outerHeight() : '100%',
          zIndex: this.options.zIndex - 1,
          position: 'fixed'
        });
      } else {
        // 已有遮罩
        this.mask = $mask;
      }
    }

    // 内容填充
    this.render();

    // 先隐藏浮动层与遮罩
    this.dialog.hide();
    if (this.options.hasMask) {
      this.mask.hide();
    }

    // 关闭按钮事件绑定
    $(this.dialog.overlay).find('.js-close').on('click', $.proxy(this.hide, this));

    // 其它按钮事件绑定
    $(this.dialog.overlay).find('[data-role=confirm]').on('click', $.proxy(this.confirm, this));
    $(this.dialog.overlay).find('[data-role=cancel]').on('click', $.proxy(this.hide, this));

    // 初始化完成标志
    this.hasInit = true;
  },

  setup: function (element) {
    var that = this;

    if (element) {
      // 触发绑定
      $(element).on('click', function (e) {
        e.preventDefault();

        that.trigger();
      });
    } else {
      that.trigger();
    }

    // 用于一些初始化的操作
    if (that.options.once) {
      $(element).one('click', function (e) {
        e.preventDefault();
        that.options.once();
      });
    }
  },

  trigger: function () {
    if (!this.hasInit) {
      this.init();
    }

    this.show();
  },

  show: function () {
    if (this.options.beforeShow) {
      this.options.beforeShow.apply(this);
    }

    this.dialog.setPosition();
    this.dialog.show();
    if (this.options.hasMask) {
      this.mask.show();
    }

    if (this.options.afterShow) {
      this.options.afterShow.apply(this);
    }
  },

  hide: function () {
    if (this.options.beforeHide) {
      this.options.beforeHide.apply(this);
    }

    this.dialog.hide();
    if (this.options.hasMask) {
      this.mask.hide();
    }

    if (this.options.afterHide) {
      this.options.afterHide.apply(this);
    }

    if (this.options.needDestroy) {
      this.destroy();
    }
  },

  render: function () {
    var $head = $(this.dialog.overlay).find('.hd');
    var $body = $(this.dialog.overlay).find('.bd');
    var $close = $(this.dialog.overlay).find('.close');
    var html;

    if (!this.options.hasTitle) {
      $head.remove();
    } else {
      $head.find('h2').text(this.options.title);
    }

    if (this.options.noClose) {
      $close.remove();
    }

    if (this.options.confirmType) {
      html = '<p class="confirm-wrap"><i class="icon-sprite icon icon-' + this.options.confirmType + '-32"></i>' + this.options.message + '</p>'
    } else {
      html = this.options.content;
    }

    if (this.options.hasBtn) {
      var btnCls;
      html += '<div class="btn-wrap">';
      for (var i = 0; i < this.options.btnText.length; i++) {
        if (this.options.btnRole[i] === 'cancel') {
          btnCls = 'gray';
        } else {
          btnCls = 'blue';
        }

        html += '<input type="button" data-role="' + this.options.btnRole[i] + '" class="dialog_btn btn-default-' + btnCls + '" value="' + this.options.btnText[i] + '"/>'
      }
      html += '</div>'
    }
    ;

    $body.html(html).css('padding', this.options.padding);
  },

  closeDelay: function (time) {
    setTimeout($.proxy(this.hide, this), time);
  },

  destroy: function () {
    this.dialog.remove();
    if (this.options.hasMask) {
      this.mask.hide();
    }
    this.destroyed = true;
  },

  confirm: function () {
    this.options.confirm.apply(this);
  }
}

// 注册插件
$.fn.dialog = function (options) {
  return this.each(function () {
    new Dialog(this, options);
  });
};

// 默认设置
$.fn.dialog.defaults = {
  dialogClass: 'js-dialog',
  maskClass: 'js-mask',
  template: '<table> <tr> <td class="edge top-edge" colspan="3"></td> </tr> <tr> <td class="edge left-edge"></td> <td class="center"> <div class="content"> <div class="hd"> <h2>提示</h2> </div> <div class="bd"></div> <div class="close"> <a href="javascript:;" class="js-close"></a> </div> </div> </td> <td class="edge right-edge"></td> </tr> <tr> <td class="edge bottom-edge" colspan="3"></td> </tr> </table>',
  width: 450,
  height: 'auto',
  zIndex: 999,
  hasMask: true,
  hasTitle: true,
  title: '提示',
  cotent: '',
  padding: '20px',
  hasBtn: false,
  btnText: ['确定', '取消'],
  btnRole: ['confirm', 'cancel'],
  message: ''
};

$.fn.dialog.Constructor = Dialog;


// 阅读并接受协议后，下一步才可点击
$('#protocol').on('change', 'input[type=checkbox]', function () {
  if (this.checked) {
    $('#submitBtn').prop('disabled', false);
  } else {
    $('#submitBtn').prop('disabled', true);
  }
});

// 现金数字转换大写
function convertCurrency(currencyDigits) {
// Constants:
  var MAXIMUM_NUMBER = 99999999999.99;
// Predefine the radix characters and currency symbols for output:
  var CN_ZERO = "零";
  var CN_ONE = "壹";
  var CN_TWO = "贰";
  var CN_THREE = "叁";
  var CN_FOUR = "肆";
  var CN_FIVE = "伍";
  var CN_SIX = "陆";
  var CN_SEVEN = "柒";
  var CN_EIGHT = "捌";
  var CN_NINE = "玖";
  var CN_TEN = "拾";
  var CN_HUNDRED = "佰";
  var CN_THOUSAND = "仟";
  var CN_TEN_THOUSAND = "万";
  var CN_HUNDRED_MILLION = "亿";
  var CN_DOLLAR = "元";
  var CN_TEN_CENT = "角";
  var CN_CENT = "分";
  var CN_INTEGER = "整";

// Variables:
  var integral; // Represent integral part of digit number.
  var decimal; // Represent decimal part of digit number.
  var outputCharacters; // The output result.
  var parts;
  var digits, radices, bigRadices, decimals;
  var zeroCount;
  var i, p, d;
  var quotient, modulus;

// Validate input string:
  currencyDigits = currencyDigits.toString();
  if (currencyDigits == "") {
    return "不能为空";
  }
  if (currencyDigits.match(/[^,.\d]/) != null) {
    return "请输入数字";
  }
  if ((currencyDigits).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {
    return "请输入完整的数字";
  }

// Normalize the format of input digits:
  currencyDigits = currencyDigits.replace(/,/g, ""); // Remove comma delimiters.
  currencyDigits = currencyDigits.replace(/^0+/, ""); // Trim zeros at the beginning.
// Assert the number is not greater than the maximum number.
  if (Number(currencyDigits) > MAXIMUM_NUMBER) {
    return "数字过大";
  }

// Process the coversion from currency digits to characters:
// Separate integral and decimal parts before processing coversion:
  parts = currencyDigits.split(".");
  if (parts.length > 1) {
    integral = parts[0];
    decimal = parts[1];
// Cut down redundant decimal digits that are after the second.
    decimal = decimal.substr(0, 2);
  }
  else {
    integral = parts[0];
    decimal = "";
  }
// Prepare the characters corresponding to the digits:
  digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE);
  radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND);
  bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION);
  decimals = new Array(CN_TEN_CENT, CN_CENT);
// Start processing:
  outputCharacters = "";
// Process integral part if it is larger than 0:
  if (Number(integral) > 0) {
    zeroCount = 0;
    for (i = 0; i < integral.length; i++) {
      p = integral.length - i - 1;
      d = integral.substr(i, 1);
      quotient = p / 4;
      modulus = p % 4;
      if (d == "0") {
        zeroCount++;
      }
      else {
        if (zeroCount > 0) {
          outputCharacters += digits[0];
        }
        zeroCount = 0;
        outputCharacters += digits[Number(d)] + radices[modulus];
      }
      if (modulus == 0 && zeroCount < 4) {
        outputCharacters += bigRadices[quotient];
      }
    }
    outputCharacters += CN_DOLLAR;
  }
// Process decimal part if there is:
  if (decimal != "") {
    for (i = 0; i < decimal.length; i++) {
      d = decimal.substr(i, 1);
      if (d != "0") {
        outputCharacters += digits[Number(d)] + decimals[i];
      }
    }
  }
// Confirm and return the final output string:
  if (outputCharacters == "") {
    outputCharacters = CN_ZERO + CN_DOLLAR;
  }
  if (decimal == "") {
    outputCharacters += CN_INTEGER;
  }
  outputCharacters = outputCharacters;
  return outputCharacters;
}


// hoverDelay
(function ($) {
  $.fn.hoverDelay = function (options) {
    var defaults = {
      hoverDuring: 200,
      outDuring: 200,
      hoverEvent: function () {
        $.noop();
      },
      outEvent: function () {
        $.noop();
      }
    };
    var sets = $.extend(defaults, options || {});
    var hoverTimer, outTimer, that = this;
    return $(this).each(function () {
      $(this).hover(function () {
        clearTimeout(outTimer);
        hoverTimer = setTimeout(function () {
          sets.hoverEvent.apply(that)
        }, sets.hoverDuring);
      }, function () {
        clearTimeout(hoverTimer);
        outTimer = setTimeout(function () {
          sets.outEvent.apply(that)
        }, sets.outDuring);
      });
    });
  }
})(jQuery);

//ie
function placeHolderIE() {
  $('input[placeholder],textarea[placeholder]').each(function (index, el) {
    var that = this;
    var holderName = $(this).attr('placeholder');
    var _placeTemp = $('<div class="placeTemp">' + holderName + '<div>');
    var inputPadLeft = $(this).css('paddingLeft');
    var inputPadTop = $(this).css('paddingTop');
    var inputHeight = $(this).css('height');
    var inputLineHeight = $(this).css('lineHeight');

    if ($(this).siblings('.placeTemp').length > 0) {
      return;
    }
    else {
      _placeTemp.insertAfter($(this)).css({
        'lineHeight': inputLineHeight,
        'height': inputHeight
      });
    }
    _placeTemp.pin({
      base: $(that),
      selfXY: [0, 0],
      baseXY: [inputPadLeft, inputPadTop]
    })

    _placeTemp.click(function (event) {
      $(this).hide();
      $(that).focus();
    });

    if ($(this).val() != '') {
      _placeTemp.hide();
    }
    ;

    $(this).focus(function (event) {
      $(this).siblings('.placeTemp').hide();
    });
    $(this).keyup(function (event) {
      var that = this
      //若前后有空格
      if ($.trim($(that).val()) == '') {
        $(this).siblings('.placeTemp').show();
      }
      else {
        $(this).siblings('.placeTemp').hide();
      }
      ;
      //tab输入
      if (event.keyCode == 9) {
        $(this).trigger('focus');
      } else {
        if ($.trim($(that).val()) == '') {
          $(this).siblings('.placeTemp').show();
        }
        else {
          $(this).siblings('.placeTemp').hide();
        }
        ;
      }
      ;
    });
    $(this).blur(function (event) {
      $(this).trigger('keyup');
    });
  });
}
