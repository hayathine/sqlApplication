<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.* , java.util.*"%>
<%
List<OrderDetailBean> orderDetailList = (List<OrderDetailBean>) request.getAttribute("orderDetailList");
int MAX_ORDER_NUM = SettingApp.getMaxOrderNum();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文明細画面</title>
</head>
<body>
	<h2>注文明細画面</h2>
	<h3><%=orderDetailList.get(0).getOrderDetailId()%></h3>
	<table>
		<tr>
			<th>商品名</th>
			<th>数量</th>
		</tr>
		<% if (orderDetailList != null) { %>
		<% for (int i = 0; i < orderDetailList.size(); i++) { %>
		<!-- 商品一覧テーブルの作成 -->
		<tr>
			<td><%=orderDetailList.get(i).getItemName()%></td>
			<td><%=orderDetailList.get(i).getNum()%>個</td>
			<form action="OrderDetailController" method="POST">
				<%if (orderDetailList.get(i).getCancel() == 0){ %>
					<td>
						<select name="updateNum">
							<% for (int j = 1; j <= MAX_ORDER_NUM; j++) { %>
								<option value="<%=j%>"><%=j%> 
							<% } %>
						</select>
						<button type="submit" name="updateOrderItem"
							value=<%=orderDetailList.get(i).getOrderDetailId() +","+orderDetailList.get(i).getItemCode()%>>数量変更</button>
					</td>
					<td>
						<button type="submit" name="cancelItem"
							value=<%=orderDetailList.get(i).getOrderDetailId() + ","+orderDetailList.get(i).getItemCode()%>>キャンセル</button>
					</td>
				<% }else{ %>
					<td>キャンセル済み</td>
				<%} %>
			</form>
		</tr>
		<% } } %>
	</table>
	<form action="OrderController" method="POST">
		<input type="submit" name="backOrderLog" value="戻る">
	</form>
</body>
</html>