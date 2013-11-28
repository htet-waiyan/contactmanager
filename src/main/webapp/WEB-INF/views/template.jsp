<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</header>
	<section class="container">
		<tiles:insertAttribute name="body"></tiles:insertAttribute>
	</section>
	<footer>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</footer>
</body>
</html>