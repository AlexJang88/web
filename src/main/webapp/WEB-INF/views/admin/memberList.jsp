<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language="javascript" src="/resources/js/member.js"></script>
<table>
	<tr>
		<td>아이디</td>
		<td>이름</td>
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
				<option value="0">탈퇴
				<option value="1" selected>일반
				<option value="2">휴먼
				<option value="10">관리자
			</select>
			
			<input type="submit" value="수정" onSubmit="return checkStatus(nowStatus_${dto.id},cstatus_${dto.id}) ">
		</td>
		</tr>
		
		</form>
	</c:forEach>
	

</table>
