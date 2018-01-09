function changeStatus(node){
    var companyId = node.nextElementSibling.value;
    var url = "/admin/express/company/status?companyId="+companyId;
    sendMessage(url, "get", function(data){
            if(data =="success"){
                alert("改变成功");
                window.location.reload();
            }else{
                alert("删除失败");
            }
        }, function(error){
            alert(error);
        }, null);
}