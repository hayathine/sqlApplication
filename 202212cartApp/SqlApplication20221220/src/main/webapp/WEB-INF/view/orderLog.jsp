<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.* , java.util.*" %>
<% 
CartBean cart = (CartBean)session.getAttribute("cart");
SqlQuery sqlQuery = new SqlQuery();
%>
<!-- 注文履歴の表示 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文確認画面</title>
</head>
<body>
<h2>注文確認画面</h2>
<p>商品名:<%=cart.getName() %></p>
<p>単価:<%= cart.getPrice() %>円</p>
<p>数量:<%= cart.getNum() %></p>
<p>代金:<%= cart.getTotalPrice() %>円</p>
<button><a href="ConfirmController?action=decision">確定</a></button>
<form action="IndexController" method="POST">
	<input type="submit" name="back" value="戻る">
</form>
<!-- 注文詳細照会画面へ -->
</body>
</html>