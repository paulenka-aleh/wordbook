<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="root" value="#request.get('javax.servlet.forward.context_path')"/>

<s:i18n name="paulenka.aleh.wordbook.jsp.header">
	<div class="navbar navbar-default navbar-fixed-top">
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
							<img src="${root}/image/flag-belarus.png"/>
						</s:a>
					</li>
					<li>
						<s:a namespace="/locale" action="change-locale">
							<s:param name="language">en</s:param>
							<img src="${root}/image/flag-english.png"/>
						</s:a>
					</li>
					<li>
						<s:a namespace="/locale" action="change-locale">
							<s:param name="language">ru</s:param>
							<img src="${root}/image/flag-russian.png"/>
						</s:a>
					</li>
				</ul>

				<s:if test="#session.user != null">
					<div class="navbar-right">
						<p class=" navbar-text">
							<s:text name="welcome-message.welcome">
								<s:param>${user.username}</s:param>
							</s:text>
						</p>
						<s:a namespace="/user" action="logout" cssClass="btn btn-primary navbar-btn">
							<s:text name="sign-in-form.sign-out-button"/>
						</s:a>
					</div>
				</s:if>
				<s:else>
					<s:form namespace="/user" action="login" method="post" theme="simple" cssClass="navbar-right navbar-form">
						<input type="hidden" name="redirectUri" value="${param.loginRedirectUri}"/>
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
				</s:else>
			</div>
		</div>
	</div>
</s:i18n>