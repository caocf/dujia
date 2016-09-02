$(document).ready(function() {
	$(window).scroll(resscrEvt);
	$(window).resize(resscrEvt);
	$(".model-alert").click(function() {
		var container = $("#" + $(this).attr("relation"));
		var htm = container.html();
		var width = container.width();
		var height = container.height();
		showWin("html", htm, "", width, height);
	});
});
function showWin(vType, url, args, w, h) {
	var bH = $("body").height();
	var bW = $("body").width();
	$("#msg").height(h);
	$("#msg").width(w);
	$("#msg").prepend('<iframe frameborder="0" src="/index.jsp" disabled="true" scrolling="no"  style="border: 0px; padding:0;width:1000px;height:500px; top:-200px;left:-350px;z-index:9;position: absolute;filter: Alpha(opacity=0);opacity:0; "></iframe>');
	var objWH = objValue("msg");
	$("#fullBg").css( {
		width : bW,
		height : bH,
		display : "block"
	});
	var tbT = objWH.split("|")[0] + "px";
	var tbL = objWH.split("|")[1] + "px";
	$("#msg").css( {
		top : tbT,
		left : tbL,
		display : "block"
	});
	if (vType == "url") {
		$("#ctt").html("<div style='margin:0 auto'></div>");
		$("#ctt").load(url, args);
	} else if (vType = "html") {
		$("#ctt").html(url);
	}
	$(".close").click(closeWin);
}
function objValue(obj) {
	var st = document.documentElement.scrollTop;
	var sl = document.documentElement.scrollLeft;
	var ch = document.documentElement.clientHeight;
	var cw = document.documentElement.clientWidth;
	var objH = $("#" + obj).height();
	var objW = $("#" + obj).width();
	var objT = Number(st) + (Number(ch) - Number(objH)) / 2;
	var objL = Number(sl) + (Number(cw) - Number(objW)) / 2;
	return objT + "|" + objL;
}
function resscrEvt() {
	var bjCss = $("#fullBg").css("display");
	if (bjCss == "block") {
		var bH2 = $("body").height();
		var bW2 = $("body").width();
		$("#fullBg").css( {
			width : bW2,
			height : bH2
		});
		var objV = objValue("msg");
		var tbT = objV.split("|")[0] + "px";
		var tbL = objV.split("|")[1] + "px";
		$("#msg").css( {
			top : tbT,
			left : tbL
		});
	}
}

function closeWin() {
	$("#fullBg").css("display", "none");
	$("#msg").css("display", "none");
}