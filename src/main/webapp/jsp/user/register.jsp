<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="root">${pageContext.request.contextPath}</s:set>

<!DOCTYPE html>
<s:i18n name="paulenka.aleh.wordbook.action.user.RegisterUserAction">
	<html lang="${locale.language}">
		<head>
			<s:include value="/jsp/tile/head.jsp" />
			<link rel="stylesheet" href="${root}/css/form.css" />
			<title><s:text name="sign-up-page.title" /></title>
		</head>
		<body>
			<div id="wrap">
				<s:include value="/jsp/tile/header.jsp" />
				<div class="container">
					<s:form namespace="/user" action="register" method="post" theme="bootstrap" cssClass="form">
						<h2 class="form-heading"><s:text name="sign-up-form.form-title" /></h2>

						<div class="form-group">
							<label for="registration.username"><s:text name="sign-up-form.username"/></label>
							<s:textfield id="registration.username" name="registration.username" placeholder="%{getText('sign-up-form.username')}" required="" autofocus="" cssClass="form-control" />
							<s:fielderror fieldName="registration.username" />
						</div>
						<div class="form-group">
							<label for="registration.password"><s:text name="sign-up-form.password"/></label>
							<s:password id="registration.password" name="registration.password" placeholder="%{getText('sign-up-form.password')}" required="" cssClass="form-control" />
							<s:fielderror fieldName="registration.password" />
						</div>
						<div class="form-group">
							<label for="registration.confirmedPassword"><s:text name="sign-up-form.confirm-password"/></label>
							<s:password id="registration.confirmedPassword" name="registration.confirmedPassword" placeholder="%{getText('sign-up-form.confirm-password')}" required="" cssClass="form-control" />
							<s:fielderror fieldName="registration.confirmedPassword" />
						</div>

						<button type="submit" class="btn btn-lg btn-primary btn-block">
							<s:text name="sign-up-form.sign-up-button" />
						</button>
					</s:form>
				</div>
			</div>
			<s:include value="/jsp/tile/footer.jsp" />
		</body>
	</html>
</s:i18n>