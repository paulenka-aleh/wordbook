<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="root">${pageContext.request.contextPath}</s:set>

<s:i18n name="paulenka.aleh.wordbook.ui.jsp.header">
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<s:a id="logo" namespace="/" action="" cssClass="navbar-brand">
					<img src="${root}/image/wordbook-logo.png" />
				</s:a>
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
			<div id="navbar-collapse" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<s:if test="%{(#session.roles != null) && ((#session.roles.contains(@paulenka.aleh.wordbook.data.Role@USER)) || (#session.roles.contains(@paulenka.aleh.wordbook.data.Role@MODERATOR)))}">
						<li>
							<s:a namespace="/wordbook" action="">
								<s:text name="navigation.wordbook" />
							</s:a>
						</li>
					</s:if>
					<s:if test="%{(#session.roles != null) && (#session.roles.contains(@paulenka.aleh.wordbook.data.Role@MODERATOR))}">
						<li>
							<s:a namespace="/wordbook" action="create">
								<span class="glyphicon glyphicon-plus"></span>
							</s:a>
						</li>
					</s:if>
					<s:if test="%{(#session.roles != null) && (#session.roles.contains(@paulenka.aleh.wordbook.data.Role@ADMINISTRATOR))}">
						<li>
							<s:a namespace="/user" action="list">
								<s:text name="navigation.users" />
							</s:a>
						</li>
					</s:if>
					<li>
						<s:a namespace="/locale" action="change-locale">
							<s:param name="language">be</s:param>
							<img src="${root}/image/flag-belarus.png" />
						</s:a>
					</li>
					<li>
						<s:a namespace="/locale" action="change-locale">
							<s:param name="language">en</s:param>
							<img src="${root}/image/flag-english.png" />
						</s:a>
					</li>
					<li>
						<s:a namespace="/locale" action="change-locale">
							<s:param name="language">ru</s:param>
							<img src="${root}/image/flag-russian.png" />
						</s:a>
					</li>
				</ul>

				<s:if test="%{#session.user != null}">
					<div class="navbar-right">
						<p class="navbar-text">
							<s:text name="welcome-message.welcome">
								<s:param value="%{#session.user.username}" />
							</s:text>
						</p>
						<s:a namespace="/user" action="logout" cssClass="btn btn-primary navbar-btn">
							<span class="glyphicon glyphicon-log-out"></span>&nbsp;
							<span><s:text name="sign-in-form.sign-out-button" /></span>
						</s:a>
					</div>
				</s:if>
				<s:else>
					<s:form namespace="/user" action="login" method="post" theme="simple" cssClass="navbar-right navbar-form">
						<div class="form-group">
							<s:textfield name="credentials.username" placeholder="%{getText('sign-in-form.username')}" cssClass="form-control"/>
						</div>
						<div class="form-group">
							<s:password name="credentials.password" placeholder="%{getText('sign-in-form.password')}" cssClass="form-control"/>
						</div>
						<button type="submit" class="btn btn-success">
							<span class="glyphicon glyphicon-log-in"></span>&nbsp;
							<span><s:text name="sign-in-form.sign-in-button" /></span>
						</button>
						<s:a namespace="/user" action="register" cssClass="btn btn-primary">
							<span class="glyphicon glyphicon-pencil"></span>&nbsp;
							<span><s:text name="sign-in-form.sign-up-button" /></span>
						</s:a>
					</s:form>
				</s:else>
			</div>
		</div>
	</div>
</s:i18n>