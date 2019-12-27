$(document).ready( function () {
    var table = $('#ownersTable').DataTable({
        "sAjaxSource": "/ownersRest/list",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "idowner"},
            { "mData": "name" },
            { "mData": "lastName" },
            { "mData": "ci" },
            { "mData": "email" },

        ]
    })
});