define([], function() {

    /**
     * Init router, that handle page events
     */
    function init() {
    	
        $$(document).on('pageBeforeInit', function(e) {
        	
            var page = e.detail.page;
            alert("router.init==="+page.name);
            load(page.name, page.query);
        });
    }

    /**
     * Load (or reload) controller from js code (another controller) - call it's init function
     * @param  controllerName
     * @param  query
     */
    function load(jsFileName, query) {
    	alert("router.load==="+jsFileName);
        if (!jsFileName) {
            return;
        }
        if (jsFileName.indexOf('smart-select') !== -1) {
            return;
        }
        require([jsFileName], function(controller) {
            controller.init();
        });
    }

    return {
        init: init,
        load: load
    };
});
