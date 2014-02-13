$(function() {
	$('#word-list-wrap').fill();

	$.post('/wordbook.ui/wordbook/list', {
		'listRequest.filter' : 'а',
		'listRequest.size' : 50,
		'listRequest.page' : 2,
	}).done(function(response) {
		$('#word-list').empty();
		$('#word-list').append($.map(response, function(word) {
			return '<a href="#" data-word-id="' + word.id + '" class="list-group-item">' + word.word + '</a>'
		}).join(''));
	}).fail(function() {
		alert("error");
	});
});