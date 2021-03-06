function setCookie(name, value){var expdate = new Date();expdate.setTime(expdate.getTime() + 365 * 24 * 3600 * 1000);	document.cookie = name+"="+value+";expires="+expdate.toGMTString()+";path=/";}
function getCookie(c_name){if (document.cookie.length>0){c_start=document.cookie.indexOf(c_name + "=");if (c_start!=-1){ c_start=c_start + c_name.length+1;c_end=document.cookie.indexOf(";",c_start);if (c_end==-1) c_end=document.cookie.length;return unescape(document.cookie.substring(c_start,c_end));}};return "";}
document.write( "<title>视频坐席：" + getCookie( "nikeName" ) + "</title>" );
function $dom( domItem ){return document.getElementById( domItem );}

function pageInitializeSetting(){
	setConstParamDiv.style.display = "none";
	outCallDiv.style.display = "none";
	takePictureDiv.style.display = "none";
	qualityDiv.style.display = "none";
	adaptiveDiv.style.display = "none";
	$dom( "nubeNumber" ).innerHTML = getCookie( "myNumber" );
	$dom( "userName" ).innerHTML = getCookie( "nikeName" );
	
	var enc = getCookie( "encVideo" );
	var dec = getCookie( "decVideo" );
	enc = enc ? enc : 16;
	dec = dec ? dec : 16;
	var upBandWidth = getCookie( "upBandWidth" );
	var downBandWidth = getCookie( "downBandWidth" );
	upBandWidth = upBandWidth < 32 ? 32 : upBandWidth;
	downBandWidth = downBandWidth < 32 ? 32 : downBandWidth;
	var useAdaptive = getCookie( "userAdaptive" );
	useAdaptive = useAdaptive ? true : false;
	butelNetPhoneInit( 240, 276, "div", "butelPhoneContainerDiv", getCookie( "myNumber" ), enc, dec, downBandWidth, upBandWidth, useAdaptive, getCookie( "serviceType" ), getCookie( "serveUserCount" ), getCookie( "userProxy" ) );
	
	eps = new ExpressionIconList( "expressionDiv", 24, [30,30] );
	eps.flushPage();
	eps.hide();
	epsInput = new ExpressionInputArea( "inputDiv", 450, 45, [20,20] );
	
	upLoadFileComponent( "fileChooseDiv", 35, 25, "img/fileChoose.png", true );
}

function showConstParamDiv(){
	setConstParamDiv.style.display = "";
	$dom( "textfield1" ).value = getCookie( "nikeName" );
	$dom( "textfield2" ).value = getCookie( "myNumber" );
	$dom( "textfield3" ).value = getCookie( "deviceNumber" );
	$dom( "textfield4" ).value = getCookie( "appKey" );
	$dom( "textfield5" ).value = getCookie( "gateWay" );
	$dom( "textfield6" ).value = getCookie( "serveUserCount" );
	$dom( "textfield7" ).value = getCookie( "accessNum" );
	$dom( "checkbox" ).checked = getCookie( "userProxy" );
	$dom( "radio1" ).checked = false;
	$dom( "radio2" ).checked = false;
	$dom( "radio3" ).checked = false;
	var tampIndex = getCookie( "serviceType" );
	var tampRadio = $dom( "radio" + tampIndex );
	if( tampRadio )tampRadio.checked = "checked";
}

function hideConstParamDiv(){
	setConstParamDiv.style.display = "none";
}

function saveConstParam(){
	setCookie( "nikeName", $dom( "textfield1" ).value );
	setCookie( "myNumber", $dom( "textfield2" ).value );
	setCookie( "deviceNumber", $dom( "textfield3" ).value );
	setCookie( "appKey", $dom( "textfield4" ).value );
	setCookie( "gateWay", $dom( "textfield5" ).value );
	setCookie( "serveUserCount", Math.min( $dom( "textfield6" ).value, 9 ) );
	setCookie( "accessNum", $dom( "textfield7" ).value );
	setCookie( "userProxy", $dom( "checkbox" ).checked ? true : "" );	
	var tamp;
	if( $dom( "radio1" ).checked )tamp = 1;
	else if( $dom( "radio2" ).checked )tamp = 2;
	else if( $dom( "radio3" ).checked )tamp = 3;
	setCookie( "serviceType", tamp );
	hideConstParamDiv();
	alert( "请刷新页面，使参数生效" );
}

