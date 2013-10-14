var xmlHttp;

function createXMLHttpRequest() {
	//表示当前浏览器不是ie,如ns,firefox
	if (window.XMLHttpRequest) {
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
		xmlHttp.onreadystatechange = callback;

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
			} else {
				document.getElementById("spanUserId").innerHTML = "";
			}
		} else {
			alert("请求失败，错误码=" + xmlHttp.status);
		}
	}
}