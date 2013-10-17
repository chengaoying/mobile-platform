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
	if(field.productname.value==""){
		window.alert("游戏名称不能为空");
		field.productname.focus();		
		return (false);
	}
	if(field.file.value==""){
		window.alert("请添加游戏包");
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
		
		var url = "<%=base %>/product/validate?productname=" + trim(field.value);
		
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
				document.getElementById("productname").focus();	
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
<h3>添加游戏</h3>
<form id="form1" name="addProduct" action="<%=base %>/product/add" enctype="multipart/form-data" method="post" onsubmit="return checkreg(this);">
	游戏名称<font  style="color: red">*</font>：<input type="text" id="productname" name="productname" onblur="validate(this)"></input><span id="spanUserId"></span> <br>
	游戏提供方<font  style="color: red">*</font>：<select id="provider"  name="provider">
				<option selected="selected">欧耶</option>
			  </select> <br>
	游戏状态<font  style="color: red">*</font>：<select id="state"  name="state">
				<option selected="selected" value="0">测试</option>
				<option value="1">上线</option>
				<option value="2">下线</option>
			  </select> <br>
	<!-- <input type="submit" value="提交" onclick="javascript:submit();"></input> -->
	<%-- <a href="javascript:submit();"><img src="<%=base %>/image/submit.jpg" style="position: absolute;"></a> --%>
	游戏包<font  style="color: red">*</font>：<input type="file" id="file" name="file"></input> <br>
	<input name="button" id="button" value="确认" type="submit">
</form>

</body>