function showAdaptiveDiv(){
	adaptiveDiv.style.display = "";
	$dom( "textfield8" ).value = getCookie( "upBandWidth" );
	$dom( "textfield9" ).value = getCookie( "downBandWidth" );
	$dom( "adaptiveCheckbox" ).checked = getCookie( "userAdaptive" );
	$dom( "encVideoCapradio1" ).checked = false;
	$dom( "encVideoCapradio2" ).checked = false;
	$dom( "encVideoCapradio3" ).checked = false;
	$dom( "decVideoCapradio1" ).checked = false;
	$dom( "decVideoCapradio2" ).checked = false;
	$dom( "decVideoCapradio3" ).checked = false;
	var enc =  getCookie( "encVideo" );
	if( enc == 4 )$dom( "encVideoCapradio1" ).checked = "checked";
	else if( enc == 32 )$dom( "encVideoCapradio3" ).checked = "checked";
	else $dom( "encVideoCapradio2" ).checked = "checked";
	var dec = getCookie( "decVideo" );
	if( dec == 4 )$dom( "decVideoCapradio1" ).checked = "checked";
	else if( dec == 32 )$dom( "decVideoCapradio3" ).checked = "checked";
	else $dom( "decVideoCapradio2" ).checked = "checked";
}

function hideAdaptiveDiv(){
	adaptiveDiv.style.display = "none";
}

function saveAdaptiveParam(){
	var tamp = 16;
	if( $dom( "encVideoCapradio1" ).checked )tamp = 4;
	else if( $dom( "encVideoCapradio2" ).checked )tamp = 16;
	else if( $dom( "encVideoCapradio3" ).checked )tamp = 32;
	setCookie( "encVideo", tamp );
	tamp = 16;
	if( $dom( "decVideoCapradio1" ).checked )tamp = 4;
	else if( $dom( "decVideoCapradio2" ).checked )tamp = 16;
	else if( $dom( "decVideoCapradio3" ).checked )tamp = 32;
	setCookie( "decVideo", tamp );
	if( $dom( "textfield8" ).value < 32 )tamp = 32;
	else tamp = $dom( "textfield8" ).value;
	setCookie( "upBandWidth", tamp );
	if( $dom( "textfield9" ).value < 32 )tamp = 32;
	else tamp = $dom( "textfield9" ).value;
	setCookie( "downBandWidth", tamp );
	setCookie( "userAdaptive", $dom( "adaptiveCheckbox" ).checked ? true : "" );
	hideAdaptiveDiv();
	alert( "请刷新页面，使参数生效" );
}

var photoDivShowing = false;

function showPhotoDiv(){
	photoDivShowing = !photoDivShowing;
	if( photoDivShowing )takePictureDiv.style.display = "";
	else takePictureDiv.style.display = "none";
}

function netPhoneReady(){
	testLogin( getCookie( "gateWay" ), getCookie( "myNumber" ), getCookie( "deviceNumber" ), getCookie( "appKey" ), getCookie( "nikeName" ) );
	//butelNetPhoneLogin( getCookie( "myNumber" ), getCookie( "deviceNumber" ), getCookie( "appKey" ), getCookie( "nikeName" ), true );
	showLog( "加载坐席客户端" );
	butelNetPhoneViewPosition( 0, 0, 240, 135, 0, 0, 240, 135, false, true );
}

function showLog( value ){
	var tipPad = $dom( "textarea" );
	tipPad.innerHTML += value + "\n";
	tipPad.scrollTop = tipPad.scrollHeight;
}

function netPhoneLoginSuccess( serverIp ){
	showLog( "登录网关成功:" + serverIp );
	butelNetPhoneServiceIsSetBusy();
}

var butelServiceIsBusy;

function netPhoneServiceOnIsSetBusy( result, isBusy, reason ){
	showLog( "查询忙闲状态" + ( result ? "成功" : "失败" ) );
	if( result )setBusyStatus( isBusy );
}

