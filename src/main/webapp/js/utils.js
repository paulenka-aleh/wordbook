String.prototype.format = function () {
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

		var height = function(element) { return $(element).outerHeight(true); };
		var sum = function(previous, current) { return previous + current; };

		var before = $.map(prev, height).reduce(sum);
		var after = $.map(next, height).reduce(sum);

		parent.css({ 'padding-top' : before, 'padding-bottom' : after });
		this.css({ 'height' : '100%' });
		$(parent.children(':first-child')).css({ 'margin-top' : -before });

		return this;
	};
})(jQuery);