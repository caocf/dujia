<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${aroundCity.id == null ? '添加':'编辑'}周边城市</title>
<%@ include file="../common/css.jsp"%>
<%@ include file="../common/js.jsp"%>
</head>
<body style="background: #afb8bf; overflow-x: hidden; overflow-y: scroll; *overflow-y: hidden">
	<form id="insertform">
		<table>
			<tr>
				<td background="${_domain}/images/RigNav.gif" height="31">
					<table>
						<tr>
							<td class="TextRig"><img src="${_domain}/images/nav_3.gif" width="11" height="7" /></td>
							<td style="width: 97%" class="NavText DText">您的位置:基础数据管理>${aroundCity.id == null ? '添加':'编辑'}周边城市</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div class="RightBg">
						<div class="RightTab">
							<table id="insertTable" style="border: 1;">
								<tr>
									<td style="display:none" data-key="id">${aroundCity.id }</td>
									<td style="width: 100px" class="TextCen"><span style="color: red">*</span>平台：</td>
									<td>
										<c:forEach varStatus="vstatus" var="o" items="${orderSource }">
											<input type="radio" id="plateform" name="plateform" data-key="plateform"  value="${o.value }" <c:if test="${aroundCity.plateform == o.value || ( aroundCity.plateform == null && vstatus.index == 0)}">checked="true"</c:if>>${o.name }</input>
										</c:forEach>
										<%-- <input type="radio" id="plateform" name="plateform" data-key="plateform"  value="2" <c:if test="${aroundCity.plateform == 2 }">checked="true"</c:if>>WAP</input>
										<input type="radio" id="plateform" name="plateform" data-key="plateform"  value="3" <c:if test="${aroundCity.plateform == 3 }">checked="true"</c:if>>APP</input> --%>
									</td>
								</tr>
								<tr>
									<td style="width: 100px" class="TextCen"><span style="color: red">*</span>展示频道：</td>
									<td>
										<c:forEach varStatus="vstatus" var="c" items="${channelTypes }">
											<input type="radio" id="type" name="type" data-key="type"  value="${c.value }" <c:if test="${aroundCity.type eq c.value || ( aroundCity.type eq null && vstatus.index == 0)}">checked="true"</c:if>>${c.name }</input>
										</c:forEach>
									</td>
								</tr>
								<tr>
									<td style="width: 100px" class="TextCen"><span style="color: red">*</span>出发城市:</td>
									<td>
										<input class="_CLS" id="cityName" name="cityName" data-key="cityName" value="${aroundCity.cityName }" type="text" style="width: 260px; height: 16px;" onkeyup="findCityList('city')" onFocus="findCityList('city');" onBlur="$('#cityDiv').hide();"/>
										<div id="cityDiv" style="position:fixed;line-height:22px; background: #FFFFFF; border:1px solid #C4C4C4;overflow :auto;width: 260px;height: 250px;display:none;" ></div>
										<input class="_CLS" id="cityId" name="cityId" data-key="cityId" value="${aroundCity.cityId }" type="hidden" style="width: 260px; height: 16px;" />
										<input class="_CLS" id="cityPinyin" name="cityPinyin" data-key="cityPinyin" value="${aroundCity.cityPinyin }" type="hidden" style="width: 260px; height: 16px;" />
									</td>
								</tr>
								<tr>
									<td style="width: 100px" class="TextCen"><span style="color: red">*</span>目的地城市:</td>
									<td>
										<input class="_CLS" id="aroundCityName" name="aroundCityName" data-key="aroundCityName" value="${aroundCity.aroundCityName }" type="text" style="width: 260px; height: 16px;"  onkeyup="findCityList('aroundCity')" onFocus="findCityList('aroundCity');" onBlur="$('#aroundCityDiv').hide();"/>
										<div id="aroundCityDiv" style="position:fixed;line-height:22px; background: #FFFFFF; border:1px solid #C4C4C4;overflow :auto;width: 260px;height: 250px;display:none;" ></div>
										<input class="_CLS" id="aroundCityId" name="aroundCityId" data-key="aroundCityId" value="${aroundCity.aroundCityId }" type="hidden" style="width: 260px; height: 16px;"  />
										<input class="_CLS" id="aroundCityPinyin" name="aroundCityPinyin" data-key="aroundCityPinyin" value="${aroundCity.aroundCityPinyin }" type="hidden" style="width: 260px; height: 16px;"  />
									</td>
								</tr>
								<tr id="tr_name">
									<td class="TextCen" style="width: 100px">展示位置:</td>
									<td>
										<input type="radio" id="aroundCity" name="aroundCity" data-key="aroundCity" <c:if test="${aroundCity.aroundCity == 0 || aroundCity.aroundCity==null }">checked="true"</c:if> value="0" onclick="onCkeck(0);">周边库</input>
										<input type="radio" id="aroundCity" name="aroundCity" data-key="aroundCity" <c:if test="${aroundCity.aroundCity == 1 }">checked="true"</c:if> value="1" onclick="onCkeck(1);">周边去哪玩</input>
										<input type="radio" id="aroundCity" name="aroundCity" data-key="aroundCity" <c:if test="${aroundCity.aroundCity == 2 }">checked="true"</c:if> value="2" onclick="onCkeck(2);">热门目的地</input>
									</td>
								</tr>
								<tr id="tr_pic1" <c:if test="${aroundCity.aroundCity == 0 || aroundCity.aroundCity == 2 || aroundCity.aroundCity==null }">hidden="true"</c:if>>
									<td class="TextCen" style="width: 100px"><span style="color: red">*</span>上传图片：</td>
									<td>
										<input type="file" name="file" id="scpic1" />
										<input name="sd" type="button" onclick="ajaxFileUpload(this)" style="width: 45px; height: 22px; color: #485058" value="上传" />
										<input id=imageUrl class="succFileName _CLS" data-key="imageUrl" value="${aroundCity.imageUrl }" type="hidden" /> 
										<span style="color: red" id="big">图片尺寸为:332×300px</span>&nbsp;&nbsp;
										<span class="resultLabel _CLS" id="resultLabel"></span>  
									</td>
									<input type="hidden" id="imgHeight" name="imgHeight" value="300" />
									<input type="hidden" id="imgWidth" name="imgWidth" value="332" />
								</tr>
								<tr id="tr_pic" <c:if test="${aroundCity.aroundCity == 0 || aroundCity.aroundCity == 2 || aroundCity.aroundCity==null }">hidden="true"</c:if>>
									<td class="TextCen" style="width: 100px">预览图：</td>
									<td id="pic1">
										<img id="viewImg" style="width: 300px; height: 200px" class="img _CLS" src="${aroundCity.imageUrl }" />
									</td>
								</tr>
								<tr id="keywordTr" <c:if test="${aroundCity.aroundCity == 0 || aroundCity.aroundCity == 2 || aroundCity.aroundCity==null }">hidden="true"</c:if>>
									<td style="width: 100px" class="TextCen"><span style="color: red">*</span>城市描述:</td>
									<td>
										<input class="_CLS" id="keyword" name="keyword" data-key="keyword" value="${aroundCity.keyword }" type="text" maxlength="10" style="width: 260px; height: 16px;" />
										<span id="sp_subtitle" >最多10个字</span>
									</td>
								</tr>
								<tr id="tr_name">
									<td class="TextCen" style="width: 100px">展示状态:</td>
									<td>
										<input type="radio" id="states" name="states" data-key="states"  value="true" <c:if test="${aroundCity.states == true || aroundCity.states == null }">checked="true"</c:if>>启用</input>
										<input type="radio" id="states" name="states" data-key="states"  value="false" <c:if test="${aroundCity.states == false}">checked="true"</c:if>>暂停</input>
									</td>
								</tr>
								<table>
									<tr>
										<td align="center">
											<input type="button" class="btn-submit" value="保存" style="width: 80px; height: 30px; color: #485058" onclick="save(insertTable);" />&nbsp; 
											<input name="returnBack" type="button" style="width: 80px; height: 30px; color: #485058" value="返回" onclick="javascript:history.go(-1);" />
										</td>
									</tr>
								</table>
							</table>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
