define([], function () {

	/**
	 * Bind DOM event to some handler function in controller
	 * @param  {Array} bindings
	 */
	function bindEvents(bindings) {
		if ($$.isArray(bindings) && bindings.length > 0) {
			for (var i in bindings) {
				$$(bindings[i].element).on(bindings[i].event, bindings[i].handler);
			}
		}
	}

	/**
	 * Set the position of buttons, which queried by selector, dynamic
	 * @param {String} selector
	 */
	function setButtonPosition(selector) {
		var pageContent = $$(selector).parents('.page-content');
		$$(selector).removeClass('fixed-bottom');
		if (!isScroll(pageContent[0])) {
			$$(selector).addClass('fixed-bottom');
		}
	}

	/**
	 * Detect whether the element has scrollbar
	 * @param  {HTMLElement}  elem
	 * @return {Boolean}      true: has scrollbar; false: hasn't
	 */
	function isScroll(elem) {
		return elem.scrollHeight > elem.clientHeight;
	}


	/**
	 * 模拟 fixed 定位按钮
	 * @param  {string} selector 模板的选择器
	 */
	function fixedButton(selector) {
		var template = $$(selector).html();
		var compiledTemplate = Template7.compile(template);
		var renderTemplate = compiledTemplate();

		$$('.toolbar-btm').html('<div class="toolbar-inner">' + renderTemplate + '</div>');
		console.log(renderTemplate);
		mainView.showToolbar();
	}

	return {
		bindEvents: bindEvents,
		setButtonPosition: setButtonPosition,
		fixedButton: fixedButton
	};
});
