<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8");%>
<c:choose>
	<c:when test="${check ==1 }">
		<c:set var="memId" value="${memberDTO.id}" scope="session"/>
		<c:set var="memStatus" value="${memberDTO.status}" scope="session"/>
		<c:redirect url="/user/main.me"/>
	</c:when>
	<c:when test="${check==0 }">
		<script> 
	 		 alert("아이디 / 비밀번호를 확인하세요.");
     		 history.go(-1);
		</script>
	</c:when>
</c:choose>
