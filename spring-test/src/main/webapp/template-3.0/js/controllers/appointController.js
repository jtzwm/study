define([ 'utils', 'views/appointView' ], function(Utils, View) {
	"use strict";
	var bindings = [ {
		element : '#nextPass',
		event : 'click',
		handler : submitTime
	}, {
		element : '.appoint-back',
		event : 'click',
		handler : appointBack
	}, {
		element : '#dayStr',
		event : 'change',
		handler : getTimeList
	} ];

	function init() {
		View.render();
		Utils.bindEvents(bindings);
		getValidDay();
	}

	function cal(minDate, maxDate) {
		var monthNames = [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ];
		var dayNamesShort = [ '日', '一', '二', '三', '四', '五', '六' ];
		var calendarInline = App.calendar({
			input : '#dayStr',
			container : '#calendar-inline-container',
			value : [ minDate ],
			dayNamesShort : dayNamesShort,
			minDate : new Date(new Date().getTime() - 1 * 24 * 60 * 60 * 1000),
			maxDate : maxDate,
			dateFormat : 'yyyymmdd',
			toolbarTemplate : '<div class="toolbar calendar-custom-toolbar">' + '<div class="toolbar-inner">' + '<div class="left">'
					+ '<a href="#" class="link icon-only"><i class="icon icon-back"></i></a>' + '</div>' + '<div class="center"></div>' + '<div class="right">'
					+ '<a href="#" class="link icon-only"><i class="icon icon-forward"></i></a>' + '</div>' + '</div>' + '</div>',
			onOpen : function(p) {
				$$('.calendar-custom-toolbar .center').text(monthNames[p.currentMonth] + ', ' + p.currentYear);
				$$('.calendar-custom-toolbar .left .link').on('click', function() {
					calendarInline.prevMonth();
				});
				$$('.calendar-custom-toolbar .right .link').on('click', function() {
					calendarInline.nextMonth();
				});
			},
			onMonthYearChangeStart : function(p) {
				$$('.calendar-custom-toolbar .center').text(monthNames[p.currentMonth] + ', ' + p.currentYear);
			},
			onDayClick : function(p, dayContainer, year, month, day) {
			}
		});

		$$('#picker-time').on('click', function() {
			app.pickerModal('.picker-time');
		});

		$$('.close-picker').on('click', function() {
			var pickedTime = document.forms[0].appointment.value;
			$$('#picker-time').val(pickedTime);
		});

	}

	function choose() {
		var items = $$('.choose-time .item');
		$$(items).click(function() {
			if (!$$(this).hasClass('disable')) {
				$$(items).removeClass('active');
				$$(this).addClass('active');
			}
		});
	}

	/**
	 * 获取有效日期
	 */
	function getValidDay() {
		var url = "getDayList.do";
		App.showPreloader();
		$$.ajax({
			url : url + "?rnd=" + new Date().getTime(), // 请求的URL
			type : 'POST', // 请求方式，get或post
			data : {
				'dateStr' : dayStr
			},
			success : function(data) {
				App.hidePreloader();
				data = JSON.parse(data);
				console.log(data);
				var errorNo = data.error_no;
				if (errorNo == 0) {
					var list = data.model.resultList;
					var minDateStr = list[0].calendar_date + "";
					var maxDateStr = list[list.length - 1].calendar_date + "";
					var minDate = strToDate(minDateStr);
					var maxDate = strToDate(maxDateStr);
					cal(minDate, maxDate);
					initStatus();
				} else {
					var errorInfo = data.error_info
					App.alert(errorInfo);
				}
			},
			error : function() {
				App.hidePreloader();
				App.alert(MESSAGE_TIMEOUT);
			},
			timeout : function() {
				App.hidePreloader();
				App.alert(MESSAGE_TIMEOUT);
			}
		});
	}

	/**
	 * 根据日期获取当日时间段信息列表
	 */
	function getTimeList() {
		var dayStr = $$('#dayStr').val();
		var url = "getTimeList.do";
		App.showPreloader();
		$$.ajax({
			url : url + "?rnd=" + new Date().getTime(), // 请求的URL
			type : 'POST', // 请求方式，get或post
			data : {
				'dateStr' : dayStr
			},
			success : function(data) {
				App.hidePreloader();
				data = JSON.parse(data);
				console.log(data);
				var errorNo = data.error_no;
				if (errorNo == 0) {
					var timeDiv = $$('.choose-time');
					var timeContent = '';
					var timeArray = data.model.resultList;
					for (var i = 0; i < timeArray.length; i++) {
						if (timeArray[i].surplus_seat == 0) {
							var timeItem = '<li class="item disable" data-time=' + timeArray[i].calendar_time + '><span class="time">' + timeArray[i].calendar_time
									+ '</span><span class="notfree">已满</span></li>';
						} else if (timeArray[i].isOverdue) {
							var timeItem = '<li class="item disable" data-time=' + timeArray[i].calendar_time + '><span class="time">' + timeArray[i].calendar_time
									+ '</span><span class="isfree"><span class="num">' + timeArray[i].surplus_seat + '</span>个空闲</span></li>';
						} else {
							var timeItem = '<li class="item" data-time=' + timeArray[i].calendar_time + '><span class="time">' + timeArray[i].calendar_time
									+ '</span><span class="isfree"><span class="num">' + timeArray[i].surplus_seat + '</span>个空闲</span></li>';
						}
						timeContent = timeContent + timeItem;
					}
					timeDiv.html(timeContent);
					choose();
				} else {
					var errorInfo = data.error_info
					App.alert(errorInfo);
				}
			},
			error : function() {
				App.hidePreloader();
				App.alert(MESSAGE_TIMEOUT);
			},
			timeout : function() {
				App.hidePreloader();
				App.alert(MESSAGE_TIMEOUT);
			}
		});
	}

	/**
	 * 设置预约时间
	 */
	function submitTime() {
		var checkTime = $$('.choose-time .active');
		if (checkTime.length == 0) {
			App.alert('请选择有效时间段');
			return;
		}
		var url = "setVideoTime.do";
		var dayStr = $$('#dayStr').val();
		var timeStr = checkTime.data('time');
		App.showPreloader();
		$$.ajax({
			url : url + "?rnd=" + new Date().getTime(), // 请求的URL
			type : 'GET', // 请求方式，get或post
			data : {
				'dateStr' : dayStr,
				'timeStr' : timeStr
			},
			success : function(data) {
				App.hidePreloader();
				data = JSON.parse(data);
				console.log(data);
				var errorNo = data.error_no;
				if (errorNo == 0) {
					mainView.reloadPage('goProfile.do' + '?rnd=' + new Date().getTime());
				} else {
					var errorInfo = data.error_info
					App.alert(errorInfo);
				}
			},
			error : function() {
				App.hidePreloader();
				App.alert(MESSAGE_TIMEOUT);
			},
			timeout : function() {
				App.hidePreloader();
				App.alert(MESSAGE_TIMEOUT);
			}
		});
	}

	function initStatus() {
		var status = $$('#status').val();
		switch (status) {
		case '4':
			App.alert("视频预约已过期，请重新预约");
			break;
		default:
			break;
		}
	}

	function appointBack() {
		mainView.reloadPage('returnIndex.do' + '?rnd=' + new Date().getTime());
	}

	function strToDate(dateStr) {
		var date = new Date(dateStr.substr(0, 4) + '-' + dateStr.substr(4, 2) + '-' + dateStr.substr(6, 2));
		return date;
	}

	return {
		init : init
	};
});