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
<title><fmt:message key="teacher_main.title"></fmt:message></title>
	</head>
	<body>
		<h1>
			<fmt:message key="teacher_main.title"></fmt:message>
		</h1>
		<br />
		<h2>
			<fmt:message key="teacher_main.text"></fmt:message>
		</h2>
		<hr></hr>
		<h3>
			<fmt:message key="teacher_main.name"></fmt:message>
			<c:out value="${user.fullName}" />
		</h3>

		<c:set scope="session" var="user" value="${user}"></c:set>

		<br />

		<table>
			<tr>
				<td><c:if test="${(empty user.courses)}">
						<fmt:message key="teacher_main.empty.table.courses"></fmt:message>
					</c:if></td>
				<td><form action="jsp/addCourse.jsp">
						<input type="submit"
							value=<fmt:message key="teacher_main.submit.add.course"></fmt:message>>
					</form></td>
			</tr>
		</table>

		<c:if test="${!(empty user.courses)}">
			<hr></hr>
			<table border="1">
				<tr>
					<td><fmt:message key="teacher_main.table.courses.title"></fmt:message></td>
					<td><fmt:message key="teacher_main.table.courses.description"></fmt:message></td>
					<td><fmt:message key="teacher_main.table.courses.hours"></fmt:message></td>
					<td><fmt:message key="teacher_main.table.courses.edit.course"></fmt:message></td>
					<td><fmt:message
							key="teacher_main.table.courses.delete.course"></fmt:message></td>
					<td><fmt:message
							key="teacher_main.table.courses.view.students"></fmt:message></td>
				</tr>
				<c:forEach var="course" items="${user.courses}">
					<tr>
						<td>${course.title}</td>
						<td>${course.description}</td>
						<td>${course.hours}</td>
						<td>
							<form action="/ElectiveCourse/controller" method="post">
								<input type="hidden" name="command" value="toEditCoursePage">
								<input type="hidden" name="courseId" value="${course.id}">
								<input type="hidden" name="courseTitle" value="${course.title}">
								<input type="hidden" name="courseDescription"
									value="${course.description}"> <input type="hidden"
									name="courseHours" value="${course.hours}"> <input
									type="submit"
									value=<fmt:message key="teacher_main.table.courses.submit.edit.course"></fmt:message>>
							</form>
						</td>
						<td>
							<form action="/ElectiveCourse/controller" method="post">
								<input type="hidden" name="command" value="deleteCourse">
								<input type="hidden" name="idCourse" value="${course.id}">
								<input type="submit"
									value=<fmt:message key="teacher_main.table.courses.submit.delete.course"></fmt:message>>
							</form>
						</td>
						<td>
							<form action="/ElectiveCourse/controller" method="post">
								<input type="hidden" name="command" value="viewStudents">
								<input type="hidden" name="courseTitle" value="${course.title}">
								<input type="hidden" name="idCourse" value="${course.id}">

								<input type="submit"
									value=<fmt:message key="teacher_main.table.courses.submit.view.students"></fmt:message>>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		<hr></hr>
		<a href="controller"> <fmt:message key="main.return">
			</fmt:message>
		</a>
	</body>
</fmt:bundle>
</html>

