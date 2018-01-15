$(function(){

    if($(".messageSelected").length > 0){
        var messageSelected = $(".messageSelected").get(0);
        var url = "/client/message/"+messageSelected.firstElementChild.value;
        sendMessage(url,"get", function(data){
            $("#message_content_div").show();
            var message = JSON.parse(data);
            $("#message_title").val(message.title);
            $("#message_content").val(message.text);
        }, function(error){
            console.error(error);
        }, null);

    }
});

function jumpIndex(node){
    var value = node.getElementsByClassName("index")[0].textContent-1;
    var url = window.location.href;
    var urls = url.split("currentMessageIndex");

    var urlP = url.split("?");

    if(urls.length>1){
        var params = urls[1].split("&");
        params.splice(0,1);
        url = urls[0]+"currentMessageIndex="+value+"&"+params.join("&");
    }else if(urlP>1){
        url = urls[0]+"currentMessageIndex="+value;
    }else{
        url = urls[0]+"?currentMessageIndex="+value;
    }

    window.location.href = url;
}