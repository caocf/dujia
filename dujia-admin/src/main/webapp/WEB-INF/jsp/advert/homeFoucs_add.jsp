<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${advert.id == null ? '添加':'编辑'}度假广告</title>
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
							<td style="width: 97%" class="NavText DText">您的位置：度假广告管理/${advert.id == null ? '添加':'编辑'}度假广告</td>
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
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>展示页面：<input type="hidden" id="moduleValue" value="${advert.module}"/></td>
									<td id="td_module"></td>
								</tr>
								<tr class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>展示位置：<input type="hidden" id="positionValue" value="${advert.position}"/></td>
									<td id="td_position"></td>
								</tr>
								<tr id="tr_name" class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit">标题名称：</td>
									<td class="NavLeft"><input class="_CLS" id="name" name="titleName" data-key="titleName"  type="text" style="width: 260px; height: 16px;" value="${advert.titleName }" /></td>
								</tr>
								<tr id="tr_pic1" class="LBodTop">
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>上传图片：</td>
									<td class="NavLeft">
										<input type="file" name="file" id="scpic1" />
										<input name="sd" type="button" onclick="ajaxFileUpload(this)" style="width: 45px; height: 22px; color: #485058" value="上传" />
										<input id="succFileName" class="succFileName _CLS" type="hidden" data-key="productImage" value="${advert.productImage }"/> 
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
									<td style="width: 100px" class="TextRig LBodRit"><span style="color: red">*</span>URL链接：</td>
									<td class="NavLeft"><input name="url" data-key="url" type="text" class="_CLS" style="width: 260px; height: 16px;" value="${advert.url }" id="lianjie" /></td>
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
									<input type="hidden" id="dataType" name="dataType" data-key="dataType" value="0"/>
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
		if (page == 'A') {//首页轮播图
			$("#imgWidth").attr("value",1920);
			$("#imgHeight").attr("value",510);
			$("#big").text("图片尺寸为:1920×510px");			
		} else if (page == 'B') {//搜索页面
			$("#imgWidth").attr("value",1920);
			$("#imgHeight").attr("value",110);
			$("#big").text("图片尺寸为:1920×110px");		
		}
	}
	
	//动态初始化添加页面平台的值
	function plateformRadioChange() {
		$("#td_plateform").children().remove();
		var plateform_select = ($("#plateformValue").val()=='') ?0:$("#plateformValue").val();
		$.ajax({
			type : "POST",
			async : false,
			url : "${_domain}/admin/advert/getRadioPlateform.html?selected="+plateform_select,
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
			url : "${_domain}/admin/advert/getRadioModule.html?selected="+module_select,
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
		changeTable();
	}
	
	//动态加载广告位置下拉选项
	function getRadioPositions(obj) {
		$("#td_position").children().remove();
		var plateform =$("input:radio[name='plateform']:checked").val();
		var module_select = $("#moduleValue").val();
		var pos_select = "";
		if(obj){
			pos_select = $("#positionValue").val();
		}
		$.ajax({
			type : "POST",
			async : false,
			url : "${_domain}/admin/advert/getRadioPosition.html?plateform="+ plateform + "&module=" + module_select+"&selected="+pos_select,
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
			url : "${_domain}/admin/advert/getRadioType.html?selected="+type_select,
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
					url : "${_domain}/admin/advert/saveHomeFocs.html",
					data : JSON.stringify(data),
					contentType : "application/json; charset=utf-8",
					success : function(data) {
						if (data == "success") {
							alert("保存成功!");
							window.location.href = '${_domain}/admin/advert/homeFocusList.html';
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
		
		//if ($("#name").val() == "") {
		//	msg = msg + "请输入广告标题!\n";
		//	result = false;
		//}
		if ($('#pic1').find('img').attr('src') == "") {
			msg = msg + "请上传广告图片!\n";
			result = false;
		}
		if ($("#lianjie").val() == "") {
			msg = msg + "请输入URL链接!\n";
			result = false;
		}
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