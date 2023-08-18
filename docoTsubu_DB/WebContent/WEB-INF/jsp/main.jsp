<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Javaクラスのインポート -->
<%@ page import="model.User,model.Mutter,java.util.List" %>
<!-- セッションスコープからユーザー情報を取得 -->
<% User loginUser = (User)session.getAttribute("loginUser"); %>
<!-- アプリケーションスコープからつぶやきリストを取得 -->
<% List<Mutter> mutterList = (List<Mutter>)request.getAttribute("mutterList"); %>
<!-- リクエストスコープに保存されたエラーメッセージを取得 -->
<% String errorMsg = (String)request.getAttribute("errorMsg"); %>

<% List<Mutter> searchList = (List<Mutter>)session.getAttribute("searchList");%>
<!-- メイン画面を出力するビュー -->

	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>どこつぶ</title>
	</head>
	<body>
	<h1>どこつぶメイン</h1>
	<p>
	<%= loginUser.getName() %>さん、ログイン中
	<a href="/docoTsubu_DB/Logout">ログアウト</a>
	</p>
	<p><a href="/docoTsubu_DB/Main">更新</a></p>
	<p><a href="/docoTsubu_DB/MutterSearch">検索</a></p>

<!-- textを受け取りMain.javaへPostリクエスト -->
	<form action="/docoTsubu_DB/Main"method="post">
	<input type="text"name="text">
	<input type="submit"value="つぶやく">
	</form>
<!-- 入力値異常を受け取ったらエラーメッセージを表示 -->
	<% if(errorMsg != null){ %>
	<p style="color:red;">
	 <%= errorMsg %></p>
	<% } %>
<!-- ArrayListに格納されたインスタンスを先頭から順に取得 -->
	<% for(Mutter mutter : mutterList){ %>
	 <p><%=mutter.getUserName() %>：<%= mutter.getText()%></p>
	<%} %>






	</body>
	</html>