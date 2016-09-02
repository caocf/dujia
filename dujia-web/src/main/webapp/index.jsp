<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:head_foot>
  <jsp:attribute name="head_tag_index">0</jsp:attribute>
  <jsp:attribute name="header">
    <title>度假_哪里好玩-国美在线</title>
    <meta name="description" content="国美旅行度假旅游提供酒店景点门票套餐预订,让您的度假旅游自由行一站式服务超级省心,休闲度假好去处周边游酒店景点门票预订首选国美旅行.有国美,生活美！">
    <meta name="Keywords" content="度假,休闲度假,休闲度假旅游,休闲旅游度假,休闲度假游,休闲度假好去处">
    <link rel="stylesheet" href="${_domain}/css/index.css?_v=${_v}" />
  </jsp:attribute>

  <jsp:attribute name="footer">
  	<script type="text/javascript" src="${_domain}/lib/jquery.lazyload.js,/js/banner_change.js,/js/accordion.js,/js/index.js,/js/thinkMore.js?_v=${_v}"></script>
    <script>
      (function(){
        var bp = document.createElement('script');
        bp.src = '//push.zhanzhang.baidu.com/push.js';
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(bp, s);
      })();

//      埋码
      s.pageName="度假周边游:首页";
      s.channel="度假周边游";
      s.prop1="度假周边游";
      s.prop2="度假周边游:首页";
      s.prop3="度假周边游:首页";
      s.prop4="度假周边游";
      var s_code=s.t();
      if(s_code)document.write(s_code);


    </script>
  </jsp:attribute>

  <jsp:body>
    <!-- 搜索框 -->
    <div class="search_box">
      <div class="search_content clearfix">
        <div class="searchcon" id="c_search_box">
          <div class="baseData">
            <div class="curr_city fold">
              <a href="javascript:" class="location "><span id="localcity">北京</span></a>
              <!-- 城市列表 -->
              <!-- 选择城市弹层 start-->
              <div class="selsect_city_box hide" id="showTicket_selsect_city_box">
                <span class="close_btn">关闭</span>
                <div class="selectcity_box" id="selectcity_box">
                  <div class="selectcity_tab_ulbg">
                    <ul class="selectcity_tab_ul clearfix" id="selectcity_ul">
                      <li class="active"><span>热门</span></li>
                      <li><span>ABCDEF</span></li>
                      <li><span>GHJ</span></li>
                      <li><span>KLMN</span></li>
                      <li><span>PQRSTW</span></li>
                      <li><span>XYZ</span></li>
                    </ul>
                  </div>
                  <!-- 热门 -->
                  <div class="selectcity_tab_cont clearfix" style="display:block" id="hotCity"></div>
                </div>
              </div>
              <!-- 选择城市弹层 end-->
              <span class="city_arrow"></span>
            </div>
            <div class="search_input">
              <input type="text" id="c_search_text" autocomplete="off" placeholder="请输入目的地，景点名称，酒店名称">
              <button class="search">搜索</button>
            </div>
          </div>

          <div class="think_more"></div>
        </div>
      </div>
      <div class="backover"></div>
    </div>

    <!-- 焦点图上的浮层 -->
    <div class="recommendBox">
      <div class="backover"></div>
      <ul class="recommendList">
        <li class="type day">
          <dl class="float_data">
            <dt>游玩天数</dt>
            <dd>
              <ol class="clearfix" id="days"></ol>
            </dd>
          </dl>
        </li>
        <li class="type method">
          <dl class="float_data">
            <dt>特色玩法</dt>
            <dd>
              <ol class="clearfix" id="plays">

              </ol>
            </dd>
          </dl>
        </li>
        <li class="type scenic">
          <dl class="float_data">
            <dt>热门景点</dt>
            <dd>
              <ol class="clearfix" id="scenery"></ol>
            </dd>
          </dl>
          <span class="more">></span>
          <div class="float_more scenic_more clearfix" style="display: none;" id="more_scenic">
            <dl>
              <dt class="fontmaincolor">苏州</dt>
              <dd>
                <ol></ol>
              </dd>
            </dl>
          </div>
        </li>
        <li class="type hotbourn">
          <dl class="float_data">
            <dt>热门目的地</dt>
            <dd>
              <ol class="clearfix" id="destination"></ol>
            </dd>
          </dl>
          <span class="more">></span>
          <ul class="float_more hotbourn_more" style="display: none;" id="morehotbourn"></ul>
        </li>
      </ul>
    </div>

    <div class="banner" id="banner">
      <ul class="list clearfix"></ul>
    </div>
  <div class="content">
    <div class="J_allfloor"></div>
    <!-- 楼层 周末去哪儿玩 手风琴效果 -->
    <div class="floor" id="accordion"></div>
  </div>
    <input type="hidden" id="cityPinyin" value=""/>
</jsp:body>
</t:head_foot>