$(function(){
//    $("#expire_date").val(new Date().Format("yyyy-MM-dd"));

//    $('#mpanel5').pointsVerify({
//        defaultNum : 4,	//默认的文字数量
//        checkNum : 2,	//校对的文字数量
//        vSpace : 5,	//间隔
//        imgUrl: '/img/',
//        imgName : ['1.jpg', '2.jpg'],
//        imgSize : {
//            width: '600px',
//            height: '200px',
//        },
//        barSize : {
//            width : '600px',
//            height : '40px',
//        },
//        ready : function() {
//        },
//        success : function() {
//            alert('验证成功，添加你自己的代码！');
//            //......后续操作
//        },
//        error : function() {
////		        	alert('验证失败！');
//        }
//    });

    jQuery.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写您的手机号码");

     $('#register_form').validate({

            rules : {
                user_name : {
                    required : true
                },
                phone_number : {
                    required : true,
                    isMobile: true,
                    minlength : 11,
                    remote : '/anyone/check/phone',
                },
                phone_number_verify:{
                    digits : true,
                    required : true,
                    remote:{
                        url: '/anyone/check/phoneVerify',
                        data:{
                            phoneNumber:function(){
                            return $("#phone_number").val()},
                            phone_number_verify: function(){
                                return $("#phone_number_verify").val()
                            }
                        }
                    }
                },
                invite_code:{
                    required: true,
                    remote: "/anyone/check/invite"
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
                    required : "请输入手机号",
                    minlength : "确认手机不能小于11个字符",
                    isMobile : "请正确填写您的手机号码",
                    remote : "手机号已被注册"
                },
                invite_code:{
                    required: "请输入邀请码",
                    remote: "无效邀请码"
                },
                phone_number_verify:{
                    required : "请输入验证码",
                    digits : "验证码应该输入数字",
                    remote: "验证码错误"
                }
            },
            submitHandler: function(){
                 var user={};

                 user.name = $("#userName").val();
                 user.password=$("#password").val();
                 user.phoneNumber=$("#phone_number").val();
                 user.invitationCode =$("#invite_code").val();
                 sendMessage("/client/register", "post", sendUserSuccess, sendUserFailed, user);
             }
      });
});

function sendRegisterCode(){
    $('.div-phone span').remove();

    var phoneNumbersCheck = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;

    var phoneNumber = $("#phone_number").val();

    if(!phoneNumbersCheck.test(phoneNumber) || phoneNumber.length ==0){
        $('.div-phone').append('<span class="error">手机格式错误</span>');

        return false;
    }else{

        sendMessage("/anyone/registerCode/send?phoneNumber="+phoneNumber, "get", function(data){},
            function(error){
                alert(error);
            }
        , null);

        var time = 30;
        $('.div-phone span').remove();
        function timeCountDown(){
             if(time==0){
             clearInterval(timer);
             $('#sendVerifyCode').addClass('canSend').removeClass('notSend').html("发送验证码");
             $('#sendVerifyCode').attr("disabled",false);
             return true;
             }
             $('#sendVerifyCode').html(time+"S后再次发送");
             time--;
             return false;
        }
        $('#sendVerifyCode').addClass('notSend').removeClass('canSend');
        $('#sendVerifyCode').attr("disabled",true);

        timeCountDown();
        var timer = setInterval(timeCountDown,1000);

    }

}


function sendUserSuccess(data){
    if(data == "success"){
        window.location.href="/login";
    }
}
function sendUserFailed(error) {
     console.log(error);
}