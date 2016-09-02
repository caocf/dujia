/**
 * Created by zhuchunle on 2015/11/30.
 */
//模拟 alert 与 confirm
window.alert = function(msg ,callBack) {
    modal.modal($("#alert"), $(".close_modal"), function() {
        $("#alert .modal-text").html(msg);
        $("#alert .btnsConfirm").click(function() {
            if(callBack) callBack();
            modal.hide($("#alert"))
        });
    })
}
window.confirm = function(msg, ok_callBack,cancel_callBack) {
    modal.modal($("#confirm"), $(".close_modal,#confirm .btnsCancel"), function() {
        $("#confirm .modal-text").html(msg);
        $("#confirm .btnsConfirm").click(function() {
            ok_callBack();
            modal.hide($("#confirm"));
        });
        $("#confirm .btnsCancel").click(function() {
            cancel_callBack();
            modal.hide($("#confirm"));
        });
    });

}
//模态框 jquery
var modal = {
    modal: function(b, c, callback) {
        b.css({
            "margin-left": -b.width() / 2
        });
        b.show();
        $(".tck-cover").show();
        if ($(".tck-cover").length == 0) {
            $("body").append('<div class="tck-cover"></div>');
            $(".tck-cover").show();
        };
        /*$(document).on("click", ".tck-cover", function() {
         modal.hide(b);
         })*/
        if (c) {
            c.click(function() {
                modal.hide(b);
            });
        }
        if (callback) {
            callback()
        }
    },
    hide: function(b) {
        b.hide();
        $(".tck-cover").remove();
    }
}
