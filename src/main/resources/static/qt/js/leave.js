var verifyCode = new GVerify("v_container");
var G = {
    typeUrl: "/api/types",
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

function addLeave() {
    var res = verifyCode.validate($("#code_input").val());
    if(!res){
        messageError("验证码输入不正确！！！");
        return false;
    }
    var data=$('#form').serializeJson();
    if(data.contactNumber==""&&data.mobilePhone==""){
        messageError("联系电话或手机号不能同时为空！！！");
        return false;
    }
    if(data.content==""){
        messageError("留言内容不能为空！！！");
        return false;
    }

    $.ajax({
        url: "/api/addLeave",
        type: "POST",
        data:data,
        success:function(data){
            layer.closeAll();
            if(data.code == 1){
                var handler = top.layer.alert("留言成功", {
                    icon: 6
                }, function () {
                    top.layer.close(handler);
                    //关闭弹出层
                    window.location.reload();

                });
            }else{
                messageError(data.msg);
            }
        },
        error:function(){
            layer.closeAll();
        }
    });
    return false;
}
$.fn.serializeJson = function () {
    var serializeObj = {};
    $(this.serializeArray()).each(function () {
        serializeObj[this.name] = this.value;
    });

    return serializeObj;
};