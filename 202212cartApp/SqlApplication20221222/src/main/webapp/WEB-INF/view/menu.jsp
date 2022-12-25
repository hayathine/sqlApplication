<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.* , java.util.*" %>
	<% 
	int MAX_ORDER_NUM = SettingApp.getMaxOrderNum();	
	List<ItemBean> itemList = (List<ItemBean>)request.getAttribute("itemList");
	List<CartBean> cartList = (List<CartBean>)request.getAttribute("cartList");
	List<SearchBean> searchList = (List<SearchBean>)request.getAttribute("searchList");
	%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧画面</title>
</head>
<body>
	<h2>商品一覧</h2>
	<%if(!itemList.isEmpty()){ %>
		<table>
		<tr><th>商品コード</th><th>商品名</th><th>価格</th></tr>
		<%for(int i = 0 ; i < itemList.size() ; i++){ %>
		<!-- 商品一覧テーブルの作成 -->
		<tr>
			<td><%= itemList.get(i).getCode() %></td>
			<td><%= itemList.get(i).getName() %></td>
			<td><%= itemList.get(i).getPrice() %>円</td>
			<!-- 商品選択ボタンから注文ページへ遷移 -->
			<td><form action="OrderController" method="GET"><button type="submit" name="cartCode" value="<%=i+1 %>">新規注文</button></form></td>
		</tr>
	<% }} %>
		</table>
	<!-- 商品検索 -->
	<h2>商品検索</h2>
	<form action="MenuController" method="GET">
		<input type="text" name="inputName" >
		<input type="submit" name="searchExecute" value="検索">
	</form>
	<% if (searchList!=null){ %>
			<table>
		<tr><th>商品コード</th><th>商品名</th><th>価格</th></tr>
		<%for(int i = 0 ; i < searchList.size() ; i++){ %>
		<!-- 商品一覧テーブルの作成 -->
		<tr>
			<td><%= searchList.get(i).getItemCode() %></td>
			<td><%= searchList.get(i).getName() %></td>
			<td><%= searchList.get(i).getPrice() %>円</td>
			<!-- 商品選択ボタンから注文ページへ遷移 -->
			<td><form action="OrderController" method="GET"><button type="submit" name="cartCode" value="<%=i+1 %>">新規注文</button></form></td>
		</tr>
	<% }} %>
		</table>
	<!--  注文中の商品の表示 -->
	<% if (cartList != null){ %>	
	<h2>カート内商品</h2>
	<table>
	<tr><th>商品名</th><th>価格</th><th>注文数量</th><th>代金</th></tr>
	<% for (int i = 0 ; i < cartList.size() ; i++){ %>
	<tr>
		<td><%= cartList.get(i).getName() %></td>
		<td><%= cartList.get(i).getPrice() %>円</td>
		<td><%= cartList.get(i).getNum() %></td>
		<td><%= cartList.get(i).getNum() *  cartList.get(i).getPrice()%>円</td>
		<form action="MenuController" method="GET">
			<td>
				<select name="updateNum">
				<% for (int j = 1 ; j <= MAX_ORDER_NUM ;j++){ %>
					<option value = "<%= j %>"><%= j %>
				<%} %>					
				</select>
				<button type="submit" name="updateOrderId" value=<%=cartList.get(i).getCartId()%>>数量変更</button>
			<td>
				<button type="submit" name="deleteOrderId" value="<%=cartList.get(i).getCartId()%>">削除</button>
			</td>
		</form>
	</tr>
	<%}} %>
	</table>
	<form action="OrderController" method="GET" >
		<input type="submit" name="decide" value="注文確定">
	</form>
	<form action="OrderLogController" method="GET" >
		<input type="submit" name="selectOrderLog" value="注文履歴">
	</form>
</body>
</html>