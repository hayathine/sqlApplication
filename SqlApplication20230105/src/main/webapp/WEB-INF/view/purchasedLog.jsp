<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.* , java.util.*" %>
<% 
List<PurchasedLogBean> purchasedLogList = (List<PurchasedLogBean>)request.getAttribute("purchasedLogList");
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
<table>
<tr><th>注文番号</th><th>商品名</th><th>単価</th><th>数量</th><th>代金</th><th>注文日時</th></tr>
<%for(int i = 0 ; i < purchasedLogList.size() ; i++){ %>
<!-- 商品一覧テーブルの作成 -->
<tr>
	<td><%= purchasedLogList.get(i).getLogId() %></td>
	<td><%= purchasedLogList.get(i).getName() %></td>
	<td><%= purchasedLogList.get(i).getPrice() %>円</td>
	<td><%= purchasedLogList.get(i).getNum() %></td>
	<td><%= purchasedLogList.get(i).getPrice() *purchasedLogList.get(i).getNum()%>円</td>
	<td><%= purchasedLogList.get(i).getDate() %>
</tr>
<% } %>
</table>
<!-- 注文詳細照会画面へ -->
<a href="MenuController">戻る</a>
</body>
</html>