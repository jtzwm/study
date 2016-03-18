define([ 'views/finishView' ], function(View) {
	"use strict";

	var code = 0;// 0成功，1待审核，2失败
	var isSuccess = [ 'success', 'wait', 'fail' ];
	function render() {
		switch (isSuccess[code]) {
		case 'success':
			// 更改提示图标
			$$('.info i')[0].className = 'icon-right';
			// 更改提示文字
			$$('.text-hint').text('签约完成');
			// 更改错误提示信息状态
			$$('.wrong-message').hide();
			// 更改重做按钮状态
			$$('.reve').hide();
			break;
		case 'wait':
			$$('.info i')[0].className = 'icon-tip';
			$$('.text-hint').text('待审核');
			$$('.wrong-message').show();
			$$('.reve').show().text('刷新');
			$$('.reve').click(function() {
				mainView.reloadPage('showAudit.do' + '?rnd=' + new Date().getTime());
			});
			break;
		case 'fail':
			$$('.info i')[0].className = 'icon-wrong';
			$$('.text-hint').text('签约失败');
			$$('.wrong-message').show();
			$$('.reve').show();
			$$('.reve').click(function() {
				resetAppoint();
			});
			break;
		}
	}

	function init() {
		View.render();
		$$('#finishBack').click(function() {
			mainView.reloadPage('goPreengageList.do' + '?rnd=' + new Date().getTime());
		});
		initInfo();
		setFinish();
	}

	function initInfo() {
		var url = "getAuditInfo.do";
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
					var state = data.model.witness_state;
					switch (state) {
					case 'SUCCESS':
						code = 0;
						break;
					case 'APPLYING':
						code = 1;
						$$('.wrong-message').html('我们会在' + data.model.waitDays + '个工作日内完成审核');
						break;
					case 'NEED_MODIFY':
						code = 2;
						$$('.wrong-message').html('原因：' + data.model.reject_reasons);
						break;
					default:
						code = 1;
						$$('.wrong-message').html('我们会在' + data.model.waitDays + '个工作日内完成审核');
						break;
					}
					$$('.project-name').html(data.model.project_name);
					$$('.project-money').html((data.model.contract_amount) + '万元');
					$$('.name').html(data.model.client_name);
					$$('.id-code').html(data.model.identify_code);
					$$('.mobile').html(data.model.mobile);
					$$('.telephone').html(data.model.telephone);
					render();
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

	// 生成审核任务
	function setFinish() {
		var url = "setFinish.do";
		$$.ajax({
			url : url + "?rnd=" + new Date().getTime(), // 请求的URL
			type : 'POST', // 请求方式，get或post
			data : {},
			success : function(data) {
				data = JSON.parse(data);
				console.log(data);
			},
			error : function() {
			},
			timeout : function() {
			}
		});
	}

	// 重新预约
	function resetAppoint() {
		var url = "resetFlowStatus.do";
		App.showPreloader();
		$$.ajax({
			url : url + "?rnd=" + new Date().getTime(), // 请求的URL
			type : 'GET', // 请求方式，get或post
			success : function(data) {
				App.hidePreloader();
				data = JSON.parse(data);
				console.log(data);
				if (data.error_no == 0) {
					mainView.reloadPage('returnAppoint.do' + '?rnd=' + new Date().getTime());
				} else {
					App.alert(data.error_info);
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
