//全局数据
var G = {
    dataListUrl: "/api/productList", // 获取数据接口
    page: 1,
    pagesize: 9,
    count: 0
};
// var gywm = [[${application.gywm.contentNoHtml}]];
$(function () {
    browserRedirect();
    // $("#gywm").text(gywm);
    $.ajax({
        url: G.dataListUrl,
        type: "POST",
        data:{
            page: G.page,
            pagesize: G.pagesize,
        },
        success:function(result){
            if(result.code == 1){
                $.each(result.data.list, function (index, value) {
                    $(".product-list").append(html(value));
                });
            }
        },
        error:function(){
        }
    });
});

function html(value) {
    let html = '<li>' +
        '                <a href="'+value.picture+'" data-magnify="gallery" title="'+value.title+'" data-caption="'+value.title+'"  class="aitem">' +
        '                    <img src="'+value.picture+'" width="270px" height="270px" border="0" title="'+value.title+'"/>' +
        '                </a>' +
        '                <span>' +
        // '                <a href="/index/productDetail?id='+value.id+'">' +
        value.title +
        // '                </a>' +
        '                </span>' +
        '            </li>';
    return html;
}


function browserRedirect() {
    var sUserAgent = navigator.userAgent.toLowerCase();
    var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
    var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
    var bIsMidp = sUserAgent.match(/midp/i) == "midp";
    var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
    var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
    var bIsAndroid = sUserAgent.match(/android/i) == "android";
    var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
    var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
    if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
        G.pagesize = 10;
    } else {
        G.pagesize = 9;
    }
}
