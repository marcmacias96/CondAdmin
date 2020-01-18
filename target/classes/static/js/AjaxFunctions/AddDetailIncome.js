function addDetail(){
    var detail = {
        detail : $("#txbdetail").val(),
        type : $("txbtype").val(),
        value : $("#txbvalor").val()
    }
    $.ajax({
        url : "/incomes/addDetail",
        method : 'POST',
        headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
        contentType : "application/json",
        dataType : "json",
        data : JSON.stringify(detail),
        success : function(response){
            $('#tblDetalle tr:not(:first-child)')
                .remove();
            $.each(response, function(i, item){
                $("#tblDetalle tr:last").after("<tr> <td  class='align-middle text-center'>" + item.detail + 
                		" </td> <td  class='align-middle text-center'>" + item.type+ 
                		" </td> <td  class='align-middle text-center'>" + item.value+ " </td> </tr>");
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