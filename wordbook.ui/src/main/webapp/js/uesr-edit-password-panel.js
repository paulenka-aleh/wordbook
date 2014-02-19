$(function() {
	$('#change-password').on('change', function (e) {
		var change = $('#change-password').prop('checked');
		$('#password').prop('disabled', !change);
		$('#confirmed-password').prop('disabled', !change);
	}).trigger('change');
});