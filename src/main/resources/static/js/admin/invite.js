function destoryInviteCode(spanNode){
    var inviteCode= spanNode.nextElementSibling.value;
    var url = "/admin/invite/destroy?inviteId="+inviteCode;
    sendMessage(url, "get", function(data){
            if(data =="success"){
                    window.location.reload();
            }
        }, function(error){
            alert(error);
    }, null);

}

function generateInviteCode(){

    sendMessage("/admin/invite/add", "get", function(data){
            if(data =="success"){
                window.location.reload();
            }
        }, function(error){
            alert(error);

        }, null);
}