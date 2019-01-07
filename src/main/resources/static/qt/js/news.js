var G = {
    typeUrl: "/api/types",
    newsUrl: "/api/newsList",
    detailUrl: "/index/newsDetail",
    pagesize: 10,
    count: 0,
    page: 1
};

$(function () {
    $.ajax({
        url: G.typeUrl,
        type: "POST",
        success: function (result) {
            if (result.code == 1) {
                $.each(result.data, function (index, value) {
                    $(".type-list").append('<li><a href="/index/product?type=' + value.id + '">' + value.name + '</a></li>');
                });
            }
        },
    });
    render(G.page, G.pagesize);
});


function render(page) {
    $.ajax({
        url: G.newsUrl,
        type: "POST",
        data: {
            page: page,
            pagesize: G.pagesize,
        },
        success: function (result) {
            if (result.code == 1 && result.data.list.length > 0) {
                G.page = page;
                $(".news_list").html("");
                var txt = "";
                $.each(result.data.list, function (index, value) {
                    G.count = result.data.total;
                    $(".news_list").append(html(value));
                });
            }
            layui.use(['laypage', 'form'], function () {
                var laypage = layui.laypage;
                var form = layui.form;
                //执行一个laypage实例
                laypage.render({
                    elem: 'pagination', //分页container ID，不用加 # 号
                    theme: '#2553A4',
                    limit: G.pagesize, //页面显示记录条数
                    count: G.count, //数据总数，从服务端得到
                    groups: 5,
                    curr: page,
                    layout: ['prev', 'page', 'next', 'limit', 'count'],
                    prev: '<i class="layui-icon layui-icon-left"></i>',
                    next: '<i class="layui-icon layui-icon-right"></i>',
                    jump: function (obj, first) {
                        //首次不执行
                        if (first) {
                            //do something
                            return;
                        }
                        render(obj.curr, obj.limit);
                    }

                });

            });

        },
    });
}


function html(value) {
    var html = '   <div class="rnewys">' +
        '<ul class="newconleft">' + value.createTime + '</ul>' +
        ' <ul class="newsconright">' +
        '    <a href="'+G.detailUrl+'?id='+value.id+'" title="'+value.title+'">' +
        '    <div class="newsconright1">' + value.title +
        '    </div>' + value.contentNoHtml.substring(0,100)+ '...</a>' +
        ' </ul></div>';
    return html;
}
