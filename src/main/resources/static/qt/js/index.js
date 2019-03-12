//全局数据
var G = {
    dataListUrl: "/api/productList", // 获取数据接口
    typeListUrl: "/api/types", // 获取数据接口
    page: 1,
    pagesize: 6,
    count: 0
};
// var gywm = [[${application.gywm.contentNoHtml}]];
$(function () {
    // browserRedirect();
    // $("#gywm").text(gywm);
    type();
    product();
});

function type() {
    $.ajax({
        url: G.typeListUrl,
        type: "POST",
        success: function (result) {
            if (result.code == 1) {
                let html = "<tr>";
                let i = 1;
                $.each(result.data, function (index, value) {
                    html += '<td height="49" style="line-height:49px;"> <a href="/index/product?type=' + value.id + '" title="' + value.name + '" style="color:#fff;font-weight:normal"> ' + value.name + ' </a> </td>';
                    if (i == 2) {
                        html += "</tr><tr>";
                        i = 1;
                    } else {
                        i++;
                    }
                });
                html += "</tr>";
                $("#type-list").append(html);
            }
        },
        error: function () {
        }
    });
}

function product() {
    $.ajax({
        url: G.dataListUrl,
        type: "POST",
        data: {
            page: G.page,
            pagesize: G.pagesize,
        },
        success: function (result) {
            if (result.code == 1) {
                let html1 = "<tr>";
                $.each(result.data.list, function (index, value) {
                    html1 += html(value);
                    if (index == 2) {
                        html1 += "</tr><tr>";
                    } else if (index == 5) {
                        html1 += "</tr>";
                    }

                    // $(".product-list").append();
                });
                $("#product-list").append(html1);
            }
        },
        error: function () {
        }
    });
}

// function html(value) {
//     let html = '<li>' +
//         '                <a href="'+value.picture+'" data-magnify="gallery" title="'+value.title+'" data-caption="'+value.title+'"  class="aitem">' +
//         '                    <img src="'+value.picture+'" width="270px" height="270px" border="0" title="'+value.title+'"/>' +
//         '                </a>' +
//         '                <span>' +
//         // '                <a href="/index/productDetail?id='+value.id+'">' +
//         value.title +
//         // '                </a>' +
//         '                </span>' +
//         '            </li>';
//     return html;
// }
function html(value) {
    let html = '<td align="center" style="padding-left:5px">'
        + ' <a href="' + value.picture + '" data-magnify="gallery" title="' + value.title + '" data-caption="' + value.title + '"  class="aitem"> <img src="' + value.picture + '"  width="258" height="200" alt="" title="' + value.title + '" border="0"/> </a><br/>'
        + ' <span style="line-height:25px;"> <a href="' + value.picture + '" data-magnify="gallery" data-caption="' + value.title + '"> ' + value.title + ' </a> </span></td>';
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
        G.pagesize = 6;
    } else {
        G.pagesize = 9;
    }
}
