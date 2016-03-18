/**
 * Created by qi on 2015/7/14.
 */
define([ 'views/indexView', 'utils' ], function(View, Utils) {
	"use strict";
	var bindings = [ {
		element : '.index .item',
		event : 'click',
		handler : loginPreengage
	}, {
		element : '#search_context',
		event : 'change',
		handler : search
	}, {
		element : '.logout',
		event : 'click',
		handler : function() {
			window.jtoJHandle.closeSJKH();
		}
	}, {
		element : '#clearcache',
		event : 'click',
		handler : function() {
			window.jtoJHandle.removeCookie();
		}
	}, {
		element:'#indexBack',
		event:'click',
		handler : function() {
			window.jtoJHandle.closeSJKH();
		}
	} ];

	function loginPreengage() {
		var authCode = $$(this).find('.authCode').val();
		var preengageNo = $$(this).find('.preengageNo').val();
		var url = "toLogin.do";
		App.showPreloader();
		$$.ajax({
			url : url + "?rnd=" + new Date().getTime(), // 请求的URL
			type : 'GET', // 请求方式，get或post
			contentType : '',
			data : {
				"authCode" : authCode,
				"preengageNo" : preengageNo
			},
			success : function(data) {
				App.hidePreloader();
				data = JSON.parse(data);
				console.log(data);
				var errorNo = data.error_no;
				if (errorNo == 0) {
					mainView.reloadPage('/goAppoint.do' + '?rnd=' + new Date().getTime());
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

	function search() {
		var search_context = $$(this).val();

	}

	function sub() {
		$$('.select').click(function(e) {
			e.stopPropagation();
			$$('.submenu').show();
		});
		$$('.submenu li').click(function(e) {
			e.stopPropagation();
			$$('.selected').text($$(this).text());
			$$('.submenu').hide();
		});
		$$('document').click(function() {
			$$('.submenu').hide();
		});
	}

	function init() {
		mainView.hideToolbar();
		Utils.bindEvents(bindings);
		jtoJHandle.getEmpNo('getList');
		//getList('001411');
		sub();
	}

	/**
	 * 获取预约列表
	 */
	window.getList = function(operatorId) {
		if (operatorId == '') {
			App.alert("获取员工号失败");
		}
		var url = "getPreengageList.do";
		App.showPreloader();
		$$.ajax({
			url : url + "?rnd=" + new Date().getTime(), // 请求的URL
			type : 'POST', // 请求方式，get或post
			data : {
				'operatorId' : operatorId
			},
			success : function(data) {
				App.hidePreloader();
				data = JSON.parse(data);
				console.log(data);
				var errorNo = data.error_no;
				if (errorNo == 0) {
					var model = {
						'list' : data.model.resultList
					};
					View.render({
						bindings : bindings,
						model : model
					});
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