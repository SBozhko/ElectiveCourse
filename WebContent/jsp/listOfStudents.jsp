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
<title><fmt:message key="list_of_students.title"></fmt:message></title>
	</head>
	<body>
		<h1>
			<fmt:message key="list_of_students.welcome"></fmt:message>
		</h1>
		<br />
		<h2>
			<fmt:message key="list_of_students.text"></fmt:message>
		</h2>
		<hr></hr>
		<h4>
			<fmt:message key="list_of_students.course.title"></fmt:message>
		</h4>
		
		<c:out value="${courseTitle}" />
		<br />
		<c:if test="${!(empty students)}">
			<c:set scope="session" value="${students}" var="students"></c:set>
			<hr></hr>
			<table border="1">
				<tr>
					<td><fmt:message
							key="list_of_students.table.students.fullname"></fmt:message></td>
					<td><fmt:message key="list_of_students.table.students.mark"></fmt:message></td>
					<td><fmt:message
							key="list_of_students.table.students.change.mark"></fmt:message></td>
				</tr>
				<c:forEach var="student" items="${students}">
					<tr>
						<td><c:out value="${student.fullName}" /></td>
						<c:forEach var="mark" items="${student.marks}">
							<form action="/ElectiveCourse/controller" method="post">
								<c:set var="checkedMark" value="${mark.value}"></c:set>
								<c:if test="${mark.value == 0}">
									<c:set var="checkedMark" value="-"></c:set>
								</c:if>
								<td><input type="number" name="newMark"
									value=<c:out value="${checkedMark}" />></td>

								<td><input type="hidden" name="command" value="changeMark">
								<input type="hidden" name="courseTitle" value="${courseTitle}">
									<input type="hidden" name="idStudent"
									value="${student.idStudent}"> <input type="hidden"
									name="idCourse" value="${mark.key.id}"> <input
									type="submit"
									value=<fmt:message key="list_of_students.table.students.submit.change.mark"></fmt:message>>
								</td>
							</form>
						</c:forEach>
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
