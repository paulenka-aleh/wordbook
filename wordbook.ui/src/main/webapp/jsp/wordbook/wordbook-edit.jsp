<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="root">${pageContext.request.contextPath}</s:set>

<!DOCTYPE html>
<html lang="${locale.language}">
	<head>
		<s:include value="/jsp/tile/head.jsp" />
		<link rel="stylesheet" href="${root}/css/form.css" />
		<title>
			<s:text name="word-edit-page.title">
				<s:param value="%{word.word}" />
			</s:text>
		</title>
	</head>
	<body>
		<div id="wrap">
			<s:include value="/jsp/tile/header.jsp" />
			<div class="container">
				<s:form namespace="/wordbook" action="%{'edit?word.id=' + word.id}" method="post" theme="bootstrap" cssClass="form">
					<s:if test="%{success}">
						<div class="alert alert-success alert-dismissable">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<p><s:text name="edit-form.success" /></p>
						</div>
					</s:if>

					<h2 class="form-heading">
						<s:text name="edit-form.form-title">
							<s:param value="%{word.word}"></s:param>
						</s:text>
					</h2>

					<div class="form-group">
						<label for="word.word"><s:text name="edit-form.word"/></label>
						<s:textfield id="word.word" name="word.word" placeholder="%{getText('edit-form.word')}" cssClass="form-control" />
						<s:fielderror fieldName="word.word" />
					</div>
					<div class="form-group">
						<label for="word.explanation"><s:text name="edit-form.explanation"/></label>
						<s:textarea id="word.explanation" name="word.explanation" placeholder="%{getText('edit-form.explanation')}" cssClass="form-control" />
						<s:fielderror fieldName="word.explanation" />
					</div>

					<div class="text-right">
						<s:a namespace="/wordbook" action="" cssClass="btn btn-lg btn-primary">
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