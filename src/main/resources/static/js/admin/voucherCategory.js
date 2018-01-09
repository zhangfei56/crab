function addCategory(){
     var detail = $("#category_id_input").val().trim();
     if(detail == ""){
        return;
     }
     var category = {};
     category.detail = detail;
     sendMessage("/admin/voucher/category", "post", function(data){
        if(data == "success"){
            alert("添加成功");
            window.location.reload();
        }else{
            alert("已存在");
        }
     }, function(error){
        alert(error);
     }, category);
 }

 function deleteCategory(node){
    var categoryId = node.nextElementSibling.value;
    var url = "/admin/voucher/category?categoryId="+categoryId;
    sendMessage(url, "delete", function(data){
        if(data =="success"){
            alert("删除成功");
            window.location.reload();
        }else{
            alert("删除失败");
        }
    }, function(error){
        alert(error);
    }, null)
 }