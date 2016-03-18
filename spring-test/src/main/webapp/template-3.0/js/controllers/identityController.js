define([ 'utils', 'views/identityView' ], function(Utils, View) {
	var bindings = [ {
		element : '#identityNext',
		event : 'click',
		handler : nextStep

	}, {
		element : '.identity-back',
		event : 'click',
		handler : identityBack
	} ];

	function init() {
		View.render();
		Utils.bindEvents(bindings);
	}

	function identityBack() {
		mainView.reloadPage('returnAppoint.do' + '?rnd=' + new Date().getTime());
	}

	function nextStep() {
		mainView.reloadPage('goWait.do' + '?rnd=' + new Date().getTime());
	}
	return {
		init : init
	};
});
