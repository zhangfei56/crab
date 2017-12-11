// $("#companyImageFile").on("change", function(){
//     var file = $("#companyImageFile").get(0).files[0];
//     if(!/image\/\w+/.test(file.type)){
//         alert("上传一个图片，见证它的威力吧！");
//         return false;
//     }
//     var reader = new FileReader();
//     //将文件以Data URL形式读入页面
//     reader.readAsDataURL(file);
//     reader.onload=function(e){
//         $("#prepareImage").attr('src', this.result);
//         updateImage();
//         //$("#preDiv").HTML = '<img src="' + this.result +'" class="drag-img" />';
//     }
// });


$(function () {
    var default_img=document.getElementById("default_template");
    default_img.onload = function () {
        redraw();
        // $("#show_template").attr('src', img);

    };

    $("#company_name_text, #category_name_input").on("change", function () {
        redraw();
        console.log("change");
    });

});
function redraw(){
    var m_background_image=document.getElementById("default_template");
    var m_company_name = $("#company_name_text").val();
    var m_category_name = $("#category_name_input").val();
    var img = drawMessage(m_background_image, m_company_name, m_category_name, new Date());
    $("#show_template").attr('src', img);
}

function drawMessage(background_image,company_name, category_name, deadline, contact, phone_number, explanation, voucher_number){
    var year = "xxxx";
    var month = "xx";
    var day = "xx";

    if(deadline != undefined){
        year = deadline.getFullYear();
        month = deadline.getMonth()+1;
        day =  deadline.getDate();
    }
    company_name == undefined ? company_name="大闸蟹公司":company_name;
    category_name == undefined ? category_name="公蟹4只":category_name;
    (contact == undefined)? contact="周XX":contact;
    (phone_number == undefined) ? phone_number="138XXX":phone_number;
    explanation == undefined?explanation="XX":explanation;
    voucher_number == undefined?voucher_number="XXXXX":voucher_number;

    var canvas_default = document.createElement("canvas");
    var ctx = canvas_default.getContext("2d");

    var image_height = background_image.height;
    var image_width = background_image.width;
    canvas_default.height = image_height+20;
    canvas_default.width= image_width+20;

    ctx.drawImage(background_image,10,10, image_width, image_height);
    ctx.font="39px 微软雅黑 ";
    ctx.fillStyle="#fff";
    ctx.fillText(company_name, 0.31*image_width, 0.19*image_height);
    ctx.fillText(category_name, 0.31*image_width, 0.28*image_height);
    ctx.fillText(contact, 0.31*image_width, 0.58*image_height);
    return canvas_default.toDataURL("image/png");
};


