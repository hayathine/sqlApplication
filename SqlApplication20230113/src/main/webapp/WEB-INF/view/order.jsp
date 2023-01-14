<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.* , java.util.*" %>
<% 
List<OrderBean> orderList = (List<OrderBean>)request.getAttribute("orderList");
int MAX_ORDER_NUM = SettingApp.getMaxOrderNum();
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
<tr><th>注文番号</th><th>代金</th><th>注文日時</th></tr>
<%for(int i = 0 ; i < orderList.size() ; i++){ %>
<!-- 商品一覧テーブルの作成 -->
<tr>
	<td><%= orderList.get(i).getLogId() %></td>
	<td><%= orderList.get(i).getPrice() %>円</td>
	<td><%= orderList.get(i).getDate() %></td>
	<form action="OrderController" method="POST">
	<% if(orderList.get(i).getCancel() == 1){%>
			<td>キャンセル済み</td>
	<%}else{ %>
		<td>
			<button type="submit" name="cancel" value="<%= orderList.get(i).getLogId() %>">キャンセル
		</td>
		<%} %>
	</form>
	<td><!-- 注文詳細照会画面へ -->
		<form action="OrderDetailController" method="GET">
			<button type="submit" name="orderDetailId" value=<%=orderList.get(i).getLogId() %>>明細画面へ</button>
		</form>
	</td>
</tr>
<% } %>
</table>

<a href="MenuController">戻る</a>
</body>
</html>