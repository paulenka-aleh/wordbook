(function($) {
	$.fn.wordList = function(options) {
		var page = 50;

		var $this = this;
		var $filter = $(options.filter);

		var $pager = $(options.pager);
		var $pagerPrevious = $pager.find(".previous");
		var $pagerNext = $pager.find(".next");
		var $pagerLabel = $pager.find(".pager-label > span");
		var pagerMessage = $pagerLabel.text();
		var total = 0;

		var $explanationArea = $(options.explanation);
		var $explanation = $explanationArea.find('.word-explanation');
		var $explanationHeader = $explanationArea.find('.explanation-header');
		var $wordheader = $explanationHeader.find('.word');
		var activeId = 0;

		var $editWordLink = $explanationArea.find('.edit-word-link');
		var editWordUrl = $editWordLink.attr('href');

		var updating = false;

		var createRequest = function() {
			return { 'listRequest.filter' : $filter.val(), 'listRequest.size' : page, 'listRequest.page' : $pager.attr('data-page-index') };
		};

		var wrapWord = function(word) {
			return '<a href="#" data-word-id="{0}" class="list-group-item">{1}</a>'.format(word.id, word.word);
		};

		var activateWord = function(active) {
			$this.find('a.list-group-item').each(function(index, a) {
				$a = $(a);
				$a.toggleClass('active', $a.is(active));
			});
		};

		var populateExplanation = function(word) {
			$editWordLink.attr('href', editWordUrl + '?word.id=' + word.id);
			$wordheader.empty().append(word.word);
			$explanation.empty().append(word.explanation);

			$explanationHeader.css('display', 'block');
		};

		var updateExplanation = function() {
			$explanation.empty();
			$wordheader.empty();
			$explanationHeader.css('display', 'none');
			activeId = $this.find('.active').attr('data-word-id');

			if (activeId) {
				$.post(options.explanationUrl, { 'word.id' : activeId }).done(function(response) {
					populateExplanation(response);
				}).fail(function() {

				});
			}
		};

		var populateWordList = function(words) {
			$this.empty();

			$this.append($.map(words, wrapWord).join(''));

			$this.find('.list-group-item').each(function(index, a) {
				var $a = $(a);
				$a.on('click', function() {
					activateWord($a);
					updateExplanation();
				});
			});

			activateWord($this.find('[data-word-id=' + activeId + ']'));
			updateExplanation();
		};

		var pagerIndex = function() {
			return Number($pager.attr('data-page-index'));
		};

		var populateWordPager = function(recordsTotal) {
			total = Math.ceil(recordsTotal / page);
			$pagerLabel.text(pagerMessage.format(pagerIndex() + 1, total));
			$pagerPrevious.toggleClass('disabled', !(pagerIndex() - 1 >= 0));
			$pagerNext.toggleClass('disabled', !(pagerIndex() + 1 < total));
		};

		var populate = function(response) {
			populateWordList(response.words);
			populateWordPager(response.total);
		};

		var update = function() {
			updating = true;
			$this.empty();

			$.post(options.listUrl, createRequest()).done(function(response) {
				populate(response);
			}).fail(function() {

			}).always(function() {
				updating = false;
			});
		};

		var previous = function() {
			var index = pagerIndex() - 1;
			if (!updating && index >= 0) {
				$pager.attr('data-page-index', index);
				update();
			}
		};

		var next = function() {
			var index = pagerIndex() + 1;
			if (!updating && index < total) {
				$pager.attr('data-page-index', index);
				update();
			}
		};

		var updateFromFilter = function() {
			$pager.attr('data-page-index', 0);
			update();
		};

		$filter.on('change input', updateFromFilter);
		$pagerPrevious.on('click', previous);
		$pagerNext.on('click', next);

		update();

		return this;
	};
})(jQuery);

$(function() {
	$('#word-list-wrap').fill();

	var message = $('#delete-confirm-message').text();

	$('#delete-confirm').on('show.bs.modal', function() {
		var id = $('#word-list .active').attr('data-word-id');
		var word = $('#explanation-area .word').text();

		$('#delete-confirm-message').text(message.format(word));
		$('#delete-confirm-id').val(id);
	});
});