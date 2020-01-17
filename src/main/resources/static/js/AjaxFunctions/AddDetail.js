function addDetail(){
    var detail = {
        detail : $("#txbdetail").val(),
        value : $("#txbvalor").val()
    }
    $.ajax({
        url : "/expenses/addDetail",
        method : 'POST',
        headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
        contentType : "application/json",
        dataType : "json",
        data : JSON.stringify(detail),
        success : function(response){
            $.each(response, function(i, item){
                $("#tblDetalle").append("<tr>");
                $("#tblDetalle").append("<td  class='align-middle text-center'>"+ item.detail +"</td>");
                $("#tblDetalle").append("<td  class='align-middle text-center'>"+ item.value +"</td>");
                $("#tblDetalle").append("</tr>");
            });
        },
        error : function(err){
            console.log(err);
        }
    });
}

$(document).ready(function(){
    $("#btnAdd").click(function(){
        addDetail();
    });
});