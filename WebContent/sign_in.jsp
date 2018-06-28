<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign in</title>
</head>
<body>
	<div>
		<h2>Sign in</h2>
		<form action="user" method="post" onsubmit="return testAll()">
		<input type="hidden" name="flag" value="sign_in">
			<div>
				<input type="text" name="user_name" placeholder="用户名" onblur="testName(this.value)"/>
				<span id="tipName"></span>
			</div>
			<div>
				<input type="password" name="password" placeholder="密码" onblur="testPwd(this.value)"/>
				<span id="tipPwd"></span>
			</div>
			<div>
				<input type="text" name="captcha" placeholder="验证码"/>
			</div>
			<div>
				<input type="checkbox" name="remember">记住用户名(3天免验证)</input>
			</div>
			<div>
				<button type="submit">登陆</button>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var isTrueName = null;
		var isTruePwd = null;
		var xmlHttp = null;
		
		function testName(name) {
			var tipName = document.getElementById("tipName");
			if(window.ActiveXObject) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} else if(window.XMLHttpRequest) {
				xmlHttp = new XMLHttpRequest();
			}
			xmlHttp.open("GET","user?flag=testName&user_name="+name,true);
			xmlHttp.send(null);
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					var resultText = xmlHttp.responseText;
					if(resultText == "✅") {
						tipName.innerText = "✅";
						isTrueName = true;
						return true;
					} else if (resultText == "❌（用户名不存在）") {
						tipName.
						innerText = "❌（用户名不存在）";
						isTrueName = false;
						return false;
					} else {
						return false;
					}
				}
			}
		}
		
		function testPwd(password) {
			var tipPwd = document.getElementById("tipPwd");
			if(window.ActiveXObject) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} else if(window.XMLHttpRequest) {
				xmlHttp = new XMLHttpRequest();
			}
			xmlHttp.open("GET","user?flag=testPwd&password="+password,true);
			xmlHttp.send(null);
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					var resultText = xmlHttp.responseText;
					if(resultText == "✅") {
						tipPwd.innerText = "✅";
						isTruePwd = true;
						return true;
					} else if (resultText == "❌（密码错误）") {
						tipPwd.innerText = "❌（密码错误）";
						isTruePwd = false;
						return false;
					} else {
						return false;
					}
				}
			}
		}
		
		function testAll() {
			if(isTrueName && isTruePwd) {
				return true;
			} else {
				return false;
			}
		}
	</script>
</body>
</html>