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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="register.title"></fmt:message></title>
	</head>
	<body>
		<h1>
			<fmt:message key="register.title"></fmt:message>
		</h1>
		<form action="/ElectiveCourse/controller" method="post">
			<input type="hidden" name="command" value="register">
			<fmt:message key="register.email"></fmt:message>
			<br> <input type="email" name="email" required="required">
			<br>
			<fmt:message key="register.password"></fmt:message>
			<br> <input type="password" name="password" required="required">
			<br>
			<fmt:message key="register.fullname"></fmt:message>
			<br> <input type="text" size="40" name="fullName"
				required="required"> <br>
			<fmt:message key="register.role"></fmt:message>
			<br>
			<fmt:message key="register.teacher"></fmt:message>
			<input type="radio" name="role" value="teacher"><br>
			<fmt:message key="register.student"></fmt:message>
			<input type="radio" name="role" value="student"> <br> <br>
			<input type="submit"
				value=<fmt:message key="register.submit"></fmt:message>>
		</form>
	</body>
</fmt:bundle>
</html>