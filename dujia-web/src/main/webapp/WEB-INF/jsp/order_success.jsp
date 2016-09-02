<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta http-equiv="Cache-Control" content="no-transform"/>
    <meta name="applicable-device" content="pc">
    <link rel="shortcut icon" href="http://img.gomein.net.cn/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="http://css.gomein.net.cn/??gmlib/reset/1.1.0/reset.css,gmpro/1.0.0/public/1.0.0/css/foot-new.min.css,gmpro/1.0.0/public/1.0.0/css/top.min.css?v=201604131502">
    <link rel="stylesheet" href="${_domain}/css/common.css?_v=${_v}" type="text/css"/>
    <!-- 公共头部 导入的 -->
    <c:choose>
        <c:when test="${_env == 'dev'}">
            <script type="text/javascript">var dynSite='http://g.gome.com.cn',staSite='http://www.gome.com.cn',contextPath='/ec/homeus',imgServer='http://img.gomein.net.cn/images',secureURL='https://g.gome.com.cn:443',pictureserver='http://img.gomein.net.cn/image',cookieDomain='.gome.com.cn',stageJsServer='http://js.gomein.net.cn',stageImageServer='http://img.gomein.net.cn',stageCssServer='http://css.gomein.net.cn',cssserver='http://css.gomein.net.cn/css',jsserver='http://js.gomein.net.cn/js',versionData='1604130302';</script>
        </c:when>
        <c:otherwise>
            <!--#include virtual="/n/common/global/global.html"-->
        </c:otherwise>
    </c:choose>
    <title>度假-支付成功</title>
    <link rel="stylesheet" href="../css/order_success.css?_v=${_v}"  type="text/css"/>
