<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spr" %>
<section class="row">
	<!-- <div class="col-md-3"></div>-->
	
	<!-- for carousel -->
	<div class="col-md-5 col-md-offset-1">
	<div class="carousel slide box-shadow" id="slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#slide" data-slide-to="0" class="active"></li>
			<li data-target="#slide" data-slide-to="1"></li>
			<li data-target="#slide" data-slide-to="2"></li>
		</ol>
		
		<div class="carousel-inner">
			<div class="item active">
				<img alt="" width="100%" src="resources/image/contact.jpg">
				<div class="carousel-caption">
					Save your friends, colleague and family members' numbers, website and email address so that
					you will never lose it.
				</div>
			</div>
			
			<div class="item">
				<img alt="" width="100%" src="resources/image/contact2.jpg">
				<div class="carousel-caption">
					Access your contacts at anytime, anywhere.
				</div>
			</div>
			
			<div class="item">
				<img alt="" width="100%" src="resources/image/contact3.jpg">
				<div class="carousel-caption">
					Looking up a yellow page was never easy. Precise search facility will list you exact contacts
					you're looking for. 
				</div>
			</div>
		</div>
		
		<a class="left carousel-control" href="#slide" data-slide="prev">
    		<span class="glyphicon glyphicon-chevron-left"></span>
  		</a>
  		<a class="right carousel-control" href="#slide" data-slide="next">
    		<span class="glyphicon glyphicon-chevron-right"></span>
  		</a>	
	</div>
	</div>
	<!-- for login form -->
	<div class="col-md-3 center-block border" style="background:#FFFFFF;">
		<spr:form action="j_spring_security_check" method="post" role="form">
			<h3>Login</h3>
			<div class="form-group">
				<c:if test="${param.login_error==1}">
					<div style="color:red">Invalid username or password</div>
				</c:if>
			</div>
			<div class="form-group">
				<label>Email</label>
				<span class="error">${email_error}</span>
				<input type="email" class="form-control" name="j_username" id="email"/>
			</div>
			<div class="form-group">
				<label>Password</label>
				<span class="error">${passwd_error}</span>
				<input type="password" class="form-control" name="j_password" id="passed"/>
			</div>
			<div class="form-group">
				<a href="" style="margin-bottom:12px;display:inline-block;">Forget Password?</a>
				<input type="submit" value="Login" class="form-control btn btn-success" id="btnLogin"/>
			</div>
			<div class="form-group">
				<a href="register" class="form-control btn btn-primary" id="btnRegister">Create a new account</a>
			</div>
		</spr:form>
	</div>
	
	<script type="text/javascript" src="resources/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<!-- <div class="col-md-3"></div>-->
	
	<script type="text/javascript">
		var btnRegister=document.querySelector("#btnRegister");
		
		btnRegister.addEventListener("click",function(){
			window.location.href="register";
		},false);
	</script>
</section>