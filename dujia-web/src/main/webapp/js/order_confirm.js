$(function(){
	var inf = new infoMain();
	cleanPage();
	calculationDate();
	
	/**
	 * 清空已经填写信息
	 */
	function cleanPage(){
		//首次加载清空已经选择的常用联系人信息
		$.grep($(".concat_person .mr24"), function(item, key){
			if($(item).find('input[type=radio]')[0].checked)
				$(item).find('input[type=radio]')[0].checked=false;
		});
		$(".userNname").val("");
		$(".userMobile").val("");
		$(".userEmail").val("");
		//首次加载清空已经选择的出游人信息
		$.grep($(".personform_info_title .mr24"), function(item, key){
			if($(item).find('input[type=checkbox]')[0].checked)
				$(item).find('input[type=checkbox]')[0].checked=false;
		});
	}
	
	/**
	 * 计算酒店景点开始结束时间
	 */
	function calculationDate(){
		var d = new Date($("#_packageSaleDate").val().replace(/-/g,'/'));
		var year = d.getFullYear();
		var month = d.getMonth() + 1;
		month = (month < 10) ? ('0'+ month) : (month + 1);
		var date = d.getDate();
		/**
		 * 计算酒店开始结束时间
		 */
		$.grep($(".life .J_live tbody tr"), function(item, key){
			var item = $(item).find(".useDay");
			var timeDate= new Date(d),time;
			var useDays = $(item).text().split(",");
			if(useDays.length > 0){
				for(var i = 0; i < useDays.length; i++){
					if(i == 0){
						timeDate.setDate(d.getDate()+(Number(useDays[i]) - 1));
						var month = timeDate.getMonth() + 1;
						time = timeDate.getFullYear() + "-" + ((month < 10) ? ('0'+ month) : month) + "-" + ( (timeDate.getDate() < 10) ? ('0'+ timeDate.getDate()) : timeDate.getDate() );
						if(key == 0){
							$(item).prev().find('input').val(time);
						}
						else{
							$(item).prev().find('span').text(time);
						}
					}
					if(useDays.length-1 == i){
						timeDate.setDate(d.getDate()+Number(useDays[i]));
						var month = timeDate.getMonth() + 1;
						time = timeDate.getFullYear() + "-" + ((month < 10) ? ('0'+ month) : month) + "-" + ( (timeDate.getDate() < 10) ? ('0'+ timeDate.getDate()) : timeDate.getDate() );
						$(item).next().find("span").text(time);
					}
				}
			}
		});
		//获取酒店景点时间
		inf.getDate({Jurl:"/order/getResProductVerifyDate.html?packageId="+$("#_packageId").val()+"&saleDate="+$("#_packageSaleDate").val(), Jtype:"POST",Jback: function() {
			var resData = this.data.data;
			if(this.data.code == 'C'){
				confirm(this.data.message, function(){
					//当获取景点日期失败时，尝试重新拉取价格，看是否售完状态
					getPackagePrice($("#_packageSaleDate").val().replace(/\//g,'-'));
				},function(){
					location.href = "/"+$("#_productId").val()+".html";
				});
			}
			else if(this.data.code == 'Y' && null != resData && resData.length > 0){
				for(var i = 0; i < resData.length; i++){
					if(resData[i].ResType == 0){
						/**
						 * 计算酒店开始结束时间
						 */
//						$.grep($(".life .J_live tbody tr"), function(item, key){
//							if(resData[i].ResourceId == $(item).find('.resourceId').text()){
//								for(var j = 0; j < resData[i].listDays.length; j ++ ){
//									var d = new Date(parseInt(resData[i].listDays[j].substr(6,18)));
//									var year = d.getFullYear();
//									var month = d.getMonth() + 1;
//									var day = d.getDate();
//									var timeDate = new Date(d),time;
//									var month = timeDate.getMonth() + 1;
//									time = timeDate.getFullYear() + "-" + ((month < 10) ? ('0'+ month) : month) + "-" + ((day < 10) ? ('0'+ day) : day);
//									if(j == 0){
//										$(item).find('.useStartTime').val(time);
//									}
//									else if(j == resData[i].listDays.length - 1){
//										$(item).find(".useEndTime").text(time);
//									}
//								}
//								
//							}
//						});
					}
					else if(resData[i].ResType == 1){
						/**
						 * 计算景点开始结束时间
						 */
						$.grep($(".life .J_play tbody tr"), function(item, key){
							if(resData[i].ResourceId == $(item).find('.resourceId').text()){
								for(var j = 0; j < resData[i].listDays.length; j ++ ){
									if(j == 0){
										$(item).find('select').empty();
										$(item).find('select').append($("<option>"+"请选择日期"+"</option>"));
									}
									var d = new Date(parseInt(resData[i].listDays[j].substr(6,18)));
									var year = d.getFullYear();
									var month = d.getMonth() + 1;
									var day = d.getDate();
									var timeDate = new Date(d),time;
									var month = timeDate.getMonth() + 1;
									time = timeDate.getFullYear() + "-" + ((month < 10) ? ('0'+ month) : month) + "-" + ((day < 10) ? ('0'+ day) : day);
									$(item).find('select').append($("<option>"+time+"</option>"));
								}
							}
						});
					}
				}
			}
		}});
	}
	
	/**
	 * 输入信息时验证
	 */
	var regTel = /^1[3|5|7|8]\d{9}$/;   //手机号
	var regTelMosaic = /^1[3|5|7|8]\d{1}\*{4}\d{4}$/;   //手机号加密后
    var regIdCard = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[X])$/;   //身份证号
    var regIdCardMosaic = /^[1-9]\d{3}\*{12}(\d{2}|\d{1}[X])$/;   //身份证号加密后
    var regName = /^[\u4e00-\u9fa5]{2,5}$/;	//姓名
    var regEmail = /^[a-zA-Z0-9]*[-_\.]?[a-zA-Z0-9]+@[a-zA-Z0-9]*.[a-zA-Z0-9]*$/;	//邮箱
    var regEmailMosaic = /^[a-zA-Z0-9]*\*{0,3}[-_\.]?\*{0,3}[a-zA-Z0-9]?\*{0,3}@[a-zA-Z0-9]*.[a-zA-Z0-9]*$/;
    var msgBox = "";
    $(".person_info").on("blur", ".name", function(){
    	msgBox = $(this).next(".msg");
        if($(this).val() == ""){
            errorMsg($(this),"姓名不能为空");
        }else if(!regName.test($(this).val())){
        	errorMsg($(this),"姓名格式不正确");
        }else{
            msgBox.remove();
        }
    });
    $(".person_info").on("blur", ".mobile", function(){
    	msgBox = $(this).next(".msg");
        if($(this).val() == ""){
            errorMsg($(this),"手机号不能为空");
        }else if((undefined == $(this).closest('ul').attr('onlyid') && !regTel.test($(this).val())) || 
        		(undefined != $(this).closest('ul').attr('onlyid') && !(regTelMosaic.test($(this).val()) || regTel.test($(this).val())))){
            errorMsg($(this),"手机号格式不正确");
        }else{
            msgBox.remove();
        }
    });
    $(".person_info").on("blur", ".email", function(){
    	msgBox = $(this).next(".msg");
        if($(this).val() != "" && (!regEmail.test($(this).val()))){
            errorMsg($(this),"请输入正确的邮箱");
        }
        else{
            msgBox.remove();
        }
    });
    $(".person_info").on("blur", ".certificateCode", function(){
    	msgBox = $(this).next(".msg");
        if($(this).val() == ""){
            errorMsg($(this),"身份证号不能为空");
        }else if((undefined != $(this).closest('ul').attr('onlyid') && !(regIdCard.test($(this).val()) || regIdCardMosaic.test($(this).val())))
        		|| (undefined == $(this).closest('ul').attr('onlyid') && !regIdCard.test($(this).val()))){
            errorMsg($(this),"请输入正确的身份证号");
        }else{
            msgBox.remove();
        }
    });
    
    function errorMsg(obj,msg){
		$(obj).each(function(i){
			var msgBox = $(this).parent("span").find(".msg");
			if(msgBox.length != 0){
				msgBox.html("<i></i>"+msg);
			}else{
				$(this).after("<span class='msg'><i></i>"+msg+"</span>");
			}
		});
    }
    
    /**
     * 提交订单前验证
     */
    function check(){
    	var result = true;
    	/*
    	 * 联系人信息验证
    	 */
    	//联系人姓名
    	var cls = $('.concat_person .name');
    	if(cls.size() > 0){
    		$.grep($(cls), function(item){
    			if($(item).val() == ""){
    				result = false;
		        	errorMsg($(item),"姓名不能为空");
		        }
    			else if(!regName.test($(item).val())){
    				result = false;
    	    		errorMsg($(item), "姓名格式不正确");
    	    	}
			});
    	}
    	//联系人电话
    	cls = $('.concat_person .mobile');
    	if(cls.size() > 0){
    		$.grep($(cls), function(item){
    			if($(item).val() == ""){
    				result = false;
		        	errorMsg($(item),"手机号不能为空！");
		        }
    			else if((undefined != $(item).closest('ul').attr('onlyid') && !(regTel.test($(item).val()) || regTelMosaic.test($(item).val()))) || 
    					(undefined == $(item).closest('ul').attr('onlyid') && !regTel.test($(item).val()))){
    				result = false;
    	    		errorMsg($(item), "手机号格式不正确！");
    	    	}
			});
    	}
    	//联系人邮箱
    	cls = $('.concat_person .email');
    	if(cls.size() > 0){
    		$.grep($(cls), function(item){
    			if($(item).val() != "" && ((undefined != $(item).closest('ul').attr('onlyid') && !(regEmail.test($(item).val()) || regEmailMosaic.test($(item).val()))) || 
    					(undefined == $(item).closest('ul').attr('onlyid') && !regEmail.test($(item).val())))){
    				result = false;
    	    		errorMsg($(item), "邮箱格式不正确！");
    			}
			});
    	}
    	/*
    	 * 常用出游人信息验证
    	 */
    	//出游人姓名
    	cls = $('.trip_person .name');
    	if(cls.size() > 0){
    		$.grep($(cls), function(item){
    			if($(item).val() == ""){
    				result = false;
		        	errorMsg($(item),"姓名不能为空！");
		        }
    			else if(!regName.test($(item).val())){
    				result = false;
    	    		errorMsg($(item), "姓名不能为空！");
    	    	}
			});
    	}
    	//出游人电话
    	cls = $('.trip_person .mobile');
    	if(cls.size() > 0){
    		$.grep($(cls), function(item){
    			if($(item).val() == ""){
    				result = false;
		        	errorMsg($(item),"手机号不能为空！");
		        }
    			else if((undefined != $(item).closest('ul').attr('onlyid') && !(regTel.test($(item).val()) || regTelMosaic.test($(item).val()))) || 
    					(undefined == $(item).closest('ul').attr('onlyid')) && !regTel.test($(item).val())){
    				result = false;
    	    		errorMsg($(item), "手机号格式不正确！");
    	    	}
			});
    	}
    	//出游人身份证
    	cls = $('.trip_person .certificateCode');
    	if(cls.size() > 0){
    		$.grep($(cls), function(item){
    			if($(item).val() == ""){
    				result = false;
		        	errorMsg($(item),"身份证不能为空！");
		        }
    			else if((undefined != $(item).closest('ul').attr('onlyid') && !(regIdCardMosaic.test($(item).val()) || regIdCard.test($(item).val()))) || 
    					(undefined == $(item).closest('ul').attr('onlyid') && !regIdCard.test($(item).val()))){
    				result = false;
    	    		errorMsg($(item), "身份证格式不正确！");
    	    	}
			});
    	}

        // 履行协议
		cls = $("#deal_confirm");
		if(!cls.attr("checked")){
			result = false;
			alert("请阅读并同意 《委托服务协议书使用说明》");
		}
		return result;
    }
    
    /**
	 * 出游人数
	 * 
	 * 点击修改
	 */
	var maxNum = 10;
	var minNum = 1;
	var regAdd = /^\w*add$/;
	var regDult = /^adult\w*$/;
	var regInteger = /^[0-9]*[1-9][0-9]*$/;
	$('.adult_num_add, .adult_num_del, .child_num_add, .child_num_del').click(function(){
		var oldNum;
		var cls;
		//判断成人还是儿童
		var def = $("#_adult_num").val();
		if(!regDult.test($(this).attr('class'))){
			def = $("#_child_num").val();
		}
		if(regAdd.test($(this).attr('class'))){
			if($(this).prev().val() < maxNum*def) {
				cls = $(this).prev();
				oldNum = $(cls).val();
				oldNum = Number(oldNum) + 1;
			}
		}
		else{
			if($(this).next().val() > minNum){
				cls = $(this).next();
				oldNum = $(cls).val();
				oldNum = Number(oldNum) - 1;
			}
		}
		if(cls){
			$(cls).val(oldNum);
			personChange();
		}
	});
	
	/**
	 * 出游人数
	 * 
	 * 手动修改
	 */
	$("#adultNum, #childNum").change(function(){
		var def = $("#_adult_num").val();
		if(!regDult.test($(this).attr('id'))){
			def = $("#_child_num").val();
		}
		if(regInteger.test($(this).val())){
			//判断成人还是儿童
			if($(this).val() < minNum || $(this).val() > maxNum){
				$(this).val(def);
			}
		}
		else{
			$(this).val(def);
		}
		personChange();
	});
	
	/**
	 * 出游人数
	 * 
	 * 计算数量
	 */
	function personChange(){
		var buyCount;
    	var dultBuyCount = parseInt($("#adultNum").val()/$("#_adult_num").val()) + ($("#adultNum").val()%$("#_adult_num").val()==0?0:1);
    	if($("#_child_num").val() != 0 && $("#childNum")){
    		var childBuyCount = parseInt($("#childNum").val()/$("#_child_num").val()) + ($("#childNum").val()%$("#_child_num").val()==0?0:1);
    		buyCount = dultBuyCount > childBuyCount ? dultBuyCount : childBuyCount;
    	}
    	else{
    		$("#childNum").val("0");
    		buyCount = dultBuyCount;
    	}
    	//加减购买数量修改
    	$("#buyCount").val(buyCount);
    	//右上方费用信息购买数量修改
    	$(".fee .buyCount").text(buyCount);
    	$(".orderTotalAmount").text(parseFloat($("#buyCount").val()*$("#_tc_direct_price").val()).toFixed(2));
	};
    
	/**
	 * 购买数量
	 * 
	 * 点击修改
	 */
	$('.total_num_add, .total_num_del').click(function(){
		var buyCount = $("#buyCount").val();
		var newNum;
		if(regAdd.test($(this).attr('class')) && $("#buyCount").val() < maxNum){
			newNum = Number(buyCount) + 1;
		}
		else{
			if($(this).next().val() > minNum){
				newNum = Number(buyCount) - 1;
			}
		}
		if(newNum){
			$("#buyCount").val(newNum);
			$(".fee .buyCount").text(newNum);
			buyCountChange();
		}
	});
	
	/**
	 * 购买数量
	 * 
	 * 手动修改
	 */
	$("#buyCount").change(function(){
		if(regInteger.test($(this).val()) && $(this).val() >= minNum && $(this).val() <= maxNum){
			$(".buyCount").text($(this).val());
			$(this).val($(this).val());
		}
		else{
			$(".buyCount").text("1");
			$(this).val("1");
		}
		buyCountChange();
	});
	
	/**
	 * 购买数量
	 * 
	 * 计算数量
	 */
	function buyCountChange(){
    	$("#adultNum").val(parseInt($("#buyCount").val() * $("#_adult_num").val()));
    	if($("#_child_num").val() != 0 && $("#childNum")){
    		$("#childNum").val(parseInt($("#buyCount").val() * $("#_child_num").val()));
    	}
    	$(".orderTotalAmount").text(parseFloat($("#buyCount").val()*$("#_tc_direct_price").val()).toFixed(2));
	}
	
	/**
	 * 日历
	 */
	$("#J_Calender").parent("span.time_container").click(function(){
		var $this = $(this);
		$this.addClass("focus");
        inf.getDate({Jurl: "/api/package/queryPrice/"+$("#_productId").val()+"/"+$("#_packageId").val()+".json", Jtype:"GET",Jback: function(){
            pickerEvent.setPriceArr(this.data);
            pickerEvent.Init("J_Calender",function(date, price){
            	AlertText.tips("d_load", "");
            	$("#order_submit_pay").addClass("forbidden");
            	updateDateAndPrice(date, price);
//            	getPackagePrice(date);
				$this.removeClass("focus");
            });

        }});
    });
	$(document).bind("click", function (event) {
		var e = event || window.event;
		var elem = e.srcElement || e.target;

		if( !$(elem).parents().is(".time_container") && !$(elem).parents().is("#calendar_choose")){
			$("#J_Calender").parent("span.time_container").removeClass("focus");
		}
	});
	
	function updateDateAndPrice(saleDate, directPrice){
		$("#_tc_direct_price").val(directPrice);
		$("#_packageSaleDate").val(saleDate);
		$("#singleprice").text("¥" + directPrice);
		var fenshu = $("#buyCount").val();
		$(".orderTotalAmount").text(parseFloat(Number(fenshu) * directPrice).toFixed(2));
		calculationDate();
		$("#order_submit_pay").removeClass("forbidden");
		AlertText.closeTips();
	}
	
	//重新获取价格
	function getPackagePrice(date){
		inf.getDate({Jurl:"/order/getPackagePrice.html?productId="+$("#_productId").val()+"&packageId="+$("#_packageId").val()+"&saleDate="+date, Jtype:"POST",Jback: function() {
			if(null != this.data.data){
				updateDateAndPrice(this.data.data.packDate, this.data.data.TcDirectPrice)
			}
			else if(this.data.code== 'C'){
				confirm(this.data.message, function(){
					getPackagePrice(date);
				},function(){
					location.href = "/"+$("#_productId").val()+".html";
				});
			}
			else if(this.data.code== 'N'){
				alert(this.data.message, function(){
					location.href = "/"+$("#_productId").val()+".html";
				});
			}
			else{
				alert('系统繁忙，请稍后再试！',function(){
					location.href = "/"+$("#_productId").val()+".html";
				});
			}
		}
		});
		return true;
	}


	// 联系人信息交互
	var $concatpersonBox = $("#concat_person");
	$concatpersonBox.find("input[type=radio]").click(function () {
		var infoDoc = $(this).closest("li");
		var name = infoDoc.attr("data_name"),mobile=infoDoc.attr("data_mobile"),email=infoDoc.attr("data_email");
		$concatpersonBox.attr("onlyid",infoDoc.attr("onlyid"));
		$concatpersonBox.find(".userNname").val(name);
		$concatpersonBox.find(".userMobile").val(mobile);
		$concatpersonBox.find(".userEmail").val(email);
		$concatpersonBox.find(".msg").remove();
	});



	// 出游人信息交互
	var $trip_person = $(".personform_info_title");

	$trip_person.find("input[type=checkbox]").click(function () {
		var infoDoc = $(this).closest("li");
		var name = infoDoc.attr("data_name"),mobile=infoDoc.attr("data_mobile"),email=infoDoc.attr("data_email"),certificateCode = infoDoc.attr("data_certifi"),onlyid= infoDoc.attr("onlyid");
		if($(this).attr("checked") != "checked"){
			$(".trip_person[onlyid='"+onlyid+"']").remove();
			return;
		}
		var data = {name:name,mobile:mobile,certificateCode:certificateCode,_need_card:$("#_need_card").val(),onlyid:onlyid}
		$(".personform_info_title").after(template("travelperson", data));
	});




	$(".newperson").click(function(){

		var data = {name:"",mobile:"",certificateCode:"",_need_card:$("#_need_card").val(),onlyid:""};
		$(".personform_info_title").after(template("travelperson", data));
		if($(".personform_info_title").find(".trip_person").length == 9){
			$(this).hide();
		}
	})

	/**
	 * 提交订单，立即支付
	 */
	$("#order_submit_pay").click(function() {
		if($("#order_submit_pay").attr("class") == "forbidden"){
			return;
		}
		AlertText.tips("d_load", "");
		var result = true;
		$("#order_submit_pay").addClass("forbidden");
		if(!check()){
			AlertText.closeTips();
			$("#order_submit_pay").removeClass("forbidden");
			return;
		}
		/**
		 * 组装提交订单参数
		 */
		//联系人
		var userConcat = {
			'id' :$concatpersonBox.attr("onlyid"),
			'name'  : $(".userNname").val(),//联系人姓名
			'mobile': $(".userMobile").val(),//联系人邮箱
            'email' : $(".userEmail").val()//联系人邮箱
		};
		//基础数据
		var data = {
			'productId' : $("#_productId").val(), //线路id
			'packageId' : $("#_packageId").val(),  //套餐id
			'adultNum' : $("#adultNum").val(), //成人数
			'childNum' : $("#childNum").val(), //儿童数
			'buyCount' : $("#buyCount").val(), //购票数量
			'saleDate' : $("#J_Calender").val() == undefined ? $("#_packageSaleDate").val().replace(/\//g,'-') : $("#J_Calender").val(),//出游日期
			'userConcat' : userConcat //联系人信息
		};
		//酒店、景点对象
		var orderDetails = [];
		$(".life tbody tr").each(function(i){
			var useStartTime = $(this).find(".useStartTime").val();//开始日期
			if(useStartTime == ''){
				useStartTime = $(this).find(".useStartTime").text();
			}
			if(useStartTime  == '' || '请选择日期' == $(this).find(".useStartTime").val()){
				var obj = $(this).find(".useStartTime");
				alert('请选择出行时间', function(){
					obj.focus();
				});
				result = false;
			}
			var resourceId = $(this).find(".resourceId").text();//资源id
			var useEndTime = $(this).find(".useEndTime").text();//结束日期
			if('' != useEndTime){
				useEndTime = useEndTime;
			}
			orderDetails.push({
				'resourceId':resourceId,
				'useStartTime':useStartTime,
				'useEndTime':useEndTime
			});
		});
		if(!result){
			AlertText.closeTips();
			$("#order_submit_pay").removeClass("forbidden");
			return;
		}
		data['orderDetails'] = orderDetails;
		//出游人
		var travelerList = [];
		$(".trip_person").each(function(i){
			var name = $(this).find(".name").val(),mobile = $(this).find(".mobile").val(),certificateCode = $(this).find(".certificateCode").val();
			var id = $(this).attr("onlyid");
			travelerList.push({
				id:id,
				'name' :name,//出游人姓名
				'mobile' : mobile,//出游人手机号
				'certificateCode':certificateCode//出游人身份证号
			})
		})
		if(travelerList.length == 0){
			alert("请添加出行人信息！");
			AlertText.closeTips();
			$("#order_submit_pay").removeClass("forbidden");
			return;
		}
		data['travelerList'] = travelerList;
		
		if((undefined == $("#childNum").val() && travelerList.length > Number($("#adultNum").val())) || 
				(undefined != $("#childNum").val() && travelerList.length > (Number($("#adultNum").val()) + Number($("#childNum").val())))){
			alert("出游人数超出套餐最大人数，请修改！");
			AlertText.closeTips();
			$("#order_submit_pay").removeClass("forbidden");
			return;
		}

		/**
		 * 最后验价
		 */
		var saleDate = $("#J_Calender").val() == undefined ? $("#_packageSaleDate").val().replace(/\//g,'-') : $("#J_Calender").val();
		inf.getDate({Jurl:"/order/getPackagePrice.html?productId="+$("#_productId").val()+"&packageId="+$("#_packageId").val()+"&saleDate="+saleDate, Jtype:"POST",Jback: function() {
			if(this.data.code == 'Y'){
				if($("#_tc_direct_price").val() == this.data.data.TcDirectPrice){
					orderSubmit(data);
				}
			}
			else if(this.data.code == 'C'){
				confirm(this.data.message, function(){
					getPackagePrice(saleDate);
					orderSubmit(data);
				},function(){
					location.href = "/"+$("#_productId").val()+".html";
				});
			}
			else{
				alert(this.data.message,function(){
					location.href = "/"+$("#_productId").val()+".html";
				});
			}
		}
		});
	});
});

function orderSubmit(orderDt){
	/**
	 * 开始提交订单了
	 */
	$.ajax({
		type : "POST",
		async : true,
		dataType: "json",
		url : "/order/submits.html",
		data : {'gome_token_name':$("#gome_token_name").val(), 'orderDtoStr':JSON.stringify(orderDt)},
		success : function(data) {
			if(data.code == "Y") {
				//订单提交成功，跳转到支付页面
				AlertText.closeTips();
				window.location.href = data.data;
			}else{
				if(data.code=='C' ){// 变价后提交
					AlertText.closeTips();
					confirm(this.data.message, function(){
						orderSubmit(data);
					},function(){
						location.href = "/"+$("#_productId").val()+".html";
					});
				}else{
					AlertText.closeTips();
					alert(data.message,function(){
						location.href = "/"+$("#_productId").val()+".html";
					});
				}
			}
		}
	});
}
function delTralP(dom){
	var inf = new infoMain();
	var $delBlock = $(dom).closest(".trip_person"),
		onlyid = $delBlock.attr("onlyid");
	if(onlyid != ""){
		inf.getDate({Jurl:"/travel/del/index.html",Jtype:"GET",Jdat:{id:onlyid},Jback:function(){
			$delBlock.remove();
			$(".personform_info_title").find("li[onlyid='"+onlyid+"']").remove();
			$("#newperson").show();
		}});
	}else{
		$delBlock.remove();

		$("#newperson").show();
	}
}
Date.prototype.format = function(fmt)
{ //author: meizz
	var o = {
		"M+" : this.getMonth()+1,                 //月份
		"d+" : this.getDate(),                    //日
		"h+" : this.getHours(),                   //小时
		"m+" : this.getMinutes(),                 //分
		"s+" : this.getSeconds(),                 //秒
		"q+" : Math.floor((this.getMonth()+3)/3), //季度
		"S"  : this.getMilliseconds()             //毫秒
	};
	if(/(y+)/.test(fmt))
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	for(var k in o)
		if(new RegExp("("+ k +")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	return fmt;
}