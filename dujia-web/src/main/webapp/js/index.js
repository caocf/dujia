/**
 * Created by zhuchunle on 2016/4/19.
 */

//城市相关
(function(){
    var inf = new infoMain();
    //获取热门城市并渲染
    var city = {
        cityId:"",
        cityPinYin:"",
        plateform:$("#plateform").val(),
        hotCityShow: function(hotcity){
            var $hotCity = $("#hotCity");
            for(var i=0;i<hotcity.length;i++){
                var $hotAtom = $('<a class="name">'+hotcity[i].name+'</a>');

                $hotAtom.data("id",hotcity[i].id);
                $hotAtom.data("name",hotcity[i].name);
                $hotAtom.data("py",hotcity[i].pinyin);
                $hotCity.append($hotAtom);
                $hotAtom.click(function(){
                    city.changeCity($(this).data("id"),$(this).data("name"),$(this).data("py"));

                });
            }

            $("#hotCity").find("a").click(function(){
                city.changeCity($(this).data("id"),$(this).data("name"),$(this).data("py"));
            });
        },
        cityListShow: function(city_data,callback){

            var $citySelectBox = $("#selectcity_box");
            for(var i = 0;i<5 ;i++){
                var $allDivs = $('<div class="selectcity_tab_cont clearfix"></div>');
                $citySelectBox.append($allDivs);
            }

            //排序
            city_data.sort(function(a,b){
                //return a.cityPinyin.toLocaleUpperCase()-b.cityPinyin.toLocaleUpperCase();
                return a.pinyin.toLocaleUpperCase().localeCompare(b.pinyin.toLocaleUpperCase())
            });
            for(var i = 0;i < city_data.length; i++){
                var $allDivs = $('.selectcity_tab_cont');
                //var $atom = '<span code="'+this.data[i].fconfigId+'" class="name">'+this.data[i].cityName+'</span>';

                var $atom = $('<a href="javascript:" id="'+city_data[i].id+'" name="'+city_data[i].name+'" class="name">'+city_data[i].name+'</a>');
                $atom.data("id",city_data[i].id);
                $atom.data("name",city_data[i].name);
                $atom.data("py",city_data[i].pinyin);
                switch (city_data[i].pinyin.charAt(0).toLocaleUpperCase()){
                    case "A":
                        if( $citySelectBox.find("#letterA").length==0){
                            var $letterA = $('<dl class="clearfix" ><dt>A</dt><dd id="letterA"></dd></dl>');
                            $($allDivs[1]).append($letterA);
                        }
                        $citySelectBox.find("#letterA").append($atom);
                        break;
                    case "B":
                        if( $citySelectBox.find("#letterB").length==0){
                            var $letterB = $('<dl class="clearfix"><dt>B</dt><dd id="letterB"></dd></dl>');
                            $($allDivs[1]).append($letterB);
                        }
                        $citySelectBox.find("#letterB").append($atom);
                        break;
                    case "C":
                        if( $citySelectBox.find("#letterC").length==0){
                            var $letterC = $('<dl class="clearfix"><dt>C</dt><dd id="letterC"></dd></dl>');
                            $($allDivs[1]).append($letterC);
                        }
                        $citySelectBox.find("#letterC").append($atom);
                        break;
                    case "D":
                        if( $citySelectBox.find("#letterD").length==0){
                            var $letterD = $('<dl class="clearfix"><dt>D</dt><dd id="letterD"></dd></dl>');
                            $($allDivs[1]).append($letterD);
                        }
                        $citySelectBox.find("#letterD").append($atom);
                        break;
                    case "E":
                        if( $citySelectBox.find("#letterE").length==0){
                            var $letterE = $('<dl class="clearfix"><dt>E</dt><dd  id="letterE"></dd></dl>');
                            $($allDivs[1]).append($letterE);
                        }
                        $citySelectBox.find("#letterE").append($atom);
                        break;
                    case "F":
                        if( $citySelectBox.find("#letterF").length==0){
                            var $letterF = $('<dl class="clearfix"><dt>F</dt><dd  id="letterF"></dd></dl>');
                            $($allDivs[1]).append($letterF);
                        }
                        $citySelectBox.find("#letterF").append($atom);
                        break;
                    case "G":
                        if( $citySelectBox.find("#letterG").length==0){
                            var $letterG = $('<dl class="clearfix"><dt>G</dt><dd  id="letterG"></dd></dl>');
                            $($allDivs[2]).append($letterG);
                        }
                        $citySelectBox.find("#letterG").append($atom);
                        break;
                    case "H":
                        if( $citySelectBox.find("#letterH").length==0){
                            var $letterH = $('<dl class="clearfix"><dt>H</dt><dd  id="letterH"></dd></dl>');
                            $($allDivs[2]).append($letterH);
                        }
                        $citySelectBox.find("#letterH").append($atom);
                        break;
                    case "J":
                        if( $citySelectBox.find("#letterJ").length==0){
                            var $letterJ = $('<dl class="clearfix"><dt>J</dt><dd  id="letterJ"></dd></dl>');
                            $($allDivs[2]).append($letterJ);
                        }
                        $citySelectBox.find("#letterJ").append($atom);
                        break;
                    case "K":
                        if( $citySelectBox.find("#letterE").length==0){
                            var $letterE = $('<dl class="clearfix"><dt>E</dt><dd  id="letterE"></dd></dl>');
                            $($allDivs[3]).append($letterE);
                        }
                        $citySelectBox.find("#letterE").append($atom);
                        break;
                    case "L":
                        if( $citySelectBox.find("#letterL").length==0){
                            var $letterL = $('<dl class="clearfix"><dt>L</dt><dd id="letterL"></dd></dl>');
                            $($allDivs[3]).append($letterL);
                        }
                        $citySelectBox.find("#letterL").append($atom);
                        break;
                    case "M":
                        if( $citySelectBox.find("#letterM").length==0){
                            var $letterM = $('<dl class="clearfix"><dt>M</dt><dd id="letterM"></dd></dl>');
                            $($allDivs[3]).append($letterM);
                        }
                        $citySelectBox.find("#letterM").append($atom);
                        break;
                    case "N":
                        if( $citySelectBox.find("#letterN").length==0){
                            var $letterN = $('<dl class="clearfix"><dt>N</dt><dd id="letterN"></dd></dl>');
                            $($allDivs[3]).append($letterN);
                        }
                        $citySelectBox.find("#letterN").append($atom);
                        break;
                    case "P":
                        if( $citySelectBox.find("#letterP").length==0){
                            var $letterP = $('<dl class="clearfix"><dt>P</dt><dd id="letterP"></dd></dl>');
                            $($allDivs[4]).append($letterP);
                        }
                        $citySelectBox.find("#letterP").append($atom);
                        break;
                    case "Q":
                        if( $citySelectBox.find("#letterQ").length==0){
                            var $letterQ = $('<dl class="clearfix"><dt>Q</dt><dd id="letterQ"></dd></dl>');
                            $($allDivs[4]).append($letterQ);
                        }
                        $citySelectBox.find("#letterQ").append($atom);
                        break;
                    case "R":
                        if( $citySelectBox.find("#letterR").length==0){
                            var $letterR = $('<dl class="clearfix"><dt>R</dt><dd id="letterR"></dd></dl>');
                            $($allDivs[4]).append($letterR);
                        }
                        $citySelectBox.find("#letterR").append($atom);
                        break;
                    case "S":
                        if( $citySelectBox.find("#letterS").length==0){
                            var $letterS = $('<dl class="clearfix"><dt>S</dt><dd id="letterS"></dd></dl>');
                            $($allDivs[4]).append($letterS);
                        }
                        $citySelectBox.find("#letterS").append($atom);
                        break;
                    case "T":
                        if( $citySelectBox.find("#letterT").length==0){
                            var $letterT = $('<dl class="clearfix"><dt>T</dt><dd id="letterT"></dd></dl>');
                            $($allDivs[4]).append($letterT);
                        }
                        $citySelectBox.find("#letterT").append($atom);
                        break;
                    case "W":
                        if( $citySelectBox.find("#letterW").length==0){
                            var $letterW = $('<dl class="clearfix"><dt>W</dt><dd id="letterW"></dd></dl>');
                            $($allDivs[4]).append($letterW);
                        }
                        $citySelectBox.find("#letterW").append($atom);
                        break;
                    case "X":
                        if( $citySelectBox.find("#letterX").length==0){
                            var $letterX = $('<dl class="clearfix"><dt>X</dt><dd id="letterX"></dd></dl>');
                            $($allDivs[5]).append($letterX);
                        }
                        $citySelectBox.find("#letterX").append($atom);
                        break;
                    case "Y":
                        if( $citySelectBox.find("#letterY").length==0){
                            var $letterY = $('<dl class="clearfix"><dt>Y</dt><dd id="letterY"></dd></dl>');
                            $($allDivs[5]).append($letterY);
                        }
                        $citySelectBox.find("#letterY").append($atom);
                        break;
                    case "Z":
                        if( $citySelectBox.find("#letterZ").length==0){
                            var $letterZ = $('<dl class="clearfix"><dt>Z</dt><dd id="letterZ"></dd></dl>');
                            $($allDivs[5]).append($letterZ);
                        }
                        $citySelectBox.find("#letterZ").append($atom);
                        break;
                }


                $atom.click(function(){
                    city.changeCity($(this).data("id"),$(this).data("name"),$(this).data("py"));
                });
            }

            if(callback){
                callback.call(city);
            }
        },
        fnTab:function(tabLi,tabContent,tabClass,tabContentClass){
            tabLi.on('click',function(event){
                var index=$(this).index();
                tabLi.each(function() {
                    $(this).removeClass(tabClass);
                });
                tabContent.each(function() {
                    $(this).removeClass(tabClass);
                });
                $(this).addClass(tabClass);
                tabContent.eq(index).show().siblings("."+tabContentClass).hide();
                event.stopPropagation();
            });
        },
        getCityData:function(url,param){
            inf.getDate({Jurl: url, Param: param, Jback: function(){
                var city_data = this.data;
                //热门城市
                city.hotCityShow(city_data.cityMap.hot);

                // 全部城市
                city.cityListShow(city_data.cityMap.all,function(){
                    this.fnTab($('#showTicket_selsect_city_box .selectcity_tab_ul li'),$('#showTicket_selsect_city_box .selectcity_tab_cont'),'active','selectcity_tab_cont');
                });
            }});
        },
        getLocalCity: function(url,callback) {
            var localCity = {localId: $.cookie("localId"), localName: $.cookie("localName"),localPinyin: $.cookie("localPinyin")};
            var $localcity = $("#localcity");

            if (localCity.localId) {
                $localcity.attr("data_configId", localCity.localId);
                $localcity.html(localCity.localName);
                city.cityId= localCity.localId;
                city.cityPinYin = localCity.localPinyin;
                //city.cityPinYin = localCity.
                callback();
                //   获取 localcity宽度 设置联想层的left值
                var currCityW = $localcity.parents(".curr_city").width()+46;
                $(".think_more").css("left",currCityW+"px");


            } else {
                inf.getDate({
                    Jurl: url, Jtype:"POST",Jback: function () {
                        if (this.data.id != null && this.data.id != "") {
                            $localcity.attr("data_configId", this.data.id);
                            $localcity.html(this.data.name);
                            $.cookie("localId", this.data.id, {expires: 7});
                            $.cookie("localName", this.data.name, {expires: 7});
                            $.cookie("localPinyin", this.data.pinyin, {expires: 7})
                            $("#localcity").attr("py", this.data.pinyin);
                            city.cityId= this.data.id;
                            city.cityPinYin = this.data.pinyin;
                            callback();
                        }
                    }
                });
            }
        },

        bannerShow: function(url){
            inf.getDate({
                Jurl: url, Jback: function () {
                    inf.ajaxTemp("banner",{arrData:this.data},$("#banner").find("ul"));
                    $.fn.bannerSlide({bannerWrap:"banner",interval:3000});
                }
            });
        },

        floorShow: function(url){
            inf.getDate({
                Jurl: url, Jback: function () {
                    var data = {arrData:this.data,dev:SiteData.DUJIA_SITE};
                    inf.ajaxTemp("floor",data,$(".J_allfloor"));
                    $(".J_allfloor").find("img").lazyload({
                        effect: "fadeIn"
                    });
                }
            });
        },

        weekAdverShow:function(url){
            inf.getDate({
                Jurl: url, Jback: function () {
                    inf.ajaxTemp("according",{arrData:this.data},$("#accordion"));
                    //slideMenu.build('sm',400,10,10,1);
                    slider.init();
                }
            });
        },

        changeCity: function(id,name,py){
            // loading begin
            //AlertText.tips("d_load", "");
            if(!id){
                city.getLocalCity("/city/locater.html",function(){
                    if(city.cityId !=null && city.cityId != ""){
                        oFloatingLayer.getLayerData("/api/line/quick/"+city.plateform+"/"+city.cityId+".json");
                        // floor
                        city.floorShow("/api/line/get/"+city.plateform+"/"+city.cityId+".json");
                        // 周末去哪玩儿
                        city.weekAdverShow("/api/city/weekend/"+city.plateform+"/"+city.cityId+".json");

                    }
                });
            }else{
                $("#localcity").text(name);
                // 获取点击的城市 存放cookie
                $.cookie("localId",id, { expires: 7 });
                $.cookie("localName",name, { expires: 7 });
                $.cookie("localPinyin",py, { expires: 7 });
                $("#localcity").attr("py",py);
                city.cityId = id;
                city.cityPinYin = py;
                //    根据城市获取数据的请求
                oFloatingLayer.getLayerData("/api/line/quick/"+city.plateform+"/"+id+".json");
                // floor
                city.floorShow("/api/line/get/"+city.plateform+"/"+city.cityId+".json");

                // 周末去哪玩儿
                city.weekAdverShow("/api/city/weekend/"+city.plateform+"/"+city.cityId+".json");
                $(".selsect_city_box").hide();

                var currCityW = $("#localcity").parents(".curr_city").width()+46;
                $(".think_more").css("left",currCityW+"px");

            }

        }
    }

    //    浮层

    var oFloatingLayer = {
        cityId:"",
        showLayer:function(data){
            if(data != null) {
                data.dev = SiteData.DUJIA_SITE;
                if (data.days != null) {
                    inf.ajaxTemp("days", data, $("#days"));
                }
                if (data.plays != null) {
                    inf.ajaxTemp("plays", data, $("#plays"));
                }

                if (data.hotScenery != null) {
                    inf.ajaxTemp("scenery", data, $("#scenery"));
                }

                if (data.aroundScenery != null) {
                    inf.ajaxTemp("aroundScenery", data, $("#more_scenic"));
                }

                if (data.destination != null) {
                    inf.ajaxTemp("destination", data, $("#destination"));
                    //热门目的地 更多
                    //1 、根据拼音排序  并将手写字母加到里面
                    data.destination.sort(function (a, b) {
                        return a.value.toLocaleUpperCase().localeCompare(b.value.toLocaleUpperCase());
                    });

                    var $morehotbourn = $("#morehotbourn");
                    $morehotbourn.empty();
                    var $cityName;
                    for (var i = 0; i < data.destination.length; i++) {
                        $cityName = $('<dd><a href="'+data.dev+'/' + data.destination[i].cityPy + '/t-d-c' + data.destination[i].id + '-s-h.html">' + data.destination[i].name + '</a></dd>');
                        switch (data.destination[i].value.charAt(0).toLocaleUpperCase()) {
                            case "A":
                                if ($morehotbourn.find("#hotA").length == 0) {
                                    var $letterA = '<li>' +
                                        '<dl id="hotA">' +
                                        '<dt class="fontmaincolor">A</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterA);
                                }
                                $morehotbourn.find("#hotA").append($cityName);
                                break;
                            case "B":
                                if ($morehotbourn.find("#hotB").length == 0) {
                                    var $letterB = '<li>' +
                                        '<dl id="hotB">' +
                                        '<dt class="fontmaincolor">B</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterB);
                                }
                                $morehotbourn.find("#hotB").append($cityName);
                                break;
                            case "C":
                                if ($morehotbourn.find("#hotC").length == 0) {
                                    var $letterC = '<li>' +
                                        '<dl id="hotC">' +
                                        '<dt class="fontmaincolor">C</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterC);
                                }
                                $morehotbourn.find("#hotC").append($cityName);
                                break;
                            case "D":
                                if ($morehotbourn.find("#hotD").length == 0) {
                                    var $letterD = '<li>' +
                                        '<dl id="hotD">' +
                                        '<dt class="fontmaincolor">D</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterD);
                                }
                                $morehotbourn.find("#hotD").append($cityName);
                                break;
                            case "E":
                                if ($morehotbourn.find("#hotE").length == 0) {
                                    var $letterE = '<li>' +
                                        '<dl id="hotE">' +
                                        '<dt class="fontmaincolor">E</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterE);
                                }
                                $morehotbourn.find("#hotE").append($cityName);
                                break;
                            case "F":
                                if ($morehotbourn.find("#hotF").length == 0) {
                                    var $letterF = '<li>' +
                                        '<dl id="hotF">' +
                                        '<dt class="fontmaincolor">F</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterF);
                                }
                                $morehotbourn.find("#hotF").append($cityName);
                                break;
                            case "G":
                                if ($morehotbourn.find("#hotG").length == 0) {
                                    var $letterG = '<li>' +
                                        '<dl id="hotG">' +
                                        '<dt class="fontmaincolor">G</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterG);
                                }
                                $morehotbourn.find("#hotG").append($cityName);
                                break;
                            case "H":
                                if ($morehotbourn.find("#hotH").length == 0) {
                                    var $letterH = '<li>' +
                                        '<dl id="hotH">' +
                                        '<dt class="fontmaincolor">H</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterH);
                                }
                                $morehotbourn.find("#hotH").append($cityName);
                                break;
                            case "J":
                                if ($morehotbourn.find("#hotJ").length == 0) {
                                    var $letterJ = '<li>' +
                                        '<dl id="hotJ">' +
                                        '<dt class="fontmaincolor">J</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterJ);
                                }
                                $morehotbourn.find("#hotJ").append($cityName);
                                break;
                            case "K":
                                if ($morehotbourn.find("#hotK").length == 0) {
                                    var $letterK = '<li>' +
                                        '<dl id="hotK">' +
                                        '<dt class="fontmaincolor">K</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterK);
                                }
                                $morehotbourn.find("#hotK").append($cityName);
                                break;
                            case "L":
                                if ($morehotbourn.find("#hotL").length == 0) {
                                    var $letterL = '<li>' +
                                        '<dl id="hotL">' +
                                        '<dt class="fontmaincolor">L</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterL);
                                }
                                $morehotbourn.find("#hotL").append($cityName);
                                break;
                            case "M":
                                if( $morehotbourn.find("#hotM").length == 0) {
                                    var $letterM = '<li>' +
                                        '<dl id="hotM">' +
                                        '<dt class="fontmaincolor">M</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterM);
                                }
                                $morehotbourn.find("#hotK").append($cityName);
                                break;
                            case "N":
                                if ($morehotbourn.find("#hotN").length == 0) {
                                    var $letterN = '<li>' +
                                        '<dl id="hotN">' +
                                        '<dt class="fontmaincolor">N</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterN);
                                }
                                $morehotbourn.find("#hotN").append($cityName);
                                break;
                            case "P":
                                if ($morehotbourn.find("#hotP").length == 0) {
                                    var $letterP = '<li>' +
                                        '<dl id="hotP">' +
                                        '<dt class="fontmaincolor">P</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterP);
                                }
                                $morehotbourn.find("#hotP").append($cityName);
                                break;
                            case "Q":
                                if ($morehotbourn.find("#hotQ").length == 0) {
                                    var $letterQ = '<li>' +
                                        '<dl id="hotQ">' +
                                        '<dt class="fontmaincolor">Q</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterQ);
                                }
                                $morehotbourn.find("#hotQ").append($cityName);
                                break;
                            case "R":
                                if ($morehotbourn.find("#hotR").length == 0) {
                                    var $letterR = '<li>' +
                                        '<dl id="hotR">' +
                                        '<dt class="fontmaincolor">R</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterR);
                                }
                                $morehotbourn.find("#hotR").append($cityName);
                                break;
                            case "S":
                                if ($morehotbourn.find("#hotS").length == 0) {
                                    var $letterS = '<li>' +
                                        '<dl id="hotS">' +
                                        '<dt class="fontmaincolor">S</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterS);
                                }
                                $morehotbourn.find("#hotS").append($cityName);
                                break;
                            case "T":
                                if ($morehotbourn.find("#hotT").length == 0) {
                                    var $letterT = '<li>' +
                                        '<dl id="hotT">' +
                                        '<dt class="fontmaincolor">T</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterT);
                                }
                                $morehotbourn.find("#hotT").append($cityName);
                                break;
                            case "W":
                                if ($morehotbourn.find("#hotW").length == 0) {
                                    var $letterW = '<li>' +
                                        '<dl id="hotW">' +
                                        '<dt class="fontmaincolor">W</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterW);
                                }
                                $morehotbourn.find("#hotW").append($cityName);
                                break;
                            case "X":
                                if ($morehotbourn.find("#hotX").length == 0) {
                                    var $letterX = '<li>' +
                                        '<dl id="hotX">' +
                                        '<dt class="fontmaincolor">X</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterX);
                                }
                                $morehotbourn.find("#hotX").append($cityName);
                                break;
                            case "Y":
                                if ($morehotbourn.find("#hotY").length == 0) {
                                    var $letterY = '<li>' +
                                        '<dl id="hotY">' +
                                        '<dt class="fontmaincolor">Y</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterY);
                                }
                                $morehotbourn.find("#hotY").append($cityName);
                                break;
                            case "Z":
                                if ($morehotbourn.find("#hotZ").length == 0) {
                                    var $letterZ = '<li>' +
                                        '<dl id="hotZ">' +
                                        '<dt class="fontmaincolor">Z</dt>' +
                                        '</dl>' +
                                        '</li>'
                                    $morehotbourn.append($letterZ);
                                }
                                $morehotbourn.find("#hotZ").append($cityName);
                                break;
                        }
                    }
                }
            }

            $(".recommendList").show();
            // 关闭 loading
            //AlertText.closeTips();
        },
        getLayerData: function (url) {
            inf.getDate({Jurl: url, Jback: function(){
                oFloatingLayer.showLayer(this.data);

            }});
        }
    };
    // 获取banner
    city.bannerShow("/api/adt/query/"+city.plateform+"/A.json");
    city.changeCity();
    city.getCityData("/api/city/query.json",{});

    //关闭选择城市弹层
    $(".close_btn").on("click",function(){
        $(".curr_city").removeClass("unfold").addClass("fold");
        $(this).parent().hide();
    });

    //城市选择框 获得焦点
    $(".curr_city").mouseover(function(event){
        var $this = $(this);
        var $isfold = $(this).find(".fold");
        $this.removeClass("fold").addClass("unfold");
        $this.addClass("show_bgc");

        var oTop=$(this).height()+3;
        $this.parent(".show_city").append($("#showTicket_selsect_city_box"));
        $("#showTicket_selsect_city_box").show().css('top',oTop+'px');
        $(".relation_show_box").hide();
    });
    $(".curr_city").mouseleave(function(e){
        $(".close_btn").click();
    });

    $(".scenic").hover(function(){
        $(".scenic_more").show();
    },function(e){
        $(".scenic_more").hide();
    });
    $(".hotbourn").hover(function(){
        $(".hotbourn_more").show();
    },function(e){
        $(".hotbourn_more").hide();
    });
}());