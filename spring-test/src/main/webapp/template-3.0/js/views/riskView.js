define(['utils'], function (Utils) {

	function render(params) {
		var template = $$('#riskTemplate').html();
		var compiledTemplate = Template7.compile(template);
		var renderTemplate = compiledTemplate({model: params.model});
		$$('#riskContent').append(renderTemplate);
		Utils.fixedButton('#riskBtnFix');
		Utils.bindEvents(params.bindings);
	}

	return {
		render: render,
	};
});
