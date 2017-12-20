$(function(){
    var status = $("#voucher_status_id").val();
    $("#voucher_status_select").val(status);

   $("#voucher_status_select").on("change", function(){
       window.location.href="/client/orders?status="+$("#voucher_status_select").val();
   });
});

function openDialog(){
    $("#orderDialog").show();
}