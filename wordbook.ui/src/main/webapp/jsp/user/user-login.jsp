<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="root">${pageContext.request.contextPath}</s:set>

<!DOCTYPE html>
<html lang="${locale.language}">
	<head>
		<s:include value="/jsp/tile/head.jsp" />
		<link rel="stylesheet" href="${root}/css/form.css" />
		<title><s:text name="sign-in-page.title" /></title>
	</head>
	<body>
		<div id="wrap">
			<s:include value="/jsp/tile/header.jsp" />
			<div class="container">
				<s:form namespace="/user" action="login" method="post" theme="bootstrap" cssClass="form">
					<h2 class="form-heading"><s:text name="sign-in-form.form-title" /></h2>

					<div class="form-group">
						<label for="credentials.username"><s:text name="sign-in-form.username" /></label>
						<s:textfield id="credentials.username" name="credentials.username" placeholder="%{getText('sign-in-form.username')}" autofocus="autofocus" cssClass="form-control" />
					</div>
					<div class="form-group">
						<label for="credentials.password"><s:text name="sign-in-form.password" /></label>
						<s:password id="credentials.password" name="credentials.password" placeholder="%{getText('sign-in-form.password')}" cssClass="form-control" />
					</div>

					<s:actionerror />

					<div class="text-center">
						<button type="submit" class="btn btn-lg btn-success">
							<s:text name="sign-in-form.sign-in-button" />
						</button>
					</div>
				</s:form>
			</div>
		</div>
		<s:include value="/jsp/tile/footer.jsp" />
	</body>
</html>