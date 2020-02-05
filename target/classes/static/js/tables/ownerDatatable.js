$(document).ready( function () {
    var id = $("#idcondominium").val();
    console.log(id);
    var table = $('#ownersTable').DataTable({
        "sAjaxSource": "/ownersRest/listByCondom/" + id,
        "sAjaxDataProp": "",
        "columnDefs": [ {
            "targets": -1,
            "data": null,
            "defaultContent": "<div class='text-center'> <button id='view' class='genric-btn btn-sm info-border circle'>Ver</button></div>",
        } ],
         "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "idowner"},
            { "mData": "name" },
            { "mData": "lastName" },
            { "mData": "email" },
            { "mData": "acctions" }
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

    $('#ownersTable tbody').on( 'click', 'button', function () {
        var data = table.row( $(this).parents('tr')).data();
        var url = "/owner/retrive/" + data.idowner;
        window.location.href = url;
    } );
});

