//$(function(){
// 根据时间段查询订单列表
$("#timeSlotSelect").on("click", function () {
    if ($("#timeSlotBox").is(":visible") == true) {
        $("#timeSlotBox").hide();
    } else {
        $("#timeSlotBox").show();
    }
    return false;
});
$(document).on("click", function (event) {
    var src = event.target;
    if (src.id && src.id === "timeSlotBox") {
        return false;
    } else {
        $("#timeSlotBox").hide();
    }
});
$("#timeSlotBox").find("a").on("click", function () {

    $("#timeSlotSpan").text($(this).text());
    $("#timeSlotSpan").attr("tval", $(this).attr("time"));

    var orderNo = $("#orderId").val();
    var orderTimeSlot = $(this).attr("time");
    submitParam(orderTimeSlot, orderNo, null);
});

//查询待付款订单列表
$("#d_wait_pay").on("click", function () {
    var orderStatus = $("#wait_pay").attr("tval");
    var params = '<input type="hidden" name="orderStatus" value="' + orderStatus + '" />';
    // 提交数据
    jQuery('<form action="' + context + '/order/list.html" method="post">' + params + '</form>')
        .appendTo('body').submit().remove();
});


// 根据订单编号查询订单列表
$("#js-search-btn").on("click", function () {
    var orderNo = $("#orderId").val();
    var orderTimeSlot = $("#timeSlotSpan").attr("tval");
    submitParam(orderTimeSlot, orderNo, null);
});

// 给取消订单按钮添加点击事件
$(".cancel_btns").on("click", function () {
    var _self = $(this);
    var orderId = $(this).next().val();
    cancelOrderClick(orderId, _self);
});

//翻页查询订单
function searchOrder(pageIndex) {
    var orderNo       = $("#orderId").val();
    var orderTimeSlot = $("#timeSlotSpan").attr("tval");
    var orderStatus   = $("#orderStatus").val();

    submitParam(orderTimeSlot, orderNo, pageIndex,orderStatus);
}

// 查看订单详情
function orderDetail(orderId) {
    var params = "";
    params += '<input type="hidden" name="detailId" value="' + orderId + '" />';
    params += '<input type="hidden" name="timeSlot" value="' + $("#timeSlotSpan").attr("tval") + '" />';
    params += '<input type="hidden" name="pageIndex" value="' + $("#currPageIndex").val() + '" />';

    jQuery('<form action="' + context + '/order/detail.html" method="post">' + params + '</form>')
        .appendTo('body').submit().remove();
}


// 点击订单取消
function cancelOrderClick(orderId, _self) {

    // 调整取消订单弹框的位置
    var tTop = $(_self).offset().top + $(_self).outerHeight();
    var tLeft = $(_self).offset().left - $("#cancelOrderPop").outerWidth();
    $('#cancelOrderPop').css({'top': tTop + 'px', 'left': tLeft + 'px', 'position': 'absolute', 'z-index': '99997'}).show();

    //取消订单
    $("#cancelConfirm").unbind().on("click", function () {

        // 调整正在取消订单弹框的位置
        var tTop = $(_self).offset().top + $(_self).outerHeight();
        var tLeft = $(_self).offset().left - $(".pop_cancel_order").outerWidth();
        $('.pop_cancel_order').css({
            'top': tTop + 'px',
            'left': tLeft + 'px',
            'position': 'absolute',
            'z-index': '99997'
        });

        $("#cancelOrderPop").hide();
        $(".pop_cancel_order").show();		// 显示正在取消中
        cancelOrder(orderId,false);		// 订单取消
    });

    // 撤销取消订单
    $("#notCancel").unbind().on("click", function () {
        $("#cancelOrderPop").hide();
    });
}
//页码只能输入数字
$("#toPageNum").keyup(function(){
    this.value = this.value.replace(/\D/g, '');
});
function queryOrderByNum() {
    var pIndex = $("#toPageNum").val();
    if(pIndex != ""){
        searchOrder(pIndex);
    }
}

// 提交数据，查询
function submitParam(orderTimeSlot, orderNo, pageIndex,orderStatus) {
    var params = "";		// 参数字符串
    if (orderTimeSlot)		// 取得时间间隔
        params += '<input type="hidden" name="dateType" value="' + orderTimeSlot + '" />';

    if (orderNo)		// 取得订单id
        params += '<input type="hidden" name="orderNo" value="' + orderNo + '" />';

    if (pageIndex)	// 取得页码
        params += '<input type="hidden" name="pageIndex" value="' + pageIndex + '" />';

    if (orderStatus) //取得订单状态
    	params += '<input type="hidden" name="orderStatus" value="' + orderStatus + '" />';
    // 提交数据
    jQuery('<form action="' + context + '/order/list.html" method="post">' + params + '</form>')
        .appendTo('body').submit().remove();
}

//倒计时
$(".timerDiv").each(function () {
    var surplusTime = $(this).attr("time");//剩余时间，毫秒
    if(surplusTime <=  0){
    	$(this).remove();
	}else{        
		var orderId = $(this).attr("orderId");
		$(this).find(".order_flights_time").countdown({     
			until: surplusTime/1000,//转成秒
			compact: true,
			format: "HMS",
			description: '',
			onExpiry: function () {
				cancelOrder(orderId,true);
			}
		});
	}
});

/**
 * 取消订单
 * @param orderId 订单ID
 * @param isSystemCancel 是否系统取消
 */
function cancelOrder(orderId,isSystemCancel) { 
    // 发起请求，去往后台取消订单
    $.ajax({
        type: "POST",
        url: context + "/order/cancel.html?orderId="+orderId+"&isSystemCancel="+isSystemCancel,
        contentType: "application/json",
        success: function (result) {
            // 重新刷页面
            submitParam($("#timeSlotSpan").attr("tval"), $("#orderId").val(), $("#currPageIndex").val());
        }
    });
}

function toDouble(num) {
	if (num < 10) {
		return "0" + num;
	} else {
		return "" + num;
	}
}

