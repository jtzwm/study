require.config({
	baseUrl: 'template-3.0/js',
    paths: {
        Framework7: 'libs/framework7',
        text: 'libs/text',
    },
    shim: {
        Framework7: {
            exports: 'Framework7'
        }
    }
});

require(['Framework7', 'router'], function (Framework7, Router) {
    window.$$ = window.Dom7;

    var device = Framework7.prototype.device;

    if (device.android) {
        window.App = new Framework7({
            cache: true,
            pushState: true,
            swipeBackPage: false,
            preloadPreviousPage: false,
            popupCloseByOutside: false,
            animateNavBackIcon: true,
            modalTitle: '系统消息',
            modalButtonOk: '确定',
            modalButtonCancel: '取消',
            smartSelectBackText: '完成',
            smartSelectBackTemplate: '<div class="left sliding"><a href="#" class="back link"><i class="icon icon-back"></i><span>{{backText}}</span></a></div>'
        });
    } else {
        window.App = new Framework7({
            cache: true,
            pushState: true,
            swipeBackPage: false,
            preloadPreviousPage: false,
            popupCloseByOutside: false,
            animateNavBackIcon: true,
            modalTitle: '系统消息',
            modalButtonOk: '确定',
            modalButtonCancel: '取消',
            smartSelectBackText: '完成',
            smartSelectBackTemplate: '<div class="left sliding"><a href="#" class="back link"><i class="icon icon-back"></i><span>{{backText}}</span></a></div>'
        });
    }

    window.mainView = window.App.addView('.view-main', {
        dynamicNavbar: true
    });

    Router.init();
    Router.load('index');
});
