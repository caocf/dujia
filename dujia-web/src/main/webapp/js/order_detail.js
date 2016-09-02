$(function(){
	//给取消订单按钮添加点击事件
	$(".cancel_btns").on("click", function () {
	    var _self   = $(this);
	    var orderId = $('#orderId').text();
	    cancelOrderClick(orderId, _self);
	});

	//点击订单取消
	function cancelOrderClick(orderId, _self) {

	    // 调整取消订单弹框的位置
	    var tTop  = $(_self).offset().top + $(_self).outerHeight();
	    var tLeft = $(_self).offset().left - $("#cancelOrderPop").outerWidth();
	    $('#cancelOrderPop').css({'top': tTop + 'px', 'left': tLeft + 'px', 'position': 'absolute', 'z-index': '99997'});

	    //取消订单
	    $("#cancelOrderPop").show();
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
	        cancelOrder(orderId);		// 订单取消
	    });

	    // 撤销取消订单
	    $("#notCancel").unbind().on("click", function () {
	        $("#cancelOrderPop").hide();
	    });
	}


	//取消订单
	function cancelOrder(orderId) {
	    $.ajax({
	        type: "POST",
	        url: SiteData.DUJIA_SITE + "/order/cancel.html?orderId="+orderId+"&isSystemCancel=false",
	        contentType: "application/json",
	        success: function (result) {
	            $(".pop_cancel_order").hide();
	            // 重新刷页面
	            window.location.href = SiteData.DUJIA_SITE + '/order/order_detail/query.html?orderId='+orderId;
	        }
	    });
	}
});