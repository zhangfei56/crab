function updatePassword(){
    var user={};
    user.oldPassword = $("#oldPassword").val();
    user.newPassword = $("#newPassword").val();
    sendMessage("/client/user/updatePassword", "post", updatePasswordSuccessCallback, function (error) {
        console.log(error);
    }, user);
}


function updatePasswordSuccessCallback(data) {
    Console.log(data);
}