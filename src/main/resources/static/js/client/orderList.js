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
          '确定': function(){
            $("#orderForm").submit();
          },
          '取消':function(){
           $(this).dialog("close");
          }
      }
   });

  $("#express_detail_dialog").dialog({
     autoOpen : false,   // 是否自动弹出窗口
     modal : true,    // 设置为模态对话框
     resizable : true,
     width : 410,   //弹出框宽度
    // height : 240,   //弹出框高度
     title : "快递查询",  //弹出框标题
//       position : "center",  //窗口显示的位置
     buttons:{
         '确定': function(){
          $(this).dialog("close");
         }
     }
  });

  $("#trackingNumberdDialog").dialog({
     autoOpen : false,   // 是否自动弹出窗口
     modal : true,    // 设置为模态对话框
     resizable : true,
     width : 410,   //弹出框宽度
     height : 240,   //弹出框高度
     title : "快递查询",  //弹出框标题
//       position : "center",  //窗口显示的位置
     buttons:{
         '确定': function(){
            $("#newTrackingForm").submit();
         }
     }
  });

    $('#newTrackingForm').validate({
         rules : {
            newTrackingNumber:{
                required : true
            }
         },
         submitHandler: function(){
            var url = "/client/json/tracking?identityCode="+identityCode+"&trackingNumber="+$("#newTrackingNumber").val();
            sendMessage(url, "get", function(successData){
                window.location.reload();
                $("#trackingNumberdDialog").dialog("close");
            },function(error){
                console.error(error);
            }, null);
         }
    });

    $('#orderForm').validate({
         rules : {
            tracking_number_input:{
                required : true
            }
         },
         submitHandler: function(){
           var type = $("#company_select").val();;
           var trackingNumber = $("#tracking_number_input").val();
           var url = "/client/json/delivery?identityCode=" + identityCode+"&type="+type+"&trackingNumber="+trackingNumber;
           sendMessage(url, "get", function(data){
                if(data=="success"){
                    $("#orderDialog").hide();
                    window.location.reload();
                }

               }, function (error) {
               console.log(error);
           }, null);
         }
    });

});
var identityCode;
function openDialog(spanNode){
    identityCode= spanNode.parentNode.parentNode.firstElementChild.textContent;
    $("#orderDialog").dialog("open");
}


function finishOrder(spanNode){
    identityCode= spanNode.parentNode.parentNode.parentNode.firstElementChild.textContent;
    $("#confirmDialog").dialog("open");
}

function getExpressDetail(spanNode){
    identityCode= spanNode.parentNode.parentNode.parentNode.firstElementChild.textContent;
    $("#express_detail_dialog").dialog("open");
    var url = "/anyone/express/"+identityCode;
    sendMessage(url, "get", expressCallback, function(error){
        var errorJson = JSON.parse(error.responseText);
        $("#error_express").text(errorJson.msg);
    }, null);

}

function expressCallback(data){
    var express = JSON.parse(data);
    express.result.list.forEach(function(val, index){
       var trcomp="<tr><td>"+val.time+"</td><td class=''></td><td>"+val.status+"</td></tr>";
       $("#show_express_table tbody").append(trcomp);
    });
}

function changeTrackingNumber(spanNode){
    identityCode= spanNode.parentNode.parentNode.parentNode.firstElementChild.textContent;
    $("#trackingNumberdDialog").dialog("open");

}

function getAllCompany(){
       sendMessage("/client/json/courierCompany?common=false", "get", function(data){
            $("#company_select").empty();
            var companies = JSON.parse(data);
            companies.forEach(function(val, index){
                var option = $("<option>").val(val.type).text(val.name);
                $("#company_select").append(option);
            });

           }, function (error) {
           console.log(error);
       }, null);
}

