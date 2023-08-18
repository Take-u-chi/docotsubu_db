<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.User,model.Mutter,java.util.List" %>
    <% String errorMsg = (String)request.getAttribute("errorMsg"); %>
    <% String errorMsg1 = (String)request.getAttribute("errorMsg1"); %>
     <% String resultMsg = (String)request.getAttribute("resultMsg"); %>

    <% List<Mutter> searchList = (List<Mutter>)session.getAttribute("searchList");%>

<!-- つぶやき検索画面を出力するビュー -->

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
<table>
<tr><th>ID</th><th>名前</th><th>投稿</th></tr>
<% if(searchList != null){ %>
<% for (Mutter mutter : searchList){ %>
<tr><td><%= mutter.getId() %></td>
    <td><%= mutter.getUserName() %></td>
    <td><%= mutter.getText() %></tr>
    <br>
</table>
<%} %>
<%} %>

<form action="/docoTsubu_DB/DeleteMutter"method="post">
<input type="text"name="id" placeholder="削除するIDを入力"required>
<input type="submit"value="削除" onclick="return Delete_Dialog()">
</form>
<% if(errorMsg1 != null){ %>
	<p style="color:red;">
	 <%= errorMsg1 %></p>
	<% } %>
<% if(resultMsg != null){%>
<p style="color:red;"><%= resultMsg %>
<%} %>

<p><a href="/docoTsubu_DB/Main">戻る</a></p>

</body>
<script type="text/javascript">
	function Delete_Dialog(){
		var res = confirm("選択したデータを削除します。よろしいですか?");
		if(res){
			return true;
		} else {
			return false;
		};
	};
</script>
</html>