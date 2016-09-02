<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<t:head_foot>
    <jsp:attribute name="header">
		<title>度假-404-错误，没有找到您想要进入的页面</title>
		<style>
			body{
			  background-color: #fff
			}
			.no-find {
			    margin: 0 auto;
			    width: 990px;
			}
			.find-tips {
			    background: url("http://app.gomein.net.cn/images/error_bg.jpg") no-repeat scroll 0 0 rgba(0, 0, 0, 0);
			    height: 600px;
			    position: relative;
			    width: 988px;
			}
			
			.find-tips span {
			    color: #383838;
			    display: block;
			    font-family: "Microsoft Yahei";
			    font-size: 14px;
			    font-weight: bold;
			    left: 210px;
			    line-height: 30px;
			    position: absolute;
			    top: 440px;
			    width: 360px;
			}
			.find-tips span a {
			    color: #06c;
			    font-size: 16px;
			    margin: 0 3px;
			    text-decoration: none;
			}
			.find-tips span a:hover {
			    color: #c00;
			    text-decoration: underline;
			}
		</style>
    </jsp:attribute>
    
    <jsp:attribute name="footer">
    </jsp:attribute>
    
    <jsp:body>
		<div class="no-find">
			<div class="find-tips">
				<span>
					抱歉，您请求的页面现在无法打开<br>您可以
					<a href="${_domain}/">继续购买周边游套餐</a>
					或
					<a href="http://www.gome.com.cn/">返回国美在线首页</a>
				</span>
			</div>
		</div>
    </jsp:body>
</t:head_foot>