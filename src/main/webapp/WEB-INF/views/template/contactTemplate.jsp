<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="../resources/css/contact-style.css"/>
<title>Insert title here</title>
</head>
<body>
	<!-- fixed menu bar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</nav>
	<section class="container">
		<tiles:insertAttribute name="body"></tiles:insertAttribute>
	</section>
</body>
</html>