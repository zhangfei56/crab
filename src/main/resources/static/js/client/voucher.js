function toggleCheck(){
    var checked = $("#control_check")[0].checked;
    var voucherChecks = document.getElementsByName("voucher_check");
    voucherChecks.forEach(function(val, index, array){
        val.checked = checked;
    });
}

function generatePic(){
    var voucherChecks = document.getElementsByName("voucher_check");


}