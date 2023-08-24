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
<h2>投稿一覧</h2>
<a href="/docoTsubu_DB/MyPage">更新</a>



<table>
    <tr>
    <th>選択</th>
    <td>名前</td>
    <td>投稿</td>
    </tr>

    <form action="/docoTsubu_DB/MyPage"method="post">
    <tr>
    <% if(myMutterList != null){ %>
    <% for (Mutter mutter : myMutterList){ %>
    <th><input type="radio" name="id" value=<%=mutter.getId() %>>
    <input type="submit"value="削除"></th>

    <td><%= mutter.getUserName() %> </td>
    <td><%= mutter.getText() %></td>
    </tr>
<%} %>
<%} %>
   </form>
</table>
<% if(errorMsg != null){ %>
	<p style="color:red;">
	 <%= errorMsg %></p>
	<% }else if(resultMsg != null){ %>
	<p style="color:red;">
	 <%= resultMsg %></p>
	<%} %>

<p><a href="/docoTsubu_DB/Main">戻る</a></p>
</body>
</html>