function sendMessage(url, method, successCallback, failCallback, body){
    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");

    $.ajax({
        url: url,
        type: method,
        data: body,
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function (data) {
            successCallback(data);
        },
        error: function (err) {
            failCallback(err);
        }
    });
}


function sendFormdata(url, method, successCallback, failCallback, body){
    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");

    $.ajax({
        url: url,
        type: method,
        data: body,
        processData: false,
        contentType: false,
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function (data) {
            successCallback(data);
        },
        error: function (err) {
            failCallback(err);
        }
    });
}