function netPhoneServiceOnSetBusy( result, isBusy, reason ){
	showLog( "手动置" + ( isBusy ? "忙" : "闲" ) + ( result ? "成功" : "失败" ) );
	if( result )setBusyStatus( isBusy );
}

function setBusyStatus( isBusy ){
	showLog( "用户状态：" + ( isBusy ? "忙" : "闲" ) );
	$dom( "isBusyButton" ).style.backgroundImage = isBusy ? "url(img/busy.png)" : "url(img/unbusy.png)";
	$dom( "isBusyButton" ).style.textAlign = isBusy ? "right" : "left";
	butelServiceIsBusy = isBusy;
}

function netPhoneLoginFailed( code, str ){
	showLog( "登录网关失败：错误码" + code + "，" + str);
}

function netPhoneRepermitLogin(){
	showLog( "失去与网关的连接" );
	if( confirm( "断网了，刷新页面重新连接" ) )document.location.reload();
}

function onBusyButton( e ){
	butelNetPhoneServiceSetBusy( !butelServiceIsBusy );
}

function showButelLog(){
	var tipPad = $dom( "textarea" );
	tipPad.innerHTML = butelNetPhoneGetLog();
	tipPad.scrollTop = tipPad.scrollHeight;
}

function clearButelLog(){
	$dom( "textarea" ).innerHTML = "";
}

function netPhoneNewCall( number, nikName, card, sid, mediatype ){
	$dom( "newCallSpan" ).innerHTML = "收到用户呼叫请求<br/>用户号码：" + number + "<br/>用户昵称：" + nikName + "<br/>呼叫类型：" + ( mediatype == 2 ? "视频" : "音频" ) + "<br/>";
	outCallDiv.style.display = "";
	if( $dom( "ringEmbed" ) )$dom( "outCallDiv" ).removeChild( $dom( "ringEmbed" ) );
	$dom( "outCallDiv" ).innerHTML += "<embed id=\"ringEmbed\" src=\"Nokia.mp3\" width=\"0\"/>";
}

function serviceUserTakeCall( take ){
	butelNetPhoneAnswerCall( take );
	outCallDiv.style.display = "none";
	if( $dom( "ringEmbed" ) )$dom( "outCallDiv" ).removeChild( $dom( "ringEmbed" ) );
}

function netPhoneCallStarted( srcNumber, sid ){
	showLog( "呼叫接通，对方号码：" + srcNumber );
}

function netPhoneServiceOnTakePicture( result, path, reason, guid ){
	showLog( "上传照片" + ( result ? "成功" : "失败" ) );
	if( result )$dom( "photoImage" ).src = path;
}

function netPhoneOnGetStatus( connectStatus, callStatus ){
	showLog( "状态查询:连接状态" + connectStatus + ",呼叫状态" + callStatus );
}

function netPhoneServiceOnRing( sid ){
	showLog( "对端振铃，sid:" + sid );
}

function netPhoneServiceOnUpLoadLog( result ){
	var resultTxt = result? "成功" : "失败";
	showLog( "上传日志结果:" + resultTxt );		
}

var userOccupyAgentArray = {};

function netPhoneServiceOnNewUserConnect( userNum, guid, cad, nAuthorize ){
	showLog( "新关联:"+ userNum + ",标识:" + guid + "随路信令:" + cad + "是否授权:" + nAuthorize );
	userOccupyAgentArray[guid] = { userNum:userNum, guid:guid, cad:cad, nAuthorize:nAuthorize };
	if( $dom(guid) )$dom( "agentOccupySpan" ).removeChild( $dom(guid) );
	var domString = "<div id=\""+guid+"\"><span onClick=\"chooseTarget(this)\">" + userNum + "</span><input type=\"checkbox\" id=\"checkbox"+guid+"\" "+(nAuthorize?"checked":"")+" onClick=\"userCallnAuthorize(this)\">";
	domString += "是否授权<input type=\"button\" id=\"kickbutton"+guid+"\" value=\"踢了\" onClick=\"kickUserAss('"+guid+"')\"></div>";
	$dom( "agentOccupySpan" ).innerHTML += domString;
}

