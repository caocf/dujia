<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>周边城市管理</title>
<%@ include file="../common/css.jsp"%>
<%@ include file="../common/js.jsp"%>

</head>
<body style="background: #afb8bf; overflow-x: hidden; overflow-y: scroll; *overflow-y: hidden">
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td background="${_domain}/images/RigNav.gif" height="31">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td class="TextRig"><img src="${_domain}/images/nav_3.gif" style="width: 11" height="7" /></td>
						<td style="width: 97%" class="NavText">您的位置：基础数据管理&gt;周边城市管理</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div class="RightBg">
					<div class="RightTab">
						<form action="${_domain}/admin/basedata/aroundCityList.html" method="post" id="pageform" name="aroundCityForm">
							<table id="searchTable">
								<tr>
									<td>
										<span style="margin-left: 50px;">出发城市: </span> 
										<input type=text id="cityName" name="cityName" value="${aroundCity.cityName}" onkeyup="findCityList('city')" onFocus="findCityList('city');" onBlur="$('#cityDiv').hide();"/>
										<div id="cityDiv" style="margin-left: 103px;position:fixed;line-height:22px; background: #FFFFFF; border:1px solid #C4C4C4;overflow :auto;width: 150px;height: 250px;display:none;" ></div>
									</td>
									<td>
										<span style="margin-left: 5px;">周边城市: </span> 
										<input type=text id="aroundCityName" name="aroundCityName" value="${aroundCity.aroundCityName}" onkeyup="findCityList('aroundCity')" onFocus="findCityList('aroundCity');" onBlur="$('#aroundCityDiv').hide();"/>
										<div id="aroundCityDiv" style="margin-left: 58px;position:fixed;line-height:22px; background: #FFFFFF; border:1px solid #C4C4C4;overflow :auto;width: 150px;height: 250px;display:none;"  ></div>
									</td>
									<td>
										<span>展示位置:</span>
										<select id="aroundCity" name="aroundCity">
											<option value=""  <c:if test="${empty aroundCity.aroundCity}">selected="selected"</c:if>>全部</option>
											<option value="0" <c:if test="${aroundCity.aroundCity ==0 }">selected="selected"</c:if>>周边库</option>
											<option value="1" <c:if test="${aroundCity.aroundCity ==1 }">selected="selected"</c:if>>周边去哪玩</option>
											<option value="2" <c:if test="${aroundCity.aroundCity ==2 }">selected="selected"</c:if>>热门目的地</option>
										</select>
									</td>
									<td>
										<span>展示状态</span>
										<select id="states" name="states">
											<option value=""  <c:if test="${empty aroundCity.states}">selected="selected"</c:if>>全部</option>
											<option value="0" <c:if test="${aroundCity.states ==false }">selected="selected"</c:if>>暂停</option>
											<option value="1" <c:if test="${aroundCity.states ==true }">selected="selected"</c:if>>启用</option>
										</select>
									</td>
									<td colspan="3">
										<input style="width: 80px; height: 30px; color: #485058" type="button" onclick="submitForm();" value="查询" /> 
										<input style="width: 80px; height: 30px; color: #485058;margin-right: 30px;" type="button" onclick="toInsertAroundCity();" value="添加"/>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div class="H10"></div>
					<div class="RightTab">
						<table>
							<tr>
								<td colspan="2">
									<table class="LBoder">
										<thead>
											<tr class="TextCen fB GDDray">
												<td style="width: 47px" class="LBodRit">序号</td>
												<td style="width: 130px" class="LBodRit">出发城市</td>
												<td style="width: 100px" class="LBodRit">周边城市</td>
												<td style="width: 100px" class="LBodRit">城市描述</td>
												<td style="width: 100px" class="LBodRit">城市图片</td>
												<td style="width: 100px" class="LBodRit">展示位置</td>
												<td style="width: 100px" class="LBodRit">最后操作人</td>
												<td style="width: 100px" class="LBodRit">最后操作时间</td>
												<td style="width: 100px" class="LBodRit">展示状态</td>
												<td style="width: 60px">操作</td>
											</tr>
										</thead>
										<tbody>
											<c:forEach varStatus="vstatus" var="aroundCity" items="${pageInfo.pageList }">
												<tr class="TextCen LBodTop">
														<td class="LBodRit LBodTop">${vstatus.count }</td>
													<td class="LBodRit LBodTop">${aroundCity.cityName}</td>
													<td class="LBodRit LBodTop">${aroundCity.aroundCityName }</td>
													<td class="LBodRit LBodTop">${aroundCity.keyword }</td>
													<td class="LBodRit LBodTop">
													<c:if test="${aroundCity.aroundCity==1}">
														<img id="viewImg" style="width: 100px; height: 60px" class="img _CLS" src="${aroundCity.imageUrl }" />
													</c:if>
													</td>
													<td class="LBodRit LBodTop">
														<c:if test="${aroundCity.aroundCity==0}">周边库</c:if>
														<c:if test="${aroundCity.aroundCity==1}">周边去哪玩</c:if>
														<c:if test="${aroundCity.aroundCity==2}">热门目的地</c:if>
													</td>
													<td class="LBodRit LBodTop">${aroundCity.updateUser }</td>
													<td class="LBodRit LBodTop" style="color: gray;"><fmt:formatDate value="${aroundCity.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
													<td class="LBodRit LBodTop">${aroundCity.states==false?"暂停":"启用" }</td>
													<td class="LBodRit LBodTop">
														<a href="javascript:toUpdateAroundCity('${aroundCity.id}')"><font color='blue'>编辑</font></a>
														<c:if test="${aroundCity.states ==false }">
															<a href="#" onclick="updateStaus(${aroundCity.id},true);"><font color="blue">启用</font></a>
														</c:if>
														<c:if test="${aroundCity.states ==true }">
															<a href="#"  onclick="updateStaus(${aroundCity.id},false);"><font color="blue">暂停</font></a>
														</c:if>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="11" class="td1">
									<div align="right">
										<c:out value="${pageInfo.html}" escapeXml="false" />
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</td>
		</tr>
	</table>
	<div id="loading" style="position:fixed !important;position:absolute;top:0;left:0;height:100%; 
	width:100%; z-index:999; background:#000 url(${_domain}/images/loading.gif) no-repeat center center; 
	opacity:0.6; filter:alpha(opacity=60);font-size:14px;line-height:20px;display:none">
		<p id="loading-one" style="color: #fff; position: absolute; top: 50%; left: 50%; margin: 20px 0 0 -50px; padding: 3px 10px;">提交中，请稍后...</p>
	</div>
	<script type="text/javascript">
		//搜索
		function submitForm() {
			$("#pageform").submit();
		}
		
		function toInsertAroundCity(){
			window.location.href = '${_domain}/admin/basedata/toInsertAroundCityPage.html';
		}
		
		function toUpdateAroundCity(id){
			window.location.href = '${_domain}/admin/basedata/toUpdateAroundCityPage.html?id='+id;
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
			$("#"+key+"Name").val(cityName);
			$("#"+cityId+"_"+key+"_span").css("background-color","#FFD2A6").css("cursor","pointer");
		}
		function removeCss(cityId,key){
			$("#"+cityId+"_"+key+"_span").css("background-color","#FFFFFF");
		}

		//更改状态
		function updateStaus(id,status) {
			var msg = (status==false)?"确认暂停此城市":"确认启用此城市";
			if(confirm(msg)){
				$.ajax({
					type : "POST",
					async : false,
					url : "${_domain}/admin/basedata/updateStatus.html?status="+status+"&id="+id,
					contentType : "application/json; charset=utf-8",
					success : function(data) {
						var obj = eval("(" + data + ")");
						alert(obj.msg);
						submitForm();
					},
					error : function() {
						alert("更改状态失败");
					}
				});
			}
		}
	</script>
</body>
</html>
