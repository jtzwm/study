define([], function() {

    /**
     * Init router, that handle page events
     */
    function init() {
        $$(document).on('pageBeforeInit', function(e) {
            var page = e.detail.page;
            load(page.name, page.query);
        });

        // $$('.logout').on('click', GS.logout);
        // $$('.version').on('click', GS.checkUpdate);

        // if (!GS.isLogin()) {
        // 	// mainView.loadPage('login.html');
        // 	mainView.loadPage(GS.startPage, false);
        // } else {
        // 	var currentUser = GS.getCurrentUser();
        // 	mainView.loadPage(currentUser.node + '.html');
        // }
    }

    /**
     * Load (or reload) controller from js code (another controller) - call it's init function
     * @param  controllerName
     * @param  query
     */
    function load(controllerName, query) {
        if (!controllerName) {
            return;
        }
        if (controllerName.indexOf('smart-select') !== -1) {
            return;
        }
        require(['controllers/' + controllerName + 'Controller'], function(controller) {
            controller.init(query);
        });
    }

    return {
        init: init,
        load: load
    };
});
