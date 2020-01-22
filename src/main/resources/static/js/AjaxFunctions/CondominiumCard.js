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
            var id = $("#idCondom").val();
            console.log(id)
        	var table = $('#houseListByCondom').DataTable({
        		"sAjaxSource": "/house/listByCondomJson/"+id,
                "sAjaxDataProp": "",
                "columnDefs": [ {
                    "targets": -1,
                    "data": null,
                    "defaultContent": "<div class='text-center'> <button id='view' class='genric-btn btn-sm info-border circle'>Ver</button></div>",
                } ],
                "order": [[ 0, "asc" ]],
                "aoColumns": [
            
                    { "mData": "number"},
                    { "mData": "block" },
                    { "mData": "actions" }
                    ],
                    "language": {
                        "lengthMenu": "Mostrar _MENU_ registros por página",
                        "zeroRecords": "No encontrado - lo siento",
                        "info": "Mostrando página _PAGE_ of _PAGES_",
                        "infoEmpty": "No hay registros disponibles",
                        "infoFiltered": "(filtrado de _MAX_ registros totales)",
                        "search":         "Buscar:",
                        "paginate": {
                            "first":      "Primero",
                            "last":       "Último",
                            "next":       "Siguiente",
                            "previous":   "Anterior"
                        }
                    }
                })

                $('#houseListByCondom tbody').on( 'click', 'button', function () {
                    var data = table.row( $(this).parents('tr')).data();
                    var url = "/house/retrive/" + data.idhouse;
                    window.location.href = url;
                } );
        },
        error : function(err){
            console.log(err);
        }
    });
}

$(document).ready(function(){
    listHousesCondominium();


});