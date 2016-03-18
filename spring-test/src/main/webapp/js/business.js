$(function(){
	$(".business_items .title_left").each(function(){
	    var siblingsHeight = $(this).siblings('.list').outerHeight(true)-16;
	    $(this).css('height',siblingsHeight)
	});
	$('.business_items .list li').on('click','.busi_question',function(){
		var dlg = new Dialog(undefined,{
			needDestroy:true,
			hasBtn:true,
			btnText:['关闭'],
			btnRole:['cancel'],
			content:'<div class="dlg_wrap"><textarea class="busi_intro">创业板，又称二板市场（Second-board Market）即第二股票交易市场，是与主板市场（Main-Board Market）不同的一类证券市场，专为暂时无法在主板上市的创业型企业、中小企业和高科技产业企业等需要进行融资和发展的企业提供融资途径和成长空间的证券交易市场，是对主板市场的重要补充，在资本市场有着重要的位置。在中国的创业板的市场代码是300开头的。创业板与主板市场相比，上市要求往往更加宽松，主要体现在成立时创业板，又称二板市场（Second-board Market）即第二股票交易市场，是与主板市场（Main-Board Market）不同的一类证券市场，专为暂时无法在主板上市的创业型企业、中小企业和高科技产业企业等需要进行融资和发展的企业提供融资途径和成长空间的证券交易市场，是对主板市场的重要补充，在资本市场有着重要的位置。在中国的创业板的市场代码是300开头的。创业板与主板市场相比，上市要求往往更加宽松，主要体现在成立时</textarea></div>'
		})
		dlg.show()
	})
	$('.business_items .list li').each(function(index, el) {
		var that = this
		$(this).find('.busi_list_mask').width($(that).outerWidth()+'px')
		$(this).hover(function() {
			$(this).find('.busi_list_mask').stop().animate({'top':0},200);
		}, function() {
			$(this).find('.busi_list_mask').stop().animate({'top':'-58px'},200);
		});
	});
})