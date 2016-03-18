define([], function() {

	var startDateTime;
	var endDateTime;
	var calendarTime;
	var timer;

	var videoServer;
	var videoPort;
	var roomId;
	var userName;

	var appointLimitTime;
	var isEnd;

	window.startBtn;
	window.overdueBtn;
	window.failedBtn;
	window.errorBtn;
	window.waitBtn;

	function init() {
		mainView.hideToolbar();
		window.APP_video.videoStatus = 0;
		isEnd = false;
		initVideoInfo();
		initBtns();
		$$('.prepare .modify').click(function() {
			if (timer != null) {
				clearTimeout(timer);
			}
			mainView.reloadPage('returnAppoint.do' + '?rnd=' + new Date().getTime());
		});
		$$('#perpareback').click(function() {
			if (timer != null) {
				clearTimeout(timer);
			}
			mainView.reloadPage('returnAgreement.do' + '?rnd=' + new Date().getTime());
		});
		getDateTime();
	}

	function initBtns() {
		startBtn = $$("#startVideo");
		overdueBtn = $$("#overdue");
		failedBtn = $$("#videoFailed");
		errorBtn = $$("#videoError");
		waitBtn = $$("#waitVideo");
		startBtn.click(function() {
			jtoJHandle.startOnlineVideo(videoServer, videoPort, userName, roomId);
		});
		overdueBtn.click(function() {
			if (timer != null) {
				clearTimeout(timer);
			}
			mainView.reloadPage('returnAppoint.do' + '?rnd=' + new Date().getTime());
		});
		failedBtn.click(function() {
			if (timer != null) {
				clearTimeout(timer);
			}
			mainView.reloadPage('/returnProfile.do' + '?rnd=' + new Date().getTime());
		});
		errorBtn.click(function() {
			jtoJHandle.startOnlineVideo(videoServer, videoPort, userName, roomId);
		})
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
		$$('#timeStr').html(timeStr);
		// 判断是否能视频
		if (betweenTime > 0) {
			waitBtn.show();
			startBtn.hide();
			overdueBtn.hide();
			failedBtn.hide();
			errorBtn.hide();
			var betweenTimeStr = '';
			if (days > 0) {
				betweenTimeStr = days + '天' + hours + '小时';
			} else if (days == 0 && hours > 0) {
				betweenTimeStr = hours + '小时' + minutes + '分';
			} else {
				betweenTimeStr = minutes + '分' + seconds + '秒';
			}
			waitBtn.find('.wast-time').html(betweenTimeStr);
			checkAgain = true;
		} else if (betweenTime <= 0 && (endDateTime - now) > 0) {
			// 视频未打开
			if (window.APP_video.videoStatus == 0) {
				start();
				// 视频验证意外中断
			} else if (window.APP_video.videoStatus == 1) {
				window.APP_video.videoRedo();
				// 视频验证失败
			} else {
				window.APP_video.videoFail();
			}
			checkAgain = true;
		} else {
			end();
		}
		// 判断是否能修改时间
		var appointLimitTime = $$('#appointLimitTime').val();
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
		waitBtn.hide();
		startBtn.show();
		overdueBtn.hide();
		failedBtn.hide();
		errorBtn.hide();
		$$('.countdown').hide();
	}

	function end() {
		isEnd = true;
		waitBtn.hide();
		startBtn.hide();
		failedBtn.hide();
		errorBtn.hide();
		overdueBtn.show();
		$$('.countdown').show();
		$$('.countdown').html('预约已过期，请重新预约。');
		if (timer != null) {
			clearTimeout(timer);
		}
	}

	function initVideoInfo() {
		videoServer = $$('#videoServer').val();
		videoPort = parseInt($$('#videoPort').val());
		// videoServer = 'demo.anychat.cn';
		// videoPort = 8906;
		var contractId = parseInt($$('#contractId').val());
		roomId = 10000000 + contractId;
		userName = 'user' + roomId;
	}

	window.APP_video = {};
	// 0正常，1意外断开，2验证失败
	window.APP_video.videoStatus = 0;
	window.APP_video.finishVideo = function(type, message) {
		// 验证成功
		if (type == 1) {
			window.APP_video.videoSuccess();
			// 意外断开
		} else if (type == 2) {
			window.APP_video.videoStatus = 1;
			window.APP_video.videoRedo()
			// 验证失败
		} else if (type == 3) {
			window.APP_video.videoStatus = 2;
			window.APP_video.videoFail(message)
			// 网络问题，连接服务器失败
		} else if (type == 4) {
			window.APP_video.videoStatus = 1;
			window.APP_video.videoRedo()
		}
	}

	window.APP_video.videoSuccess = function() {
		if (timer != null) {
			clearTimeout(timer);
		}
		mainView.reloadPage('showAudit.do' + '?rnd=' + new Date().getTime());
	}

	window.APP_video.videoFail = function(message) {
		if (timer != null) {
			clearTimeout(timer);
		}
		failedBtn.show();
		waitBtn.hide();
		startBtn.hide();
		errorBtn.hide();
		overdueBtn.hide();
		$$('.countdown').show();
		$$('.countdown').html("视频验证不通过，原因：" + message);
	}

	window.APP_video.videoRedo = function() {
		if (!isEnd) {
			failedBtn.hide();
			waitBtn.hide();
			startBtn.hide();
			errorBtn.show();
			overdueBtn.hide();
			$$('.countdown').show();
			$$('.countdown').html("网络异常，请重新连接。");
		}
	}

	return {
		init : init
	};
});