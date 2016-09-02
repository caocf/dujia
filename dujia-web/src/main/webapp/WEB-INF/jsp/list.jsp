<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<t:head_foot>
  <jsp:attribute name="header">
    <title>${s_city_name}${s_scenery}度假${s_days}${s_theme}${s_hotelGrade}酒店景点门票预订-国美在线</title>
    <meta name="description" content="国美旅行提供${s_city_name}${s_scenery}度假${s_days}${s_theme}${s_hotelGrade}酒店景点门票预订.有国美,生活美！">
    <meta name="Keywords" content="${s_city_name}度假酒店景点门票预订,周边游线路">

    <link rel="stylesheet" href="${_domain}/css/list.css?_v=${_v}"  type="text/css"/>
  </jsp:attribute>

  <jsp:attribute name="footer">
    <script type="text/javascript" src="${_domain}/js/list.js,/js/thinkMore.js?_v=${_v}"></script>
    <script>
        var baseAddress="/${search.request.cityPy}/t${search.request.title}-d${search.request.days}-c${search.request.targetCityId}-s${search.request.scenic}-h${search.request.hotelGrade}.html";
        var keyword = $("#s_k").val()  ,orderby = $("#s_m").val(),jiage = "",pageNum = $("#s_p").val();
        //      只有最小价50
        if($("#s_j_min").val()!="" && $("#s_j_max").val() !=""){
            jiage = $("#s_j_min").val()+"_"+$("#s_j_max").val();
        }else if($("#s_j_min").val()!="" && $("#s_j_max").val() ==""){
            jiage = $("#s_j_min").val();
        }else if($("#s_j_min").val()=="" && $("#s_j_max").val() !=""){
            jiage = "0_"+$("#s_j_max").val();
        }
        /**
         * 分页方法
         * @param pageNum
         */
        function searchPage(pageNum) {

            var condition = "?"+((keyword!="")?("k="+keyword+"&"):"")+((orderby!="")?("m="+orderby+"&"):"") + ((jiage!="")?("j="+jiage+"&"):"") + "p="+pageNum;
            location.href = baseAddress +condition;
        }
        function list_Sort(list_Sort){
            var data_sort = $(list_Sort).attr("data_sort");
            if(data_sort){
                if(data_sort == "2"){
                    orderby = 3;
                }else if(data_sort == "3"){
                    orderby = 2;
                }else{
                    orderby = data_sort;
                }
            }else{
                $(list_Sort).attr("data_sort","2");
                orderby = 3;
            }
            var condition = "?"+((keyword!="")?("k="+keyword+"&"):"")+((orderby!="")?("m="+orderby+"&"):"") +((jiage!="")?("j="+jiage+"&"):"");
            condition = condition.substring(0,condition.length-1);

            location.href = baseAddress + condition;
        };

        function priceRange(minPrice,maxPrice){
            var j ="";
            if(minPrice!="" && maxPrice !=""){
                j = minPrice+"_"+maxPrice;
            }else if(minPrice!="" && maxPrice ==""){
                j = minPrice;
            }else if(minPrice=="" && maxPrice !=""){
                j = "0_"+maxPrice;
            }
            var condition = "?"+((keyword!="")?("k="+keyword+"&"):"") +((j!="")?("j="+j+"&"):"");
            condition = condition.substring(0,condition.length-1);
            location.href = baseAddress + condition;
        }

        //      埋码
        s.pageName="度假周边游:列表页";
        s.channel="度假周边游";
        s.prop1="度假周边游";
        s.prop2="度假周边游:列表页";
        s.prop3="度假周边游:列表页";
        s.prop4="度假周边游";
        var s_code=s.t();
        if(s_code)document.write(s_code);


    </script>


  </jsp:attribute>
    <jsp:body>
        <div class="content ">
            <!-- 面包屑 -->
            <div class="breadcrumb">
                <h2><a href="${_domain_lvxing}">旅行</a></h2>&gt; <a href="${_domain}">度假</a>&gt; ${s_city_name}周边游
                <div class="search_box" id="c_search_box">
                    <a href="javascript:" class="img"></a>
                    <div class="clearfix" style="position:relative;z-index: 2;width: 614px;margin: 0 auto;">
                        <div class="search_btn">
                            <input type="text" id="c_search_text" placeholder="请输入目的地，景点名称，酒店名称" autocomplete="off" value="${s_k}"/>
                            <button class="search">搜索</button>
                        </div>
                        <div class="think_more"></div>
                    </div>
                </div>
            </div>
            <c:set var="conditions" value="?"></c:set>
            <c:if test="${not empty s_k}">
                <c:set var="conditions" value="${conditions}k=${s_k}&"></c:set>
            </c:if>
            <c:if test="${not empty s_m}">
                <c:set var="conditions" value="${conditions}m=${s_m}&"></c:set>
            </c:if>
            <c:set var="conditionsL" value="${fn:length(conditions)}"></c:set>
            <c:set var="conditions" value="${fn:substring(conditions,0,conditionsL-1)}"></c:set>

            <c:if test="${search.count != 0}">
                <!-- 检索区 -->
                <div class="fieldsSearch clearfix" id="search_box">
                    <div class="conditions">
                        <c:if test="${empty s_theme && fn:length(search.titles)!=0}">
                            <dl class="">
                                <dt class="search_type">游玩主题</dt>
                                <dd class="search_item">
                                    <c:forEach var="titleonly" items="${search.titles}">
                                        <a href="/${search.request.cityPy}/t${titleonly.value}-d${search.request.days}-c${search.request.targetCityId}-s${search.request.scenic}-h${search.request.hotelGrade}.html${conditions}">${titleonly.name} (${titleonly.count})</a>
                                    </c:forEach>
                                </dd>
                            </dl>
                        </c:if>
                        <c:if test="${empty s_days && fn:length(search.days)!=0}">
                            <dl class="">
                                <dt class="search_type">游玩天数</dt>
                                <dd class="search_item">
                                    <c:forEach var="dayonly" items="${search.days}">
                                        <a href="/${search.request.cityPy}/t${search.request.title}-d${dayonly.value}-c${search.request.targetCityId}-s${search.request.scenic}-h${search.request.hotelGrade}.html${conditions}">${dayonly.name}</a>
                                    </c:forEach>
                                </dd>
                            </dl>
                        </c:if>
                        <c:if test="${empty s_scenery && fn:length(search.scenics)!=0}">
                            <dl class="">
                                <dt class="search_type">游玩景点</dt>
                                <dd class="search_item">
                                    <c:forEach var="sceniconly" items="${search.scenics}">
                                        <a href="/${search.request.cityPy}/t${search.request.title}-d${search.request.days}-c${search.request.targetCityId}-s${sceniconly.value}-h${search.request.hotelGrade}.html${conditions}" value="${sceniconly.value}">${sceniconly.name}</a>
                                    </c:forEach>
                                </dd>
                            </dl>
                        </c:if>
                        <c:if test="${empty s_city && fn:length(search.citys)!=0}">
                            <dl class="">
                                <dt class="search_type">目的地</dt>
                                <dd class="search_item">
                                    <c:forEach var="targetonly" items="${search.citys}">
                                        <a href="/${search.request.cityPy}/t${search.request.title}-d${search.request.days}-c${targetonly.value}-s${search.request.scenic}-h${search.request.hotelGrade}.html${conditions}" value="">${targetonly.name}</a>
                                    </c:forEach>
                                </dd>
                            </dl>
                        </c:if>
                        <c:if test="${empty s_hotelGrade && fn:length(search.hotelGrades)!=0}">
                            <dl class="">
                                <dt class="search_type">酒店等级</dt>
                                <dd class="search_item">
                                    <c:forEach var="staronly" items="${search.hotelGrades}">
                                        <a href="/${search.request.cityPy}/t${search.request.title}-d${search.request.days}-c${search.request.targetCityId}-s${search.request.scenic}-h${staronly.value}.html${conditions}">${staronly.name}</a>
                                    </c:forEach>
                                </dd>
                            </dl>
                        </c:if>
                        <a href="javascript:" class="up" style="display: none;"></a>
                        <a href="javascript:" class="down" style="display: none;"></a>
                    </div>

                    <div class="selected">
                        <p class="count"><i>${search.count}</i> 条符合条件的线路</p>
                        <c:if test="${not empty s_theme}">
                            <span>${s_theme}<a href="/${search.request.cityPy}/t-d${search.request.days}-c${search.request.targetCityId}-s${search.request.scenic}-h${search.request.hotelGrade}.html"></a></span>
                        </c:if>
                        <c:if test="${not empty s_days}">
                            <span>${s_days}<a href="/${search.request.cityPy}/t${search.request.title}-d-c${search.request.targetCityId}-s${search.request.scenic}-h${search.request.hotelGrade}.html"></a></span>
                        </c:if>
                        <c:if test="${not empty s_scenery}">
                            <span>${s_scenery}<a href="/${search.request.cityPy}/t${search.request.title}-d${search.request.days}-c${search.request.targetCityId}-s-h${search.request.hotelGrade}.html"></a></span>
                        </c:if>
                        <c:if test="${not empty s_city}">
                            <span>${s_city}<a href="/${search.request.cityPy}/t${search.request.title}-d${search.request.days}-c-s${search.request.scenic}-h${search.request.hotelGrade}.html"></a></span>
                        </c:if>
                        <c:if test="${not empty s_hotelGrade}">
                            <span>${s_hotelGrade}<a href="/${search.request.cityPy}/t${search.request.title}-d${search.request.days}-c${search.request.targetCityId}-s${search.request.scenic}-h.html"></a></span>
                        </c:if>
                        <c:if test="${not empty s_theme || not empty s_days || not empty s_scenery || not empty s_city || not empty s_hotelGrade}">
                            <a href="/${search.request.cityPy}/t-d-c-s-h.html">清除</a>
                        </c:if>
                    </div>
                </div>
            </c:if>

            <!-- 产品区域 -->
            <div class="products clearfix">
                <c:choose>
                    <c:when test="${search.count == 0}">
                        <div class="no-result">
                            <p> 很抱歉，没有找到符合您要求的线路，请重新搜索</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="list">
                            <div class="list_sort">
                                <a href="javascript:" data_sort="0" class="defalte
                <c:if test="${empty s_m || s_m==0}"> curr</c:if>
                " onclick="list_Sort(this)">人气推荐</a>
                                <a href="javascript:" data_sort="1" onclick="list_Sort(this)" class="<c:if test="${s_m == 1}" > curr asc</c:if>">销量</a>
                                <a href="javascript:" onclick="list_Sort(this)"<c:if test="${s_m == 2}"> data_sort="2" class="pricesort asc curr"</c:if> <c:if test="${s_m == 3}"> data_sort="3" class="pricesort desc curr"</c:if>>价格</a>
                                <ul class="filter-priceRange-box clearfix ">
                                    <li class="priceRange-input"><input id="fc-lowPrice" type="text" value="${s_j_min}" placeholder="¥" maxlength="4"></li>
                                    <li class="priceRange-link">-</li>
                                    <li class="priceRange-input"><input id="fc-highPrice" type="text" value="${s_j_max}" placeholder="¥" maxlength="4"></li>
                                    <li class="priceRange-btn"><a id="fc-btn-cancel" class="fc-btn-cancel" href="javascript:void(0)">清除</a></li>
                                    <li class="priceRange-btn"><a id="fc-btn-ok" class="fc-btn-ok" href="javascript:void(0)">确定</a></li>
                                </ul>
                            </div>
                            <ul class="list_box clearfix">
                                <c:forEach items="${search.products}" var="pro">
                                    <li title="${pro.subName}">
                                        <div class="pro_img">
                                            <a href="/${pro.productId}.html" target="_blank"><img src="${pro.listImageUrl}" alt="${pro.subTitle}" width="290" height="190" /></a>
                                            <p class="pro_city"><span class="city">${pro.cityName}</span><span class="days">${pro.days}天${pro.days-1}晚</span></p>
                                            <p class="pro_over"></p>
                                        </div>
                                        <div class="pro_content">
                                            <p class="title">
                                                <a href="/${pro.productId}.html" target="_blank">${pro.subName}</a>
                                            </p>
                                            <div class="descripe">
                                                <p>
                                                    <c:if test="${fn:length(pro.scenics) != 0}">
                                                        <img src="../../images/address.png">
                                                    </c:if>

                                                    <c:forEach var="scenic" items="${pro.scenics}">
                                                        ${scenic.resName}&nbsp;
                                                    </c:forEach>
                                                </p>
                                                <p>
                                                    <c:if test="${fn:length(pro.hotels) != 0}">
                                                        <img src="../../images/hotel.png">
                                                    </c:if>
                                                    <c:forEach var="hotel" items="${pro.hotels}">
                                                        ${hotel.resName}&nbsp;
                                                    </c:forEach>
                                                </p>
                                                <p>
                                                    <c:if test="${fn:length(pro.titles) != 0}">
                                                        <img src="../../images/title.png">
                                                    </c:if>
                                                    <c:forEach var="title" items="${pro.titles}">
                                                        ${title.titleName}&nbsp;
                                                    </c:forEach>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="pro_price">
                                            <p class="price">¥ <i><fmt:formatNumber type="number" value="${pro.productMinPrice}" pattern="0" maxFractionDigits="0"/></i> </i> ${pro.sellUnit}</p>
                                            <a class="to_detail" href="/${pro.productId}.html" target="_blank">查看详情</a>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                                <%-- fenye --%>
                            <div class="pageHelper" id="pageHtml">
                                <c:if test="${totalPage > 1}">
                                    <c:if test="${s_p > 0 && s_p <= totalPage}">
                                        <c:choose>
                                            <c:when test="${s_p == 1}">
                                                <a href="javascript:;" class="prev front"><i></i>上一页</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="javascript:searchPage(${s_p-1})" class="prev"><i></i>上一页</a>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${totalPage > 4}">
                                                <c:if test="${s_p < 4}" >
                                                    <c:forEach var="i" begin="1" end="4">
                                                        <a href="javascript:searchPage(${i})" <c:if test="${s_p == i}">class="check"</c:if>>${i}</a>
                                                    </c:forEach>
                                                    <c:if test="${totalPage - 4 !=1}">
                                                        <span>...</span>
                                                    </c:if>
                                                    <a href="javascript:searchPage(${totalPage})">${totalPage}</a>
                                                </c:if>
                                                <c:if test="${s_p >= 4 && s_p<totalPage-3}">
                                                    <a href="javascript:searchPage(1)">1</a>
                                                    <span>...</span>
                                                    <a href="javascript:searchPage(${s_p-1})">${s_p-1}</a>
                                                    <a href="javascript:searchPage(${s_p})" class="check">${s_p}</a>
                                                    <a href="javascript:searchPage(${s_p+1})">${s_p+1}</a>
                                                    <span>...</span>
                                                    <a href="javascript:searchPage(${totalPage})">${totalPage}</a>
                                                </c:if>
                                                <c:if test="${s_p >= totalPage-3}">
                                                    <a href="javascript:searchPage(1)">1</a>
                                                    <span>...</span>
                                                    <c:forEach var="i" begin="${totalPage-3}" end="${totalPage}">
                                                        <a href="javascript:searchPage(${i})"  <c:if test="${s_p == i}">class="check"</c:if>>${i}</a>
                                                    </c:forEach>
                                                </c:if>
                                            </c:when>
                                            <c:otherwise>
                                                <c:forEach var="i" begin="1" end="${totalPage}">
                                                    <a href="javascript:searchPage(${i})" <c:if test="${s_p == i}">class="check"</c:if>>${i}</a>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${s_p == totalPage}">
                                                <a href="javascript:;" class="next last"><i></i>下一页</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="javascript:searchPage(${s_p+1})" class="next"><i></i>下一页</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </c:if>
                            </div>
                                <%-- fenye end--%>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="adver">
                    <div class="all_love" id="all_love">
                        <h2>看看大家去哪玩</h2>
                        <ul></ul>
                    </div>
                    <div class="hotest_sales" id="historyView">
                        <h4>浏览历史</h4>
                        <ul id="history"></ul>
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" value="${s_k}" id="s_k" />
        <input type="hidden" value="${s_p}" id="s_p" />
        <input type="hidden" value="${s_m}" id="s_m" />
        <input type="hidden" value="${s_j_min}" id="s_j_min" />
        <input type="hidden" value="${s_j_max}" id="s_j_max" />
        <input type="hidden" value="${s_city_id}" id="cityId" />
        <input type="hidden" value="${search.request.cityPy}" id="cityPy" />

    </jsp:body>
</t:head_foot>