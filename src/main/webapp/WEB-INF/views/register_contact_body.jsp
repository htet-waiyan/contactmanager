<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spr" %>
<section class="row">
	<div class="col-md-8 col-md-offset-2 form border">
	<h4 class="center">Register : Contacts</h4>
	<form method="post" role="form" action="register/contacts" id="f1">
		<div class="col-md-6">	
			<div class="form-group first">
				<label>Phone</label>
				<span class="error"></span>
				<div class="row">
					<div class="col-sm-4">
						<input placeholder="number" type="text" class="form-control" name="txtNumber" id="number"/>
					</div>
					<div class="col-sm-5">
						<select id="type" name="ddlTypes" class="form-control">
						<c:forEach items="${types}" var="type">
							<option value="${type}">${type}</option>
						</c:forEach>
						</select>
					</div>
					<div class="col-sm-3">
						<a class="btn btn-default" id="btnAdd">
							<span class="glyphicon glyphicon-plus"></span>
						</a>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label>Facebook</label>
				<input type="text" class="form-control" name="txtFB" id="fb" placeholder="facebook (optional)"/>
			</div>
			
			<div class="form-group">
				<label>Twitter</label>
				<input type="text" class="form-control" name="txtTwitter" id="twitter" placeholder="twitter (optional)"/>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label>Website</label>
				<input type="text" class="form-control" name="txtWeb" id="web" placeholder="website (optional)"/>
			</div>
			
			<div class="form-group">
				<label>Address</label>
				<textarea id="addr" placeholder="(optinal)" class="form-control" name="addrArea" rows="5"></textarea>
			</div>
			
			<div class="form-group pull-right">
				<button id="btnSave" class="btn btn-primary btn-large">Save</button>
			</div>
		</div>
		</form>
	</div>
</section>
<script type="text/javascript" src="resources/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#btnAdd").click(function(){
			var $number=$("#number");
			var reg=new RegExp("[0-9]+");
			
			if($number.val()==""){
				console.log("not specify");
				return true;
			}
			else if(!reg.test($number.val())){
				var errorText="Number must contain only digits";
				console.log("invalid");
				return false;
			}
			else{
				console.log("valid");
				var $noText=$("#number").val();
				
				var $noWrapper=$("div");
				var $selType=$("#type").val();
				
				var $no=$("span").addClass("number").text($noText);
				$noWrapper.append($no);
				
				/*var $type=$("span").addClass("number").text($selType);
				$noWrapper.append($type);
				
				var $icon=$("span").addClass("glyphicon glyphicon-remove");
				$noWrapper.append($icon);*/
				
				
				$("div.first").append($noWrapper);
				
				return true;
			}
		})
	})
</script>
