<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="root" value="#request.get('javax.servlet.forward.context_path')"/>

<!DOCTYPE html>
<s:i18n name="paulenka.aleh.wordbook.action.user.ListUserAction">
	<html lang="${WW_TRANS_I18N_LOCALE.language}">
		<head>
			<s:include value="/jsp/tile/head.jsp"/>
			<title><s:text name="sign-in-page.title"/></title>
		</head>
		<body>
			<div id="wrap">
				<s:include value="/jsp/tile/navbar.jsp">
					<s:param name="localeRedirectUri" value="/user/list" />
					<s:param name="loginRedirectUri" value="/user/list" />
					<s:param name="logoutRedirectUri" value="/user/login" />
				</s:include>
				<div class="container">
					<div class="list-group">
						<s:iterator value="users" var="user">
							<s:a namespace="/user" action="edit" cssClass="list-group-item">
								<s:param name="id" value="#user.id" />
								<s:property value="#user.username" />
							</s:a>
						</s:iterator>
					</div>
				</div>
			</div>
			<s:include value="/jsp/tile/footer.jsp"/>
		</body>
	</html>
</s:i18n>