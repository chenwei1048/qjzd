

$(function () {
    checkIp();
});
function checkIp() {
    $.ajax({
        url: "/client/isSaveIp",
        type: "POST",
        data:{
            cip:cip,
            cname:cname
        },
        success:function(res){
            if(res.code!=1){
                addCount();
            }
        },
        error:function(){
        }
    });
}

function addCount() {
    $.ajax({
        url: "/client/addCount",
        type: "GET",
        success:function(res){
        },
        error:function(){
        }
    });
}
