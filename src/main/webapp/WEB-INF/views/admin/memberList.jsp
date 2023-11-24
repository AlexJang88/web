<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script language="javascript" src="/resources/js/member.js"></script>
<table border="1">
	<tr>
		<td>아이디</td>
		<td>이름</td>
		<td>가입날짜</td>
		<td>등급</td>
		<td>등급수정</td>
	</tr>
	
	<c:forEach var="dto" items="${list}">
	<form method="post" action="/user/admin/statusChange.ad">
		<input type="hidden" name="id" value="${dto.id}" >
		<input type="hidden" name="nowStatus" value="${dto.status}" id="nowStatus_${dto.id}">
		
		<tr>
		<td>${dto.id}</td>
		<td>${dto.name}</td>
		<td><fmt:formatDate value="${dto.reg_date}" type="date" dateStyle="short"/></td>
		<td>
		<c:if test="${dto.status==0}">
		탈퇴
		</c:if>
		<c:if test="${dto.status==1}">
		일반
		</c:if>
		<c:if test="${dto.status==2}">
		휴먼
		</c:if>
		<c:if test="${dto.status==10}">
		관리자
		</c:if>
		
		</td>
			<td>
				<select name="cstatus" id="cstatus_${dto.id}">
					<option value="0" ${dto.status==0?'selected':''}>탈퇴</option>
					<option value="1" ${dto.status==1?'selected':''}>일반</option>
					<option value="2" ${dto.status==2?'selected':''}>휴먼</option>
					<option value="10" ${dto.status==10?'selected':''}>관리자</option>
				</select>
				<input type="submit" value="수정" onSubmit="return checkStatus(nowStatus_${dto.id},cstatus_${dto.id}) ">
			</td>
		</tr>
		</form>
	</c:forEach>
	

</table>
