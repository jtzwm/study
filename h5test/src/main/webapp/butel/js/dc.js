var leftFirstPart = '<div><table width="425" border="0" cellspacing="0" cellpadding="0"><tr>';
leftFirstPart += '<td width="42" align="right" valign="top"><img src="img/disIcon.png" width="32" height="33"  alt=""/></td>';
leftFirstPart += '<td><table border="0" cellspacing="0" cellpadding="0"><tr><td width="15" height="10"  class="leftTop"></td>'
leftFirstPart += '<td class="topIcon"></td><td width="15" class="rightTop"></td></tr>';
leftFirstPart += '<tr><td valign="top" bgcolor="#FF0000" class＝"leftIcon" background="img/leftIcon.png"><table width="15" border="0" cellspacing="0" cellpadding="0">';
leftFirstPart += '<tr><td background="img/sayIcon.png" height="10"></td></tr></table></td><td bgcolor="#e0eefc">';
var leftLastPart = '</td><td class="rightIcon"></td></tr><tr><td height="10" class="leftBottom"></td>';
leftLastPart += '<td class="bottomIcon"></td><td class="rightBottom"></td></tr></table></td><td width="48">&nbsp;';
leftLastPart += '</td></tr></table></div>';

var rightFirstPart = '<div><table width="425" border="0" cellspacing="0" cellpadding="0"><tr>';
rightFirstPart += '<td width="42" align="right" valign="top">&nbsp;</td>';
rightFirstPart += '<td align="right"><table border="0" cellspacing="0" cellpadding="0"><tr><td width="15" height="10"  class="leftTop"></td>'
rightFirstPart += '<td class="topIcon"></td><td width="15" class="rightTop"></td></tr>';
rightFirstPart += '<tr><td valign="top" bgcolor="#FF0000" class＝"leftIcon" background="img/leftIcon.png">';
rightFirstPart += '</td><td bgcolor="#e0eefc">';
var rightLastPart = '</td><td class="rightIcon"><table width="15" border="0" cellspacing="0" cellpadding="0"><tr><td background="img/sayrIcon.png" height="10"></td></tr></table></td></tr><tr><td height="10" class="leftBottom"></td>';
rightLastPart += '<td class="bottomIcon"></td><td class="rightBottom"></td></tr></table></td><td width="48"><img src="img/selfIcon.png" width="32" height="33"  alt=""/>';
rightLastPart += '</td></tr></table></div>';

function addText( msg, isRight ){
	var outputInner = document.getElementById( "outputInner" );
	if( isRight )outputInner.innerHTML += rightFirstPart + msg + rightLastPart;
	else outputInner.innerHTML += leftFirstPart + msg + leftLastPart;
	var outputDiv = document.getElementById( "outputDiv" );
	outputDiv.scrollTop = outputInner.offsetHeight - 275;
}