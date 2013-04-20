<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<c:if test="${(empty localeName)}">
	<c:set var="loc" value="${pageContext.request.locale}" scope="session"/>
</c:if>
<c:if test="${!(empty localeName)}">
	<c:set var="loc" value="${localeName}" scope="session"/>
</c:if>
<fmt:setLocale value="${loc}" />
<fmt:bundle basename="properties.lang">
	<head>
<title><fmt:message key="index.title"></fmt:message></title>
	</head>
	<body>
		<form name="locale" action="controller" method="post">
			<input type="hidden" name="command" value="locale"></input> <input
				type="submit" name="lang" value="English"></input> <input
				type="submit" name="lang" value="Русский"></input>
			<!--<c:url value="index.jsp" var="engURL">
				<c:param name="locale" value="en-US" />
			</c:url>
			<a href="${engURL}"> English</a> |
			<c:url value="index.jsp" var="russURL">
				<c:param name="locale" value="ru-RU" />
			</c:url>
			<a href="${russURL}"> Русский </a> <br /> <br />-->
		</form>
		<h1>
			<fmt:message key="index.greetings"></fmt:message>
		</h1>
		<h2>
		
			<fmt:message key="index.information"></fmt:message>
		</h2>

		<a href="jsp/login.jsp"> <fmt:message key="index.start"></fmt:message>
		</a>
	</body>
</fmt:bundle>
</html>