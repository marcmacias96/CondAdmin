function addDetail(){
    var detail = {
        type : $("#txbtype").val(),
        detail: $("#txbdetail").val(),
        value : $("#txbvalor").val()
    }
    $.ajax({
        url : "/incomes/addDetailIncome",
        method : 'POST',
        headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
        contentType : "application/json",
        dataType : "json",
        data : JSON.stringify(detail),
        success : function(response){
            $('.det').remove();
            var total = 0;
            $.each(response, function(i, item){

                $("#tblDetalle tr:last").after("<tr class='det'>  <td  class='align-middle text-center'>" + item.type + " </td> <td  class='align-middle text-center'>"
                    + item.detail+ " </td> <td  class='align-middle text-center'>" + item.value+ " </td> </tr>  <hr/>");
                total+= item.value;
            });
            $('#total').text(total)
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