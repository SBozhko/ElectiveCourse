<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>

<c:if test="${!(empty localeName)}">
	<c:set var="loc" value="${localeName}" />
</c:if>
<fmt:setLocale value="${loc}" />
<fmt:bundle basename="properties.lang">
	<head>
<title><fmt:message key="login.title"></fmt:message></title>
	</head>
	<body>
		<h1>
			<fmt:message key="login.text"></fmt:message>
		</h1>
		<br />
		<form name="loginForm" method="POST" action="/ElectiveCourse/controller">
			<input type="hidden" name="command" value="login" />
			<fmt:message key="login.email"></fmt:message>
			<br /> <input type="email" name="email" value="" required="required"><br />
			<fmt:message key="login.password"></fmt:message>
			<br /> <input type="password" name="password" value=""
				required="required"> <br /> <input type="submit"
				value=<fmt:message key="login.submit"></fmt:message>>
		</form>


		<form action="register.jsp">
			<input type="submit"
				value=<fmt:message key="login.register"></fmt:message>>
		</form>
	</body>
</fmt:bundle>
</html>
