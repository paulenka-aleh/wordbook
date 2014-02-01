<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<s:i18n name="paulenka.aleh.wordbook.action.user.LoginUserAction">
	<html lang="${WW_TRANS_I18N_LOCALE.language}">
		<head>
			<s:include value="/jsp/tile/head.jsp"/>
			<link href="${root}/css/sign-forms.css" rel="stylesheet">
	
			<title><s:text name="sign-in-page.title"/></title>
		</head>
		<body>
			<div id="wrap">
				<s:include value="/jsp/tile/navbar.jsp">
					<s:param name="localeRedirectUri">/user/list</s:param>
					<s:param name="loginRedirectUri">/user/list</s:param>
					<s:param name="logoutRedirectUri">/user/login</s:param>
				</s:include>
				<div class="container">
					<ul>
						<c:forEach items="${users}" var="item">
							<li>${item.username}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<s:include value="/jsp/tile/footer.jsp"/>
		</body>
	</html>
</s:i18n>