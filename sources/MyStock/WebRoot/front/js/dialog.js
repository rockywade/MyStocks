/*
    Create By:Mike.Jiang
    Create Date:2012-07-03
*/
var Dialog = {

    RemoveDialog: function() {
        $("#dFilter").remove();
        $("#dBody").remove();
        Dialog.ShowSelect();
    }
}
$(function() {
    $.fn.extend({
        ShowDialog: function(options) {
            //插件内部函数
            var nFun = {
                HideSelect: function() {
                    $("select").hide();
                },
                ShowSelect: function() {
                    $("select").show();
                },
                RemoveDialog: function() {
                    $("#dFilter").remove();
                    $("#dBody").remove();
                    nFun.ShowSelect();
                },
                CenterShow: function(dBody, width, height) {
                    var topPX = 0;
                    topPX = $(document.documentElement).height() - height;
                    if (topPX < 0) {
                        topPX = 0;
                    } else {
                        topPX = $(document.documentElement).scrollTop() + topPX / 2;
                    }
                    var leftX = $(document.documentElement).width() - width;
                    if (leftX < 0) {
                        leftX = 0;
                    } else {
                        leftX = $(document.documentElement).scrollLeft() + leftX / 2;
                    }
                    dBody.css({ "left": leftX + "px", "top": topPX + "px" });
                }
            }
            var settings = {
                height: 400, //弹出框的高度
                width: 400, //弹出框的宽度
               // title: "标题", // 弹出框的标题
                src: "", //弹出框的页面URL
                beforeShow: nFun.HideSelect, //在显示弹出框之前的方法
                afterClose: nFun.ShowSelect //在关闭弹出框之后的方法
            };
            if (options) {
                $.extend(settings, options);
            }
            nFun.RemoveDialog();
            settings.beforeShow();
            //背影遮盖(是否为模式窗口)
            var dFilter = $('<div id="dFilter" class="dFilter"></div>').appendTo(document.body);
            dFilter.css("opacity", "0.5");
            dFilter.css("height", $(document).height());
            dFilter.css("width", $(document).width());
            //弹出页面的主DIV
            var dBody = $('<div id="dBody" class="dBody"  ></div>').appendTo(document.body);
            var dBodyHeight = settings.height + 20;
            dBody.css({ "width": settings.width + "px", "height": dBodyHeight + "px" });
            //弹出页面的头部
            var dHeader = $('<div class="dHeader" ></div>').appendTo(dBody);
            //弹出页面的标题
            var dTitle = $('<label></label>').appendTo(dHeader);
            dTitle.text(settings.title);
            //弹出页面的关闭按钮
            var dClose = $('<img alt="" />').appendTo(dHeader);
            //弹出页面的内容容器iframe
            var dIframe = $('<iframe frameborder="0"  ></iframe>').appendTo(dBody);
            
            //设置弹出框的宽高
            var dIframeWidth = settings.width;
            dIframe.attr({ "width": dIframeWidth + "px", "height": settings.height + "px", "src": settings.src });
            
            //设置弹出框居中显示
            nFun.CenterShow(dBody, settings.width, settings.height);
            //关闭按钮事件
            dClose.click(function() {
                nFun.RemoveDialog();
                settings.afterClose();
            });
            $(window).resize(function() {
                nFun.CenterShow(dBody, settings.width, settings.height);
            });
        }
    });
});