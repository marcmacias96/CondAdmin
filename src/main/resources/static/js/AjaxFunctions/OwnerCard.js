
function setContainer(response){
    $("#HouseList").empty();
    $("#HouseList").html(response);
}

function listHouseOwner () {
    var id = $("#idowner").val();
    $.ajax({
        url : "/house/listByOwner/" + id,
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
    listHouseOwner();


});