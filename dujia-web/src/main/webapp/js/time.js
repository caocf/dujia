var oShow;
var timer = null;
/**
 * @param delayTime
 * 延迟时间 单位/毫秒
 * @param cls
 * 显示的class名称
 */
function timerStart(delayTime, cls, hideCls) {
	oShow = $(cls);
	var endTime = new Date().getTime() + parseInt(delayTime);
	timer = setInterval(function() {
		time((endTime - new Date().getTime()) / 1000, hideCls);
	}, 1000);
}

function time(iRemain, hideCls) {
	if (iRemain <= 0) {
		clearInterval(timer);
		iRemain = 0;
		$(hideCls).remove();
		oShow.text("已结束");
	}
	else{
		var iHours = parseInt(iRemain / 3600);
		iRemain %= 3600;
		var iMinutes = parseInt(iRemain / 60);
		iRemain %= 60;
		var iSeconds = parseInt(iRemain);

		oShow.text(toDouble(iHours) + "时" + toDouble(iMinutes) + "分" + toDouble(iSeconds) + "秒");
	}
}

function toDouble(num) {
	if (num < 10) {
		return "0" + num;
	} else {
		return "" + num;
	}
}
