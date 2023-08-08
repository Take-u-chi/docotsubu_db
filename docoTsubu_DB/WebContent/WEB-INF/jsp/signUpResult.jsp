<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Userクラスをインポート -->
<%@ page import="model.User" %>

<!-- セッションスコープからユーザー情報を取得 -->
<% User loginUser = (User)session.getAttribute("loginUser"); %>

<!-- ユーザー登録結果を出力するビュー -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
<h1>ユーザー登録結果</h1>

<!-- ユーザー情報が取得できた時 -->
 <% if(loginUser != null){%>
 <p>ユーザー登録しました</p>
 <p>ようこそ<%=loginUser.getName()%>さん</p>
 <a href="/docoTsubu_DB/Main">つぶやき投稿・閲覧へ</a>

<!-- ユーザー情報が取得できなかった時 -->
 <% }else{ %>
 <p>登録に失敗しました</p>
 <a href="/docoTsubu_DB/">TOPへ</a>
 <%} %>

</head>
<body>

</body>
</html>