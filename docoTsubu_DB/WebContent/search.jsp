<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.User,model.Mutter,java.util.List" %>
    <% String errorMsg = (String)request.getAttribute("errorMsg"); %>

    <% List<Mutter> searchList = (List<Mutter>)session.getAttribute("searchList");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>つぶやき検索</h1>
	<form action="/docoTsubu_DB/MutterSearch"method="post">
	<input type="text"name="text">
	<input type="submit"value="検索">


</form>
<!-- 入力値異常を受け取ったらエラーメッセージを表示 -->
	<% if(errorMsg != null){ %>
	<p style="color:red;">
	 <%= errorMsg %></p>
	<% } %>
	
<% for(Mutter mutter : searchList){ %>
	 <p><%=mutter.getUserName() %>：<%= mutter.getText()%></p>
	<%} %>



</body>
</html>