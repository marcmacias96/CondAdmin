$(document).ready( function () {
    var id = $("#idannualcounts").val();
    console.log(id);
    var table = $('#monthlyTable').DataTable({
        "sAjaxSource": "/monthlyAccounts/listJsonMonthly/"+id,
        "sAjaxDataProp": "",
        "columnDefs": [ {
            "targets": -1,
            "data": null,
            "defaultContent": "<div class='text-center'> <button id='view' class='genric-btn btn-sm info-border circle'>Ver</button></div>",
        } ],
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "idmonthlyaccounts"},
            { "mData": "month" },
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

    $('#monthlyTable tbody').on( 'click', 'button', function () {
        var data = table.row( $(this).parents('tr')).data();
        var url = "/monthlyAccounts/retrive/" + data.idmonthlyaccounts;
        window.location.href = url;
    } );
});