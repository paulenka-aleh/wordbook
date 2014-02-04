<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="root" value="#request.get('javax.servlet.forward.context_path')" />

<!DOCTYPE html>
<s:i18n name="paulenka.aleh.wordbook.action.user.ListUserAction">
	<html lang="${WW_TRANS_I18N_LOCALE.language}">
		<head>
			<s:include value="/jsp/tile/head.jsp" />
			<title><s:text name="user-list-page.title" /></title>
		</head>
		<body>
			<div id="wrap">
				<s:include value="/jsp/tile/header.jsp" />
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
			<s:include value="/jsp/tile/footer.jsp" />
		</body>
	</html>
</s:i18n>