/**
 * Created by zhuchunle on 2016/5/12.
 */

// 输入框 获取联想词汇
//定义缓存对象
var ajaxCache = {};
$("#c_search_text").on("keyup focus",function (e) {
    var _this = $(this);
    var inputVal = $.trim(_this.val());
    if (inputVal != "") {
        $("#quickdelete").show();
    } else {
        $("#quickdelete").hide();
    }
    clearTimeout(timeout);
    var timeout = setTimeout(function () {
        if (!!ajaxCache[inputVal]) {
            //显示自动提示框，给框里填关联词条的内容
            showList(ajaxCache[inputVal], inputVal, $(".think_more"));
        } else {
            if (inputVal != '') {
                // 显示当前
                $(".search_history").hide();
                textSearchShow(inputVal, $(".think_more"), ajaxCache);
            }else{
                $(".think_more").hide();
            }
        }
    }, 300);
});
/*请求数据 搜索
 * ajaxCache ajax缓存
 * */
function textSearchShow(txt, wrapbox, ajaxCache) {
    var url = "/api/search/auto/" + encodeURIComponent(txt) + ".json";
    var inf = new infoMain();
    inf.getDate({
        Jurl: url, Jdat: {}, Jback: function () {
            showList(this.data, txt, wrapbox);
            ajaxCache[txt] = [];
            //给缓存对象赋值
            ajaxCache[txt] = this.data;
        }
    });
};
/*
 *  显示联想列表
 *  data 数据
 *  txt 搜索内容
 *  wrapbox 列表容器
 * */
function showList(data, txt, wrapbox) {
    $(wrapbox).hide();
    $(wrapbox).empty();
    if (data.citys.length != 0) {
        var $citys = $('<ul class="think_address"><li class="type_icon">地点</li></ul>');
        $(wrapbox).append($citys);

        for (var i = 0; i < data.citys.length; i++) {
            var key = data.citys[i].resName;
            key = key.replace(/<[^>]+>/g, "");

            $citys.append('<li><a href="/search.html?k=' + key + '" id="' + data.citys[i].resId + '" >' + data.citys[i].resName + '</a></li>');
        }
    }

    if (data.scenics.length != 0) {
        var $scenics = $('<ul class="think_scenic"><li class="type_icon">景点</li></ul>');
        $(wrapbox).append($scenics);

        for (var i = 0; i < data.scenics.length; i++) {
            var key = data.scenics[i].resName;
            key = key.replace(/<[^>]+>/g, "");
            $scenics.append('<li><a href="/search.html?k=' + key + '" id="' + data.scenics[i].resId + '" >' + data.scenics[i].resName + '　' + data.scenics[i].resCity + '</a></li>');
        }
    }

    if (data.hotels.length != 0) {
        var $hotels = $('<ul class="think_hotel"><li class="type_icon">酒店</li></ul>');
        $(wrapbox).append($hotels);

        for (var i = 0; i < data.hotels.length; i++) {
            var key = data.hotels[i].resName;
            key = key.replace(/<[^>]+>/g, "");
            $hotels.append('<li><a href="/search.html?k=' + key + '" id="' + data.hotels[i].resId + '" >' + data.hotels[i].resName + '　' + data.hotels[i].resCity + '</a></li>');
        }
    }

    if (data.citys.length != 0 || data.scenics.length != 0 || data.hotels.length != 0) {
        $(wrapbox).show();
    }
}

$(document).bind("click", function (event) {
    var e = event || window.event;
    var elem = e.srcElement || e.target;

    if (!$(elem).parents().is(".think_more") && !$(elem).is("a.btn.next")) {
        $(".think_more").hide();
    }
});

$("#c_search_box").find("button.search").bind("click", function () {
    var sValue = $.trim($("#c_search_text").val());
    if (sValue != "") {
        location.href = "/search.html?k=" + encodeURIComponent(sValue);
    }
});
$('#c_search_text').keydown(function (e) {
    if (e.keyCode == 13) {
        $("#c_search_box").find("button.search").click();
    }
});