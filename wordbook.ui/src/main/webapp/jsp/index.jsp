<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="root">${pageContext.request.contextPath}</s:set>

<!DOCTYPE html>
<html lang="${locale.language}">
	<head>
		<s:include value="/jsp/tile/head.jsp" />
		<title>Главная страница</title>
	</head>
	<body>
		<div id="wrap">
			<s:include value="/jsp/tile/header.jsp" />
			<div class="container">
			</div>
		</div>
		<s:include value="/jsp/tile/footer.jsp" />
	</body>
</html>