<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ page import = "java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/resources/etc/color.jsp"%>
<html>
<head>
<title>게시판</title>
<link href="/mvc/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="${bodyback_c}">  
<center>
	<b>글내용 보기</b>
	<br />
	<table width="500" border="1" cellspacing="0" cellpadding="0"  bgcolor="${bodyback_c}" align="center">  
		<tr height="30">
			<td align="center" width="125" bgcolor="${value_c}">글번호</td>
			<td align="center" width="125" align="center">${board.num}</td>
			<td align="center" width="125" bgcolor="${value_c}">조회수</td>
			<td align="center" width="125" align="center">${board.readcount}</td>
		</tr>
		<tr height="30">
			<td align="center" width="125" bgcolor="${value_c}">작성자</td>
			<td align="center" width="125" align="center">${board.writer}</td>
			<td align="center" width="125" bgcolor="${value_c}" >작성일</td>
			<td align="center" width="125" align="center"><fmt:formatDate value="${board.reg_date}" type="date"/></td>
		</tr>
 		<tr height="30">
			<td align="center" width="125" bgcolor="${value_c}">글제목</td>
			<td align="center" width="375" align="center" colspan="3">${board.subject}</td>
		</tr>
		<c:if test="${board.isFile>0}">
		<tr>
			<td align="center" width="125" bgcolor="${value_c}">첨부파일</td>
			<td align="right" width="375" colspan="3">
				<c:forEach var="name" items="${filenames}">
				<a href="/free/download?filename=${name}">${name}</a><br />
				</c:forEach>
			</td>
 		</tr>
 		</c:if>
		<tr>
			<td align="center" width="125" bgcolor="${value_c}">글내용</td>
			<td align="left" width="375" colspan="3"><pre>${board.content}</pre></td>
 		</tr>
		<tr height="30">      
			<td colspan="4" bgcolor="${value_c}" align="right" > 
	  			<input type="button" value="글수정" 
	  			onclick="document.location.href='/free/updateForm?num=${board.num}&pageNum=${pageNum}'">
	  			&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="글삭제" 
				onclick="document.location.href='/free/deleteForm?num=${board.num}&pageNum=${pageNum}'">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="답글쓰기"
				onclick="document.location.href='/free/writeForm?num=${num}&ref=${ref}&re_step=${re_step}&re_level=${re_level}'">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="글목록"  onclick="document.location.href='/free/list?pageNum=${pageNum}">
			</td>
 		</tr>
 	</table>    
	<br />    
</body>
</html>      





