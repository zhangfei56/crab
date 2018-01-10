$(function(){
//    $("#expire_date").val(new Date().Format("yyyy-MM-dd"));

    $.validator.setDefaults({
		submitHandler: updatePassword
	});
     $('#change_password_form').validate({

            rules : {
                oldPassword : {
                    required : true,
                    remote: '/client/json/user/checkPassword'
                },
                newPassword:{
                    required: true,
                    minlength: 5
                },
                confirmPassword:{
                    required: true,
                    minlength: 5,
                    equalTo: "#newPassword"
                }
            },

            messages : {
                oldPassword : {
                    required : '密码不能为空！',
                    remote: '密码错误'
                },
                newPassword : {
                    required : '密码不能为空！',
                },
                confirmPassword:{
                    required: '密码不能为空！',
                    equalTo: "两次密码不一致"
                },
            }
      });
});

function updatePassword(){
    var user={};
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