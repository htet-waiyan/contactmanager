<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section class="slide">
	<script type="text/javascript">
		$(function(){
			$("#btnRegister").click(function(){
				location.href="register";
			})
		})
	</script>
	</section>
	<section class="login">
		<form action='<c:url value="/login"></c:url>' method="post">
			<h3>Login</h3>
			<ul class="signin">
				<li>
					<label>Email</label>
					<input type="email" name="txtEmail" id="email"/>
				</li>
				<li>
					<label>Password</label>
					<input type="password" name="txtPasswd" id="passed"/>
				</li>
				<li>
					<a href="">Forget Password</a>
					<input type="submit" value="Login" id="btnLogin"/>
				</li>
			</ul>
		</form>
		<span>
			<button id="btnRegister">Create a new account</button>
		</span>
	</section>
	<div style="clear:both;"></div>