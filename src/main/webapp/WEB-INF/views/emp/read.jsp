<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h1>emp</h1>
<c:forEach var="dto" items="${list}">
	${dto.empno} ${dto.ename} ${dto.job}<br />
</c:forEach>
