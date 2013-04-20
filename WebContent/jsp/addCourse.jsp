<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>

<c:if test="${!(empty localeName)}">
	<c:set var="loc" value="${localeName}" />
</c:if>
<fmt:setLocale value="${loc}" />
<fmt:bundle basename="properties.lang">
	<head>
<title><fmt:message key="add_course.title"></fmt:message></title>
	</head>
	<body>
		<h1>
			<fmt:message key="add_course.text"></fmt:message>
		</h1>
		<br />
		<form method="POST"
			action="/ElectiveCourse/controller">
			<input type="hidden" name="command" value="addCourse" />
			<fmt:message key="add_course.course.title"></fmt:message>
			<br /> <input type="text" name="title" value="" required="required"
				size="60"><br />
			<fmt:message key="add_course.course.hours"></fmt:message>
			<br /> <input type="text" name="hours" value="" required="required">
			<br />
			<fmt:message key="add_course.course.description"></fmt:message>
			<br />  <textarea name="description"  rows="5" cols="60"></textarea>
			<br />
			<br>
			<input type="submit"
				value=<fmt:message key="add_course.button.add"></fmt:message>>
		</form>

	</body>
</fmt:bundle>
</html>
