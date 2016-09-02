/**
 * Created by zhuchunle on 2016/5/9.
 */
(function(){
    var inf = new infoMain();
    var adver = {
        showTopAdver: function(url){
            inf.getDate({
                Jurl: url, Jback: function () {
                    if(this.data.length!=0){
                        var $a =  $(".search_box").find("a");
                        $a.css("background-image","url("+this.data[0].productImage+")");
                        var adverUrl = this.data[0].url;
                        $a.attr("href",adverUrl);
                    }

                }
            })
        },

        all_love: function(url,param){
            var $all_love = $("#all_love").find("ul");
            if($all_love.length > 0){
                inf.getDate({
                    Jurl: url,Jdat:{cityId:param}, Jback: function () {
                        for (var i = 0; i < this.data.length; i++) {
                            var className = "";
                            if(i<3){
                                className = "top";
                            }
                            if (param)
                                $all_love.append("<li><a href='/" + $("#cityPy").val() + "/list.html?s=" + this.data[i].resId + "' class='clearfix'> <i class='"+className+"'>"+(i+1)+"</i><span>" + this.data[i].resName + "</span></a></li>");
                            else
                                $all_love.append("<li><a href='/search.html?k=" + this.data[i].resName + "' class='clearfix'><i class='"+className+"'>"+(i+1)+"</i><span>" + this.data[i].resName + "</span></a></li>");
                        }
                    }
            });
            }

        },
        history:function(url,productids){
            if(productids){
                inf.getDate({Jurl: url, Jdat:{productIds: productids}, Jtype:"GET",Jback: function(){
                    if(this.data!=null){
                        $("#historyView").show();
                        inf.ajaxTemp("history",{history:this.data},$("#history"));
                    }
                }})
            }
        }
    }
    adver.showTopAdver("/api/adt/query/1/B.json");
    adver.all_love("/hotscenery.html",$("#cityId").val());

    var $filter_priceRange = $(".filter-priceRange-box");
    $filter_priceRange.find("input").focus(function(){
        $filter_priceRange.addClass("filter-priceRange-click");
    })
    $filter_priceRange.mouseleave(function(){
        $filter_priceRange.removeClass("filter-priceRange-click");
    });
    $filter_priceRange.find("input").keyup(function(){
        this.value = this.value.replace(/\D/g, '');
    });
   $("#fc-btn-cancel").click(function () {
       $filter_priceRange.find("input").val("");
   });
    $("#fc-btn-ok").click(function () {
        priceRange($("#fc-lowPrice").val(),$("#fc-highPrice").val());
    });


    $(".conditions dl").each(function(i,n){
            var curHeight = $(n).height(),
            autoHeight = $(n).css('height', 'auto').height();
            if(autoHeight > curHeight){
                $(n).height(curHeight);
                var $more = $('<dd class="more unfold"><a href="javascript:"><i></i></a></dd>');
                $more.click(function(){
                    if($(this).hasClass("fold")){
                        $(n).animate({height: curHeight}, 500);
                        $(this).removeClass("fold").addClass("unfold");
                    }else{
                        $(n).animate({height: autoHeight}, 500);
                        $(this).removeClass("unfold").addClass("fold");
                    }



                });
                $(n).append($more);
            }
    });

    //历史查看
    if($.cookie) {
        var historyIds = $.cookie("historyIds");
        if (historyIds != null) {
            adver.history("/history.html", historyIds);
        }
    }else{
        $.getScript("../js/cookie.js",function(){
            var historyIds = $.cookie("historyIds");
            if (historyIds != null) {
                adver.history("/history.html", historyIds);
            }
        })
    }


})()