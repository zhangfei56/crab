function postUser() {
    var user={};

    user.name = $("#userName").val();
    user.password=$("#password").val();
    sendMessage("/admin/addUser", "post", sendUserSuccess, sendUserFailed, user);

}
function sendUserSuccess(data){
    console.log(data);
}
function sendUserFailed(error) {
     console.log(error);
}