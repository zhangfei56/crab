$(function(){
    sendMessage("/client/json/messages/count", "get", function(data){
        if(data != 0){
            $("#message_number_span").show();
            $("#message_number_span").text(data);
        }else{
            $("#message_number_span").hide();
        }

    }, function(error){
    }, null);

    sendMessage("/client/json/order/count", "get", function(data){
        if(data != 0){
            $("#order_number_span").show();
            $("#order_number_span").text(data);
        }else{
            $("#order_number_span").hide();
        }

    }, function(error){
    }, null);

});