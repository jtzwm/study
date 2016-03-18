define(['utils'], function (Utils) {
    "use strict";

    function render(params) {
        Utils.fixedButton('#identityBtnFix');
    }

    return {
        render: render
    };
});