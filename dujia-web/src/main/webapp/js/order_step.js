$(function(){
	if (!Array.prototype.indexOf)
	{
		Array.prototype.indexOf = function(elt /*, from*/)
		{
			var len = this.length >>> 0;
			var from = Number(arguments[1]) || 0;
			from = (from < 0)
				? Math.ceil(from)
				: Math.floor(from);
			if (from < 0)
				from += len;
			for (; from < len; from++)
			{
				if (from in this &&
					this[from] === elt)
					return from;
			}
			return -1;
		};
	}

	var statusMap= [];
    statusMap[1] = "提交成功";
    statusMap[2] = "支付成功";
    statusMap[4] = "已确认";
    statusMap[6] = "已取消";
    statusMap[7] = "已完成";
    statusMap[9] = "已退款";
    statusMap[10]= "超时支付";
    var  orderId = $('#orderId').text();

    $.ajax({
        url: "/order/order_status/query.html",
        type: "GET",
        cache: false,
        data: {"orderId": orderId},
        async: false,
        dataType: "json",
        success: function (data) {
            if(data != '' && data != null){
                var data = JSON.parse(data);
                if(typeof JSON == 'undefined'){  //匹配ie兼容模式
                    data = eval("("+data+")");
                }
                // else{
                //     data = JSON.parse(data);
                // }
                $.each(data.orderStatusList, function(i,n){
                    n.recordTime = new Date(n.recordTime).format('yyyy-MM-dd hh:mm');
                    if(i == 0){
                        if(data.orderStatus.length == 1){
                            $('.step').append('<li class="item">\
								<span class="img_icon"></span>\
								<div class="line">\
									<span class="progress"></span>\
									<i></i>\
								</div>\
								<div class="text">\
									<span>'+statusMap[n.orderStatus]+'</span><br>\
									<span class="date" >'+n.recordTime+'</span>\
								</div>\
							</li>');
                            addGrayPaySuccess();
                            addGrayTicketSuccess();
                            addGraySuccess();
                        }
                        else if(n.orderStatus == 1){
                            $('.step').append('<li class="item">\
								<span class="img_icon"></span>\
								<div class="line line1"></div>\
								<div class="text">\
									<span>'+statusMap[n.orderStatus]+'</span><br>\
									<span class="date" >'+n.recordTime+'</span>\
								</div>\
							</li>');
                        }
                    }
                    else if(i > 0){
                        if(data.orderStatus.indexOf(10) > -1 ){
                            if(n.orderStatus == 6){
                                $('.step').append('<li class="item">\
									<span class="img_icon"></span>\
									<div class="line line1"></div>\
									<div class="text">\
										<span>'+statusMap[n.orderStatus]+'</span><br>\
										<span class="date" >'+n.recordTime+'</span>\
									</div>\
								</li>');
                            }
                            else if(n.orderStatus == 10){
                                if(data.orderStatus.indexOf(9) > -1 ){
                                    $('.step').append('<li class="item">\
										<span class="img_icon"></span>\
										<div class="line line1"></div>\
										<div class="text">\
											<span>'+statusMap[n.orderStatus]+'</span><br>\
											<span class="date" >'+n.recordTime+'</span>\
										</div>\
									</li>');
                                }else{
                                    $('.step').append('<li class="item">\
										<span class="img_icon"></span>\
										<div class="line">\
											<span class="progress"></span>\
											<i></i>\
										</div>\
										<div class="text">\
											<span>'+statusMap[n.orderStatus]+'</span><br>\
											<span class="date" >'+n.recordTime+'</span>\
										</div>\
									</li>');
                                    if(data.orderStatus.length == i + 1){
                                        addGrayRefund();
                                    }
                                }
                            }
                            else if(n.orderStatus == 9){
                                $('.step').append('<li class="item item2">\
										<span class="img_icon"></span>\
										<div class="text">\
											<span>'+statusMap[n.orderStatus]+'</span><br>\
											<span class="date" >'+n.recordTime+'</span>\
										</div>\
									</li>');
                            }
                        }
                        else if(data.orderStatus.indexOf(6) > -1 ){
                            if(n.orderStatus == 6){
                                $('.step').append('<li class="item item2">\
									<span class="img_icon"></span>\
									<div class="text">\
										<span>'+statusMap[n.orderStatus]+'</span><br>\
										<span class="date" >'+n.recordTime+'</span>\
									</div>\
								</li>');
                            }
                        }
                        else if(data.orderStatus.indexOf(9) > -1 && data.orderStatus.indexOf(10) == -1){
                            if(n.orderStatus == 9){
                                $('.step').append('<li class="item item2">\
									<span class="img_icon"></span>\
									<div class="text">\
										<span>'+statusMap[n.orderStatus]+'</span><br>\
										<span class="date" >'+n.recordTime+'</span>\
									</div>\
								</li>');
                            }else{
                                $('.step').append('<li class="item">\
									<span class="img_icon"></span>\
									<div class="line line1"></div>\
									<div class="text">\
										<span>'+statusMap[n.orderStatus]+'</span><br>\
										<span class="date" >'+n.recordTime+'</span>\
									</div>\
								</li>');
                            }
                        }
                        else{
                            if(data.orderStatus.length > i + 1){
                                $('.step').append('<li class="item">\
										<span class="img_icon"></span>\
										<div class="line line1"></div>\
										<div class="text">\
											<span>'+statusMap[n.orderStatus]+'</span><br>\
											<span class="date" >'+n.recordTime+'</span>\
										</div>\
									</li>');
                            }
                            if(data.orderStatus.length == i + 1){
                                if(data.orderStatus.indexOf(7) > -1){
                                    $('.step').append('<li class="item item2">\
											<span class="img_icon"></span>\
											<div class="text">\
												<span>'+statusMap[n.orderStatus]+'</span><br>\
												<span class="date" >'+n.recordTime+'</span>\
											</div>\
										</li>');
                                }
                                else{
                                    $('.step').append('<li class="item">\
											<span class="img_icon"></span>\
											<div class="line">\
												<span class="progress"></span>\
												<i></i>\
											</div>\
											<div class="text">\
												<span>'+statusMap[n.orderStatus]+'</span><br>\
												<span class="date" >'+n.recordTime+'</span>\
											</div>\
										</li>');
                                }
                                /* 添加未到达的步骤 */
                                if(i == 1){
                                    addGrayTicketSuccess();
                                    addGraySuccess();
                                }else if(n.orderStatus != 7){
                                    addGraySuccess();
                                }
                            }
                        }
                    }
                });
            }
        }
    });

    function addGrayPaySuccess(){
        $('.step').append('<li class="item">\
			<span class="img_icon img_icon1"></span>\
			<div class="line"></div>\
			<div class="text">\
				<span>支付成功</span><br>\
			</div>\
		</li>');
    };

    function addGrayTicketSuccess(){
        $('.step').append('<li class="item">\
			<span class="img_icon img_icon1"></span>\
			<div class="line"></div>\
			<div class="text">\
				<span>已确认</span><br>\
			</div>\
		</li>');
    };

    function addGraySuccess(){
        $('.step').append('<li class="item item2">\
			<span class="img_icon img_icon1"></span>\
			<div class="text">\
				<span>已完成</span><br>\
			</div>\
		</li>');
    };

    function addGrayRefund(){
        $('.step').append('<li class="item item2">\
			<span class="img_icon img_icon1"></span>\
			<div class="text">\
				<span>已退款</span><br>\
			</div>\
		</li>');
    };

});