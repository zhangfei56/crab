function updateUser(){
    var fd = new FormData();
    fd.append("file", $("#userImage").get(0).files[0]);
    sendFormdata("/client/updateUser", "post", updateUserSuccessCallback, function (error) {
        console.log(error);
    }, fd)
}


function updateUserSuccessCallback(data) {
    Console.log(data);

}