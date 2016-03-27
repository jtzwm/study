require.config({
	baseUrl: 'js/f7test',
	paths: {
		Framework7: '/h5test/js/libs/framework7',
		TestJs: 'test'

	},
	shim: {
		Framework7: {
			exports: 'Framework7'
		},
		TestJs: {
			exports: 'init'
		}
	}

});


require(['Framework7', 'router','test'], function(Framework7, Router,TestJs) {
	//alert("fuck");
	window.$$ = window.Dom7;
	//window.jtoJHandle.removeCookie();
	window.App = new Framework7();

	window.mainView = window.App.addView('.view-main', {
		dynamicNavbar: true
	});

	init();

//	require(['trade'], function(Trade) {
//		Trade.init();
//	});
//	Router.init();
});