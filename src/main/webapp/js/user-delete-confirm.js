$(function() {
	var message = $('#delete-confirm-message').text();
	var url = $('#delete-confirm-a').attr('href');

	$('#delete-confirm').on('show.bs.modal', function (e) {
		var id = $(e.relatedTarget).attr('data-user-id');
		var name = $(e.relatedTarget).attr('data-user-name');

		$('#delete-confirm-message').text(message.format(id, name));
		$('#delete-confirm-a').attr('href', url + '?user.id=' + id);
	});
});