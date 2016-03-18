define([ 'views/riskView' ,'utils'], function(View,Utils) {
	"use strict";
	var bindings = [ {
		element : '.select .item',
		event : 'click',
		handler : choose
	}, {
		element : '.risk-next-button',
		event : 'click',
		handler : submitRisk
	}, {
		element : '.risk-back',
		event : 'click',
		handler : riskBack
	} ];

	var paperId;

	function init() {
		initRisk()
	}

	function initRisk() {
		App.showIndicator();
		$$.ajax({
			url : '/getRisk.do?rnd=' + new Date().getTime(),
			type : 'POST',
			success : function(data) {
				data = JSON.parse(data);
				App.hideIndicator();
				if (data.error_no == 0) {
					paperId = data.model.riskPaperId;
					var questions = [];
					var riskList = data.model.resultList;
					for (var i = 0; i < riskList.length; i++) {
						var risk = {};
						risk.index = i;
						risk.topic = riskList[i].question_content;
						if (riskList[i].question_kind == "1") {
							risk.multi = true;
						}
						risk.options = [];
						risk.question_no = riskList[i].question_no;
						for ( var p in riskList[i].answer_content) {
							var option = {};
							option.option = riskList[i].answer_content[p];
							option.value = p;
							option.question_no = riskList[i].question_no;
							if (riskList[i].cus_answer) {
								var optionNo = parseInt(p);
								var cusAnswer = riskList[i].cus_answer;
								if (cusAnswer.indexOf(optionNo) != -1) {
									option.checked = true;
								}
							} else {
								if (riskList[i].default_answer.indexOf(p) != -1) {
									option.checked = true;
								}
							}
							risk.options.push(option);
						}
						questions.push(risk);
					}
					var model = {
						'questions' : questions
					};
					View.render({
						bindings : bindings,
						model : model
					});
				} else {
					Utils.bindEvents(bindings);
					App.alert(data.error_info);
				}
			}
		});
	}

	function submitRisk() {
		var answers = formatAnswers();
		if (!answers) {
			return;
		}
		App.showIndicator();
		$$.ajax({
			url : '/submitRisk.do?rnd=' + new Date().getTime(),
			type : 'POST',
			data : {
				'paperAnswer' : answers,
				'paperId' : paperId
			},
			success : function(data) {
				data = JSON.parse(data);
				App.hideIndicator();
				if (data.error_no == 0) {
					App_riskResult.paperSource = data.model.paper_score;
					App_riskResult.corpRiskLevel = data.model.corp_risk_level;
					App_riskResult.riskLevelName = data.model.risk_level_name;
					App_riskResult.investAdvice = data.model.invest_advice;
					App_riskResult.minSource = data.model.exam_minimum_score;
					mainView.reloadPage('goRiskResult.do' + '?rnd=' + new Date().getTime());
				} else {
					App.alert(data.error_info);
				}
			}
		});
	}

	function choose() {
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
	}

	/**
	 * 格式化评测答案
	 * 
	 * @return {Object} 格式化后的数据
	 */
	function formatAnswers() {
		var questions = $$('#riskContent .question');
		var resultArray = [];
		for (var i = 0; i < questions.length; i++) {
			var answers = $$(questions[i]).find('.checked');
			if (answers.length == 0) {
				App.alert("第" + (i + 1) + "题没有选择答案");
				return;
			}
			var answerArry = [];
			for (var j = 0; j < answers.length; j++) {
				answerArry.push($$(answers[j]).data('answervalue'));
			}
			var result = $$(questions[i]).data('questionno') + '&' + answerArry.join('^');
			resultArray.push(result);
		}
		var answers = resultArray.join('|');
		answers += '|';
		return answers;

	}

	function riskBack() {
		mainView.reloadPage('returnProfile.do' + '?rnd=' + new Date().getTime());
	}
	return {
		init : init
	};
});