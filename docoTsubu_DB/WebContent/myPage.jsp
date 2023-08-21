<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.User,model.Mutter,java.util.List" %>
      <% String errorMsg = (String)request.getAttribute("errorMsg"); %>
        <% String resultMsg = (String)request.getAttribute("resultMsg"); %>
    <% List<Mutter> myMutterList = (List<Mutter>)application.getAttribute("myMutterList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>マイページ</h1>
<h3>投稿一覧</h3>

<form action="/docoTsubu_DB/MyPage"method="post">

<table>
<tr><th>選択</th><th>名前</th><th>投稿</th></tr>

<% if(myMutterList != null){ %>
<% for (Mutter mutter : myMutterList){ %>
<tr><td><input type="radio" name="id" value=<%=mutter.getId() %> >
        <input type="submit"value="削除"></td>
        </form>

    <td><%= mutter.getUserName() %></td>
    <td><%= mutter.getText() %></tr>
    <br>
</table>
<%} %>
<%} %>

<% if(errorMsg != null){ %>
	<p style="color:red;">
	 <%= errorMsg %></p>
	<% }else if(resultMsg != null){ %>
	<p style="color:red;">
	 <%= resultMsg %></p>
	<%} %>

<a href="/docoTsubu_DB/Main">戻る</a>
</body>
</html>