<%@ page language="java" contentType="text/html;charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>

<c:if test="${!(empty localeName)}">
	<c:set var="loc" value="${localeName}" />
</c:if>
<fmt:setLocale value="${loc}" />
<fmt:bundle basename="properties.lang">
	<head>
<title><fmt:message key="edit_course.title"></fmt:message></title>
	</head>
	<body>
		<h1>
			<fmt:message key="edit_course.text"></fmt:message>
		</h1>
		<br />
		<form method="POST" action="/ElectiveCourse/controller">
			<input type="hidden" name="idCourse" value="${courseToEdit.id}">
			<input type="hidden" name="command" value="editCourse" />
			<fmt:message key="edit_course.course.title"></fmt:message>
			<br /> <input type="text" name="title" value="${courseToEdit.title}"
				required="required" size="100"><br />
			<fmt:message key="edit_course.course.hours"></fmt:message>
			<br /> <input type="text" name="hours" value="${courseToEdit.hours}"
				required="required"> <br />
			<fmt:message key="edit_course.course.description"></fmt:message>
			<br />
			<textarea name="description" rows="15" cols="100">${courseToEdit.description}</textarea>
			<br /> <br> <input type="submit"
				value=<fmt:message key="edit_course.button.save"></fmt:message>>



		</form>

	</body>
</fmt:bundle>
</html>
