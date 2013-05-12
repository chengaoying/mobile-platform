<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ include file="taglib.jsp"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
 <base href="<%=basePath%>">
<link href="css/admin_css.css" rel="stylesheet" type="text/css">
<script language="JavaScript">
	function checkreg(field)
	{
		if (field.name.value=="") {
			window.alert("名称不能为空");
			field.name.focus();		
			 return (false);
			}
		if (field.passWord.value=="") {
			window.alert("密码不能为空");
			field.password.focus();		
			 return (false);
			}
		if (field.passWord2.value=="") {
			window.alert("确认密码不能为空");
			field.password2.focus();		
			 return (false);
			}
		if (field.passWord.value != field.passWord2.value) {
			alert("密码不一致,请重新输入!");
			field.passWord2.focus();
			return (false);
			}
	
		if (field.authority.value=="请给权限值") {
			window.alert("请给权限值");
			field.authority.focus();		
			 return (false);
			}
	}	
</script>	
</head>
<body>
<table style="margin: 0pt auto; padding-top: 10px;" height="101" bgcolor="#cccccc" border="0" cellpadding="3" cellspacing="1" width="98%">
  <form name="myform" action="<%=basePath%>update?pageNo=1" method="post" onsubmit="return checkreg(this);">
    <tbody><tr>
      <td colspan="2" height="24" bgcolor="#879999"><font color="#FFFFFF">&nbsp;<strong>管理员修改</strong></font></td>
    </tr>
    <tr>
      <td height="24" align="right" bgcolor="#f8f8f8" valign="middle" width="13%">用户名：</td>
	       <td height="24" align="left" bgcolor="#f8f8f8" valign="middle" width="87%">
	       <input name="id" value="${user2.id}" type="hidden" />
	       <input name="name" id="name" size="20" value="${user2.name }" type="text"></td>
    </tr>
	     <tr>
      <td height="24" align="right" bgcolor="#f8f8f8" valign="middle" width="13%">角色:</td>
      <td height="24" align="left" bgcolor="#f8f8f8" valign="middle" width="87%">
           <select id="role" name="role">
	            <option value="请分配角色" selected="selected">请分配角色</option>
	            <c:forEach items="${roleList}" var="r">
	            	<c:if test="${r.name eq 'role' }">
	            		<c:choose>
	            			<c:when test="${r.value eq user2.role }">
	            				<option selected="selected" value="${r.value }">${r.value }</option>
	            			</c:when>
	            			<c:otherwise>
	            				<option value="${r.value }">${r.value }</option>
	            			</c:otherwise>
	            		</c:choose>
	            	</c:if>
	            </c:forEach>
	         </select>
      </td>
    </tr><%--
    <tr>
      <td height="24" align="right" bgcolor="#f8f8f8" valign="middle" width="13%">权限:</td>
      <td height="24" align="left" bgcolor="#f8f8f8" valign="middle" width="87%">
           <select id="authority" name="authority">
	            <option value="请给权限值" >请给权限值</option>
	             <%
	           	   List<DataDictionary> authoritys = DataDictionaryManager.getInstance().getDictionaryByName("authority");
	               for(Iterator<DataDictionary> it = authoritys.iterator(); it.hasNext();){
						DataDictionary dataDictionary = it.next();	 
						String selectedStr= "";
						if (authority == Integer.parseInt(dataDictionary.getValue())) {
							selectedStr = "selected";
						}
	            %>
	            <option value="<%=dataDictionary.getValue() %>" <%=selectedStr %> ><%=dataDictionary.getValue() %></option>
	            <%} %>
	         </select>
      </td>
    </tr>
    --%><tr>
      <td height="24" align="right" bgcolor="#f8f8f8" valign="middle" width="13%">请输入密码：</td>
      <td height="24" align="left" bgcolor="#f8f8f8" valign="middle" width="87%">
      <input name="passWord" id="passWord" value="" size="20" type="password"></td>
    </tr>
    <tr>
      <td height="24" align="right" bgcolor="#f8f8f8" valign="middle" width="13%">请再次输入密码：</td>
      <td height="24" align="left" bgcolor="#f8f8f8" valign="middle" width="87%">
      <input name="passWord2" id="passWord2" value="" size="20" type="password">
      </td>
    </tr>
    <tr>
      <td height="24" bgcolor="#f8f8f8">&nbsp;</td>
        <td height="24" bgcolor="#f8f8f8">
       <input name="button" id="button" value="确认" type="submit">
       <input name="back" id="back" value="返回" onclick="javascript:window.history.go(-1);" type="button"></td>
    </tr>
  </form>
</tbody>
</table>
</body>
</html>
