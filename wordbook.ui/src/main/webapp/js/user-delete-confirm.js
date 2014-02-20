$(function() {
	var message = $('#delete-confirm-message').text();

	$('#delete-confirm').on('show.bs.modal', function(e) {
		var id = $(e.relatedTarget).attr('data-user-id');
		var name = $(e.relatedTarget).attr('data-user-name');

		$('#delete-confirm-message').text(message.format(id, name));
		$('#delete-confirm-id').val(id);
	});
});