function updatePassword(){
    var user={};
    user.oldPassword = $("#oldPassword").val();
    user.newPassword = $("#newPassword").val();
    sendMessage("/client/json/user/updatePassword", "post", updatePasswordSuccessCallback, function (error) {
        console.log(error);
    }, user);
};


function updatePasswordSuccessCallback(data) {
    if(data=="success"){
        alert("update success");
    }else if(data == "oldPasswordFailed"){
        alert("old password error");
    }
}