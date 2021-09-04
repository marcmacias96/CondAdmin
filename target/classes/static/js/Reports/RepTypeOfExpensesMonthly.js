function load(){
    $.ajax({
        url: "/annualCounts/repTypeOfExpensesMonthly/"+ $("#idcondominium").val(),
        method : 'GET',
        dataType : 'json',
        contentType : 'application/json',
        success : function (response) {

            Array.prototype.unique=function(a){
                return function(){return this.filter(a)}}(function(a,b,c){return c.indexOf(a,b+1)<0
            });

            var MONTHS = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio',
                'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

            var toData1 = [];
            var toData2 = [];
            var toLabels = [];
            var toColors1 = [];
            var toColors2 = [];

            $.each(response, function(i, item){
                switch (item.tipo) {
                    case "Mantenimiento":
                        toData1.push(item.value);

                        break;

                    case "Servicios Basicos" :
                        toData2.push(item.value);
                        break;
                }

                var color1 = getRandomColor();
                var color2 = getRandomColor();
                if(toLabels[i-1] != MONTHS[item.month]) {
                    toLabels.push(MONTHS[item.month]);
                }
                toColors1.push(color1);
                toColors2.push(color2);

            });
            toLabels.unique();
            console.log(toLabels);
            var config = {
                type: 'bar',
                data : {
                    datasets:[{
                        label : "Mantenimiento",
                        data: toData1,
                        backgroundColor: toColors1
                    },
                        {
                            label : "Servicios Basicos",
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
                        text: 'Tipos de Gastos Mensuales'
                    },
                    animation:{
                        animateScale: true,
                        animateRotate: true
                    }
                }
            };


            var ctx = document.getElementById('chart-expmonthly').getContext('2d');
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