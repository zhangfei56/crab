$(function(){
    $(".qrcode_td > div").each(function(index, val){
        var id = val.id;
        var qrcode = new QRCode(val, {
            width : 100,
            height : 100
        });
        qrcode.makeCode(id);
    });
});
function toggleCheck(){
    var checked = $("#control_check")[0].checked;
    var voucherChecks = document.getElementsByName("voucher_check");
    voucherChecks.forEach(function(val, index, array){
        val.checked = checked;
    });
}

function generatePic(){
    var voucherChecks = document.getElementsByName("voucher_check");
    var qrcode = new QRCode(document.getElementById("qrcode"), {
        width : 100,
        height : 100
    });
    qrcode.makeCode("this is text");


    var imgData = $("#qrcode img").attr("src");

    var zip = new JSZip();
    var img = zip.folder("images");
    img.file("code.png", imgData, {base64: true});
    zip.generateAsync({type:"blob"})
    .then(function(content) {
        // see FileSaver.js
        saveAs(content, "example.zip");
    });
}


