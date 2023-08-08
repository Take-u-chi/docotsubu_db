<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>アカウント登録</h1>
<form action="/docoTsubu_DB/SignUp"method="post">
<input type="text"name="name" placeholder="ユーザー名"required><br>
<input type="password"name="pass" placeholder="パスワード"required><br>

<% String errorMsg = (String)request.getAttribute("errorMsg"); %>
<% if(errorMsg != null){ %>
	<p style="color:red;">
	<%= errorMsg %></p>
<%} %>
<input type="submit"value="アカウント登録"><br>
</form>
<a href="/docoTsubu_DB/index.jsp">キャンセル</a>
</body>
</html>