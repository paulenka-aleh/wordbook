<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="root">${pageContext.request.contextPath}</s:set>

<!DOCTYPE html>
<html lang="${locale.language}">
	<head>
		<s:include value="/jsp/tile/head.jsp" />
		<title>Главная страница</title>
	</head>
	<link rel="stylesheet" href="${root}/css/index.css" />
	<body>
		<div id="wrap">
			<s:include value="/jsp/tile/header.jsp" />
			<div class="container">
				<s:include value="%{'/jsp/tile/index/index-contemt-' + locale.language + '.jsp'}" />
			</div>
		</div>
		<s:include value="/jsp/tile/footer.jsp" />
	</body>
</html>