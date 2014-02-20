<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="root">${pageContext.request.contextPath}</s:set>

<!DOCTYPE html>
<s:i18n name="paulenka.aleh.wordbook.ui.jsp.error">
	<html lang="${locale.language}">
		<head>
			<s:include value="/jsp/tile/head.jsp" />
			<link rel="stylesheet" href="${root}/css/error.css" />
			<title><s:text name="error.500.title" /></title>
		</head>
		<body class="server">
			<div class="box">
				<h1><s:text name="error.500.title" /></h1>
				<p><s:text name="error.500.message" /></p>
				<p><s:a namespace="/" action=""><s:text name="error.main" /></s:a></p>
			</div>
		</body>
	</html>
</s:i18n>