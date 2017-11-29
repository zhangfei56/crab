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