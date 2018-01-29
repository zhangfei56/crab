$(function(){
    sendMessage("/client/voucherCategory", "get", voucherCategorySuccessCallback, function (error) {
     console.log(error);
    }, null);


    $('#addVoucherForm').validate({
         rules : {
            voucherCreateDate:{
                required: true,
                date: true
            },
            voucherTotal :{
                required: true,
                number: true,
                min:1,
                max:10
            },
         },
         submitHandler: function(){
             var categoryId = $("#category").val();
                 var deadlineDate = new Date($("#voucherCreateDate").val());
                 var total = parseInt($("#voucherTotal").val());

                 sendMessage("/client/voucher/addVoucher","post",
                     function(successData){
                         if(successData == "success"){
                             window.location.href="/client/vouchers?status=-1";
                         }
                     }, function(errorData){
                         console.log(errorData);
                     },
                     {total,categoryId, deadlineDate});
         }
    });
});

 function voucherCategorySuccessCallback(data) {
     var categories = JSON.parse(data);
     categories.forEach(function(category,index,array){
         var option = $("<option>").val(category.id).text(category.detail);
         $("#category").append(option);
     });
 }
