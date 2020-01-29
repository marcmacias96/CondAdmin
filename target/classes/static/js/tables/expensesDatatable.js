$(document).ready( function () {
    var id = $("#idMonthAccount").val();
    var table = $('#expensesTable').DataTable({
        "sAjaxSource": "/monthlyAccounts/listJsonExpenses/" + id,
        "sAjaxDataProp": "",
        "columnDefs": [ {
            "targets": -1,
            "data": null,
            "defaultContent": "<div class='text-center'> <button id='view' class='genric-btn btn-sm info-border circle'>Ver</button></div>",
        } ],
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "idexpenses"},
            { "mData": "value" },
            { "mData": "date" },
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

    $('#expensesTable tbody').on( 'click', 'button', function () {
        var data = table.row( $(this).parents('tr')).data();
        var url = "/expenses/retrieve/" + data.idexpenses;
        window.location.href = url;
    } );
});

