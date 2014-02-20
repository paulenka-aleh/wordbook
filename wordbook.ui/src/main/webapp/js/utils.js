String.prototype.format = function() {
	var args = arguments;
	return this.replace(/\{(\d+)\}/g, function(m, n) {
		return args[n];
	});
};

(function($) {
	$.fn.fill = function() {

		var parent = this.parent();
		var prev = this.prevAll();
		var next = this.nextAll();

		var height = function(element) {
			return $(element).outerHeight(true);
		};
		var sum = function(previous, current) {
			return previous + current;
		};

		var before = prev.length == 0 ? 0 : $.map(prev, height).reduce(sum);
		var after = next.length == 0 ? 0 : $.map(next, height).reduce(sum);

		parent.css({ 'padding-top' : before, 'padding-bottom' : after });
		this.css({ 'height' : '100%' });
		$(parent.children(':first-child')).css({ 'margin-top' : -before });

		return this;
	};

	$ajax = $.ajax;
	$.ajax = function() {
		var result = $ajax.apply(this, arguments);

		result.done(function(respone) {
			if (respone == 'login') {
				window.location.replace($.login);
				window.location.href = $.login;
			}
		});

		return result;
	};
})(jQuery);

$(function() {
	$('html').on('scroll', function() {
		console.log('xxx');
	});

	$('body').on('scroll', function() {
		console.log('xxx');
	});

	$('#wrap').on('scroll', function() {
		console.log('xxx');
	});

	$('#wrap').on('scroll', function() {
		console.log('xxx');
	});
});