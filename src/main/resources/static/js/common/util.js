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

function drawMessage(background_image,company_name, category_name, deadline, contact, phone_number, address, voucher_number, qr_image){
    deadline = deadline == undefined ? new Date(): deadline;
    deadline = deadline.Format("yyyy年MM月dd日");

    company_name == undefined ? company_name="大闸蟹公司":company_name;
    category_name == undefined ? category_name="公蟹4只":category_name;
    contact == undefined ? contact="周XX":"("+contact+")";
    phone_number == undefined ? phone_number="138XXX":phone_number;
    address == undefined ? address="XX":address;
    voucher_number == undefined ? voucher_number="1234567890":voucher_number;
    var canvas_default = document.createElement("canvas");
    var ctx = canvas_default.getContext("2d");

    var image_height = background_image.height;
    var image_width = background_image.width;
    canvas_default.height = image_height+20;
    canvas_default.width= image_width+20;

    ctx.drawImage(background_image,10,10, image_width, image_height);
    ctx.font="36px 微软雅黑 ";
    ctx.fillStyle="#fff";
    ctx.fillText(company_name, 0.3*image_width, 0.17*image_height);
    ctx.fillText(category_name, 0.3*image_width, 0.26*image_height);
    ctx.fillText(deadline, 0.3*image_width, 0.43*image_height);
    ctx.fillText(contact, 0.45*image_width, 0.71*image_height);
    ctx.fillText(phone_number, 0.325*image_width, 0.71*image_height);
    ctx.fillText(address, 0.325*image_width, 0.805*image_height);
    ctx.font="40px 微软雅黑 ";
    ctx.fillText(voucher_number, 0.09*image_width, 0.885*image_height);
    qr_image == undefined ? null: ctx.drawImage(qr_image,0.826*image_width, 0.58*image_height, qr_image.height, qr_image.width);
    return canvas_default.toDataURL("image/png");
}

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function logout(){
    sendFormdata("/logout", "post", null, null, null);
}