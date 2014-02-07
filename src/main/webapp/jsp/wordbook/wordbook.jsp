<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="root">${pageContext.request.contextPath}</s:set>

<!DOCTYPE html>
<html lang="${locale.language}">
	<head>
		<s:include value="/jsp/tile/head.jsp" />
		<link rel="stylesheet" href="${root}/css/wordbook.css" />
		<title>Главная страница</title>
	</head>
	<body>
		<div id="wrap" class="full-height">
			<s:include value="/jsp/tile/header.jsp" />
			<div class="full-height container padding">
				<div class="col-xs-4 full-height">
					<div class="input-group">
						<input type="search" class="form-control" />
						<span class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span>
					</div>
					<div class="list-group">
						<a href="#" class="list-group-item">123</a>
						<a href="#" class="list-group-item">123</a>
						<a href="#" class="list-group-item">123</a>
						<a href="#" class="list-group-item">123</a>
						<a href="#" class="list-group-item">123</a>
					</div>
				</div>
				<div class="col-xs-8 full-height">
					<div class="full-height" style="background: #f8f8f8; border-radius: 4px; border: 1px solid #ccc;">

					</div>
				</div>
			</div>
		</div>
		<s:include value="/jsp/tile/footer.jsp" />
	</body>
</html>