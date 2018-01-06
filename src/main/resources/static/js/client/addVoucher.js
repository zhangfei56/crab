$(function(){
    sendMessage("/client/voucherCategory", "get", voucherCategorySuccessCallback, function (error) {
        console.log(error);
    }, null)
});

function voucherCategorySuccessCallback(data) {
    var categories = JSON.parse(data);
    categories.forEach(function(category,index,array){
        var option = $("<option>").val(category.id).text(category.detail);
        $("#category").append(option);
    });
}

function postVoucher(){
    var categoryId = $("#category").val();
    var deadlineDate = new Date($("#voucherCreateDate").val());
    var total = parseInt($("#voucherTotal").val());

    sendMessage("/client/voucher/addVoucher","post",
        function(successData){
            if(successData == "success"){
                window.location.href="/client/vouchers?status=-1&size=10";
            }
        }, function(errorData){
            console.log(errorData);
        },
        {total,categoryId, deadlineDate});

}