<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="root">${pageContext.request.contextPath}</s:set>

<!DOCTYPE html>
<html lang="${locale.language}">
	<head>
		<s:include value="/jsp/tile/head.jsp" />
		<link rel="stylesheet" href="${root}/css/list.css" />
		<script type="text/javascript" src="${root}/js/user-delete-confirm.js"></script>
		<title><s:text name="user-list-page.title" /></title>
	</head>
	<body>
		<div id="delete-confirm" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content panel-danger">
					<div class="modal-header panel-heading">
						<h4 class="modal-title"><s:text name="delete-confirm.title" /></h4>
					</div>
					<div class="modal-body">
						<p id="delete-confirm-message">
							<s:text name="delete-confirm.message" />
						</p>
					</div>
					<div class="modal-footer">
						<s:form namespace="/user" action="delete" method="post">
							<a class="btn btn-default" data-dismiss="modal">
								<span class="glyphicon glyphicon-circle-arrow-left"></span>&nbsp;
								<span><s:text name="delete-confirm.cancel" /></span>
							</a>
							<button type="submit" class="btn btn-danger">
								<s:hidden name="user.id" id="delete-confirm-id" />
								<span class="glyphicon glyphicon-remove"></span>&nbsp;
								<span><s:text name="delete-confirm.delete" /></span>
							</button>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	
		<div id="wrap">
			<s:include value="/jsp/tile/header.jsp" />
			<div class="container">
				<div class="list-group list">
					<s:iterator value="%{users}" var="user">
						<div class="list-group-item">
							<span class="pull-left">
								<s:text name="user-list.item">
									<s:param value="%{#user.id}"/>
									<s:param value="%{#user.username}"/>
								</s:text>
							</span>
							<div class="pull-right">
								<s:a namespace="/user" action="edit" cssClass="btn btn-sm btn-warning">
									<s:param name="user.id" value="%{#user.id}" />
									<span class="glyphicon glyphicon-edit"></span>&nbsp;
									<span><s:text name="user-list.edit" /></span>
								</s:a>
								<s:if test="%{#session.user.id == #user.id}">
									<button type="button" disabled="disabled" class="btn btn-sm btn-danger">
										<span class="glyphicon glyphicon-remove"></span>&nbsp;
										<span><s:text name="user-list.delete" /></span>
									</button>
								</s:if>
								<s:else>
									<button type="button" data-toggle="modal" data-target="#delete-confirm" data-user-id="${user.id}" data-user-name="${user.username}" class="btn btn-sm btn-danger">
										<span class="glyphicon glyphicon-remove"></span>&nbsp;
										<span><s:text name="user-list.delete" /></span>
									</button>
								</s:else>
							</div>
							<div style="clear: both;"></div>
						</div>
					</s:iterator>
				</div>
			</div>
		</div>
		<s:include value="/jsp/tile/footer.jsp" />
	</body>
</html>