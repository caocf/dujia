/**
 * Created by zhuchunle on 2016/5/23.
 */
var AlertText = {
    time_out_id : [],
    //失败弹出层提示，type:1,延迟消失，2：不延迟；operationType：按钮格式控制，d_alert，d_confirm，确定和取消按钮;text：显示错误内容
    tips: function (operationType, text, callback) {
        $('#mcon').html('');
        var html_btn = '';
        switch (operationType) {
            case "d_alert" :
                html_btn = '<div id="cancelOrder"><span class="close_cancelOrder">x</span><div><i></i><span>' + text + '</span></div><p><button class="sure_btn">确定</button></p></div>';
                //  html_btn = '<div style="margin-top:20px;" class="text_center pop_btn_box"><a class="pop_btn_grey" href="javascript:void(0)" onclick="AlertText.closeTips()">确定</a></div>';

                break;
            case "d_confirm" :
                html_btn = '<div id="cancelOrder"><span class="close_cancelOrder">x</span><div><i></i><span>' + text + '</span></div><p><button class="affirm_btn">确定</button><button class="return_back" onclick="AlertText.closeTips()">取消</button></p></div>';
                //   html_btn = '<div style="margin-top:20px;" class="text_center pop_btn_box"><a class="pop_btn_grey mr10" href="javascript:void(0)" onclick="AlertText.gotocreatorder()">确定</a><a class="pop_btn_grey" onclick="AlertText.closeTips()" href="javascript:void(0)">取消</a></div>';
                break;
            case "d_load" :
                if(!text){
                    text = "努力为您加载，请稍后"
                }
                html_btn = '<div class="pop_cancel_order"><a href="javascript:;" class="btn_flight_close"></a><div class="clearfix flight_tip_box"><div class="tip_icon"></div><div class="tip_worp">'+text+'</div></div></div>';
                break;
            case "dely_load" :
                html_btn = '<div class="pop_cancel_order"><a href="javascript:;" class="btn_flight_close"></a><div class="clearfix flight_tip_box"><div class="tip_icon"></div><div class="tip_worp">努力为您加载，请稍后</div></div></div>';
                break;
            case "hide" :
                //    html_btn = '<div style="margin-top:20px;" class="text_center pop_btn_box"><a class="pop_btn_grey" href="javascript:void(0)" onclick="AlertText.closeTips()">确定</a></div>';
                //   setTimeout(AlertText.closeTips,5000)
                break;
            case "d_success" :
                html_btn = '<div id="cancelOrder"><span class="close_cancelOrder">x</span><div><i class="icon_Prosperity"></i><span>' + text + '</span></div><p><button class="sure_btn">确定</button></p></div>';
                break;
            case "auto_close" :
                html_btn = '<div id="cancelOrder"><span class="close_cancelOrder">x</span><div><i></i><span>' + text + '</span></div><p><button class="sure_btn">确定</button></p></div>';
                if (callback) {
                    setTimeout(callback, 3000);
                } else {
                    setTimeout(AlertText.closeTips, 5000);
                }
                break;
            case "hide_s" :
                //     html_btn = '<div style="margin-top:20px;" class="text_center pop_btn_box"><a class="pop_btn_grey" href="javascript:void(0)" onclick="AlertText.closeTips()">确定</a></div>';
//                setTimeout(function(){
//                    window.location.href ="/serchTracking.html"
//                },5000);
                break;
        }
        $('#mcon').append(html_btn);
        //$(html_btn).appendTo('#mcon');

        $(".close_cancelOrder").click(function () {
            AlertText.closeTips(callback);
        });
        $(".sure_btn").click(function () {
            AlertText.closeTips(callback);
        });
        if(operationType=="dely_load"){
            var this_id_ = setTimeout(function(){
                if(AlertText.time_out_id.length>0){
                    $('#mask_boxs').show();
                }
            },3000);
            AlertText.time_out_id.push(this_id_);
//            	$('#mask_boxs').show();

        }else{
            $('#mask_boxs').show();
        }

        // $(".pop_cover").show();
        // $("#booking_pop_content").show().append(html_text+html_btn)
    },
    calTips: function (callback) {
        $('#mask_boxs').hide();
        if(AlertText.time_out_id){
            for(out_id_ in AlertText.time_out_id){
                clearTimeout(out_id_);
            }
            AlertText.time_out_id = [];
        }
        if (callback) {
            callback();
        }
    },
    closeTips: function (callback) {
        if(AlertText.time_out_id){
            for(out_id_ in AlertText.time_out_id){
                clearTimeout(out_id_);
            }
            AlertText.time_out_id = [];
        }
        $('#mcon').html('');
        $('#mask_boxs').hide();
        if (callback) {
            callback();
        }
//        $(".pop_cover").hide();
//        $("#booking_pop_content").empty().hide();
//        $("#loading_jpg").hide();
        //window.location.reload()
    },
    isShow : function(){
        if($('#mask_boxs').css("display")=="none"){
            false;
        }else{
            return true;
        }

    }
}