function upLoadFileComponent( divName, width, height, buttonPicture, transparentBackground ){
	var flashvars = {};
	var params = {};
	if( buttonPicture )flashvars.buttonURL = buttonPicture;
	if( transparentBackground == true || transparentBackground == "true" )params.wmode = "transparent";
	swfobject.embedSWF("FileUpload.swf", divName, width, height, "11.2.0", "expressInstall.swf", flashvars, params );
}

function upLoadFileProgress( upLoadedPersent ){
	try{
		upLoadProgress( upLoadedPersent );
	}
	catch(e){
	}
}