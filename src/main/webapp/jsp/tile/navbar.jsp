<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:i18n name="message.jsp.tile.navigation">
	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<s:a namespace="/" action="" cssClass="navbar-brand">
					<s:text name="application.title"/>
				</s:a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<%--
						<li><a href="#about">Wordbook</a></li>
						<li><a href="#about">Users</a></li>
						<li><a href="#about">About</a></li>
					--%>
					<li>
						<s:a namespace="/locale" action="change-locale">
							<s:param name="language">be</s:param>
							<s:param name="redirectUri">${param.redirectUri}</s:param>
							<img src="/wordbook-struts/image/flag-belarus.png"/>
						</s:a>
					</li>
					<li>
						<s:a namespace="/locale" action="change-locale">
							<s:param name="language">en</s:param>
							<s:param name="redirectUri">${param.redirectUri}</s:param>
							<img src="/wordbook-struts/image/flag-english.png"/>
						</s:a>
					</li>
					<li>
						<s:a namespace="/locale" action="change-locale">
							<s:param name="language">ru</s:param>
							<s:param name="redirectUri">${param.redirectUri}</s:param>
							<img src="/wordbook-struts/image/flag-russian.png"/>
						</s:a>
					</li>
				</ul>

				<s:form namespace="/user" action="login" method="post" theme="simple" cssClass="navbar-right navbar-form">
					<div class="form-group">
						<s:textfield name="credentials.username" placeholder="getText('sign-in-form.username')" cssClass="form-control"/>
					</div>
					<div class="form-group">
						<s:password name="credentials.password" placeholder="getText('sign-in-form.password')" cssClass="form-control"/>
					</div>
					<button type="submit" class="btn btn-success">
						<s:text name="sign-in-form.sign-in-button"/>
					</button>
					<s:a namespace="/user" action="register" cssClass="btn btn-primary">
						<s:text name="sign-in-form.sign-up-button"/>
					</s:a>
				</s:form>
			</div>
		</div>
	</div>
</s:i18n>