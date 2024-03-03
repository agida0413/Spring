<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<div class="row">
		<h3>글쓰기</h3>
	</div>
	<form action="insert_ok.do" method="get">
	<table class="table">
		<tr>
		<th width="15%">이름</th>
		<td width="85%">
			<input type="text" name="name" size="50" class="input-sm">
		</td>
		</tr>
		
			<tr>
		<th width="15%">제목</th>
		<td width="85%">
			<input type="text" name="subject" size="50" class="input-sm">
		</td>
		</tr>
		
		<tr>
		<th width="15%">내용</th>
		<td width="85%">
		<textarea rows="10" cols="52" name="content"></textarea>
		</td>
		</tr>
		
			<tr>
		<th width="15%">비밀번호</th>
		<td width="85%">
			<input type="password" name="pwd" size="15" class="input-sm">
		</td>
		</tr>
		
		<tr>
			<td colspan="2" class="text-center">
				<input type="submit" value="글쓰기" class="btn-primary btn-sm">
				<input type="button" value="취소" class="btn btn-sm btn-danger" onClick="javascript:history.back()">
			</td>
		</tr>
	</table>
	</form>
</div>
</body>
</html>