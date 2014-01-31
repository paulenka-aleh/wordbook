<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="${WW_TRANS_I18N_LOCALE.language}">
	<head>
		<s:include value="/jsp/tile/head.jsp"/>
		<link href="${root}/css/sign-forms.css" rel="stylesheet">

		<title>Главная страница</title>
	</head>
	<body>
		<div id="wrap">
			<s:include value="/jsp/tile/navbar.jsp">
				<s:param name="localeRedirectUri">/</s:param>
				<s:param name="loginRedirectUri">/</s:param>
				<s:param name="logoutRedirectUri">/</s:param>
			</s:include>
			<div class="container">

			</div>
		</div>
		<s:include value="/jsp/tile/footer.jsp"/>
	</body>
</html>