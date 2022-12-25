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
<title>商品注文画面</title>
</head>
<body>
	<h2>商品一覧</h2>

	<%if(!itemList.getItemList().isEmpty()){ %>
		<%for(ItemBean item:itemList.getItemList()){ %>
		<!-- 商品一覧テーブルの作成 -->
		<table>
		<tr><th>商品コード</th><th>商品名</th><th>価格</th></tr>
		<tr><td><%= item.getCode() %></td><td><%= item.getName() %></td><td><%= item.getPrice() %></td></tr>
		</table>
			
	<% }} %>
	<% if(cartBean != null){ %>
		<h2>注文中の商品</h2>
	<% } %>
	<h2>商品注文</h2>
	<form action="MainController" method="POST">
		<p>商品コード:<input type="number" name="code"></p>
		<p>数量:      <input type="number" min="0" name="num" value="0"></p>
		<input type="submit" value="注文">
	</form>
</body>
</html>