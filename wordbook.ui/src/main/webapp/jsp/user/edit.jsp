<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="root">${pageContext.request.contextPath}</s:set>

<!DOCTYPE html>
<html lang="${locale.language}">
	<head>
		<s:include value="/jsp/tile/head.jsp" />
		<link rel="stylesheet" href="${root}/css/form.css" />
		<script type="text/javascript" src="${root}/js/uesr-edit-password-panel.js"></script>
		<title>
			<s:text name="user-edit-page.title">
				<s:param value="%{user.username}" />
			</s:text>
		</title>
	</head>
	<body>
		<div id="wrap">
			<s:include value="/jsp/tile/header.jsp" />
			<div class="container">
				<s:form namespace="/user" action="%{'edit?user.id=' + user.id}" method="post" theme="bootstrap" cssClass="form">
					<s:if test="%{success}">
						<div class="alert alert-success alert-dismissable">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<p><s:text name="edit-form.success" /></p>
						</div>
					</s:if>

					<h2 class="form-heading">
						<s:text name="edit-form.form-title">
							<s:param value="%{user.username}"></s:param>
						</s:text>
					</h2>

					<div class="panel panel-danger">
						<div class="panel-heading"><s:text name="edit-form.edit-roles-title" /></div>
						<div class="panel-body">
							<s:iterator value="%{roles}" var="entry">
								<s:set value="#entry.key" var="role" />
								<div class="checkbox">
									<s:checkbox id="%{'roles.' + #role}" name="%{'roles.' + #role}" value="%{#entry.value}" disabled="%{#session.user.id == user.id}" />
									<s:label for="%{'roles.' + #role}" value="%{getText('role.' + #role.roleId)}" />
								</div>
							</s:iterator>
						</div>
					</div>
					
					<div class="panel panel-danger">
						<div class="panel-heading">
							<div class="checkbox">
								<s:checkbox id="change-password" name="changePassword" />
								<s:label for="change-password" value="%{getText('edit-form.change-password-title')}" />
							</div>
						</div>
						<div class="panel-body">
							<div class="form-group">
								<label for="password"><s:text name="edit-form.password"/></label>
								<s:password id="password" name="password" placeholder="%{getText('edit-form.password')}" cssClass="form-control" />
								<s:fielderror fieldName="password" />
							</div>
							<div class="form-group">
								<label for="confirmed-password"><s:text name="edit-form.confirm-password"/></label>
								<s:password id="confirmed-password" name="confirmedPassword" placeholder="%{getText('edit-form.confirm-password')}" cssClass="form-control" />
								<s:fielderror fieldName="confirmedPassword" />
							</div>
						</div>
					</div>

					<div class="text-right">
						<s:a namespace="/user" action="list" cssClass="btn btn-lg btn-primary">
							<s:text name="edit-form.back-button" />
						</s:a>
						<button type="submit" class="btn btn-lg btn-danger">
							<s:text name="edit-form.save-button" />
						</button>
					</div>
				</s:form>
			</div>
		</div>
		<s:include value="/jsp/tile/footer.jsp" />
	</body>
</html>