$(document).ready( function () {
    var id = $("#idUser").val();
    console.log(id);
    var table = $('#ownersTable').DataTable({
        "sAjaxSource": "/ownersRest/listByCondom/" + id,
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "idowner"},
            { "mData": "name" },
            { "mData": "lastName" },
            { "mData": "ci" },
            { "mData": "email" },

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
});