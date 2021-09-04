
$(document).ready(function(){

	$("#success-alert").fadeTo(2000,1).slideUp(500, function(){
		$("#success-alert").slideUp(500);
	});


});

function getRandomColor() {
	var letters = '0123456789ABCDEF';
	var color = '#';
	for (var i = 0; i < 6; i++) {
		color += letters[Math.floor(Math.random() * 16)];
	}
	return color;
}