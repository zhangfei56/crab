$(function(){
    var status = $("#voucher_status_id").val();
    $("#voucher_status_select").val(status);

   $("#voucher_status_select").on("change", function(){
       window.location.href="/client/orders?status="+$("#voucher_status_select").val();
   });

   sendMessage("/client/json/courierCompany?common=true", "get", function(data){
        var companies = JSON.parse(data);
        companies.forEach(function(val, index){
            var option = $("<option>").val(val.type).text(val.name);
            $("#company_select").append(option);
        });

       }, function (error) {
       console.log(error);
   }, null);

   $("#confirmDialog").dialog({
       autoOpen : false,   // 是否自动弹出窗口
       modal : true,    // 设置为模态对话框
       resizable : true,
       width : 410,   //弹出框宽度
       height : 240,   //弹出框高度
       title : "确认完成",  //弹出框标题
//       position : "center",  //窗口显示的位置
       buttons:{
           '确定':function(){
              sendMessage("/client/json/finish/order?identityCode="+identityCode, "get", function(data){
                   if(data=="success"){
                       window.location.reload();
                   }

                  }, function (error) {
                  console.log(error);
              }, null);
           },
           '取消':function(){
            $(this).dialog("close");
           }
       }
   });

   $("#orderDialog").dialog({
      autoOpen : false,   // 是否自动弹出窗口
      modal : true,    // 设置为模态对话框
      resizable : true,
      width : 410,   //弹出框宽度
      height : 240,   //弹出框高度
      title : "确认完成",  //弹出框标题
//       position : "center",  //窗口显示的位置
      buttons:{
          '确定':beginSendExpress,
          '取消':function(){
           $(this).dialog("close");
          }
      }
   });

});
var identityCode;
function openDialog(spanNode){
    identityCode= spanNode.parentNode.parentNode.firstElementChild.textContent;
    $("#orderDialog").dialog("open");
}

function beginSendExpress(){
   var type = $("#company_select").val();;
   var trackingNumber = $("#tracking_number_input").val();
   var url = "/client/json/delivery?identityCode=" + identityCode+"&type="+type+"&trackingNumber"+trackingNumber;
   sendMessage(url, "get", function(data){
        if(data=="success"){
            $("#orderDialog").hide();
            window.location.reload();
        }

       }, function (error) {
       console.log(error);
   }, null);
}

function finishOrder(spanNode){
    identityCode= spanNode.parentNode.parentNode.firstElementChild.textContent;
    $("#confirmDialog").dialog("open");
}