<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="root" value="#request.get('javax.servlet.forward.context_path')" />

<!DOCTYPE html>
<s:i18n name="paulenka.aleh.wordbook.action.user.EditUserAction">
	<html lang="${WW_TRANS_I18N_LOCALE.language}">
		<head>
			<s:include value="/jsp/tile/head.jsp" />
	
			<title><s:text name="user-edit-page.title" /></title>
		</head>
		<body>
			<div id="wrap">
				<s:include value="/jsp/tile/header.jsp" />
				<div class="container">
					${param.id}
				</div>
			</div>
			<s:include value="/jsp/tile/footer.jsp" />
		</body>
	</html>
</s:i18n>