function kickUserAss( guid ){
	if( guid && userOccupyAgentArray[guid].userNum ){
		butelNetPhoneServiceReleaseUser( guid, userOccupyAgentArray[guid].userNum );
		showLog("踢用户" + userOccupyAgentArray[guid].userNum );
	}
	else{
		showLog( "踢用户时发生了异常" );
	}
}

function chooseTarget( userNumSpan ){
	$dom( "targetNumberText" ).value = userNumSpan.innerHTML;
}

function netPhoneServiceOnUserDisconnect( guid, userNum, reason ){
	showLog( "断开用户:" + guid );
	if( $dom(guid) )$dom( "agentOccupySpan" ).removeChild( $dom(guid) );
	if( userOccupyAgentArray[guid] )delete userOccupyAgentArray[guid];
}

function userCallnAuthorize( clickingCheckBox ){
	var guid = clickingCheckBox.id.substr(8);
	if( guid && userOccupyAgentArray[guid] )butelNetPhoneServicePermitUserCall( guid , userOccupyAgentArray[guid].userNum, "video", clickingCheckBox.checked );
}

function netPhoneServiceOnPermitUserCall( result, userNum, callType, isPermit , reason, guid ){
	showLog( ( isPermit ? "授权":"取消授权" ) + ( result ? "失败" : "成功" ) + ",用户" + userNum + ( callType == "video" ? "视频" : "音频" ) + "呼叫" );
}

function netPhoneOnNewCallReq( guid, userNum, mediaType ){
	showLog( "用户请求授权，号码：" + userNum );
}

function netPhoneServiceOnGetUserList( ul, reason ){
	if( ul ){
		var userList = ul.split( "||" );
		showLog( "关联用户信息:" + userList.length + "个" );
		for( var i = 0; i<userList.length; i++ )showLog(userList[i].split("@")[1]);
	}else{
		if( reason )showLog( "获取信息失败，" + reason );
		else showLog( "关联用户信息:0人" );
	}
}

function getButelServiceQueueCount(){
	var accNum = getCookie( "accessNum" );
	if( accNum )butelNetPhoneServiceGetAccessNumQueueSize( accNum );
}

function netPhoneServiceOnAccessNumQueueSize( result, count, reason ){
	if( result ){
		showLog( "排队人数为：" + count );
		$dom( "waitingCount" ).innerHTML = "" + count;
	}
	else showLog( "获取接入号排队数失败，" + reason );	
}

function netPhoneServiceOnCallQualityStatistics( local_up_videosize, local_up_rate, local_down_videosize, local_down_rate, upP2pLoss, upP2pDelay, downP2pLoss, downP2pDelay ){
	if( !qualityDivShowing )return;
	$dom( "up_videosize" ).innerHTML = local_up_videosize;
	$dom( "up_rate" ).innerHTML = local_up_rate;
	$dom( "down_videosize" ).innerHTML = local_down_videosize;
	$dom( "down_rate" ).innerHTML = local_down_rate;
	$dom( "upP2pLoss" ).innerHTML = upP2pLoss;
	$dom( "upP2pDelay" ).innerHTML = upP2pDelay;
	$dom( "downP2pLoss" ).innerHTML = downP2pLoss;
	$dom( "downP2pDelay" ).innerHTML = downP2pDelay;
}

var qualityDivShowing = false;

function showQualityStatistics(){
	qualityDivShowing = !qualityDivShowing;
	if( qualityDivShowing )qualityDiv.style.display = "";
	else qualityDiv.style.display = "none";
}

function clearQualityStatisticsDiv(){
	netPhoneServiceOnCallQualityStatistics( "", "", "", "", "", "", "", "" );
}

function netPhoneCallEnd( code, str, sid ){
	clearQualityStatisticsDiv();
	if( code == -6031 ){//对方呼叫中挂断
		outCallDiv.style.display = "none";
		if( $dom( "ringEmbed" ) )$dom( "outCallDiv" ).removeChild( $dom( "ringEmbed" ) );
		return;
	}
	showLog( "通话结束：" + code + str );
}

