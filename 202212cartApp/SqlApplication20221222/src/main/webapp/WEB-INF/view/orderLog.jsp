<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.* , java.util.*" %>
<% 
OrderLogBeen orderLog = (OrderLogBeen)session.getAttribute("orderLog");
SqlQuery sqlQuery = new SqlQuery();
%>
<!-- 注文履歴の表示 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文履歴画面</title>
</head>
<body>
<h2>注文履歴画面</h2>
<p>注文番号:<%=orderLog.getLogId() %></p>
<p>商品:<%=orderLog.getName() %></p>
<p>単価:<%= orderLog.getPrice() %>円</p>
<p>数量:<%= orderLog.getNum() %></p>
<p>代金:<%= orderLog.getPrice()*orderLog.getNum() %>円</p>
<!-- 注文詳細照会画面へ -->
<a href="MenuController">戻る</a>
</body>
</html>