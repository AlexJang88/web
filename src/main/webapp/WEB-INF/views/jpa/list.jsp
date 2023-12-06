<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h1>JPA LIST</h1>

<h3>전체글 : [${count}]</h3>
<c:forEach var="dto" items="${list}">
	${dto.id} ${dto.pw} ${dto.age} ${dto.reg} <br />
</c:forEach>