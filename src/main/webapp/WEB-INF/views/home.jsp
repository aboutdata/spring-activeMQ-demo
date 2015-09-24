<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	hi guys. im activeMQ + spring mvc test demo.
</h1>
	<c:if test="${not empty send}">
	<P> 	send message success;</P>
	</c:if>
	<c:if test="${empty send}">
	<P>  The time on the server is ${serverTime}. 
	</c:if>
	
	</br>
	<a href="${pageContext.request.contextPath}/send">sendTest</a>
	</br>
	<a href="${pageContext.request.contextPath}/receive">receiveTest</a>
</body>
</html>
