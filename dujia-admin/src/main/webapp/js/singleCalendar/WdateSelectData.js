/**
 * 时间选择插件公用方法
 * @author zr_wangweiran
 * @param idName 所选input的id
 */
function selectData(idName) {
	if (window.ActiveXObject) {
		document.getElementById(idName).click();
	} else {
		var evt = document.createEvent("MouseEvents");
		evt.initEvent("click", true, true);
		document.getElementById(idName).dispatchEvent(evt);
	}
}