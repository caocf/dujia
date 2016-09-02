<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
	<title></title>
	<style type="text/css">
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
	}
	
	.navPoint {
		COLOR: white;
		CURSOR: hand;
		FONT-FAMILY: Webdings;
		FONT-SIZE: 9pt
	}
	</style>
</head>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var lr = true;
	function changeLr() {
		if(lr){
			lr = false;
			$("#left").hide();
			$("#changeImgLr").attr("src", "/images/right.png");
		}else{
			lr = true;
			$("#left").show();
			$("#changeImgLr").attr("src", "/images/left.png");
		}
	}
	
	var ud = true;
	function changeUd() {
		if(ud){
			ud = false;
			$("#up").hide();
			$("#changeImgUd").attr("src", "/images/down.png");
		}else{
			ud = true;
			$("#up").show();
			$("#changeImgUd").attr("src", "/images/up.png");
		}
	}
</script>
<body>
	<table style="height: 100%" cellspacing="0" cellpadding="0" width="100%" border="0">
		<tbody>
			<tr>
				<td id="up" colspan="3">
					<iframe style="WIDTH: 100%; HEIGHT: 100px"
						name="Explorer_Tool" marginwidth="0" marginheight="0"
						src="top.jsp" frameBorder="0" noResize scrolling="no"
						bordercolor="threedface">
					</iframe>
				</td>
			</tr>
			<tr>
				<td colspan="3" height="6" style="height: 6px;" align="center" bgcolor="#1873aa">
					<img src="/images/up.png" border="0" id="changeImgUd" onclick="changeUd()" style="cursor:pointer;"/>
				</td>
			</tr>
			<tr>
				<td id="left" align="center" width="225" height="100%">
					<iframe style="WIDTH: 225px; HEIGHT: 100%" id="BoardLeft" 
						name="BoardLeft" marginwidth="0" frameSpacing="2" marginheight="0" 
						src="left.jsp" frameborder="0" noResize>
					</iframe>
				</td>
				<td width="6" style="width: 6px;" valign="middle" bgcolor="#1873aa">
					<img src="/images/left.png" border="0" id="changeImgLr" onclick="changeLr()" style="cursor:pointer;"/>
				</td>
				<td height="100%">
					<iframe style="WIDTH: 100%; HEIGHT: 100%" id="main" name="main" src="right.jsp" 
					frameSpacing="1" frameBorder="0"/>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
