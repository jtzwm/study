define([ 'utils'], function(Utils) {

	var bindings = [ {		 
		element : '#openAccountBtn',
		event : 'click',
		handler : openAccount
	} ];
	
	function init() {
		console.log("indexController:init");
		Utils.bindEvents(bindings);
		
	}
	
	function openAccount() {
		console.log("indexController:openAccount");
		   mainView.router.loadPage('openAccount.do');
		
	}

	return {
		init: init
	};
});