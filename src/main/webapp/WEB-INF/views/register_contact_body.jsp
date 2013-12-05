<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spr" %>
<section class="row">
	<spr:form cssClass="form-horinzontal" role="form" action="dashboard" modelAttribute="contact">
		<div class="col-md-4">
			<h4>Register : Contacts</h4>
			
			<div class="form-group">
				<label>Phone</label>
				
				<div class="col-sm-5">
					<input placeholder="number" type="text" name="txtNumber" id="number"/>
				</div>
				
				<div class="col-sm-5">
					<select id="type" name="ddlTypes">
						<c:forEach items="${types}" var="type">
							<option value="${type}">${type}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label>Facebook</label>
				<input type="text" class="form-control" name="txtFB" id="fb" placeholder="facebook(optional)"/>
			</div>
			
			<div class="form-group">
				<label>Twitter</label>
				<input type="text" class="form-control" name="txtTwitter" id="twitter" placeholder="twitter(optionl)"/>
			</div>
		</div>
		
		<div class="col-md-4">
			<div class="form-group">
				<label>Website</label>
				<input type="text" name="txtWeb" id="web" placeholder="website(optional)"/>
			</div>
			
			<div class="form-group">
				<label>Address</label>
				<textarea id="addr" name="addrArea" rows="5"></textarea>
			</div>
			
			<div class="form-group">
				<button id="btnSave" class="btn btn-primary btn-large">Save</button>
			</div>
		</div>
	</spr:form>
</section>