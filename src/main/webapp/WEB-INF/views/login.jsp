<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<div class="col-md-3 center-block">
		<form action='<c:url value="/login"></c:url>' method="post" role="form">
			<h3>Login</h3>
			<div class="form-group">
				<label>Email</label>
				<input type="email" class="form-control" name="txtEmail" id="email"/>
			</div>
			<div class="form-group">
				<label>Password</label>
				<input type="password" class="form-control" name="txtPasswd" id="passed"/>
			</div>
			<div class="form-group">
				<a href="" style="margin-bottom:12px;display:inline-block;">Forget Password?</a>
				<input type="submit" value="Login" class="form-control btn btn-success" id="btnLogin"/>
			</div>
			<div class="form-group">
				<button class="form-control btn btn-primary" id="btnRegister">Create a new account</button>
			</div>
		</form>
	</div>
	
	<script type="text/javascript" src="resources/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<!-- <div class="col-md-3"></div>-->
</section>