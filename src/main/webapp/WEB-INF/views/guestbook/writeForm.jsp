<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<title>게시판</title>
<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="/resources/js/script.js"></script>
<script>
	function fileAdd(){
		document.getElementById("files").innerHTML+='<br /><input type="file" size="40" maxlength="30" name="files" />';
	}
</script>
</head>
<body bgcolor="${bodyback_c}">
	<center>
		<b>글쓰기</b> <br>
		<form method="post" name="writeform" action="/guest/writePro" enctype="multipart/form-data" onsubmit="return writeSave()">
			<table width="400" border="1" cellspacing="0" cellpadding="0"
				bgcolor="${bodyback_c}" align="center">
				<tr>
					<td align="right" colspan="2" bgcolor="${value_c}">
						<a href="/guest/main"> 글목록</a>
					</td>
				</tr>
				<tr>
					<td width="70" bgcolor="${value_c}" align="center">제 목</td>
					<td width="330">
							<input type="text" size="40" maxlength="50" name="title"></td>
				</tr>
				<tr>
					<td width="70" bgcolor="${value_c}" align="center">내 용</td>
					<td width="330"><textarea name="content" rows="13" cols="40"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan=2 bgcolor="${value_c}" align="center">
						<input type="submit" value="글쓰기"> 
						<input type="reset" value="다시작성"> 
						<input type="button" value="목록보기" OnClick="window.location='/guest/main'">
					</td>
				</tr>
			</table>
		</form>
</body>