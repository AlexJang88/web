<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>/JPA/form.jsp</h1>

<form action="/jpa/insert" method="post">
	id: <input type="text" name="id" /><br />
	pw: <input type="password" name="pw" /><br />
	age: <input type="number" name="age" /><br />
		<input type="submit" value="전송" /><br />
</form>

<form action="/jpa/delete" method="post">
	id: <input type="text" name="id" /><br />
	pw: <input type="password" name="pw" /><br />
		<input type="submit" value="전송" /><br />
</form>