<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.* , java.util.*" %>
<% 
CartBean cart = (CartBean)session.getAttribute("cart");
SqlQuery sqlQuery = new SqlQuery();
%>
<!-- 注文する商品の商品名、商品単価、代金を表示、確定ボタンを押すとORDERテーブルにinsert -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文確認画面</title>
</head>
<body>
<h2>注文確認画面</h2>
<p>商品名:<%=cart.getName() %></p>
<p>単価:<%= cart.getPrice() %></p>
<p>数量:<%= cart.getNum() %></p>
<p>代金:<%= cart.gettotalPrice() %></p>
<button><a href="/WEB-INF/view/order.jsp">戻る</a></button>
<form action="MainController" method="POST">
<input type="submit" name="decision" onklick="<%sqlQuery.insert(cart) %>" value="確定">
</form>
</body>
</html>