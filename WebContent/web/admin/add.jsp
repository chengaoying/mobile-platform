<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ include file="taglib.jsp"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
	//System.out.println(basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>无标题文档</title>
<base href="<%=basePath %>">
<link href="css/admin_css.css" rel="stylesheet" type="text/css">
<script src="js/client_validate.js"></script>
<script language="JavaScript">
	function checkreg(field)
	{
		if (field.name.value=="") {
			window.alert("名称不能为空");
			field.name.focus();		
			 return (false);
			}
		if (field.password.value=="") {
			window.alert("密码不能为空");
			field.password.focus();		
			 return (false);
			}
		if (field.password2.value=="") {
			window.alert("确认密码不能为空");
			field.password2.focus();		
			 return (false);
			}
		if (field.password.value != field.password2.value) {
			alert("密码不一致,请重新输入!");
			field.password2.focus();
		}
		if (field.role.value=="请分配角色") {
			window.alert("请分配角色");
			field.role.focus();		
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
		//alert(field.value);
		if (trim(field.value).length != 0) {
			//创建Ajax核心对象XMLHttpRequest
			createXMLHttpRequest();
			
			var url = "<%=basePath %>/validate?name=" + trim(field.value);
			
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
					document.getElementById("spanUserId").innerHTML = "<font color='red'>" + xmlHttp.responseText + "</font>"
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
</head>
</head>
<body>
<table style="margin: 0pt auto; padding-top: 10px;" height="101" bgcolor="#cccccc" border="0" cellpadding="3" cellspacing="1" width="98%">
  <form name="myform" action="<%=basePath %>add?pageNo=1" method="post" onsubmit="return checkreg(this);">
    <tbody><tr>
      <td colspan="2" height="22" bgcolor="#879999"><font color="#FFFFFF">&nbsp;<strong>添加管理员&nbsp;</strong></font></td>
    </tr>
    <tr>
      <td height="24" align="right" bgcolor="#f8f8f8" valign="middle" width="13%">用户名:</td>
      <td height="24" align="left" bgcolor="#f8f8f8" valign="middle" width="87%"><input name="name" id="name" size="15" type="text" value="" onblur="validate(this)"/>
      <span id="spanUserId"></span>
      </td>
    </tr>

    <tr>
      <td height="24" align="right" bgcolor="#f8f8f8" valign="middle" width="13%">密码:</td>
      <td height="24" align="left" bgcolor="#f8f8f8" valign="middle" width="87%"><input name="passWord" id="password" size="15" value="" type="password"/></td>
    </tr>
    <tr>
      <td height="24" align="right" bgcolor="#f8f8f8" valign="middle" width="13%">重复密码:</td>
      <td height="24" align="left" bgcolor="#f8f8f8" valign="middle" width="87%"><input name="password2" id="password2" size="15" value="" type="password" /></td>
    </tr>
    <tr>
      <td height="24" align="right" bgcolor="#f8f8f8" valign="middle" width="13%">角色:</td>
      <td height="24" align="left" bgcolor="#f8f8f8" valign="middle" width="87%">
           <select id="role" name="role">
	            <option value="请分配角色" selected="selected">请分配角色</option>
	            <c:forEach items="${roleList}" var="r">
	            	<c:if test="${r.name eq 'role' }">
	            		<option value="${r.value }">${r.value }</option>
	            	</c:if>
	            </c:forEach>
	         </select>
      </td>
    </tr>
    <tr>
      <td height="24" bgcolor="#f8f8f8">&nbsp;</td>
      <td height="24" bgcolor="#f8f8f8">
       <input name="button" id="button" value="确认" type="submit">
       <input name="back" id="back" value="返回" onclick="javascript:window.history.go(-1);" type="button"></td>
    </tr>
  </form>
</tbody></table>


</body></html>