function netPhoneTargetCamera( closed ){
	showLog( "远端摄像头" + ( closed ? "关闭" : "打开" ) );	
}

function netPhoneMsgSendResult( code, msgID, serverTime ){
	showLog( "消息发送:" + ( code ? "失败" : "成功" ) );
	if( code )showLog( "失败原因：" + code );
}

function netPhoneServiceOnRedirectCall( result, reason ){
	if( !result )showLog( "转接成功！" );
	else showLog( "转接失败，" + reason );
}

function netPhoneMsgArrived( targetNumber, targetNikName, time, message, serverTime ){
	var outArea = $dom( "outputDiv" );
	addText( epsInput.translateToExpressionString(message), true );
}

function netPhoneNewOnline( target, message ){
	var outArea = $dom( "outputDiv" );
	addText( epsInput.translateToExpressionString(message), true );
}

var eps;
var epsInput;
var timeoutId;
function showExpression( cuurentButton ){
	eps.show();
	$dom("iconPageDown").style.display="";
	$dom("iconPageUp").style.display="";
	cuurentButton.blur();
	setExpressTimeout();
}
function setExpressTimeout(){
	clearTimeout( timeoutId );
	timeoutId = setTimeout( hideExpressList, 3000 );
}
function hideExpressList(){
	eps.hide();
	$dom("iconPageDown").style.display="none";
	$dom("iconPageUp").style.display="none";
}
function expressionPageDown(){
	setExpressTimeout();
	eps.pageDown();
}
function expressionPageUp(){
	setExpressTimeout();
	eps.pageUp();
}
function expressionIconClick( expressionString ){
	clearTimeout( timeoutId );
	hideExpressList();
	epsInput.appendExpression( expressionString );
}
document.onkeydown = function( e ){
	if( e.ctrlKey &&  e.which == 13 ){
		sendText();
	}
	else if( e.altKey && e.which == 13 ){
		sendOnlineText();
	}
}

function sendText(){
	var stringWithExpression = epsInput.getText();
	epsInput.clear();
	if( !stringWithExpression )return;
	showLog( "发送消息" + stringWithExpression );
	var outArea = $dom( "outputDiv" );
	addText( epsInput.translateToExpressionString(stringWithExpression) );
	var msgId = butelNetPhoneMsgSend( stringWithExpression, $dom( "targetNumberText" ).value );
}

function sendOnlineText(){
	var stringWithExpression = epsInput.getText();
	epsInput.clear();
	if( !stringWithExpression )return;
	showLog( "发送消息" + stringWithExpression );
	var outArea = $dom( "outputDiv" );
	addText( epsInput.translateToExpressionString(stringWithExpression) );
	butelNetPhoneOnlineMessage( $dom( "targetNumberText" ).value, stringWithExpression );
}

var caremaClosed = true;

function upLoadProgress( upLoadedPersent ){
	showLog( "文件上传" + upLoadedPersent + "%" );
}

function upLoadFileFailed( reson ){
	showLog( "上传文件失败" + reson );
}

function upLoadFileComplete( type, url, fileName, fileBytes ){
	showLog( "上传文件成功" + type + "," + url + "," + fileName + "," + fileBytes );
	butelNetPhoneFileUploaded( $dom( "targetNumberText" ).value, type, url, fileName, fileBytes );
	
	if( type == "picture2" ){
		var smallPicture = url.substr( 0,url.indexOf( "," ) );
		var bigPicture = url.substr( url.lastIndexOf( "," ) + 1 );
		addText( "<a href='" + bigPicture + "' target='_blank'><img src='" + smallPicture + "' width='50' height='50' /></a>" );
	}
}

function netPhoneFileArrived( targetNumber, targetNikName, time, message, type, url, title, sendTime ){
	showLog( "收到文件" + type + "," + url );
	if( type == "picture2" ){
		var smallPicture = url.substr( 0,url.indexOf( "," ) );
		var bigPicture = url.substr( url.lastIndexOf( "," ) + 1 );
		addText( "<a href='" + bigPicture + "' target='_blank'><img src='" + smallPicture + "' width='50' height='50' /></a>", true );
	}
}
