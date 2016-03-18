define([], function() {
	"use strict";

	var startDateTime;
	var endDateTime;
	var calendarTime;
	var riskLimitTime;
	var appointLimitTime;
	var timer;

	function init() {
		mainView.hideToolbar();
		initParams();
		$$('.prepare .modify').click(function() {
			mainView.reloadPage('returnAppoint.do' + '?rnd=' + new Date().getTime());
		});
		$$('#fiftyBack').click(function() {
			mainView.reloadPage('returnProfile.do' + '?rnd=' + new Date().getTime());
		});
		getDateTime();
	}

	function initParams() {
		riskLimitTime = $$('#riskLimitTime').val();
		appointLimitTime = $$('#appointLimitTime').val();
	}

	function getDateTime() {
		var url = "getVideoDate.do";
		App.showPreloader();
		$$.ajax({
			url : url + "?rnd=" + new Date().getTime(), // 请求的URL
			type : 'POST', // 请求方式，get或post
			data : {},
			success : function(data) {
				App.hidePreloader();
				data = JSON.parse(data);
				console.log(data);
				var errorNo = data.error_no;
				if (errorNo == 0) {
					startDateTime = new Date(data.model.startDateTime);
					endDateTime = new Date(data.model.endDateTime);
					calendarTime = data.model.calendar_time;
					checkTime();
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

	function checkTime() {
		var now = new Date();
		var betweenTime = startDateTime - now;
		var days = Math.floor(betweenTime / (24 * 60 * 60 * 1000));
		var leaveDay = betweenTime % (24 * 60 * 60 * 1000);
		var hours = Math.floor(leaveDay / (60 * 60 * 1000));
		var leaveHour = leaveDay % (60 * 60 * 1000);
		var minutes = Math.floor(leaveHour / (60 * 1000))
		var leaveMinute = leaveHour % (60 * 1000);
		var seconds = Math.round(leaveMinute / 1000);
		var timeStr = (startDateTime.getMonth() + 1) + '月' + (startDateTime.getDate()) + "日 " + calendarTime;
		var checkAgain = false;
		$$('#waitTimeStr').html(timeStr);
		// 判断是否能风险评测
		if (betweenTime > riskLimitTime * 60 * 1000) {
			var betweenTimeStr = '';
			if (days > 0) {
				betweenTimeStr = days + '天' + hours + '小时';
			} else if (days == 0 && hours > 0) {
				betweenTimeStr = hours + '小时' + minutes + '分';
			} else {
				betweenTimeStr = minutes + '分' + seconds + '秒';
			}
			$$('.wast-time').html(betweenTimeStr);
			checkAgain = true;
		} else if (betweenTime <= riskLimitTime * 60 * 1000 && (endDateTime - now) > 0) {
			start();
		} else {
			end();
		}
		// 判断是否能修改时间
		if (betweenTime < appointLimitTime * 60 * 1000 && (endDateTime - now) > 0) {
			$$('.prepare .modify').hide();
		} else {
			$$('.prepare .modify').removeAttr('style');
		}
		// 是否继续定时检查时间状态
		if (checkAgain) {
			timer = setTimeout(function() {
				checkTime();
			}, 1000);
		}
	}

	function start() {
		$$('.prepare .wait-time').addClass('start').find('.wast-time').text("开始风险评测");
		$$('.countdown').hide();
		if (timer != null) {
			clearTimeout(timer);
		}
		mainView.reloadPage('goRisk.do' + '?rnd=' + new Date().getTime());
	}

	function end() {
		$$('.prepare .wait-time').removeClass('start').find('.wast-time').text("预约过期");
		$$('.countdown').show();
		$$('.countdown').html('预约已过期，请重新预约。');
		if (timer != null) {
			clearTimeout(timer);
		}
	}

	return {
		init : init
	};
});