define([ 'utils', 'views/revealView' ], function(Utils, View) {
	"use strict";
	var bindings = [ {
		element : '#revealNext',
		event : 'click',
		handler : goVideo
	}, {
		element : '.reveal-back',
		event : 'click',
		handler : function() {
			mainView.reloadPage('returnRisk.do' + '?rnd=' + new Date().getTime());
		}
	}, {
		element : '#scrollContent',
		event : 'scroll',
		handler : scrollToEnd
	}, {
		element : '#agree',
		event : 'change',
		handler : iAgree
	} ];

	var timer;
	var agreementInfo = {};

	var isScrollToEnd = false;
	var isTimeEnd = false;
	var isCheckAgree = false;

	function init() {
		isScrollToEnd = false;
		isTimeEnd = false;
		isCheckAgree = false;
		initAgreement();
		View.render();
		Utils.bindEvents(bindings);
	}

	function initAgreement() {
		var url = "getAgreement.do";
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
					agreementInfo.protocolContent = data.model.protocol_content;
					agreementInfo.protocolId = data.model.protocol_id;
					agreementInfo.protocolMd5 = data.model.protocol_md5;
					agreementInfo.protocolName = data.model.protocol_name;
					agreementInfo.protocolVersion = data.model.protocol_version;
					agreementInfo.protocolRemark = data.model.protocol_remark;
					$$('#protocolName').html(agreementInfo.protocolName);
					$$('#protocolContent').html(agreementInfo.protocolContent);
					var readTime = data.model.readTime;
					countDown(readTime, 0);
					checkScroll();
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

	function checkScroll() {
		var scrollContent = $$('#scrollContent')[0];
		if (scrollContent.scrollHeight <= scrollContent.clientHeight) {
			isScrollToEnd = true;
		}
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
			timer = setTimeout(function() {
				countDown(setTime, curTime);
			}, 1000);
		} else {
			isTimeEnd = true;
			$$('#countdown').remove();
			reset();
		}
	}

	function reRender(leftTime) {
		$$('#countdown').html('（' + leftTime + 's）');
	}

	function reset() {
		if (isScrollToEnd && isTimeEnd && isCheckAgree) {
			$$('#revealNext').removeClass('disabled');
		}
	}

	function scrollToEnd() {
		if (this.scrollHeight <= (this.scrollTop + this.clientHeight)) {
			isScrollToEnd = true;
			reset();
		}
	}

	function iAgree() {
		var isChecked = $$(agree).prop('checked');
		if (isChecked) {
			isCheckAgree = true;
			reset();
		} else {
			isCheckAgree = false;
			$$('#revealNext').addClass('disabled');
		}
	}

	function goVideo() {
		var url = "sginAgreement.do";
		App.showPreloader();
		$$.ajax({
			url : url + "?rnd=" + new Date().getTime(), // 请求的URL
			type : 'POST', // 请求方式，get或post
			charset : 'UTF-8',
			data : {
				'protocolId' : agreementInfo.protocolId,
				'protocolName' : agreementInfo.protocolName,
				'protocolMd5' : agreementInfo.protocolMd5,
				'plainText' : agreementInfo.protocolMd5
			},
			success : function(data) {
				App.hidePreloader();
				data = JSON.parse(data);
				console.log(data);
				var errorNo = data.error_no;
				if (errorNo == 0) {
					mainView.reloadPage('/goVideo.do' + '?rnd=' + new Date().getTime());
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

	return {
		init : init
	};
});