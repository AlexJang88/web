<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="${bodyback_c}">
	<center>
		<table width="700">
			<tr>
				<td align="right" bgcolor="${value_c}">
					<a href="/guest/writeForm">글쓰기</a>
				</td>
		</table>
		<c:if test="${count==0}">
			<table width="700" border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center">방명록에 저장된 글이 없습니다.</td>
			</table>
		</c:if>
		<c:if test="${count>0}">
			<table border="1" width="700" cellpadding="0" cellspacing="0"
				align="center">
				<tr height="30" bgcolor="${value_c}">
					<td align="center" width="50">번 호</td>
					<td align="center" width="250">제 목</td>
					<td align="center" width="100">작성자</td>
					<td align="center" width="150">작성일</td>
					<td align="center" width="50">조 회</td>
					<td align="center" width="100">IP</td>
				</tr>
				<c:forEach var="article" items="${list}">
					<tr height="30">
						<td align="center" width="50">${article.num}</td>
								<a href="/guest/content?num=${article.num}&pageNum=${pageNum}">${article.title}</a> 
							<c:if test="${article.readcount >=20 }">
								<img src="/resources/img/hot.gif" border="0" height="16">
							</c:if>
							<button type="button" onclick="window.location='/guest/delete?num=${article.num}'">삭제</button>
						</td>
						<td align="center" width="100">
						<td align="center" width="50">${article.id}</td>
						</td>
						<td align="center" width="150">
							<fmt:formatDate value="${article.reg_date}" type="date" dateStyle="short" />
						</td>
						<td align="center" width="50">${article.readcount}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${count>0}">
			<c:if test="${startPage>10}">
	        	<a href="/guest/main?pageNum=${startPage-10}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        	<a href="/guest/main?pageNum=${i}">[${i}]</a>
			</c:forEach>
				<c:if test="${endPage<pageCount}">
	        	<a href="/guest/main?pageNum=${startPage+10}">[다음]</a>
			</c:if>
		</c:if>
	</center>
</body>
</html>