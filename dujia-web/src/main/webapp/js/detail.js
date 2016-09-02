/**
 * Created by zhuchunle on 2016/5/4.
 */
//城市相关
(function() {
    $(".direction").find("img").lazyload({
        effect: "fadeIn"
    });
    $.get("/api/productBiz/addPVForProduct/"+$("#productId").val());
    var inf = new infoMain();
    var meals_url = "/pks/"+$("#productId").val()+".html";
    var meals = {
        getMealsData:function(url,param){
            $(".cover").show();
            $(".comboGroup").hide();
            if($("#isDelete").val() != "true"){
                inf.getDate({Jurl: url, Param: param, Jtype:"GET",Jback: function(){
                    meals.showMealDate(this.data);
                    $(".cover").hide();
                    $(".comboGroup").show();

                }});
            }else{
                $(".cover").hide();
                $(".comboGroup").show();
            }

        },

        showMealDate:function(meals){
            var meals_detail = {
                getMealsData:function(url,$box){
                    inf.getDate({Jurl: url, Jtype:"GET",Jback: function(){
                        meals_detail.showMealDate(this.data,$box);
                    }});
                },
                showMealDate:function(meals_detail_data ,$box){
                    if(meals_detail_data){
                        inf.ajaxTemp("mealdetail",meals_detail_data, $box);

                        $box.parents(".Jmeal_info").siblings().find(".mealdetail").slideUp();
                        $box.slideDown();
                        $box.parents(".Jmeal_info").siblings().find("a").removeClass("active");
                        $box.parents(".more").siblings().find("a").removeClass("active");
                        $box.siblings().find("a").addClass("active");
                        $(".mealdetail dt").click(function(){
                            detailView($(this).attr("resid"));
                        });
                    }
                }
            }
            
            if(meals){
                $(".time_container").show();
                if(meals.saleDate !=""){
                    $("#J_Calender").val(meals.saleDate);
                }
                $(".pricenum i").text(meals.minPrice);
                inf.ajaxTemp("meal",meals, $("#meals"));

                //绑定事件
                $(".J_Calender").click(function(){
                	var pid = $("#productId").val();
                	var pkid = $(this).attr("data-pkid");
                	if(pid && pkid) {
                		inf.getDate({Jurl: "/api/package/queryPrice/"+pid+"/"+pkid+".json", Jtype:"GET",Jback: function(){
                			pickerEvent.setPriceArr(this.data);
                			pickerEvent.Init("",function(date){
                				date = date.replace(/-/g,"");
                				window.location.href="/order/buy.html?productId="+pid+"&packageId="+pkid+"&saleDate="+date;
                			});
                		}});
                		
                	}
                });
                
                $("#comboGroupMore").click(function(){
                    var $moreBlock = $(".comboGroup_main").find(".more");
                    if($moreBlock.is(":hidden")){
                        $(".comboGroup_main").find(".more").slideDown();
                        $(this).find("a").removeClass().addClass("unfold").text("收起");
                    }else{
                        $(".comboGroup_main").find(".more").slideUp();
                        $(this).find("a").removeClass().addClass("fold").text("查看更多套餐");
                    }

                });
                $(".comboGroup_main li.title a,.comboGroup_main .more li.title a").click(function(){
                    var $meals_detail_container=$(this).parents(".Jmeal_info").find(".mealdetail");
                    var meals_detail_url="/api/package/res/"+$("#productId").val()+"/"+$(this).attr("packageid")+".json";
                    if($meals_detail_container.find("ol").length > 0){
                        if($meals_detail_container.is(":hidden"))
                        {
                            $meals_detail_container.parents(".Jmeal_info").siblings().find(".mealdetail").slideUp();
                            $meals_detail_container.parents(".more").siblings().find(".mealdetail").slideUp();
                            $meals_detail_container.slideDown();
                            $(this).parents(".Jmeal_info").siblings().find("a").removeClass("active");
                            $(this).parents(".more").siblings().find("a").removeClass("active");

                            $(this).addClass("active");
                        }
                        else{
                            $meals_detail_container.slideUp();
                            $(this).removeClass("active");
                        }
                    }else{
                        meals_detail.getMealsData(meals_detail_url,$meals_detail_container);
                    }
                });
            }else{
            }

        },
        history:function(url,productids){
            if(productids){
                inf.getDate({Jurl: url, Jdat:{productIds: productids}, Jtype:"GET",Jback: function(){
                    if(this.data!=null && this.data.length !=0){
                        $("#historyView").show();
                        inf.ajaxTemp("history",{history:this.data,dev:SiteData.DUJIA_SITE},$("#history"));
                    }

                }});
            }
       }
    }

    var adver = {
        favorate:function(url,productId){
            if(productId){
                inf.getDate({Jurl: url, Jdat:{productId: productId}, Jtype:"GET",Jback: function(){
                    if(this.data!=null && this.data.length !=0){
                        inf.ajaxTemp("favorate",{data:this.data,dev:SiteData.DUJIA_SITE},$("#favorate"));
                        $("#favorate").parents(".favorate").show();
                    }


                    $("#favorate").find("li").mouseover(function(){
                        $(this).addClass("active").siblings().removeClass("active");
                    });

                }});
            }
        }
    }



    meals.getMealsData(meals_url);
    adver.favorate("/enjoy.html",$("#productId").val());

    var detailView = function(resid){

        if(resid){
            inf.getDate({Jurl: "/api/package/res/"+resid+".json", Jtype:"GET",Jback: function(){
                if(this.data!=null){
                    var $popDetail = $("#popdetail");
                }
                var _scrollHeight = $(document).scrollTop(),//获取当前窗口距离页面顶部高度
                    _windowHeight = $(window).height(),//获取当前窗口高度
                    _windowWidth = $(window).width(),//获取当前窗口宽度
                    _popupHeight = $popDetail.height(),//获取弹出层高度
                    _popupWeight = $popDetail.width();//获取弹出层宽度
                var show = function(flag,url,index){
                    var $bigImg=$("#bigImg");
                    if(flag){
                        $bigImg.show();
                        if(url!=null){
                            $bigImg.html("<img src='"+url+"' width=480>");
                        }

                        var left = (index+1) * 137+ index*5 + 40;
                        if(index > 3){
                            left = (index) * 137+ (index-1)*5-$bigImg.width() + 30;
                        }

                        // 定义上与左坐标：图片跟随鼠标动
                        $bigImg.css({
                            "position":"absolute",
                            "left":left+"px",
                            "top":"90px"
                        })
                    }else{
                        // mouseout,不显示
                        $bigImg.hide();
                    }
                }

                $popDetail.css({"left": (_windowWidth - _popupWeight) / 2 + "px", "top":  (_windowHeight - _popupHeight) / 2 + _scrollHeight + "px"});//设置position
                $popDetail.show();
                $("#lean_overlay").show();

                inf.ajaxTemp("detail",this.data,$popDetail);

                $popDetail.find(".nor").mouseover(function(e){
                    show(1,$(this).attr("bigsrc"),$(this).index());
                }).mouseout(function(){
                    show(0);
                });

                // 关闭按钮
                $(".close").click(function(){
                    $("#lean_overlay").click();
                })

            }});
        }
    }

    /*$("#J_Calender").parent("span.time").click(function(){
        $(this).addClass("focus");
        inf.getDate({Jurl: "/api/package/queryPrice/"+$("#productId").val()+".json", Jtype:"GET",Jback: function(){
            pickerEvent.setPriceArr(this.data);
            pickerEvent.Init("J_Calender",function(date){
                date = date.replace(/-/g,"");
                meals.getMealsData(meals_url+"?d="+date);
                $(this).removeClass("focus");
            });
        }});
    });*/
    

    $(document).bind("click", function (event) {
        var e = event || window.event;
        var elem = e.srcElement || e.target;

        if( !$(elem).parents().is("span.time") && !$(elem).parents().is("#calendar_choose")){
            $("#J_Calender").parent("span.time").removeClass("focus");
        }
    });

    // 查看详情

    $(".J_detail a").click(function(){
        detailView($(this).attr("resid"));
    });

    $(".detail_head").find(".close").click(function(){
        event.stopPropagation();
        $(".popdetail").hide();
        $("#lean_overlay").hide();
    });
    $("#lean_overlay").click(function(e){
        $(".popdetail").hide();
        $(".largemap").hide();
        $("#lean_overlay").hide();
    });

    //价格说明

    $(".J_instruction").mouseover(function(d) { //鼠标移上事件
        var c = $(this).attr("data_content"); //把title的赋给自定义属性 myTilte ，屏蔽自带提示
        $("body").append('<div id="tooltip">' + c + "</div>"); //创建提示框,添加到页面中
        $("#tooltip").css({
            left: (d.pageX-10 ) + "px",
            top: (d.pageY+10) + "px",
            opacity: "0.8"
        }).show(250); //设置提示框的坐标，并显示
    }).mouseout(function() { //鼠标移出事件
        $("#tooltip").remove(); //移除弹出框
    }).mousemove(function(d) { //跟随鼠标移动事件
        $("#tooltip").css({
            left: (d.pageX - 10) + "px",
            top: (d.pageY+10) + "px"
        });
    });



    //历史查看
    if($.cookie) {
        var historyIds = $.cookie("historyIds");

        if (historyIds != null) {

            meals.history("/history.html", historyIds);


            var arrHistory = historyIds.split(",");
            for (var i = 0; i < arrHistory.length; i++) {

                if (arrHistory[i]!="" && arrHistory[i] == $("#productId").val()) {
                    arrHistory.splice(i, 1);
                    break;
                }
            }
            if (arrHistory.length >= 5) {
                arrHistory.splice(arrHistory.length-1, 1);
            }
            arrHistory.splice(0, 0, $("#productId").val());
            $.cookie("historyIds", arrHistory.toString());
        }else{
            $.cookie("historyIds", $("#productId").val());
        }
    }else{
        $.getScript("../js/cookie.js",function(){
            var historyIds = $.cookie("historyIds");

            if (historyIds != null) {

                meals.history("/history.html", historyIds);


                var arrHistory = historyIds.split(",");
                for (var i = 0; i < arrHistory.length; i++) {

                    if (arrHistory[i]!="" && arrHistory[i] == $("#productId").val()) {
                        arrHistory.splice(i, 1);
                        break;
                    }
                }
                if (arrHistory.length >= 5) {
                    arrHistory.splice(arrHistory.length-1, 1);
                }
                arrHistory.splice(0, 0, $("#productId").val());
                $.cookie("historyIds", arrHistory.toString());
            }else{
                $.cookie("historyIds", $("#productId").val());
            }
        })
    }

})()

/**
 * Created by liuna-ds1 on 2016/5/12.
 */
//焦点图
$(document).ready(function() {
    var a = {
        init: function() {
            this.lunbo();
        },
        lunbo: function() {
            // $("#focal01").mouseover(function() {
            //     $("#focal01 .focal-btn>a").css("display", "block")
            // });
            // $("#focal01").mouseleave(function() {
            //     $("#focal01 .focal-btn>a").css("display", "none")
            // });
            dj.get("ui.focusimg", ["ui.gun"],
                function() {
                    $("#focal01 .scroll-wrap-inner>ul").focusimg({
                        item: "li",
                        index: 0,
                        auto: true,
                        interval: 5000,
                        vertical: false,
                        circle: true,
                        prev: "#focal01 .focal-btn-lt",
                        next: "#focal01 .focal-btn-rt",
                        smallimg:  $("#focal01 ol li"),
                        smallevent: "mouseover",
                        smallSelectedClass: "focal-active"
                        // onFocusimgChanged: b
                    });
                });
        }
    };
    a.init();
});