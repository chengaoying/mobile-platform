<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加奖品</title>
</head>
<%
	String base = request.getContextPath();
%>
<body>
<h3>添加奖品</h3>
<form name="addPrize" action="<%=base %>/prize/add" method="post" enctype="multipart/form-data">
	奖品名称：<input type="text" name="name"></input> <br>
	奖品金额：<input type="text" name="price"></input> <br>
	所属游戏：<select name="productid">
				<option selected="selected">请选择奖品所属游戏</option>
				<c:forEach items="${products}" var="product">
					<option value="${product.productid }">${product.productname }</option>
				</c:forEach>
			  </select> <br>
	活动期数：<select name="activityid">
				<option selected="selected">请选择奖品活动期数</option>
				<c:forEach items="${activityPrizes}" var="activityPrize">
					<option value="${activityPrize.activityid }">第${activityPrize.activityid }期</option>
				</c:forEach>
			  </select> <br>
	奖品图片：<input type="file" name="file"></input> <br>
	<input type="submit" value="提交"></input>
</form>

</body>
