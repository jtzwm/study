define([ 'utils'], function(Utils) {

	var bindings = [ {		 
		element : '#btnOpenAccount',
		event : 'click',
		handler : openAccount
	} ];

	function init() {
		console.log("openAccountController:init");
		Utils.bindEvents(bindings);

	}
	
	function jtojOpenAndroidActivity(){
		
	}
	
	function openAccount(){
		console.log("openAccountController:test by click event");
	}

	return {
		init: init
	};
});