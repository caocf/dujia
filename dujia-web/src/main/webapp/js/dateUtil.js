
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};

	if(!format){
		format = "yyyy-MM-dd hh:mm:ss";
	}else if(1==format){
		format = "yyyy-MM-dd";
	}
	
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

/*日，一，二，三，四，五，六*/
var week = ["\u65e5","\u4e00","\u4e8c","\u4e09","\u56db","\u4e94","\u516d"];
Date.prototype.getChineseDay=function(){
	var day_ = this.getDay();
	return week[day_];
};
Date.prototype.getSubDate =function(){
	return (this.getMonth()+1)+"-"+this.getDate();
};
function parseDate(strDate){
	var d = new Date(strDate);
	if(isNaN(d)){
		d = new Date(strDate.replace(/-/g, "/"));
	}
	if(isNaN(d)){
		if(strDate.lastIndexOf("-")>-1){
			if(strDate.length==10){
				var dateMsgs = strDate.split("-");
				
				d.setFullYear(dateMsgs[0]);
				d.setMonth(dateMsgs[1]-1);
				d.setDate(dateMsgs[2]);
				d.setHours(0, 0, 0, 0);
				return d;
			}
		}
	}
	return d;
}