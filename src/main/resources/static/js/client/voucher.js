$(function(){
    $(".qrcode_td").each(function(index, val){
        var id = val.id;
        var qrcode = new QRCode(val, {
            width : 100,
            height : 100
        });
        qrcode.makeCode(id);
    });

    sendMessage("/client/voucher/json/template", "get", function(data){
            template = data;
        }, function (error) {
        console.log(error);
    }, null);

    var status = $("#voucher_status_id").val();
    $("#voucher_status_select").val(status);

   $("#voucher_status_select").on("change", function(){
       window.location.href="/client/vouchers?status="+$("#voucher_status_select").val();
   });
});
var template={};
function toggleCheck(){
    var checked = $("#control_check")[0].checked;
    var voucherChecks = document.getElementsByName("voucher_check");
    voucherChecks.forEach(function(val, index, array){
        val.checked = checked;
    });
}

function generatePic(){
    var voucherChecks =  $("input[name='voucher_check']:checkbox:checked");
    var default_template = document.getElementById("default_template");
    var zip = new JSZip();
    var img = zip.folder("images");
    voucherChecks.each(function (index, val) {
        var qrimage = val.nextElementSibling.children[1];
        var deadline = new Date(val.nextElementSibling.nextElementSibling.value);
        var categoryName = val.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.value;
        var identityCode = val.nextElementSibling.nextElementSibling.nextElementSibling.value;
        var vouURI = drawMessage(default_template,template.companyName, categoryName, deadline, template.contact, template.phoneNumber, template.owner, identityCode, qrimage);
        var imgData = vouURI.split("data:image/png;base64,")[1];
        img.file( identityCode+".png", imgData, {base64: true});
    })

    zip.generateAsync({type:"blob"})
    .then(function(content) {
        // see FileSaver.js
        saveAs(content, "example.zip");
    });

}


