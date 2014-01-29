<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spr" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<spr:form action="add" class="form-horizontal" modelAttribute="contact">
	<!--<c:choose>
		<c:when test="${empty contact.firstName}">
			<input type="hidden" value="add" name="param"/>
		</c:when>
		<c:otherwise>
			<input type="hidden" value="edit" name="param"/>
		</c:otherwise>
	</c:choose>-->
	
	<input type="hidden" value="${trigger}" name="param"/>
	
	<div style="margin-top:60px;"></div>
	<div class="row">
		<div class="col-md-2">																																																																																		
			<spr:hidden path="contactID"/>
		</div>
		<div class="col-md-8">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">First Name</label>
						<div class="col-md-8">
							<spr:errors path="firstName" class="error"></spr:errors>
							<spr:input path="firstName" class="form-control" placeholder="first name"/>
						</div>
					</div>
					
					<!-- nick name -->
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">Last Name</label>
						<div class="col-md-8">
							<spr:errors path="lastName" class="error"></spr:errors>
							<spr:input path="lastName" class="form-control" placeholder="last name"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4">Group</label>
						<div class="col-md-8">
							<spr:select path="group.description" class="form-control">
								<spr:options items="${groupNames}"/>
							</spr:select>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row separator"></div>
			
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">Email</label>
						<div class="col-md-8">
							<spr:errors path="email" class="error"></spr:errors>
							<spr:input class="form-control" path="email" placeholder="email"/>
						</div>
					</div>
					
					<div  id="newNumber">
					<div class="form-group">
						<label class="control-label col-md-4">Phone</label>
							<div class="col-md-8">
								<a href="#" class="btn btn-primary" id="addNumber">
								<span class="glyphicon glyphicon-plus-sign"></span> Add New (number)</a>
							</div>
					</div>
					
					<div class="form-group">
						<span class="num-error">${numError}</span>
					</div>
					<c:forEach items="${contact.contactNumberSets}" var="number">
					<div class="form-group">	
							<div class="col-md-4 delDiv">
								<a href="#" class="glyphicon glyphicon-remove-circle del pull-right"></a>
							</div>
							<div class="col-md-4" id="numToInsert">				
								<input value="${number.number}" class="form-control txtNumber text-box-size" name="number" placeholder="phone number"/>
							</div>
							<div class="col-md-4" id="typeToInsert">
								<select name="type" id="numberType" class="form-control">
									<c:forEach items="${contactType}" var="type">
										<c:choose>
										<c:when test="${type eq number.contactType['description']}">
											<option value="${type}" selected="selected">${type}</option>
										</c:when>
										<c:otherwise>
											<option value="${type}">${type}</option>
										</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
							</div>
					</c:forEach>
					
					
					</div>
					<div class="form-group">
						<label class="control-label col-md-4">Facebook</label>
						<div class="col-md-8">
							<spr:input class="form-control" placeholder="eg. facebook.com/someone" path="facebook"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4">Twitter</label>
						<div class="col-md-8">
							<spr:input path="twitter" class="form-control"  placeholder="eg. @someone"/>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">Website</label>
						<div class="col-md-8">
							<spr:input class="form-control" path="website" placeholder="eg. website.com"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4">Address</label>
						<div class="col-md-8">
							<spr:textarea placeholder=" (optional)" rows="6" class="form-control" path="address"></spr:textarea>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row separator"></div>
			
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label class="control-label col-md-2">Note</label>
						<div class="col-md-10">
							<spr:textarea placeholder="140 characters only (optional)" rows="4" class="form-control" path="note"></spr:textarea>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row separator"></div>
			
			<div class="row">
				<div class="col-md-12">
					<input type="submit" value="Save" class="btn btn-primary pull-right"/>
				</div>
			</div>
		</div>
		<div class="col-md-2"></div>
	</div>
	
	<div class="row">
		<div class=""></div>
	</div>
</spr:form>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/js/numberValidator.js"></script>
<script>
	$(function(){
		console.log("jquery inserted");
		$("#addNumber").click(function(){
			var types=["Mobile","Home","Office"];
			$newFormGroup=$('<div>').addClass("form-group");
			$newNumberDiv=$('<div>').addClass("col-md-4");
			$newDelDiv=$('<div>').addClass("col-md-4 delDiv");
			$newTypeDiv=$('<div>').addClass("col-md-4");
			
			$newNumberInput=$('<input>').attr('type','text').attr('name','number').attr('placeholder','phone number').addClass("form-control").addClass("text-box-size txtNumber");
			$newTypeSelect=$('<select>').attr("name","type").addClass("form-control");
			$newDel=$("<a>").addClass("glyphicon glyphicon-remove-circle del").addClass("pull-right").attr("href","#");
			
			$.each(types,function(index,value){
				var $option=$('<option>').attr("value",value).attr("text",value).attr("label",value);
				$newTypeSelect.append($option);
			});
			
			$newDelDiv.append($newDel);
			$newNumberDiv.append($newNumberInput);
			$newTypeDiv.append($newTypeSelect);
			$newFormGroup.append($newDelDiv).append($newNumberDiv).append($newTypeDiv);
			
			$newFormGroup.appendTo("#newNumber");
		});
		
		$(document).on("click",".del",function(){
			console.log("delete");
			$(this).parents(".form-group").remove();
		});
		
		Validator.validateNumbers('.txtNumber');
	})
</script>