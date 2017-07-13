define([ 'utils'], function(Utils) {

	var bindings = [ {		 
		element : '#openAPPBtn',
		event : 'click',
		handler : openAPP
	} ];

	function init() {
		console.log("personal-center:init");
		Utils.bindEvents(bindings);

	}

	
	function openAPP(){
		//window.demo.openVideo();
		console.log("personal-centerController:test by click event");
		window.location = "cairenhui://zhuwm.host/h5";  
	}

	return {
		init: init
	};
});