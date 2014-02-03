<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="root" value="#request.get('javax.servlet.forward.context_path')"/>

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
					<s:if test="#parameters.redirectUri != null">
						<s:param name="localeRedirectUri">/user/login?redirectUri=${param.redirectUri}</s:param>
					</s:if>
					<s:else>
						<s:param name="localeRedirectUri">/user/login</s:param>
					</s:else>

					<s:param name="loginRedirectUri">${param.redirectUri}</s:param>
					<s:param name="logoutRedirectUri">/user/login</s:param>
				</s:include>
				<div class="container">
					<s:form namespace="/user" action="login" method="post" theme="bootstrap" cssClass="form-sign">
						<input type="hidden" name="redirectUri" value="${param.redirectUri}"/>
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