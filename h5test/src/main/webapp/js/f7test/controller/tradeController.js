define([ 'utils'], function(Utils) {

	var bindings = [ {		 
		element : '#buyBtn',
		event : 'click',
		handler : test
	} ];

	function init() {
		alert("4444444  trade.init");
		Utils.bindEvents(bindings);

	}
	
	function test(){
		alert("55555555555555");
	}

	return {
		init: init
	};
});