<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	String base = request.getContextPath();
%>
<script src="<%=base %>/js/client_validate.js"></script>
<script language="JavaScript">

function checkreg(field) {
	if(field.productid.value==""){
		window.alert("没有可下载的游戏包");
		field.productid.focus();		
		return (false);
	}
}
</script>
<body>
	<a href="<%=base%>/product/add">添加游戏</a>
	<br>
	<a href="<%=base%>/prize/index">添加奖品</a>
	<br><br>

	<form action="<%=base%>/product/download" method="post" onsubmit="return checkreg(this);">
		<select id="productid" name="productid">
			<c:forEach items="${products}" var="product">
				<option value="${product.productid }">${product.productname}</option>
			</c:forEach>
		</select>
		<input name="button" id="button" value="游戏包下载" type="submit">
	</form>

</body>
</html>