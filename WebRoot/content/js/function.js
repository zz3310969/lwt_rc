function formattime(strTime) {
	if (strTime == null || strTime == undefined)
		return "";
	var datestr = strTime.replace("T", " ");
	return datestr;
	/*
	 * var date = new Date(Date.parse(datestr)); var year = date.getFullYear();
	 * var month = date.getMonth() + 1; var day = date.getDate(); var hour =
	 * date.getHours(); var minute = date.getMinutes(); var seconds =
	 * date.getSeconds(); return year + "-" + month + "-" + day + " " + hour +
	 * ":" + minute + ":" + seconds;
	 */
};
function formatsorttime(strTime) {
	if (strTime == null || strTime == undefined)
		return "";
	var datestr = strTime;
	if (strTime.indexOf("T") > 0)
		datestr = strTime.split("T")[0];
	
	return datestr;

};
function IsNullOrEmpty(str) {
	if(str == undefined || str == null)
		return true;
	else
		return false;
	
}
function convertnull(nullorundefind) {
	if (nullorundefind == undefined || nullorundefind == null)
		return "";
	else {
		return nullorundefind;
	}
}
Array.prototype.contains = function(element) {
	for ( var i = 0; i < this.length; i++) {
		if (this[i] == element) {
			return true;
		}
	}
	return false;
};
function TranslationColumn(obj, key) {
	if (obj == undefined || obj == null)
		return key;

	var name = "";

	$.each(obj, function(i) {
		if (obj[i].code == key || Number(obj[i].code) == Number(key))
			name = obj[i].name;
	});
	return name;

}
function StringSub(txt, length) {
	if(txt == null || txt == undefined)
		return "";
	if (txt.toString().length <= length)
		return txt;
	var return_str = '<span title=' + txt + '>' + txt.substring(0, length)
			+ '...</span>';
	return return_str;
}
function ConvertObjectList(array, key, value) {
	var Obj = {};
	$.each(array, function(i) {
		var Objarray = {};
		if (key != undefined && key != null)
			Objarray["code"] = eval("array[" + i + "]." + key);
		else {
			Objarray["code"] = array[i].id;
		}
		if (value != undefined && value != null) {
			Objarray["name"] = eval("array[" + i + "]." + value);
		} else
			Objarray["name"] = array[i].name;

		Obj[i] = Objarray;
	});
	return Obj;
}

function makeselectsys(objcollection, opt, selectId) {
	if (objcollection == undefined)
		return "";

	var $select = $('<select>').addClass("select");
	if (opt != undefined && opt != null)
		$select.attr("id", opt.id);

	$select.append($('<option>').attr("value", "-1").text("请选择"));

	var JsonArray = eval(objcollection);
	$.each(JsonArray, function(i) {
		if (JsonArray[i].code == selectId)
			$select.append($('<option>').attr('value', JsonArray[i].code).attr(
					"selected", "selected").text(JsonArray[i].name));
		else
			$select.append($('<option>').attr('value', JsonArray[i].code).text(
					JsonArray[i].name));
	});

	return $select;
}

function status_con(st){
	if (st == undefined || st == null)
		return "";
	if(st == 1)
	return "会诊申请";
	if(st == 2)
		return "会诊失败";
	if(st == 3)
		return "会诊提交";
	if(st == 4)
		return "会诊安排";
	if(st == 5)
		return "会诊完成";
} 
