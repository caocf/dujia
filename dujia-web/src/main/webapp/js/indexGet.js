var infoMain = function () {
};

infoMain.prototype = {
    /**模板方法**/
    ajaxTemp: function (temp,dat,box) {
        var _htm = template(temp, dat);
        $(box).html(_htm);
    },
    getDate: function (dat) {
        var _d = {};
        _d.data = undefined, _t = this;
        $.ajax({
            type: dat.Jtype?dat.Jtype:'GET',
            url: dat.Jurl,
            data: dat.Jdat,
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {

                _d.data = data, _d.obj = _t;
                if (dat.Param)_d.param = dat.Param;
                if (dat.Jback)_d.callback = dat.Jback;
                _t.isLogin.apply(_d);
            },
            error: function (xhr, status) {
            }
        });
    },
    isLogin: function () {
        if (this.callback) {
            this.callback.apply(this);
        };
    }
};
/* 事件初始化 */
/*var inf = new infoMain();

/!**初始化模板**!/
var domInit = {
    //埋码
    embeddedCode:function(){
    },
    placeholder:function(input){
        var i = document.createElement('input'),
                placeholdersupport = 'placeholder' in i;
        if(!placeholdersupport){
            var inputs = $(input);
            inputs.each(function(){
                var input = $(this),
                    text = input.attr('placeholder'),
                    pdl = 0,
                    height = input.outerHeight(),
                    width = input.outerWidth(),
                    placeholder = $('<span class="phTips">'+text+'</span>');
                    input.parent().css("position","relative");
                try{
                    pdl = input.css('padding-left').match(/\d*!/i)[0] * 1;
                }catch(e){
                    pdl = 5;
                }
                placeholder.css({'margin-left': -(width-pdl-120),'height':height,'line-height':height+"px",'position':'absolute',top:0, 'color': "#cecfc9", 'font-size' : "12px", 'padding-left':0});
                placeholder.click(function(){
                    input.focus();
                });
                if(input.val() != "" || input.is(":hidden")){
                    placeholder.css({display:'none'});
                }else{
                    placeholder.css({display:'inline'});
                }
                placeholder.insertAfter(input);
                input.keyup(function(e){
                    if($(this).val() != ""){
                        placeholder.css({display:'none'});
                    }else{
                        placeholder.css({display:'inline'});
                    }
                });
            });
        }
        return inputs;
    }

};
/!* 起始调用 *!/
//domInit.embeddedCode();
//domInit.placeholder('input[placeholder]');*/
