$(function(){
//    $("#expire_date").val(new Date().Format("yyyy-MM-dd"));

    $.validator.setDefaults({
		submitHandler: postUser
	});
     $('#add_user_form').validate({

            rules : {
                user_name : {
                    required : true
                },
                phone_number : {
                    required : true,
                    rangelength: [11,11],
                    remote : '/admin/user/phone',
                },
                expire_date :{
                    required: true,
                    date: true
                },
                pre_password:{
                    required: true,
                    minlength: 5
                },
                password:{
                    required: true,
                    minlength: 5,
                    equalTo: "#pre_password"
                }
            },

            messages : {
                user_name : {
                    required : '用户名称不得为空！'
                },
                phone_number : {
                    required : '手机号不得为空！',
                    rangelength : '注意！请输入正确的手机号',
                    remote: '手机号已被注册'
                },
                expire_date:{
                    required: '请输入有效日期！'
                },
//                submitHandler: postUser
            }
      });
});


function postUser() {
    var user={};

    user.name = $("#userName").val();
    user.password=$("#password").val();
    user.phoneNumber=$("#phone_number").val();
    user.expireDate = new Date($("#expire_date").val());
    sendMessage("/admin/addUser", "post", sendUserSuccess, sendUserFailed, user);

}
function sendUserSuccess(data){
    if(data == "success"){
        window.location.href="/admin/users";
    }
}
function sendUserFailed(error) {
     console.log(error);
}