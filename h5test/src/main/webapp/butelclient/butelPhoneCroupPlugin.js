// JavaScript Document
function butelNetPhoneActiveGroupPlugin(){
	butelNetPhone.activeGroupPlugin();
}

function butelNetPhoneGroupCreate( groupName, iconUrl, userIDList){
	butelNetPhone.groupCreate( groupName, iconUrl, userIDList );
}

function butelNetPhoneGroupUpdate( groupId, groupName, iconUrl ){
	butelNetPhone.groupUpdate( groupId, groupName, iconUrl );
}

function butelNetPhoneGroupAddUsers(groupId, userList ){
	butelNetPhone.groupAddUsers(groupId, userList );
}

function butelNetPhoneGroupDelUser(groupId, userList){
	butelNetPhone.groupDelUser(groupId, userList);
}

function butelNetPhoneGroupQuit( groupId, ownerNub){
	butelNetPhone.groupQuit( groupId, ownerNub);
}

function butelNetPhoneGroupDelete(groupId){
	butelNetPhone.groupDelete(groupId);
}

function butelNetPhoneGroupQueryDetail(groupId){
	butelNetPhone.groupQueryDetail(groupId);
}

function butelNetPhoneGroupGetAll(){
	butelNetPhone.groupGetAll();
}

function butelPhoneOnGroupMgrSendMessage( result,  type, resultString ){
	try{
		netPhoneOnGroupMgrSendMessage( result,  type, resultString );
	}
	catch(e){
	}
}

function butelPhoneOnGroupMgrNewMessage( type, msgString ){
	try{
		netPhoneOnGroupMgrNewMessage( type, msgString );
	}
	catch(e){
	}
}

function butelNetPhoneSendGroupTextMessage(groupId, msgText){
	butelNetPhone.sendGroupTextMessage(groupId, msgText);
}

function butelNetPhoneSendGroupFileMessage( groupId, fileType, fileUrl, fileName, fileSize ){
	butelNetPhone.sendGroupFileMessage( groupId, fileType, fileUrl, fileName, fileSize );
}

function butelPhoneOnGroupSendMessage( msgId, result, servertime ){
	try{
		netPhoneOnGroupSendMessage( msgId, result, servertime );
	}
	catch(e){
	}
}

function butelPhoneOnGroupNewTextMessage( userId, nickName, serverTime, groupId, msgString ){
	try{
		netPhoneOnGroupNewTextMessage( userId, nickName, serverTime, groupId, msgString );
	}
	catch(e){
	}
}

function butelPhoneOnGroupNewFileMessage( senderId, nickName, serverTime, groupId, fileType, fileUrl ){
	try{
		netPhoneOnGroupNewFileMessage( senderId, nickName, serverTime, groupId, fileType, fileUrl );
	}
	catch(e){
	}
}