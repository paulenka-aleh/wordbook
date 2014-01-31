<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<s:i18n name="paulenka.aleh.wordbook.action.user.RegisterUserAction">
	<html lang="${WW_TRANS_I18N_LOCALE.language}">
		<head>
			<s:include value="/jsp/tile/head.jsp"/>
			<link href="${root}/css/sign-forms.css" rel="stylesheet">

			<title><s:text name="sign-up-page.title"/></title>
		</head>
		<body>
			<div id="wrap">
				<s:include value="/jsp/tile/navbar.jsp">
					<s:param name="redirectUri">/user/register</s:param>
					<s:param name="localeRedirectUri">/user/register</s:param>
					<s:param name="loginRedirectUri">/</s:param>
					<s:param name="logoutRedirectUri">/user/register</s:param>
				</s:include>
				<div class="container">
					<s:form namespace="/user" action="register" method="post" theme="bootstrap" cssClass="form-sign">
						<h2 class="form-sign-heading"><s:text name="sign-up-form.form-title"/></h2>
						<s:textfield name="registration.username" placeholder="getText('sign-up-form.username')" required="" autofocus="" cssClass="form-control" />
						<s:fielderror fieldName="registration.username"/>
						<s:password name="registration.password" placeholder="getText('sign-up-form.password')" required="" cssClass="form-control" />
						<s:fielderror fieldName="registration.password"/>
						<s:password name="registration.confirmedPassword" placeholder="getText('sign-up-form.confirm-password')" required="" cssClass="form-control" />
						<s:fielderror fieldName="registration.confirmedPassword"/>
						<button type="submit" class="btn btn-lg btn-primary btn-block">
							<s:text name="sign-up-form.sign-up-button" />
						</button>
					</s:form>
				</div>
			</div>
			<s:include value="/jsp/tile/footer.jsp"/>
		</body>
	</html>
</s:i18n>