/**
 * @description banner of adver
 * @version 1.0 
 * @author  zhuchunle
 * @date    2015-09-14
 */
(function($) {
	var settings = {};
    var methods = {
        init : function(options) {
            settings = $.extend( {}, {
		    	currentIndex:0,
		    	interval:3000,
		    	bannerWrapId:"banner",
		    	totalPic:0,
		    	animating:false
            }, options );
            //划出control点 并返回 doc
            methods.drawCotrol();
            methods.play();
            $("#"+settings.bannerWrapId).on("mouseover", methods.stop);
			$("#"+settings.bannerWrapId).on("mouseout", methods.play);
        },
        drawCotrol:function(){
        	var $bannerWrap = $("#"+settings.bannerWrapId);
        	var $controlDot = $('<ol class="dot clearfix"></ol>');
        	var $dot;
        	for(var i = 0; i < methods.getPicTotal(); i++){
        		$dot = $('<li index='+i+'></li>');
        		if(i == 0){
        			$dot.addClass("active");
        		}
        		$dot.on("click",methods.displayPic);
        		$controlDot.append($dot)
        	}
        	var $prev = $('<a class="btn prev"></a>');
        	var $next = $('<a class="btn next"></a>');
        	$prev.on("click",methods.prevClick);
        	$next.on("click",methods.nextClick);
        	$bannerWrap.append($controlDot).append($prev).append($next);
        	return $controlDot;
        },
        getPicTotal: function(){
        	return $("#"+settings.bannerWrapId+" .list").find("li").length;
        },
        showBtn:function(){
        	$(".dot").find("li").removeClass();
        	$(".dot").find("li").eq(settings.currentIndex).addClass("active");
		},
		displayPic: function(){
			if(!settings.animating){
				if($(this).hasClass("active")){
					return;
				}
				settings.currentIndex = $(this).index();
				methods.showBtn();
				methods.animate();
			}
		},
        prevClick: function(){
        	if(!settings.animating){
	        	if(settings.currentIndex == 0){
					settings.currentIndex = methods.getPicTotal()-1;
				}else{
					settings.currentIndex -= 1;
				}
				methods.showBtn();
				methods.animate();
			}
        },
        nextClick: function(){
        	if(!settings.animating){
	        	if(settings.currentIndex == methods.getPicTotal()-1){
					settings.currentIndex = 0;
				}else{
					settings.currentIndex += 1;
				}
				methods.showBtn();
				methods.animate();
			}
        },
        play: function(){
			timer = setInterval(function(){
				$(".next").click();
			},settings.interval);
		},
		stop: function(){
			clearInterval(timer);
		},
		animate: function(){
			settings.animating = true;
			var $curr_banner = $("#"+settings.bannerWrapId+" .list").find("li").eq(settings.currentIndex);
			if(!document.all){
				$curr_banner.show();
				$curr_banner.animate({
					opacity:1,
					zIndex:1
				},1000,function(){
					settings.animating = false;
				});
				$curr_banner.siblings().animate({
					opacity:0,
					zIndex:0
				},1000,function(){
					settings.animating = false;
					$(this).hide();
				});
			}else{
				$curr_banner.show().siblings().hide();
				settings.animating = false;
			}
		}
    }
    $.fn.bannerSlide = function(method) {
        return methods.init.apply(this, arguments);
    };
})(jQuery);