var G = {
    typeUrl: "/api/types",
    productUrl: "/api/productList",
    detailUrl:"/index/productDetail",
    pagesize:9,
    count:0,
    page: 1
};

$(function () {
    $.ajax({
        url: G.typeUrl,
        type: "POST",
        success: function (result) {
            if (result.code == 1) {
                $.each(result.data, function (index, value) {
                    $(".type-list").append('<li><a href="/index/product?type='+value.id+'">' + value.name + '</a></li>');
                });
            }
        },
    });
    render(G.page, G.pagesize);

});

function render(page) {
    let type = $("#type").val();
    $.ajax({
        url: G.productUrl,
        type: "POST",
        data:{
            page:page,
            pagesize:G.pagesize,
            type:type
        },
        success: function (result) {
            if (result.code == 1 && result.data.list.length > 0) {
                G.page = page;
                $(".product-list").html("");
                $.each(result.data.list, function (index, value) {
                    G.count = result.data.total;
                    $(".product-list").append(html(value));
                });

            }
            layui.use(['laypage', 'form'], function () {
                var laypage = layui.laypage;
                var form = layui.form;
                //执行一个laypage实例
                laypage.render({
                    elem: 'pagination', //分页container ID，不用加 # 号
                    theme:'#2553A4',
                    limit: G.pagesize, //页面显示记录条数
                    count: G.count, //数据总数，从服务端得到
                    groups:     5,
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
    $('[data-magnify]').magnify({
        headToolbar: [
            'close'
        ],
        footToolbar: [
            'zoomIn',
            'zoomOut',
            'prev',
            'fullscreen',
            'next',
            'actualSize',
            'rotateRight'
        ],
        title: false
    });

}

function html(value) {
    var html = '<li>' +
        '<a   title="'+value.title+'" class="aitem" ' +
        'data-magnify="gallery"   href="'+value.picture+'" data-caption="'+value.title+'" >' +
        '<img src="'+value.picture+'" alt="'+value.title+'"  /></a>' +
        '                <span>' +
        // '                <a href="/index/productDetail?id='+value.id+'">' +
        value.title +
        // '                </a>' +
        '</span>' +
        ' </li>';
    return html;
}
