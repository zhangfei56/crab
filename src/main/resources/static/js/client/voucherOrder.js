function sendVoucherOrder(){
    var fd = new FormData();
    fd.append("voucher.identityCode", $("#code_input").val());

    fd.append("contact",$("#contact_input").val());
    fd.append("phoneNumber",$("#phone_number_input").val());

    fd.append("address",$("#address_input").val());
    fd.append("needDateTime",new Date($("#need_date_input").val()));

    sendFormdata("/anyone/order","post", function(data){
        if(data=="success"){
            window.location.href = "/anyone/voucher/order/"+ $("#code_input").val();
        }else{
            window.location.href = "/client/voucher/voucherExist";
        }
    }, function (error) {

    }, fd);

}