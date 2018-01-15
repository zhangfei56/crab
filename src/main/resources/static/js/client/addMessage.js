$(function(){

   sendMessage("/client/json/message/users", "get", function(data){
        var users = JSON.parse(data);
        users.forEach(function(val, index){
            var option = $("<option>").val(val.id).text(val.name);
            $("#add_message_select").append(option);
        });

       }, function (error) {
       console.log(error);
   }, null);


    $('#add_message_form').validate({
         rules : {
            add_message_title:{
                required: true,
                minlength:5,
                maxlength:20
            },
            add_message_content :{
                required: true,
                minlength:10,
                maxlength:100
            },
         },
         submitHandler: function(){
             var userId = $("#add_message_select").val();
             var title =  $("#add_message_title").val();
             var text = $("#add_message_content").val();

             sendMessage("/client/json/message","post",
                 function(successData){
                     if(successData == "success"){
                         window.location.href="/client/messages";
                     }
                 }, function(errorData){
                     console.log(errorData);
                 },
                 {userId,title, text});
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
