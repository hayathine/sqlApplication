<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.* , java.util.*" %>
	<% 
	ItemListBean itemList = (ItemListBean)request.getAttribute("itemList");
	CartBean 	 cartBean = (CartBean)session.getAttribute("cart");
	%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧画面</title>
</head>
<body>
	<h2>商品一覧</h2>
	<%if(!itemList.getItemList().isEmpty()){ %>
		<%for(int i = 0 ; i < itemList.getItemList().size() ; i++){ %>
		<!-- 商品一覧テーブルの作成 -->
		<table>
		<tr><th>商品コード</th><th>商品名</th><th>価格</th></tr>
		<tr>
			<td><%= itemList.getItemList().get(i).getCode() %></td>
			<td><%= itemList.getItemList().get(i).getName() %></td>
			<td><%= itemList.getItemList().get(i).getPrice() %>円</td>
			<!-- 商品選択ボタンから注文ページへ遷移 -->
			<td><form action="IndexController" method="POST"><button type="submit" name="orderCode" value="<%=i+1 %>">商品選択</button></form></td>
		</tr>
		</table>
	<% }} %>
</body>
</html>