define([ 'utils'], function(Utils) {

	var bindings = [ {		 
		element : '#buyBtn',
		event : 'click',
		handler : test
	} ];

	function init() {
		console.log("tradeController:init");
		Utils.bindEvents(bindings);

	}
	
	function test(){
		console.log("tradeController:test by click event");
	}

	return {
		init: init
	};
});