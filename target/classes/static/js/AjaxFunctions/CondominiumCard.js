
function setContainer(response){
    $("#HousesList").empty();
    $("#HousesList").html(response);
}

function listHousesCondominium () {
    var id = $("#idCondom").val();
    $.ajax({
        url : "/house/listByCondom/" + id,
        method : 'GET',
        success : function(response){

            setContainer(response);
        },
        error : function(err){
            console.log(err);
        }
    });
}

$(document).ready(function(){
    listHousesCondominium();


});