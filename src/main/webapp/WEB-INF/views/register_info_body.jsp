<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spr" %>
<section class="row">
	<div class="col-md-3 border" style="background:#FFFFFF; margin-left:440px;">
		<spr:form action="register" method="post" modelAttribute="contact">
			<h4>Register : Personal Info</h4>
			<div class="form-group">
				<label>First Name</label><br/>
				<spr:errors path="firstName" cssClass="error"></spr:errors>
				<spr:input name="firstName" id="fname" path="firstName" cssClass="form-control"/>
			</div>
			
			<div class="form-group">
				<label>Last Name</label><br/>
				<spr:errors path="lastName" cssClass="error"></spr:errors>
				<spr:input path="lastName" id="lname" name="lastName" cssClass="form-control"/>
			</div>
			
			<div class="form-group">
				<label>Email</label><br/>
				<spr:errors path="email" cssClass="error"></spr:errors>
				<spr:input path="email" id="email" name="email" cssClass="form-control"/>
			</div>
			
			<div class="form-group">
				<label>Password</label><br/>
				<spr:errors path="password" cssClass="error"></spr:errors>
				<spr:password path="password" id="pwd" name="passwd" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<input type="submit" id="btnSubmit" class="form-control btn btn-primary" value="Continue"/>
			</div>
		</spr:form>
	</div>
</section>