function load(){

    $.ajax({
        url: "/annualCounts/repIncVsExp/"+$("#idcondominium").val(),
        method : 'GET',
        dataType : 'json',
        contentType : 'application/json',
        success : function (response) {
            console.log(response);

            var MONTHS = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio',
                'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

            var toData1 = [];
            var toData2 = [];
            var toLabels = [];
            var toColors1 = [];
            var toColors2 = [];

            $.each(response, function(i, item){
                console.log(item);
                toData1.push(item.incomes);
                toData2.push(item.expenses);
                toLabels.push(MONTHS[item.month]);
                var color1 = getRandomColor();
                var color2 = getRandomColor();
                toColors1.push(color1);
                toColors2.push(color2);
            });

            var config = {
                type: 'bar',
                data : {
                    datasets:[{
                        label : "Ingresos",
                        data: toData1,
                        backgroundColor: toColors1
                    },
                        {
                            label : "Gastos",
                            data: toData2,
                            backgroundColor: toColors2
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
                        text: 'Gastos vs Ingresos'
                    },
                    animation:{
                        animateScale: true,
                        animateRotate: true
                    }
                }
            };


            var ctx = document.getElementById('chart-ivse').getContext('2d');
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