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
<title><fmt:message key="avialable_courses.title"></fmt:message></title>
	</head>
	<body>
		<h1>
			<fmt:message key="avialable_courses.title"></fmt:message>
		</h1>
		<br />
		<br />
		<c:set scope="session" var="availableCourses" value="${courses}"></c:set>

		<c:if test="${!(empty courses)}">
			<hr></hr>
			<table border="1">
				<tr>
					<td><fmt:message key="avialable_courses.table.title"></fmt:message></td>
					<td><fmt:message key="avialable_courses.table.description"></fmt:message></td>
					<td><fmt:message key="avialable_courses.table.hours"></fmt:message></td>
					<td><fmt:message key="avialable_courses.table.enter"></fmt:message></td>
				</tr>
				<c:forEach var="course" items="${courses}">
					<tr>
						<td><c:out value="${course.title}" /></td>
						<td><c:out value="${course.description}" />
						<td><c:out value="${course.hours}" /></td>
						<td>
							<form action="/ElectiveCourse/controller" method="post">
								<input type="hidden" name="command" value="enterForCourse">
								<input type="hidden" name="idCourse" value="${course.id}">
								<input type="submit"
									value=<fmt:message key="available_courses.table.submit"></fmt:message>>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${(empty courses)}">
		<fmt:message key="available_courses.empty"></fmt:message>
		</c:if>
		<hr></hr>
		<a href="controller"> <fmt:message key="main.return">
			</fmt:message>
		</a>
	</body>
</fmt:bundle>
</html>
