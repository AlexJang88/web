<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <img src="/resources/file/user/${dto.img}" width="100"/><br />
 	<form action ="/user/imgChange.me" method="post" enctype="multipart/form-data">   
    바꿀 사진 : <input type="file" name="profile"> <br />
    <input type="submit" value="변경"> <br />
    </form>