<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>标签管理</title>
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
						<td style="width: 97%" class="NavText">您的位置：基础数据管理&gt;标签管理</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div class="RightBg">
					<div class="RightTab">
						<form action="${_domain}/admin/basedata/recomInfoList.html" method="post" id="pageform" name="recomInfoForm">
							<table id="searchTable">
								<tr>
									<td>
										<span style="margin-left: 50px;">标签名称: </span> 
										<input type=text id="title" name="title" value="${recomInfo.title}"/>
									</td>
									<td>
										<span style="margin-left: -50px;">状态:</span>
										<select id="isDelete" name="isDelete">
											<option value=""  <c:if test="${empty recomInfo.isDelete}">selected="selected"</c:if>>全部</option>
											<option value="false" <c:if test="${recomInfo.isDelete == false }">selected="selected"</c:if>>显示</option>
											<option value="true" <c:if test="${recomInfo.isDelete == true}">selected="selected"</c:if>>隐藏</option>
										</select>
									</td>
									<td>
										<span style="margin-left: 5px;">新增日期</span>
										<input id="startTime" name="startTime" type="text" readonly="readonly" value="<fmt:formatDate value="${recomInfo.startTime}" pattern="yyyy-MM-dd" />" onclick="WdatePicker()" class="Wdate" />
										&nbsp;&nbsp;至 
										<input id="endTime" name="endTime" type="text" readonly="readonly" value="<fmt:formatDate value="${recomInfo.endTime}" pattern="yyyy-MM-dd" />" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\',{d:0});}'})" />
									</td>
									<td colspan="4">
										<input style="width: 80px; height: 30px; color: #485058;margin-right: 50px;" type="button" onclick="submitForm();" value="查询" /> 
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
												<td style="width: 47px" class="LBodRit">批量选择</td>
												<td style="width: 130px" class="LBodRit">标签(商品数量)</td>
												<td style="width: 100px" class="LBodRit">新增日期</td>
												<td style="width: 100px" class="LBodRit">最后操作人</td>
												<td style="width: 100px" class="LBodRit">最后操作时间</td>
												<td style="width: 100px" class="LBodRit">状态</td>
												<td style="width: 60px">操作</td>
											</tr>
										</thead>
										<tbody>
											<c:forEach varStatus="vstatus" var="recomInfo" items="${pageInfo.pageList }">
												<tr class="TextCen LBodTop">
													<td class="LBodRit LBodTop"><input type="checkbox" name="check" id="check" value="${recomInfo.title}"/></td>
													<td class="LBodRit LBodTop">${recomInfo.title}(${recomInfo.titleCount })</td>
													<td class="LBodRit LBodTop" style="color: gray;"><fmt:formatDate value="${recomInfo.createTime }" pattern="yyyy-MM-dd" /></td>
													<td class="LBodRit LBodTop">${recomInfo.updateUser }</td>
													<td class="LBodRit LBodTop" style="color: gray;"><fmt:formatDate value="${recomInfo.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
													<td class="LBodRit LBodTop">
														<c:if test="${!recomInfo.isDelete}">显示</c:if>
														<c:if test="${recomInfo.isDelete}">隐藏</c:if>
													</td>
													<td class="LBodRit LBodTop">
														<c:if test="${recomInfo.isDelete == false}">
															<a href="javascript:handleRecomInfo(0,1,'${recomInfo.title}')"><font color='blue'>隐藏</font></a>
															<font style="margin-left: 5px;">显示</font>
														</c:if>
														<c:if test="${recomInfo.isDelete == true}">
															隐藏
															<a href="javascript:handleRecomInfo(0,0,'${recomInfo.title}')"><font color='blue' style="margin-left: 5px;">显示</font></a>
														</c:if>
													</td>
												</tr>
											</c:forEach>
										</tbody>
										<tr>
											<td class="LBodRit LBodTop" colspan="7">
												<input type="checkbox" id="selectAll" name="selectAll" value="" style="margin-left: 50px;" onclick="selectAll()">全选</input>
												<input type="button" id="delCheckAll" name="delCheckAll" value="全部隐藏" style="margin-left: 50px;" onclick="handleRecomInfo(1,1)"/>
												<input type="button" id="showCheckAll" name="showCheckAll" value="全部显示" style="margin-left: 50px;" onclick="handleRecomInfo(1,0)"/>
											</td>
										</tr>
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
		
		//复选框事件  
		//全选、取消全选的事件  
		function selectAll(){  
		    if ($("#selectAll").attr("checked")) {  
		        $(":checkbox").attr("checked", true);  
		    } else {  
		        $(":checkbox").attr("checked", false);  
		    }  
		} 
		
		function findRecomInfoTitle() {
            var str="";
            $("input[name='check']:checkbox").each(function(){ 
                if($(this).attr("checked")){
                    str += "," + $(this).val();
                }
            })
            return str;
        }
		
		function handleRecomInfo(type,status,title){
			var titleStr = "";
			if( type == 0 ){
				titleStr = title;
			}else{
				titleStr = findRecomInfoTitle();
				if( titleStr == "" ){
					alert("请选择您要操作的标签!");
					return false;
				}
				titleStr = titleStr.substr(1);
			}
			updateRecomInfoStatus( status , titleStr );
		}
		
		function updateRecomInfoStatus(status,title) {
			$.ajax({
				type : "POST",
				async : false,
				cache: false,
				url : "${_domain}/admin/basedata/updateRecomInfoStatus.html?"+Math.random(),
				data : {
					"isDelete" : status,
					"title" : title
				},
				success : function(data) {
					alert("操作成功!");
					submitForm();
				},
				error : function() {
					alert("操作失败!");
				}
			});
		}
	</script>
</body>
</html>
