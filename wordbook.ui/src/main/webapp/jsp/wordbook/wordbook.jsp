<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="root">${pageContext.request.contextPath}</s:set>

<!DOCTYPE html>
<html lang="${locale.language}">
	<head>
		<s:include value="/jsp/tile/head.jsp" />
		<link rel="stylesheet" href="${root}/css/wordbook.css" />
		<script type="text/javascript" src="${root}/js/wordbook.js"></script>
		<title><s:text name="wordbook-page.title" /></title>
	</head>
	<body>
		<div id="wrap" class="full-height">
			<s:include value="/jsp/tile/header.jsp" />
			<div class="full-height container padding">
				<div class="col-xs-4 full-height">
					<div class="full-height">
						<div id="word-search-wrap" class="input-group">
							<input id="word-search" type="search" class="form-control" placeholder="<s:text name="word-list.filter" />" autofocus="autofocus" />
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-search"></span>
							</span>
						</div>
						<div id="word-list-wrap">
							<div id="word-list" class="list-group full-height">
								<a href="#" class="list-group-item">здесь</a>
								<a href="#" class="list-group-item">будет</a>
								<a href="#" class="list-group-item">список</a>
								<a href="#" class="list-group-item">слов</a>
							</div>
						</div>
						<ul id="word-list-pager" class="pager" style="overflow-y: hidden;">
							<li class="previous"><a href="#"><span class="glyphicon glyphicon-arrow-left"></span></a></li>
							<li><span id="pager-label"><s:text name="word-list.pager" /></span></li>
							<li class="next"><a href="#"><span class="glyphicon glyphicon-arrow-right"></span></a></li>
						</ul>
					</div>
				</div>
				<div class="col-xs-8 full-height">
					<div id="word-explanation" class="full-height">Здесь будет толкование слова.</div>
				</div>
			</div>
		</div>
		<s:include value="/jsp/tile/footer.jsp" />
	</body>
</html>