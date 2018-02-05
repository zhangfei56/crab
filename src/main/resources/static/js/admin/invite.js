function destoryInviteCode(spanNode){
    var inviteCode= spanNode.parentNode.parentNode.parentNode.firstElementChild.textContent;
    $("#express_detail_dialog").dialog("open");
    var url = "/anyone/express/"+identityCode;
    sendMessage(url, "get", expressCallback, function(error){
        var errorJson = JSON.parse(error.responseText);
        $("#error_express").text(errorJson.msg);
    }, null);

}

function generateInviteCode(){

}