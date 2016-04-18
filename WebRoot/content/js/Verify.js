//格式验证函数

//判断是否合法Email地址
function valid_email(in_str){

    var emailPat=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;	
    
    if(in_str == null) 
        return false;
    else 
        return in_str.match(emailPat)?true:false;
}


//判断是否合法手机号
function valid_mobile_num(in_str){

    var telpat=/(^(130|131|132|133|134|135|136|137|138|139|150|151|152|153|154|155|156|157|158|159|180|185|186|187|188|189)\d{8}$)/;
   
    if(in_str == null) 
        return false;
    else 
        return in_str.match(telpat)?true:false;
    
}
//判断邮编是否有效
function valid_postcode(in_str){

    var Postcode = /^[0-9]\d{5}$/;
    if(in_str == null)
        return false;
    else
        return Postcode.test(in_str)?true:false;
}
//判断MSN的账号格式是否正确
function valid_msnaccount(in_str){

    var msn = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
    if(in_str == null)
        return false;
    else
        return msn.test(in_str)?true:false;
}

//汉字的URLENCODE 编码 utf-8
function EncodeUtf8(s1) {
	var s=escape(s1);
	var sa=s.split("%");
	var retV="";
	if(sa[0]!="")retV=sa[0];
	for(var i=1;i<sa.length;i++)
	{
		if(sa[i].substring(0,1)=="u") {
			retV+=Hex2Utf8(Str2Hex(sa[i].substring(1,5)));
		}else
		retV+="%"+sa[i];
	}
	return retV;
}
function Str2Hex(s) {
	var c="";
	var n;
	var ss="0123456789ABCDEF";
	var digS="";
	for(var i=0;i<s.length;i++)
	{
		c=s.charAt(i);
		n=ss.indexOf(c);
		digS+=Dec2Dig(eval(n));
	}
	return digS;
}
function Dec2Dig(n1) {
	var s="";
	var n2=0;
	for(var i=0;i<4;i++)
	{
		n2=Math.pow(2,3-i);
		if(n1>=n2) {
			s+='1';
			n1=n1-n2;
		}else
		s+='0';
	}
	return s;
}
function Dig2Dec(s)
{
	var retV=0;
	if(s.length==4) {
		for(var i=0;i<4;i++) {
			retV+=eval(s.charAt(i))*Math.pow(2,3-i);
		}
		return retV;
	}
	return -1;
}
function Hex2Utf8(s)
{
	var retS="";
	var tempS="";
	var ss="";
	if(s.length==16) {
		tempS="1110"+s.substring(0,4);
		tempS+="10"+s.substring(4,10);
		tempS+="10"+s.substring(10,16);
		var sss="0123456789ABCDEF";
		for(var i=0;i<3;i++) {
			retS+="%";
			ss=tempS.substring(i*8,(eval(i)+1)*8);
			retS+=sss.charAt(Dig2Dec(ss.substring(0,4)));
			retS+=sss.charAt(Dig2Dec(ss.substring(4,8)));
		}
		return retS;
	}
	return "";
}
function getparaUrl(key) {
	var url=window.location.toString ();
	var tmp;
	if(url&&url.indexOf("?")) {
		var arr=url.split("?");
		var parms=arr[1];
		if(parms&&parms.indexOf("&")) {
			var parmList=parms.split("&");
			$.each(parmList,function (key,val) {
				if(val&&val.indexOf("=")) {
					var parmarr=val.split("=");
					if(key) {
						if(typeof(key)=="string"&&key==parmarr[0]) {
							tmp=parmarr[1]==null?'':parmarr[1];
						}
					}
					else {
						tmp=parms;
					}
				}
			});
		}
	}
	return tmp;
}