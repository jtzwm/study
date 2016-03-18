define(['utils'], function (Utils) {
    function render(params) {
    	var template = $$('#indexTemplate').html();
		var compiledTemplate = Template7.compile(template);
		var renderTemplate = compiledTemplate({model: params.model});

		$$('#indexContent').append(renderTemplate);
        Utils.bindEvents(params.bindings);
    }
    return {
        render: render
    };
});