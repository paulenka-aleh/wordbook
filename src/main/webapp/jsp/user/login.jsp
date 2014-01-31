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
					<s:param name="localeRedirectUri">/user/login</s:param>
					<s:param name="loginRedirectUri">/</s:param>
					<s:param name="logoutRedirectUri">/user/login</s:param>
				</s:include>
				<div class="container">
					<s:form namespace="/user" action="login" method="post" theme="bootstrap" cssClass="form-sign">
						<s:hidden name="redirectUri" value="/"/>
						<h2 class="form-sign-heading"><s:text name="sign-in-form.form-title"/></h2>
						<s:textfield name="credentials.username" placeholder="getText('sign-in-form.username')" required="" autofocus="" cssClass="form-control"/>
						<s:password name="credentials.password" placeholder="getText('sign-in-form.password')" required="" cssClass="form-control"/>
						<s:actionerror/>
						<button type="submit" class="btn btn-lg btn-primary btn-block">
							<s:text name="sign-in-form.sign-in-button"/>
						</button>
					</s:form>
				</div>
			</div>
			<s:include value="/jsp/tile/footer.jsp"/>
		</body>
	</html>
</s:i18n>