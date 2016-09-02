<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${advert.id == null ? '添加':'编辑'}度假推荐商品</title>
<%@ include file="../common/css.jsp"%>
<%@ include file="../common/js.jsp"%>
</head>
<body>
<body style="background: #afb8bf; overflow-x: hidden; overflow-y: scroll; *overflow-y: hidden">
	<form id="insertform" action="${_domain}/admin/advert/saveHomeFocs.html" method="post">
		<table>
			<tr>
				<td background="${_domain}/images/RigNav.gif" height="31">
					<table>
						<tr>
							<td class="TextRig"><img src="${_domain}/images/nav_3.gif" width="11" height="7" /></td>
							<td style="width: 97%" class="NavText DText">您的位置：度假广告管理/${advert.id == null ? '添加':'编辑'}度假推荐商品</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div class="RightBg">
						<div class="RightTab">
							<table id="insertTable" class="LBoder" style="border: 1;">
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>选择平台：<input type="hidden" id="plateformValue" value="${advert.plateform}"/></td>
									<td id="td_plateform"></td>
								</tr>
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>选择展示频道：<input type="hidden" id="typeValue" value="${advert.type}"/></td>
									<td id="td_type"></td>
								</tr>
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>展示城市：</td>
									<td class="NavLeft">
										<input class="_CLS" id="cityName" name="cityName" data-key="cityName" value="${advert.cityName }" type="text" style="width: 260px; height: 16px;" onkeyup="findCityList('city')" onFocus="findCityList('city');" onBlur="$('#cityDiv').hide();"/>
										<div id="cityDiv" style="position:fixed;line-height:22px; background: #FFFFFF; border:1px solid #C4C4C4;overflow :auto;width: 260px;height: 220px;display:none;" ></div>
										<input class="_CLS" id="cityId" name="cityId" data-key="cityId" value="${advert.cityId }" type="hidden" style="width: 260px; height: 16px;" />
									</td>
								</tr>
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>展示版块：<input type="hidden" id="moduleValue" value="${advert.module}"/></td>
									<td id="td_module"></td>
								</tr>
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>展示位置：<input type="hidden" id="positionValue" value="${advert.position}"/></td>
									<td id="td_position"></td>
								</tr>
								<tr id="tr_id" class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit">产品编号：</td>
									<td class="NavLeft">
									<input class="_CLS" id="productId" name="productId" data-key="productId"  type="text" style="width: 260px; height: 16px;" value="${advert.productId }" />
									<input type="button" class="btn-submit" value="检索" style="width: 80px; height: 30px; color: #485058" onclick="findProduct();" />
									</td>
								</tr>
								<tr id="tr_name" class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span id="cpid">关联产品名称</span>：</td>
									<td class="NavLeft">
										<input class="_CLS" id="productName" name="productName" data-key="productName"  type="text" style="width: 260px; height: 16px;" value="${advert.productName }" />
										<input type="hidden" id="productPrice" name="productPrice" data-key="productPrice" value="${advert.productPrice }"/>
										<input type="hidden" id="productCityId" name="productCityId" data-key="productCityId" value="${advert.productCityId }"/>
										<input type="hidden" id="productCityName" name="productCityName" data-key="productCityName" value="${advert.productCityName }"/>
									</td>
								</tr>
								<tr id="tr_pic1" class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit">上传图片：</td>
									<td class="NavLeft">
										<input type="file" name="file" id="scpic1" />
										<input name="sd" type="button" onclick="ajaxFileUpload(this)" style="width: 45px; height: 22px; color: #485058" value="上传" />
										<input id="succFileName" class="succFileName _CLS" type="hidden"  data-key="productImage" value="${advert.productImage }"/> 
										<span style="color: red" id="big"></span>&nbsp;&nbsp;
										<span class="resultLabel _CLS" id="resultLabel"></span>  
										<input type="hidden" id="imgHeight" name="imgHeight" />
										<input type="hidden" id="imgWidth" name="imgWidth" />
									</td>
								</tr>
								<tr id="tr_pic" class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit">预览图：</td>
									<td class="NavLeft" id="pic1">
										<img id="viewImg" style="width: 300px; height: 200px" class="img _CLS" src="${advert.productImage }" />
									</td>
								</tr>
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit">URL链接：</td>
									<td class="NavLeft"><input name="url" data-key="url"  type="text" class="_CLS" style="width: 260px; height: 16px;" value="${advert.url }" id="lianjie" /></td>
								</tr>
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>投放时间：</td>
									<td class="NavLeft"><input id="startTime" name="startTime" data-key="startTime" type="text" value="<fmt:formatDate value="${advert.startTime}" pattern="yyyy-MM-dd" />" onclick="WdatePicker()" class="Wdate _CLS" /></td>
								</tr>
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>结束时间：</td>
									<td class="NavLeft"><input id="endTime" name="endTime" data-key="endTime" class="Wdate _CLS" value="<fmt:formatDate value="${advert.endTime}" pattern="yyyy-MM-dd" />" type="text" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\',{d:0});}'})" /></td>
								</tr>
								<tr class="LBodTop">
									<td colspan="2" align="center"><input type="hidden" id="id" name="id" data-key="id" value="${advert.id}"/>
									<input type="hidden" id="states" name="states" data-key="states" value="${advert.states }"/>
									<input type="hidden" id="dataType" name="dataType" data-key="dataType" value="1"/>
									<input type="button" class="btn-submit" value="保存" style="width: 80px; height: 30px; color: #485058" onclick="save(insertTable);" />&nbsp; 
									<input name="returnBack" type="button" style="width: 80px; height: 30px; color: #485058" value="返回" onclick="javascript:history.go(-1);" />
									</td>
								</tr>
							</table>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
	//初始化方法
	$(function() {
		moduleRadioChange();
		plateformRadioChange();
		typeChange();
	})
	// 更改页面布局、图片尺寸提示 
	function changeTable() {
		$("#resultLabel").text("");
		var page = $("input:radio[name='module']:checked").val();
		if (page == 'F1') {//周边自由行
			var position = $("input:radio[name='position']:checked").val();
			if (position == 'F11') {//位置1
				$("#imgWidth").attr("value", 592);
				$("#imgHeight").attr("value", 295);
				$("#big").text("图片尺寸为:592×295px");
			}else{
				$("#imgWidth").attr("value", 0);
				$("#imgHeight").attr("value", 0);
				$("#big").text("");
			}
		}else if (page == 'S' || page == 'T') {//搜索什么都不处理
			$("#imgWidth").attr("value", 0);
			$("#imgHeight").attr("value", 0);
			$("#big").text("");
		}else{
			var position = $("input:radio[name='position']:checked").val();
			if (position == 'F21' || position == 'F31'|| position == 'F41'|| position == 'F51') {//位置1
				$("#imgWidth").attr("value", 288);
				$("#imgHeight").attr("value", 295);
				$("#big").text("图片尺寸为:288×295px");
			}else{
				$("#imgWidth").attr("value", 0);
				$("#imgHeight").attr("value", 0);
				$("#big").text("");
			}
		}
	}
	//动态初始化添加页面平台的值
	function plateformRadioChange() {
		$("#td_plateform").children().remove();
		var plateform_select = ($("#plateformValue").val()=='') ?0:$("#plateformValue").val();
		$.ajax({
			type : "POST",
			async : false,
			url : "${_domain}/admin/recommend/getRadioPlateform.html?selected="+plateform_select,
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				$("#td_plateform").append(data);
			},
			error : function() {
				$("#td_plateform").append('<option value="">无数据</option>');
			}
		});
		$("#plateformValue").val($("input:radio[name='plateform']:checked").val());
	}
	
	//动态初始化添加页面设置的值
	function moduleRadioChange() {
		$("#td_module").children().remove();
		var module_select = $("#moduleValue").val();
		$.ajax({
			type : "POST",
			async : false,
			url : "${_domain}/admin/recommend/getRadioModule.html?selected="+module_select,
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				$("#td_module").append(data);
			},
			error : function() {
				$("#td_module").append('<option value="">无数据</option>');
			}
		});
		chaneModule();
	}
	//改变展示页面值
	function chaneModule(){
		var b = false;
		if($("input:radio[name='module']:checked").val()==$("#moduleValue").val()){
			b=true;
		}
		$("#moduleValue").val($("input:radio[name='module']:checked").val());
		getRadioPositions(b);
		removeFirstPos();
	}
	
	//动态加载广告位置下拉选项
	function getRadioPositions(obj) {
		$("#td_position").children().remove();
		var plateform =$("input:radio[name='plateform']:checked").val();
		var module_select = $("#moduleValue").val();
		var pos_select ="";
		if(obj){
			pos_select = $("#positionValue").val();
		}
		$.ajax({
			type : "POST",
			async : false,
			url : "${_domain}/admin/recommend/getRadioPosition.html?plateform="+ plateform + "&module=" + module_select+"&selected="+pos_select,
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				$("#td_position").append(data);
			},
			error : function() {
				$("#td_position").append('<option value="">无数据</option>');
			}
		});
	}
	
	//动态初始化添加页面展示频道的值
	function typeChange() {
		$("#td_type").children().remove();
		var type_select = ($("#typeValue").val()=='') ?1:$("#typeValue").val();
		$.ajax({
			type : "POST",
			async : false,
			url : "${_domain}/admin/recommend/getRadioType.html?selected="+type_select,
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				$("#td_type").append(data);
			},
			error : function() {
				$("#td_type").append('<option value="">无数据</option>');
			}
		});
		$("#typeValue").val($("input:radio[name='channelType']:checked").val());
	}
	
	/*
	* 动态删除周边自由行位置1的产品编号，并将关联产品名称改为产品名称
	*/
	function removeFirstPos(){
		changeTable();
		if( $("#moduleValue").val()=='F1' && $("input:radio[name='position']:checked").val()=='F11'){
			$("#cpid").html("产品名称");
			$("#tr_id").hide();
		}else{
			$("#cpid").html("关联产品名称");
			$("#tr_id").show();
		}
	}
	
	
	function findProduct(){
		$.ajax({
			type : "POST",
			async : false,
			url : "${_domain}/admin/recommend/findProduct.html?productId="+ $("#productId").val(),
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				var json = eval('('+ data +')');
				if(json.status=='success'){
					if(json.product!='' || json.product!='underfined'){
						$("#productName").val(json.product.subName);
						$("#productPrice").val(json.product.productMinPrice);
						$("#succFileName").val(json.product.imageUrl);
						$("#productCityId").val(json.product.cityId);
						$("#productCityName").val(json.product.cityName);
					}
				}else{
					alert("检索失败");
				}
			},
			error : function() {
				alert("检索失败");
			}
		});
	}
	
	function findCityList(key) {
		$("#"+key+"Div").show();
		$.ajax({
				type : "POST",
				async : false,
				url : "${_domain}/admin/basedata/findCityInfoByName.html",
				data : {
					"cityName" : $("#"+key+"Name").val()
				},
				success : function(data) {
					var str = "";
					var city = eval("(" + data + ")");
					if( data =='[]' || city.length == 0 ){
						$("#"+key+"Id").val('');
						//$("#cityDiv").css("height",220);
					}else{
						/* if(city.length<10){
							$("#cityDiv").css("height",22*city.length);
						}else{
							$("#cityDiv").css("height",220);
						} */
					    for(var i=0; i<city.length; i++){
					    	str += "<span style='display:block;' id='"+city[i].id+"_"+key+"_span'onMouseOver='selectCityInfo("+city[i].id+",\""+city[i].name+"\",\""+key+"\",\""+city[i].pinyin+"\")' onMouseOut='removeCss("+city[i].id+",\""+key+"\")'>" + city[i].name + "</span>";
					    }
					}
				    $("#"+key+"Div").html(str);
				},
				error : function() {
					alert("提交失败!");
				}
			});
	}
	function selectCityInfo(cityId,cityName,key,cityPinyin){
		$("#"+key+"Id").val(cityId);
		$("#"+key+"Name").val(cityName);
		$("#"+cityId+"_"+key+"_span").css("background-color","#FFD2A6").css("cursor","pointer");
	}
	function removeCss(cityId,key){
		$("#"+cityId+"_"+key+"_span").css("background-color","#FFFFFF");
	}
	
	
	//上传图片
	function ajaxFileUpload(obj) {
		// 上传图片宽高 
		var height= $("#imgHeight").val();
		var width = $("#imgWidth").val();
		
		var inputId = $(obj).prev("input").attr("id");
		var succr = $("#resultLabel");
		var succv = $("#succFileName");
		$.ajaxFileUpload({
					url : "${_domain}/admin/advert/fileUpload.html?height="+height+"&width="+width,
					type : "POST",
					secureuri : false,
					fileElementId : inputId,
					dataType : "json",
					success : function(data) {
						if (data.result == "-1") {
							succr.text(data.msg).css("color", "#FF0000");
						} else {
							succr.text("上传成功").css("color", "#228B22");
							succv.val(data.url);
							$("#viewImg").attr("src",data.url);
						}
					},
					error : function(data) {
						console.debug(data);
						alert("出错了，请重新上传！");
					}
				});
		return false;
	}
	//保存
	function save(obj){
		if(validate()){
			//$("#insertform").submit();
			var rowData = {};
			var data = tableDataToJson(rowData, obj);
			$.ajax({
					type : "POST",
					async : false,
					url : "${_domain}/admin/recommend/saveRecommend.html",
					data : JSON.stringify(data),
					contentType : "application/json; charset=utf-8",
					success : function(data) {
						if (data == "success") {
							alert("保存成功!");
							window.location.href = '${_domain}/admin/recommend/recommendProList.html';
						} else if (data == "failure") {
							alert("保存失败!");
						} else {
							alert(data);
						}
					},
					error : function() {
						alert("提交失败!");
					}
				});
		}
	}
	//提交前数据校验
	function validate() {
		var msg = "";
		var result = true;
		var page = $("input[name='polintLocation']:checked").val();
		var position = $("input[name='position']:checked").val();
		
		if ($("#cityName").val() == "" || $("#cityId").val() == "") {
			msg = msg + "请输入展示城市!\n";
			result = false;
		}
		//if ($('#pic1').find('img').attr('src') == "") {
		//	msg = msg + "请上传推荐图片!\n";
		//	result = false;
		//}
//		if ($("#lianjie").val() == "") {
//			msg = msg + "请输入URL链接!\n";
//			result = false;
//		}
		if ($("#startTime").val() == "") {
			msg = msg + "请选择投放时间!\n";
			result = false;
		}
		if ($("#endTime").val() == "") {
			msg = msg + "请选择结束时间!\n";
			result = false;
		}
		if (msg != "")
			alert(msg);
		return result;
	}
</script>
</html>