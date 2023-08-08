<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Userクラスをインポート -->
<%@ page import="model.User" %>

<!-- セッションスコープからユーザー情報を取得 -->
<% User loginUser = (User)session.getAttribute("loginUser"); %>


<!-- ログイン結果画面を出力するビュー -->

 <!DOCTYPE html>
 <html>
 <head>
 <meta charset="UTF-8">
 <title>どこつぶログイン</title>
 </head>
 <body>
 <h1>どこつぶログイン</h1>

<!-- ユーザー情報が取得できた時 -->
 <% if(loginUser != null){%>
 <p>ログインに成功しました</p>
 <p>ようこそ<%=loginUser.getName()%>さん</p>
 <a href="/docoTsubu_DB/Main">つぶやき投稿・閲覧へ</a>

<!-- ユーザー情報が取得できなかった時 -->
 <% }else{ %>
 <p>ログインに失敗しました</p>
 <a href="/docoTsubu_DB/">TOPへ</a>
 <%} %>

 </body>
 </html>