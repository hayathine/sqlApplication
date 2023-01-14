<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.* , java.util.*" %>
	<% 
	int MAX_ORDER_NUM = SettingApp.getMaxOrderNum();	
	CartBean cartBean = (CartBean)session.getAttribute("cart");
	%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品注文画面</title>
</head>
<body>
	<!-- 注文中の商品の表示 -->
	<h2>商品注文</h2>
	<form action="CartController" method="GET">
		<p>商品：<%= cartBean.getName() %></p>
		<p>価格：<%= cartBean.getPrice() %>円</p>
		<p>数量:<input type="number" min="1" max="<%= MAX_ORDER_NUM %>" name="num" value="1" ></p>
		<input type="submit" value="注文">
	</form>
	<p><a href="http://localhost:8080/SqlApplication/MenuController?action=back" >戻る</a></p>
</body>
</html>