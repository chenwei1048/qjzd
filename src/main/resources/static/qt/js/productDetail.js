var G = {
    typeUrl: "/api/types",
    productUrl: "/api/productList",
    pagesize:9,
    count:6,
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
});
    
