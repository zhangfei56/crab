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
    var m_contact = $("#contact_input").val();
    var m_phone_number = $("#phone_number_input").val();
    var m_address = $("#owner_input").val();
    var img = drawMessage(m_background_image, m_company_name, undefined, undefined, m_contact, m_phone_number, m_address, undefined, undefined, undefined);
    $("#show_template").attr('src', img);
}

function saveTemplate(){
    var template = {};
    template.id = $("#template_id").val();
    template.companyName = $("#company_name_text").val();
    template.contact = $("#contact_input").val();
    template.phoneNumber = $("#phone_number_input").val();
    template.owner = $("#owner_input").val();
    sendMessage("/client/voucher/json/template", "put", function(data){alert("save success")}, function(failedData){alert("save failed")}, template);

}
