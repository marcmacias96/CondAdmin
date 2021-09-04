function load(){
    $.ajax({
        url: "/monthlyAccounts/repTypeOfIncome/"+ $("#idcondominium").val(),
        method : 'GET',
        dataType : 'json',
        contentType : 'application/json',
        success : function (response) {


            var toData = [];
            var toLabels = [];
            var toColors = [];

            $.each(response, function(i, item){
                console.log(item);
                toData.push(item.value);
                toLabels.push(item.house);
                var color = getRandomColor();
                toColors.push(color);
            });

            var config = {
                type: 'pie',
                data : {
                    datasets:[{
                        data: toData,
                        backgroundColor: toColors
                    }],
                    labels: toLabels
                },
                options:{
                    responsive: true,
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: 'Numero de pagos por casa'
                    },
                    animation:{
                        animateScale: true,
                        animateRotate: true
                    }
                }
            };


            var ctx = document.getElementById('chart-inc').getContext('2d');
            window.myBar = new Chart(ctx, config);

        },
        error: function(err){
            console.log(err);
        }
    });
}

$(document).ready(function () {
    load();
});