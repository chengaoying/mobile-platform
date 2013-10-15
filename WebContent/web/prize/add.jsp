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
<script src="<%=base %>/js/client_validate.js"></script>
<script language="JavaScript">

function checkreg(field) {
	if(field.name.value==""){
		window.alert("名称不能为空");
		field.name.focus();		
		return (false);
	}
	if(field.price.value==""){
		window.alert("价格不能为空");
		field.price.focus();		
		return (false);
	}else{
		var reg=/^[0-9]*$/;
		if(!reg.test(field.price.value)){
			window.alert("价格必须为数字");
			field.price.focus();		
			return (false);
		}
	}
	if(field.productid.value=="请选择奖品所属游戏"){
		window.alert("请选择奖品所属游戏");
		field.productid.focus();		
		return (false);
	}
	if(field.activityid.value=="请选择奖品活动期数"){
		window.alert("请选择奖品活动期数");
		field.activityid.focus();		
		return (false);
	}
	if(field.file.value==""){
		window.alert("奖品图片不能为空");
		field.file.focus();		
		return (false);
	}
}

var xmlHttp;

function createXMLHttpRequest() {
	//表示当前浏览器不是ie,如ns,firefox
	if(window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function validate(field) {
	//alert(document.getElementById("userId").value);
	//alert(trim(field.value).length);
	if (trim(field.value).length != 0) {
		//创建Ajax核心对象XMLHttpRequest
		createXMLHttpRequest();
		
		var url = "<%=base %>/prize/validate?name=" + trim(field.value);
		
		//设置请求方式为GET，设置请求的URL，设置为异步提交
		xmlHttp.open("GET", url, true);
		
		//将方法地址复制给onreadystatechange属性
		xmlHttp.onreadystatechange=callback;
		
		//将设置信息发送到Ajax引擎
		xmlHttp.send(null);
	} else {
		document.getElementById("spanUserId").innerHTML = "";
	}	
}

function callback() {
	//alert(xmlHttp.readyState);
	//Ajax引擎状态为成功
	if (xmlHttp.readyState == 4) {
		//HTTP协议状态为成功
		if (xmlHttp.status == 200) {
			if (trim(xmlHttp.responseText) != "") {
				//alert(xmlHttp.responseText);
				document.getElementById("spanUserId").innerHTML = "<font color='red'>" + xmlHttp.responseText + "</font>";
				document.getElementById("name").focus();	
			}else {
				document.getElementById("spanUserId").innerHTML = "";
			}
		}else {
			alert("请求失败，错误码=" + xmlHttp.status);
		}
	}
}

</script>
<body>
<h3>添加奖品</h3>
<form id="form1" name="addPrize" action="<%=base %>/prize/add" method="post" enctype="multipart/form-data" onsubmit="return checkreg(this);">
	奖品名称<font  style="color: red">*</font>：<input type="text" id="name" name="name" onblur="validate(this)"></input><span id="spanUserId"></span> <br>
	奖品金额<font  style="color: red">*</font>：<input type="text" id="price"  name="price"></input> <br>
	所属游戏<font  style="color: red">*</font>：<select id="productid"  name="productid">
				<option selected="selected">请选择奖品所属游戏</option>
				<c:forEach items="${products}" var="product">
					<option value="${product.productid }">${product.productname }</option>
				</c:forEach>
			  </select> <br>
	活动期数<font  style="color: red">*</font>：<select id="activityid" name="activityid">
				<option selected="selected">请选择奖品活动期数</option>
				<c:forEach items="${activityPrizes}" var="activityPrize">
					<option value="${activityPrize.activityid }">第${activityPrize.activityid }期</option>
				</c:forEach>
			  </select> <br>
	奖品图片<font  style="color: red">*</font>：<input type="file" id="file" name="file"></input> <br>
	<!-- <input type="submit" value="提交" onclick="javascript:submit();"></input> -->
	<%-- <a href="javascript:submit();"><img src="<%=base %>/image/submit.jpg" style="position: absolute;"></a> --%>
	<input name="button" id="button" value="确认" type="submit">
</form>

</body>
