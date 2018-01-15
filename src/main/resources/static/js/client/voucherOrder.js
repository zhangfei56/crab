//获取屏幕宽度，自适应
var oHtml = document.documentElement;
getSize();
window.onresize = function(){
    getSize();
}
function getSize(){
    var screenWidth = oHtml.clientWidth;
   	if(screenWidth < 320){
   		oHtml.style.fontSize = '17.0667px';
   	} else if(screenWidth > 750){
   		oHtml.style.fontSize = '40px';
   	}else{
   		oHtml.style.fontSize = screenWidth/(750/40) + 'px';
   	}
}

$(function(){
    if ($("#voucher_status").val() == 2){
        var url = "/anyone/express/"+$("#identity_code").val();
        sendMessage(url, "get", expressCallback, function (error) {
                console.log(error);
            }, null)
    };

    $("#need_date_input").val(new Date().Format("yyyy-MM-dd"));

         $('#form_address_info').validate({

                rules : {
                    contact : {
                        required : true,
                    },
                    phoneNumber:{
                        required: true,
                        minlength: 8,
                        maxlength: 11
                    },
                    address:{
                        required: true,
                        minlength: 5,
                    },
                    needDateTime:{
                        required: true,
                        date: true,
                    },
                },

                messages : {
                    contact : {
                        required : "请输入联系人",
                    },
                    phoneNumber:{
                        required: "请输入手机号",
                        minlength: "请输入正确的手机号",
                        maxlength: "请输入正确的手机号"
                    },
                    address:{
                        required: "请输入地址",
                        minlength: "请输入详细地址",
                    },
                    needDateTime:{
                        required: "请选择送达时间",
                    },

                },
                submitHandler: function(){
                      var fd = new FormData();
                        fd.append("voucher.identityCode", $("#code_input").val());

                        fd.append("contact",$("#contact_input").val());
                        fd.append("phoneNumber",$("#phone_number_input").val());

                        fd.append("address",$("#address_input").val());
                        var needDate = $("#need_date_input").val();
                        needDate = needDate == null? new Date(): new Date(needDate);
                        fd.append("needDateTime",needDate);

                        sendFormdata("/anyone/order","post", function(data){
                            if(data=="success"){
                                window.location.href = "/anyone/voucher/order/"+ $("#code_input").val();
                            }else{
                                window.location.href = "/client/voucher/voucherExist";
                            }
                        }, function (error) {

                        }, fd);
                }
          });

})

function expressCallback(data){
    console.log(data);
    var express = JSON.parse(data);
    express.result.list.forEach(function(val, index){
       var trcomp="<tr><td>"+val.time+"</td><td class=''></td><td>"+val.status+"</td></tr>";
       $("#show_express_table tbody").append(trcomp);
    });

}
