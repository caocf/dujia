
var obj = { date: new Date(), year: -1, month: -1, priceArr: [] };
var htmlObj = { header: "", left: "", right: "" };
var elemId = null;
function getAbsoluteLeft(objectId) {
   var o = document.getElementById(objectId);
   var oLeft = o.offsetLeft;
    while (o.offsetParent != null) {
        oParent = o.offsetParent;
        oLeft += oParent.offsetLeft;
        o = oParent;
    }
    return oLeft;
}
//获取控件上绝对位置
function getAbsoluteTop(objectId) {
   var o = document.getElementById(objectId);
   var oTop = o.offsetTop + o.offsetHeight + 10;
    while (o.offsetParent != null) {
        oParent = o.offsetParent;
        oTop += oParent.offsetTop;
        o = oParent;
    }
    return oTop
}
//获取控件宽度
function getElementWidth(objectId) {
    x = document.getElementById(objectId);
    return x.clientHeight;
}
var pickerEvent = {
    Init: function (elemid,callback) {
        if (obj.year == -1) {
            dateUtil.getCurrent();
        }
        for (var item in pickerHtml) {
                pickerHtml[item]();
        }
        var p = document.getElementById("calendar_choose");
        var overlay = document.getElementById("date_overlay");
        if (p != null) {
            document.body.removeChild(p);
            if(null != overlay){
            	document.body.removeChild(overlay);
            }
        }
        var html = '<div id="calendar_choose" class="calendar" style="display: block; position: absolute;">'
        html += htmlObj.left;
        html += htmlObj.header;
        html += '<div class="basefix" id="bigCalendar" style="display: block;">';
        html += htmlObj.right;
        html += '<div style="clear: both;"></div>';
        html += "</div></div>";
        
        $(document.body).append(html);
        if(!elemid){
        	$(document.body).append("<div class='lean_overlay' id='date_overlay'></div>");
        	document.getElementById("calendar_choose").style.position = "fixed";
        	document.getElementById("calendar_choose").style.left = ($(window).width()/2-document.getElementById("calendar_choose").clientWidth/2)+"px";
        	document.getElementById("calendar_choose").style.top = ($(window).height()/2-document.getElementById("calendar_choose").clientHeight/2)+"px";
        }else{
        	elemId=elemid;
            var elemObj = document.getElementById(elemid);       
            document.getElementById("calendar_choose").style.left = getAbsoluteLeft(elemid)+"px";
            document.getElementById("calendar_choose").style.top  = getAbsoluteTop(elemid)+"px";
        }
        $("#picker_last").click(function(){
            pickerEvent.getLast(callback);
        });
        $("#picker_next").click(function(){
            pickerEvent.getNext(callback);
        });
		$("#picker_today").click(function(){
            pickerEvent.getToday(callback);
        });
		
		document.getElementById("calendar_choose").style.zIndex = 1000;
        var tds = document.getElementById("calendar_tab").getElementsByTagName("td");
        for (var i = 0; i < tds.length; i++) {
            if (tds[i].getAttribute("date") != null && tds[i].getAttribute("date") != "" && tds[i].getAttribute("price") != "-1") {
                tds[i].onclick = function () {
                    commonUtil.chooseClick(this,callback);
                };
            }
        }
        
       
    },
    getLast: function (callback) {
        dateUtil.getLastDate();
        pickerEvent.Init(elemId,callback);
    },
    getNext: function (callback) {
        dateUtil.getNexDate();
        pickerEvent.Init(elemId,callback);
    },
	getToday:function(callback){
		dateUtil.getCurrent();
		pickerEvent.Init(elemId,callback);
	},
    setPriceArr: function (arr) {
        obj.priceArr = arr;
    },
    remove: function () {
        var p = document.getElementById("calendar_choose");
        if (p != null) {
            document.body.removeChild(p);
        }
    },
    isShow: function () {
        var p = document.getElementById("calendar_choose");
        if (p != null) {
            return true;
        }
        else {
            return false;
        }
    }
}
var pickerHtml = {
    getHead: function () {
        var head = '<ul class="calendar_num basefix"><li class="bold">六</li><li>五</li><li>四</li><li>三</li><li>二</li><li>一</li><li class="bold">日</li></ul>';
        htmlObj.header = head;
    },
    getLeft: function () {
        var left = '<div class="calendar_left pkg_double_month"><p class="date_text">' + obj.year + '年' + obj.month + '月</p><a href="javascript:" title="上一月" id="picker_last" class="pkg_circle_top">&lt;</a><a href="javascript:" title="下一月" id="picker_next" class="pkg_circle_bottom ">&gt;</a></div>';
        htmlObj.left = left;
    },
    getRight: function () {
        var days = dateUtil.getLastDay();
        var week = dateUtil.getWeek();
        var html = '<table id="calendar_tab" class="calendar_right"><tbody>';
        var index = 0;
        for (var i = 1; i <= 42; i++) {
            if (index == 0) {
                html += "<tr>";
            }
            var c = week > 0 ? week : 0;
            if ((i - 1) >= week && (i - c) <= days) {
                var price_stock = commonUtil.getPriceAndStock((i - c));
                var priceStr = "";
                var classStyle = "";
                if(price_stock.price){
                	priceStr = "<dfn>¥</dfn>" + price_stock.price;
                }else{
                	priceStr ="";
                }
                
                if(price_stock.inventoryStats && price_stock.inventoryStats !=4 ) {
                	if(price_stock.openingSale) {
                		//开放售卖
                		classStyle = "class='on'";
                	}else{
                		//限量售卖
                		if(price_stock.stock == 0) {
                			//库存为0
                			priceStr+="<span class='store0'>售罄</span>";
                		}else{
                			//库存不为0
                			if (price_stock.price != -1 && price_stock.price != "") {
                				if(price_stock.stock >0 && price_stock.stock<5){
                                    priceStr+="<span>余"+price_stock.stock+"</span>";
                                }
                				classStyle = "class='on'";
                			}
                		}
                	}
                }else{
                	if(price_stock.price){
                		priceStr+="<span class='store0'>售罄</span>";
                	}
                }

				//if (price_stock.price != -1 && price_stock.stock != 0&&obj.year==new Date().getFullYear()&&obj.month==new Date().getMonth()+1&&i-c==new Date().getDate()) {
                //    classStyle = "class='on today'";
                //}
                var monthStr = obj.month,
                    dayStr = (i-c);
                if(obj.month < 10){
                    monthStr="0"+obj.month
                }
                if( (i - c) < 10){
                    dayStr="0"+ (i - c);
                }
				//判断今天
				if(obj.year==new Date().getFullYear()&&obj.month==new Date().getMonth()+1&&i-c==new Date().getDate()){
					html += '<td  ' + classStyle + ' date="' + obj.year + "-" + monthStr + "-" + dayStr + '"stock="' + price_stock.stock + '" price="' + price_stock.price + '"><a><span class="date basefix">' + (i - c) + '</span><span class="team basefix" style="display: none;">&nbsp;</span><span class="calendar_price01">' + priceStr + '</span></a></td>';
				}
				else{
                	html += '<td  ' + classStyle + ' date="' + obj.year + "-" + monthStr + "-" + dayStr + '"stock="' + price_stock.stock + '" price="' +price_stock. price + '"><a><span class="date basefix">' + (i - c) + '</span><span class="team basefix" style="display: none;">&nbsp;</span><span class="calendar_price01">' + priceStr + '</span></a></td>';
				}
                if (index == 6) {

                    html += '</tr>';
                    index = -1;
                }
            }
            else {
                html += "<td></td>";
                if (index == 6) {
                    html += "</tr>";
                    index = -1;
                }
            }
            index++;
        }
        html += "</tbody></table>";
        htmlObj.right = html;
    }
}
var dateUtil = {
    //根据日期得到星期
    getWeek: function () {
        var d = new Date(obj.year, obj.month - 1, 1);
        return d.getDay();
    },
    //得到一个月的天数
    getLastDay: function () {
        var new_year = obj.year;//取当前的年份        
        var new_month = obj.month;//取下一个月的第一天，方便计算（最后一不固定）        
        var new_date = new Date(new_year, new_month, 1);                //取当年当月中的第一天        
        return (new Date(new_date.getTime() - 1000 * 60 * 60 * 24)).getDate();//获取当月最后一天日期        
    },
    getCurrent: function () {
        var dt = obj.date;
        obj.year = dt.getFullYear();
        obj.month = dt.getMonth() + 1;
		obj.day = dt.getDate();
    },
    getLastDate: function () {
        var dt = obj.date;
        if(obj.year == dt.getFullYear() && obj.month == dt.getMonth() + 1){
            return;
        }
        if (obj.year == -1) {
            var dt = new Date(obj.date);
            obj.year = dt.getFullYear();
            obj.month = dt.getMonth() + 1;
        }
        else {
            var newMonth = obj.month - 1;
            if (newMonth <= 0) {
                obj.year -= 1;
                obj.month = 12;
            }
            else {
                obj.month -= 1;
            }
        }
    },
    getNexDate: function () {
        var dt = obj.date;
        if(((obj.year == dt.getFullYear()) && (obj.month > (dt.getMonth() + 1 + 1))) || ((obj.year > dt.getFullYear()) && (obj.month+12) > (dt.getMonth() + 1 + 1))){
            return;
        }

        if (obj.year == -1) {
            var dt = new Date(obj.date);
            obj.year = dt.getFullYear();
            obj.month = dt.getMonth() + 1;
        }
        else {
            var newMonth = obj.month + 1;
            if (newMonth > 12) {
                obj.year += 1;
                obj.month = 1;
            }
            else {
                obj.month += 1;
            }
        }
    }
}
var commonUtil = {
    getPriceAndStock: function (day) {
        var price_stock = {price:"",stock:""};
        var dt = obj.year + "-";
        if (obj.month < 10)
        {
            dt += "0"+obj.month;
        }
        else
        {
            dt+=obj.month;
        }
        if (day < 10) {
            dt += "-0" + day;
        }
        else {
            dt += "-" + day;
        }
        
        for (var i = 0; i < obj.priceArr.length; i++) {
            if (obj.priceArr[i].saleDate == dt) {
                price_stock.price = obj.priceArr[i].price;
                price_stock.stock = obj.priceArr[i].stock;
                price_stock.inventoryStats = obj.priceArr[i].inventoryStats;
                price_stock.openingSale = obj.priceArr[i].openingSale;
                return price_stock;
            }
        }
        return price_stock;
    },
    chooseClick: function (sender,callback) {
        if($(sender).hasClass("on")){
            var date = sender.getAttribute("date");
            var price = sender.getAttribute("price");
            var el = document.getElementById(elemId);
            if (el) {
                el.value = date;
            }
            callback(date, price);
            pickerEvent.remove();

        }
    }
}

$(document).bind("click", function (event) {
    var e = event || window.event;
    var elem = e.srcElement || e.target;
    while (elem) {
        if (elem.id == "calendar_choose") {
            return;
        }
        elem = elem.parentNode;
    }
    obj = { date: new Date(), year: -1, month: -1, priceArr: [] };
    pickerEvent.remove();
    $("#date_overlay").remove();
});