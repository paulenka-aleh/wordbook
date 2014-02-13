$(function() {
	$('#word-list-wrap').fill();

	$.post('/wordbook.ui/wordbook/list', {
		'listRequest.filter' : 'а',
		'listRequest.size' : 50,
		'listRequest.page' : 2,
	}).done(function(response) {
		alert(response);
	}).fail(function() {
		alert("error");
	});
});