</head>
<body>
    <c:choose>
        <c:when test="${_env == 'dev'}">
            <div class="gome-top">
                <div id="gome-top-body" class="wbox">
                    <ul>
                        <li id="gome-logo-sm"><a data-code="public01001" href="http://www.gome.com.cn/" target="_blank"><i></i>国美在线</a></li>
                        <li id="gome-user" data-load="user">
                            <b id="loginDiv-name"><a data-code="public01053" href="http://v.gome.com.cn" target="_blank">国美会员</a><i></i></b>
                            <div class="public-dropdown"></div>
                        </li>
                        <li id="loginDiv-link" class="gome-link">
                            <a data-code="public01002" href="https://login.gome.com.cn/login">登录</a><a data-code="public01003" href="https://reg.gome.com.cn/register/index/person">注册</a>
                        </li>
                    </ul>
                    <ul id="gome-top-right">
                        <li class="gome-link"><a data-code="public01004" href="http://g.gome.com.cn/ec/homeus/myaccount/myOrders.jsp?flag=a0">我的订单</a></li>
                        <li id="gome-mygome" data-load="mygome">
                            <b><a data-code="public01050" href="http://g.gome.com.cn/ec/homeus/myaccount/gome/profileHome.jsp?flag=a" target="_blank">我的国美</a><i></i></b>
                            <div class="public-dropdown"></div>
                        </li>
                        <li class="gome-spacer"></li>
                        <li id="gome-service" class="drop" data-load="service">
                            <b><em class="gm-icon">f</em><a data-code="public01052" href="http://help.gome.com.cn/" target="_blank">服务中心</a><i></i></b>
                            <div class="public-dropdown"></div>
                        </li>
                        <li id="gome-sitemap" class="drop" data-type="b03" data-load="sitemap">
                            <b>网站导航<i></i></b>
                            <div class="public-dropdown"></div>
                        </li>
                        <li class="gome-spacer"></li>
                        <li id="gome-phone" class="drop">
                            <b><em class="gm-icon">á</em><a data-code="public01049" href="http://shouji.gome.com.cn/" target="_blank">手机国美</a><i></i></b>
                            <div class="public-dropdown"><a data-code="public01005" href="http://shouji.gome.com.cn/" target="_blank"></a></div>
                        </li>
                    </ul>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <!-- 头部begin -->
            <!--#include virtual="/n/common/b03/head.html"-->
            <!-- 头部end -->
        </c:otherwise>
    </c:choose>
    <!-- 支付头 公共的 可以放到tag中 -->
    <div class="pay-head">
        <div class="wrap">
            <div class="logo"><a href="${_domain_lvxing}/index.html"><img src="/images/gome-logo.png" alt=""></a>
            </div>
            <ul class="step clearfix" id="_todo_">
                <li class="item cur"><b>1</b>
                    <div class="line lines"><span class="progress"></span><i></i></div>
                    <span class="text">填写核对订单信息</span>
                </li>
                <li class="item cur"><b>2</b>
                    <div class="line lines"><span class="progress"></span><i></i></div>
                    <span class="text">在线支付</span>
                </li>
                <li class="item cur"><b>3</b>
                    <div class="line"><span class="progress"></span><i></i></div>
                    <span class="text">待确认</span>
                </li>
                <li class="item"><b>4</b><span class="text">完成预订</span></li>
            </ul>
        </div>
    </div>

    <div class="content">
        <!-- 支付成功内容页面 -->
        <div class="reservationover clearfix" id="reservationovers">
            <ol>
                <li>
                    <c:choose>
                        <c:when test="${orderInfo.orderStatus == 8 }">
                            <h4>您的订单已取消，请等待退款......</h4>
                        </c:when>
                        <c:otherwise>
                            <h4>订单提交成功，等待酒店确认，预订结果会以短信形式通知您!</h4>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li>
                    <span class="order_number">订单编号：<b>${orderInfo.orderId}</b></span>
                    <span>订单金额：<i>¥${orderInfo.orderAmount}</i></span>
                    <span>应付金额：<em>¥${orderInfo.paymentAmount}</em></span>
                </li>
            </ol>
            <button id="viewOrder">查看订单</button>
            <button id="toSearch">继续预订</button>
        </div>

        <div class="order_info clearfix">
            <dl>
                <dt>商品信息：</dt>
                <dd>${orderInfo.productName}
                    <br/>${orderInfo.packageName}
                </dd>
            </dl>
            <dl>
                <dt>出游时间：</dt>
                <dd><fmt:formatDate value="${orderInfo.travelStartTime}" pattern="yyyy-MM-dd"/></dd>
            </dl>
            <dl>
                <dt>套餐份数：</dt>
                <dd>${orderInfo.buyCount}份</dd>
            </dl>
            <dl>
                <dt>出游人数：</dt>
                <dd>${orderInfo.adultNum}成人　
                    <c:if test="${not empty orderInfo.childNum}">
                        ${orderInfo.childNum}儿童
                    </c:if>
                    <br/>
                    <c:if test="${travelerList != null}">
                        <c:forEach var="item" items="${travelerList}">
                            ${item.name}　
                        </c:forEach>
                    </c:if>
                </dd>
            </dl>
            <dl>
                <dt>联 系 人：</dt>
                <dd>${orderInfo.contactsName}</dd>
            </dl>
            <dl>
                <dt>联系电话：</dt>
                <dd>${orderInfo.contactsTelphone}</dd>
            </dl>
        </div>

    </div>
  <a href="javascript:;" class="gome_flights_gotop">回顶部</a>
  <c:choose>
  <c:when test="${_env == 'dev'}">
  <!-- 尾部begin -->
  <div id="gome-foot-new" class="clearfix">
      <div id="gome-foot-new-body">
          <div class="gome-foot-slogan">
              <a data-code="1000048306" href="http://lvxing.gome.com.cn/" target="_blank"><img src="http://img12.gomein.net.cn/image/prodimg/topics/liubing-ds1/201603/18/20160318103416.jpg"></a>
          </div>
          <div class="gome-foot-help clearfix">
              <dl>
                  <dt>机票预订帮助</dt>
                  <dd><ul>
                      <li><a data-code="1000049073-0" href="http://help.gome.com.cn/question/252.html#lc" target="_blank" rel="nofollow">机票预订流程</a></li>
                      <li><a data-code="1000049073-1" href="http://help.gome.com.cn/question/252.html#xz" target="_blank" rel="nofollow">购票需知</a></li>
                      <li><a data-code="1000049073-2" href="http://help.gome.com.cn/question/252.html#wt" target="_blank" rel="nofollow">预定常见问题</a></li>
                      <li><a data-code="1000049073-3" href="http://help.gome.com.cn/question/252.html#sm" target="_blank" rel="nofollow">退票及改签说明</a></li>
                      <li><a data-code="1000049073-4" href="http://help.gome.com.cn/question/252.html#pz" target="_blank" rel="nofollow">行程单/发票/报销凭证</a></li>
                  </ul></dd>
              </dl>
              <dl>
                  <dt>酒店预订帮助</dt>
                  <dd><ul>
                      <li><a data-code="1000049076-0" href="http://help.gome.com.cn/question/335.html#yd" target="_blank" rel="nofollow">如何预订酒店</a></li>
                      <li><a data-code="1000049076-1" href="http://help.gome.com.cn/question/335.html#jd" target="_blank" rel="nofollow">如何找到合适的酒店</a></li>
                      <li><a data-code="1000049076-2" href="http://help.gome.com.cn/question/335.html#rz" target="_blank" rel="nofollow">如何办理入住</a></li>
                      <li><a data-code="1000049076-3" href="http://help.gome.com.cn/question/335.html#db" target="_blank" rel="nofollow">信用卡担保问题说明</a></li>
                      <li><a data-code="1000049076-4" href="http://help.gome.com.cn/question/335.html#sm" target="_blank" rel="nofollow">常见问题说明</a></li>
                  </ul></dd>
              </dl>
              <dl>
                  <dt>火车票预订帮助</dt>
                  <dd><ul>
                      <li><a data-code="1000049079-0" href="http://help.gome.com.cn/question/401.html#lc" target="_blank" rel="nofollow">火车票预订流程</a></li>
                      <li><a data-code="1000049079-1" href="http://help.gome.com.cn/question/401.html#xz" target="_blank" rel="nofollow">购票需知</a></li>
                      <li><a data-code="1000049079-2" href="http://help.gome.com.cn/question/401.html#bx" target="_blank" rel="nofollow">保险常见问题</a></li>
                      <li><a data-code="1000049079-3" href="http://help.gome.com.cn/question/401.html#yd" target="_blank" rel="nofollow">预定常见问题</a></li>
                      <li><a data-code="1000049079-4" href="http://help.gome.com.cn/question/401.html#tp" target="_blank" rel="nofollow">退票及退款说明</a></li>
                  </ul></dd>
              </dl>
              <dl>
                  <dt>会员专区</dt>
                  <dd><ul>
                      <li><a data-code="1000049082-0" href="http://help.gome.com.cn/question/26.html" target="_blank" rel="nofollow">会员介绍</a></li>
                      <li><a data-code="1000049082-1" href="http://help.gome.com.cn/question/23.html" target="_blank" rel="nofollow">积分说明</a></li>
                      <li><a data-code="1000049082-2" href="http://help.gome.com.cn/question/27.html" target="_blank" rel="nofollow">优惠券说明</a></li>
                      <li><a data-code="1000049082-3" href="http://help.gome.com.cn/question/20.html" target="_blank" rel="nofollow">电子券</a></li>
                      <li><a data-code="1000049082-4" href="http://help.gome.com.cn/question/42.html" target="_blank" rel="nofollow">国美社区</a></li>
                  </ul></dd>
              </dl>
              <dl>
                  <dt>国美在线</dt>
                  <dd><ul>
                      <li><a data-code="1000049085-0" href="http://help.gome.com.cn/about/company.html" target="_blank" rel="nofollow">关于国美在线</a></li>
                      <li><a data-code="1000049085-1" href="http://www.gome.com.cn/join/" target="_blank" rel="nofollow">商家入住</a></li>
                      <li><a data-code="1000049085-2" href="http://cps.gome.com.cn/" target="_blank" rel="nofollow">销售联盟</a></li>
                      <li><a data-code="1000049085-3" href="http://help.gome.com.cn/question/21.html" target="_blank" rel="nofollow">联系我们</a></li>
                      <li><a data-code="1000049085-4" href="http://www.gome.com.cn/links/" target="_blank" rel="nofollow">友情链接</a></li>
                  </ul></dd>
              </dl>
          </div>
          <div class="gome-foot-code2">
              <i><a data-code="1000048307" href="http://shouji.gome.com.cn/?intcmp=sy-1000043112-0" target="_blank"><img src="http://img13.gomein.net.cn/image/prodimg/topics/liubing-ds1/201604/5/20160405183749.jpg"></a></i>
              <p>扫描下载客户端</p>
          </div>
      </div>
  </div>
  <div id="copyright">
      <p>
          <a href="http://www.bj.cyberpolice.cn/index.do" rel="nofollow" target="_blank">朝阳网络警察</a>
          <a href="http://search.cxwz.org/cert/l/CX20111018000607000618" rel="nofollow" target="_blank">诚信网站</a>
          <a href="https://ss.knet.cn/verifyseal.dll?sn=2011071100100011373&amp;comefrom=trust" rel="nofollow" target="_blank">可信网站信用评价</a>
          <a href="http://www.sgs.gov.cn/lz/licenseLink.do?method=licenceView&amp;entyId=20111216142637606" rel="nofollow" target="_blank">经营性网站备案信息</a>
          ©2000-2014 国美在线电子商务有限公司 版权所有 沪ICP备11009419号 沪B2-20120004号
      </p>
  </div>
  </c:when>
  <c:otherwise>
  <!-- 尾部begin -->
  <!--#include virtual="/n/common/b03/foot_new.html"-->
  <!-- 尾部end -->
  </c:otherwise>
  </c:choose>

  <div class="modal-wapper clearfix" id="alert">
      <a href="javascript:" class="close_modal"></a>
      <p class="modal-text"></p>
      <p class="btns">
          <a href="javascript:void(0)" class="btnsConfirm">确定</a>
      </p>
  </div>
  <!-- confirm弹框 -->
  <div class="modal-wapper clearfix" id="confirm">
      <a href="javascript:" class="close_modal"></a>
      <p class="modal-text"></p>
      <p class="btns">
          <a href="javascript:void(0)" class="btnsConfirm">确定</a>
          <a href="javascript:void(0)" class="btnsCancel">取消</a>
      </p>
  </div>

  <div class="mask_boxs" id="mask_boxs">
      <div class="masks"></div>
      <div id="mcon">
      </div>
  </div>

  <c:choose>
  <c:when test="${_env == 'dev'}">
  <script src="http://js.gomein.net.cn/gmlib/jq/1.7.1/jquery.js" type="text/javascript"></script>
  <script src="http://js.gomein.net.cn/gmlib/cookie/1.0.0/cookie.js" type="text/javascript"></script>
      <%--        <script src="http://js.gomein.net.cn/gmpro/1.0.0/public/1.0.0/js/signtop.min.js" type="text/javascript"></script>
              <script src="http://js.gomein.net.cn/gmlib/unit/bigcode/1.0.0/bigcode.min.js" type="text/javascript"></script>--%>
  <script type="text/javascript" src="http://js.gomein.net.cn/??/f2ejs/g/jsConfig.min.js,/f2ejs/g/jsCssImport.min.js?v=20150041002500060041"></script>
  <script>
      //$("#topnav").html(topHtml.con);
      g$("aaaaa","/js/g/jquery/jquery.cookie,/js/g/ui/live800_gome,/js/n/firstPage/ghover",function(){
          $("#topNavHover").ghover({
              addcss : "tophover"
          });
          $(".js-tophover").ghover( {
              addcss : "hover"
          });
          $(".triggerLive800").click(function () {
              //jqueryM的trigger在老版的webkit下支持的不好
              //所以webkit下 用原生js
              var isWebKit = window.navigator.userAgent.indexOf('WebKit') > 0;
              if (isWebKit) {
                  var ev = document.createEvent('HTMLEvents');
                  ev.initEvent('click', false, true);
                  $(".live800_gome").get(0).dispatchEvent(ev);
              }
              else {
                  $(".live800_gome").trigger("click");
              }
          });
          /*自定义方法*/
      });
      $(window).scroll(function (){
          if($(window).scrollTop()>0){
              $(".gome_flights_gotop").show();
          }else{
              $(".gome_flights_gotop").hide();
          };
      });
  </script>
  </c:when>
  <c:otherwise>
  <script src="http://js.${_resource_env}.net.cn/??<!--#include virtual='/n/common/b03/js.html'-->"></script>
  </c:otherwise>
  </c:choose>
  <script type="text/javascript" src="${_domain}/js/common.js,/js/indexGet.js,/tpl/build/template.js,/js/confirm.override.js,/js/commonbase.js?_v=${_v}"></script>
    <script type="text/javascript" src="http://js.${_resource_env}.net.cn/gmlib/unit/scode/1.0.0/scode.js"></script>
    <script type="text/javascript" src="http://js.${_resource_env}.net.cn/f2ejs/common/bigData.min.js"></script>
    <script type="text/javascript">

      //返回顶部
      $(".gome_flights_gotop").click(function (){
          $('html,body').animate({scrollTop: '0px'}, 100);
      });
  </script>
  <!--server ip is <%=request.getLocalAddr()%> -->

    <script>
        $("#viewOrder").click(function () {
            var url = "/order/order_detail/query.html?orderId=${orderInfo.orderId}";
            window.location.href = url;
        });

        $("#toSearch").click(function () {
            window.location.href = '${_domain}';
        });

        s.pageName="购物流程:度假周边游:预订成功页";
        s.channel="度假周边游";
        s.prop1="购物流程:度假周边游";
        s.prop2="购物流程:度假周边游:预订成功页";
        s.prop3="购物流程:度假周边游:预订成功页";
        s.prop4="购物流程:度假周边游";
        s.events="purchase";
        s.products=";${orderInfo.productId};${orderInfo.buyCount};${orderInfo.orderAmount}";
        s.eVar11="${orderInfo.orderId}";
        s.TransactionID="${orderInfo.orderId}";
        s.purchaseID="${orderInfo.orderId}";
        s.eVar5="${payMode}";
        var s_code=s.t();
        if(s_code)document.write(s_code);
    </script>
</body>
</html>