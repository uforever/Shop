<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>
	<div>
		<h2>Register</h2>
		<form action="user" method="post" onsubmit="return validate()">
		<input type="hidden" name="flag" value="register">
			<div>
				<input type="text" name="user_name" placeholder="用户名" onblur="validateName(this.value)"/>
				<span id="tipName">4-16位(只能包含字母,数字,下划线)</span>
			</div>
			<div>
				<input type="password" name="password" placeholder="密码" onblur="validatePwd(this.value)"/>
				<span id="tipPwd">8-16位(至少包含一个1个数字,1个大写字母和1个小写字母)</span>
			</div>
			<div>
				<input type="password" name="password_again" placeholder="确认密码" onblur="validatePwdAgain(this.value)"/>
				<span id="tipPwdAgain">再次输入密码(至少包含一个1个大写字母,1个小写字母,1个数字)</span>
			</div>
			<div>
				<input type="text" name="email" placeholder="邮箱" onblur="validateEmail(this.value)"/>
				<span id="tipEmail">请输入可用的邮箱</span>
			</div>
			<div>
				<input type="text" name="telephone" placeholder="手机号" onblur="validateTphone(this.value)"/>
				<span id="tipTphone">请输入正确的手机号码</span>
			</div>
			<div>
				<button type="submit">注册</button>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var legalName = null;
		var legalPwd = null;
		var legalPwdAgain = null;
		var legalEmail = null;
		var legalTphone = null;
		var validateNameResult = null;
		
		function validateName(name) {
			var tipName = document.getElementById("tipName");
			var reg = /^[\w]{4,16}$/;
			if(reg.test(name)) {
				legalName = name;
				return sendAjax(name,tipName);
			} else {
				tipName.innerText = "❌（4-16位(只能包含字母,数字,下划线)）";
				validateNameResult = false;
				return false;
			}
		}

		var xmlHttp = null;
		
		function createXMLHttpRequest() {
			if(window.ActiveXObject) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} else if(window.XMLHttpRequest) {
				xmlHttp = new XMLHttpRequest();
			}
		}
		
		function sendAjax(name, tip) {
			createXMLHttpRequest();
			xmlHttp.open("GET","user?flag=validateName&user_name="+name,true);
			xmlHttp.send(null);
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					var resultText = xmlHttp.responseText;
					tip.innerText = resultText;
					if(resultText == "❌（用户名已被注册）") {
						validateNameResult = false;
						return false;
					} else if(resultText == "✅") {
						validateNameResult = true;
						return true;
					}
				}
			}
		}
		
		function validatePwd(pwd) {
			var tipPwd = document.getElementById("tipPwd");
			var reg = /^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])[\s\S]{8,16}$/;
			if(reg.test(pwd)) {
				legalPwd = pwd;
				tipPwd.innerText = "✅";
				return true;
			} else {
				tipPwd.innerText = "❌（8-16位(至少包含一个1个数字,1个大写字母和1个小写字母)）";
				return false;
			}
		}
		
		function validatePwdAgain(pwdagain) {
			var tipPwdAgain = document.getElementById("tipPwdAgain");
			if(pwdagain == legalPwd) {
				legalPwdAgain = pwdagain;
				tipPwdAgain.innerText = "✅";
				return true;
			} else {
				tipPwdAgain.innerText = "❌（两次输入的密码不一致）";
				return false;
			}
		}
		
		function validateEmail(email) {
			var tipEmail = document.getElementById("tipEmail");
			var reg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
			if(reg.test(email)) {
				tipEmail.innerText = "✅";
				legalEmail = email;
				return true;
			} else {
				tipEmail.innerText = "❌（邮箱格式不正确）";
				return false;
			}
		}
		
		function validateTphone(tphone) {
			var tipTphone = document.getElementById("tipTphone");
			var reg = /^1[3|4|5|7|8][0-9]{9}$/;
			if(reg.test(tphone)) {
				tipTphone.innerText = "✅";
				legalTphone = tphone;
				return true;
			} else {
				tipTphone.innerText = "❌（手机号输入有误）";
				return false;
			}
		}
		
		function validate() {
			var x1 = validateNameResult;
			var x2 = validatePwd(legalPwd);
			var x3 = validatePwdAgain(legalPwdAgain);
			var x4 = validateEmail(legalEmail);
			var x5 = validateTphone(legalTphone);
			if(x1 && x2 && x3 && x4 && x5) {
				return true;
			} else {
				return false;
			}
		}
	</script>
</body>
</html>