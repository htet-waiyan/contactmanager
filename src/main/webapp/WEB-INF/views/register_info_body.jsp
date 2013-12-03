<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spr" %>
<script type="text/javascript">
	$(function(){
		$("#btnSubmit").click(function(){
			if($('#pwd').text()!=$('#repwd').text()){
				alert("Password didn't match");
				return false;
			}
		});
	})
</script>
<spr:form action="register" method="post" modelAttribute="contact">
<h3>Registeration : Personal Information</h3>
<ul>
	<li>
		<label>First Name</label>
		<spr:input name="firstName" id="fname" path="firstName"/>
		<spr:errors name="firstName" cssClass="error"></spr:errors>
	</li>
	
	<li>
		<label>Last Name</label>
		<spr:input path="lastName" id="lname" name="lastName"/>
		<spr:errors name="lastName" cssClass="error"></spr:errors>
	</li>
	
	<li>
		<label>Email</label>
		<spr:input path="email" id="email" name="email"/>
		<spr:errors name="email" cssClass="error"></spr:errors>
	</li>
	
	<li>
		<label>Password</label>
		<spr:password path="password" id="pwd" name="passwd"/>
		<spr:errors name="password" cssClass="error"></spr:errors>
	</li>
	
	<li>
		<label>Retype password</label>
		<input type="password" id="repwd" name="repassed"/>
	</li>
	
	<li>
		<input type="submit" id="btnSubmit" value="Continue"/>
	</li>
</ul>
</spr:form>