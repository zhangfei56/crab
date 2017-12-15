$(function(){
    var url = "/anyone/express/"+$("#identity_code").val();
    sendMessage(url, "get", expressCallback, function (error) {
            console.log(error);
        }, null)
})

function expressCallback(data){
    var express = JSON.parse(data);

}