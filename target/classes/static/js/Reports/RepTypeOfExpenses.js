function load(){
    $.ajax({
        url: "/monthlyAccounts/repTypeOfExpense/"+1,
        method : 'GET',
        dataType : 'json',
        contentType : 'application/json',
        success : function (response) {
            console.log(response);

            var toData = [];
            var toLabels = [];
            var toColors = [];

            $.each(response, function(i, item){
                console.log(item);
                toData.push(item.value);
                toLabels.push(item.type);
                var color = getRandomColor();
                toColors.push(color);
            });

            var config = {
                type: 'doughnut',
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
                        text: 'Tipo de Gastos'
                    },
                    animation:{
                        animateScale: true,
                        animateRotate: true
                    }
                }
            };


            var ctx = document.getElementById('chart-exp').getContext('2d');
            window.myRadar = new Chart(ctx, config);

        },
        error: function(err){
            console.log(err);
        }
    });
}

$(document).ready(function () {
    load();
});