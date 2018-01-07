$(function(){
    if ($("#voucher_status").val() == 2){
        var url = "/anyone/express/"+$("#identity_code").val();
        sendMessage(url, "get", expressCallback, function (error) {
                console.log(error);
            }, null)
    };

    $("#need_date_input").val(new Date().Format("yyyy-MM-dd"));

})

function expressCallback(data){
    console.log(data);
    var express = JSON.parse(data);
    express.result.list.forEach(function(val, index){
       var trcomp="<tr><td>"+val.time+"</td><td class=''></td><td>"+val.status+"</td></tr>";
       $("#show_express_table tbody").append(trcomp);
    });

}

function sendVoucherOrder(){
    var fd = new FormData();
    fd.append("voucher.identityCode", $("#code_input").val());

    fd.append("contact",$("#contact_input").val());
    fd.append("phoneNumber",$("#phone_number_input").val());

    fd.append("address",$("#address_input").val());
    var needDate = $("#need_date_input").val();
    needDate = needDate == null? new Date().Format("yyyy-MM-dd"): needDate;
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