<script type="text/javascript" src="${_domain}/js/json2.js"></script>	
<script type="text/javascript">
		//上传图片
		function ajaxFileUpload(obj) {
			// 上传图片宽高 
			var height= $("#imgHeight").val();
			var width = $("#imgWidth").val();
			
			var inputId = $(obj).prev("input").attr("id");
			var succr = $("#resultLabel");
			var succv = $("#imageUrl");
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
		function save(obj) {
			if (validate()) {
			var rowData = {};
			var data = tableDataToJson(rowData, obj);
			$.ajax({
					type : "POST",
					async : false,
					url : "${_domain}/admin/basedata/saveAroundCity.html",
					data : JSON.stringify(data),
					contentType : "application/json; charset=utf-8",
					success : function(data) {
						if (data == "success") {
							alert("保存成功!");
							window.location.href = '${_domain}/admin/basedata/aroundCityList.html';
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

		function validate() {
			var msg = "";
			var result = true;
			if ($("#cityName").val() == "" || $("#cityId").val() == "") {
				msg = msg + "请输入出发城市名称!\n";
				result = false;
			}
			if ($("#aroundCityName").val() == "" || $("#aroundCityId").val() == "") {
				msg = msg + "请输目的地城市名称!\n";
				result = false;
			}
			var aroundCityVal = $("input[name='aroundCity']:checked").val();
			if( aroundCityVal == 1 ){
				var imageUrlVal = $("#imageUrl").val();
				if( imageUrlVal == null || imageUrlVal == "" ){
					msg = msg + "请上传图片!\n";
					result = false;
				}
				var keywordVal = $("#keyword").val();
				if( keywordVal == null || keywordVal == "" ){
					msg = msg + "请输入城市描述!\n";
					result = false;
				}
			}
			if (msg != "")
				alert(msg);
			return result;
		}
		
		function onCkeck(type) {
			if( type == 1 ){
				$("#tr_pic").show();
				$("#tr_pic1").show();
				$("#keywordTr").show();
			}else{
				if( '${aroundCity.id }' == null || '${aroundCity.id }' == '' ){
					$("#imageUrl").val("");
					$("#viewImg").attr("src","");
					$("#resultLabel").text("");
					$("#keywordTr").val("");
					$("#keyword").val("");
				}
				$("#tr_pic").hide();
				$("#tr_pic1").hide();
				$("#keywordTr").hide();
			}
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
						}else{
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
			$("#"+key+"Pinyin").val(cityPinyin);
			$("#"+cityId+"_"+key+"_span").css("background-color","#FFD2A6").css("cursor","pointer");
		}
		function removeCss(cityId,key){
			$("#"+cityId+"_"+key+"_span").css("background-color","#FFFFFF");
		}
		<%--function loadListPage(){--%>
			<%--window.location.href = '${_domain}/admin/basedata/aroundCityList.html';--%>
		<%--}--%>
	</script>
</body>
</html>
