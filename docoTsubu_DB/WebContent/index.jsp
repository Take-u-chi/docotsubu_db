<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- トップ画面 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶへようこそ</h1>
<form action="/docoTsubu_DB/Login"method="post">
ユーザー名：<input type="text" name="name"><br>
パスワード：<input type="password" name="pass"><br>
<input type="submit"value="ログイン">
</form>
<a href="/docoTsubu_DB/signUp.jsp">ユーザー登録</a>
</body>
</html>