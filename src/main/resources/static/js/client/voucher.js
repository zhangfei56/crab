$(function(){
    $(".qrcode_td").each(function(index, val){
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
    var voucherChecks =  $("input[name='voucher_check']:checkbox:checked");
    var default_template = document.getElementById("default_template");
    var zip = new JSZip();
    var img = zip.folder("images");
    voucherChecks.each(function (index, val) {
        var qrimage = val.nextElementSibling.children[1];
        var vouURI = drawMessage(default_template,"shanghai", "公蟹", new Date(), "zhang", "123", "fsdf", "fsd", qrimage);
        var imgData = vouURI.split("data:image/png;base64,")[1];
        img.file( val.nextElementSibling.id+".png", imgData, {base64: true});
    })

    zip.generateAsync({type:"blob"})
    .then(function(content) {
        // see FileSaver.js
        saveAs(content, "example.zip");
    });

}


