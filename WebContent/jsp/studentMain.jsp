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
<title><fmt:message key="student_main.title"></fmt:message></title>
	</head>
	<body>
		<h1>
			<fmt:message key="student_main.title"></fmt:message>
		</h1>
		<br />
		<h2>
			<fmt:message key="student_main.text"></fmt:message>
		</h2>
		<hr></hr>
		<h3>
			<fmt:message key="student_main.name"></fmt:message>
			<c:out value="${user.fullName}" />
		</h3>
		
		<br />
		<c:set scope="session" var="user" value="${user}"></c:set>
		

		<form action="/ElectiveCourse/controller" method="POST">
			<input type="hidden" value="${stud}" name="student"> <input
				type="hidden" name="command" value="viewCourses"> <input size="120"
				type="submit"
				value=<fmt:message key="student_main.submit.view.courses"></fmt:message>>
		</form>

		<c:if test="${!(empty user.marks)}">
			<hr></hr>
			<table border="1">
				<tr>
					<td><fmt:message key="student_main.table.courses.title"></fmt:message></td>
					<td><fmt:message key="student_main.table.courses.hours"></fmt:message></td>
					<td><fmt:message key="student_main.table.courses.mark"></fmt:message></td>
					<td><fmt:message key="student_main.table.courses.refuse"></fmt:message></td>
				</tr>
				<c:forEach var="mark" items="${user.marks}">
					<tr>
						<c:set var="course" value="${mark.key}"></c:set>
						<td><c:out value="${course.title}" /></td>
						<td><c:out value="${course.hours}" /></td>
						<c:set var="checkedMark" value="${mark.value}"></c:set>
						<c:if test="${mark.value == 0}">
							<c:set var="checkedMark" value="-"></c:set>
						</c:if>
						<td><c:out value="${checkedMark}" /></td>
						<td>
							<form action="/ElectiveCourse/controller" method="post">
								<input type="hidden" name="command" value="refuseFromCourse">
								<input type="hidden" name="idCourse" value="${course.id}">
								<input type="submit"
									value=<fmt:message key="student_main.table.courses.submit.refuse"></fmt:message>>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<hr></hr>
		<a href="controller"> <fmt:message key="student_main.return">
			</fmt:message>
		</a>
	</body>
</fmt:bundle>
</html>
