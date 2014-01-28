<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style="margin-top: 60px;"></div>
<!-- Action bar -->
<div class="row">
	<div class="col-md-1">
		
	</div>
	<div class="col-md-3">
		<!-- col-md-2 col-xs-6  -->
		<div class="btn-group">
			<button type="button" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown">
				<span class="glyphicon glyphicon-chevron-down"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="#" id="selectAll">All</a></li>
				<li><a href="#" id="selectNone">None</a></li>
			</ul>
		</div>
	</div>
	<div class="col-md-8">
		<!-- col-md-2 col-xs-6  -->
		<div class="btn-group">
			<a href="#" class="btn btn-del btn-default">
				<span class="glyphicon glyphicon-trash"></span>
			</a>
		</div>

		<div class="btn-group">
				<button type="button" class="btn btn-moveto btn-default dropdown-toggle"
					data-toggle="dropdown">
					<span class="glyphicon glyphicon-folder-close"></span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#" class="move">Other</a></li>
					<li><a href="#" class="move">Colleague</a></li>
					<li><a href="#" class="move">Family</a></li>
					<li><a href="#" class="move">Favorite</a></li>
					<li><a href="#" class="move">Friend</a></li>
				</ul>
		</div>
		<div class="btn-group">
			<button type="button" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown">
				<span class="glyphicon glyphicon-th-list"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="../contacts/all">All</a></li>
				<li><a href="../contacts/Colleague">Colleague</a></li>
				<li><a href="../contacts/Family">Family</a></li>
				<li><a href="../contacts/Favorite">Favorite</a></li>
				<li><a href="../contacts/Friend">Friend</a></li>
			</ul>
		</div>
	</div>
</div>


<!-- Contacts -->
<form action="../contacts/action" name="f1" method="post" id="contactDisplayForm">
<input type="hidden" class="actionLabel" name="actionHidden"/>
<input type="hidden" class="to" name="toGroup"/>
<c:forEach items="${contactList}" var="contact">
	<!-- 6 columns -->
	<div class="row row-gap" id="contacts">
		<div class="col-md-1">
			
		</div>
		
		<div class="col-md-1 ver-align">
			<input type="checkbox" value="${contact.contactID}" class="chk" name="check" />
		</div>
		
		<div class="col-md-8">
			<!-- <div class="col-md-2">
			<c:choose>
				<c:when test="${empty contact.thumbnail}">
					<img alt="" src="../resources/image/user_thumbnail.jpg">
				</c:when>
				<c:otherwise>
					<span> <img alt="" src="${contact.thumbnail}">
					</span>
				</c:otherwise>
			</c:choose>
			</div> -->
			
			
			<!-- Contact Name -->
			<div class="col-md-2 ver-align">
				<a href="detail?id=${contact.contactID}">${contact.firstName} ${contact.lastName}</a>
			</div>
			
			<!-- Email -->
			<div class="col-md-3 ver-align">
				<label>${contact.email}</label>
			</div>
			
			<!-- Number -->
			<div class="col-md-4 ver-align">
			<c:forEach items="${contact.contactNumberSets}" var="number"
				begin="0" end="0">
				<label>${number['number']} ${number.contactType['description']}</label>
			</c:forEach>
			</div>
			
			<!-- Icon -->
			<div class="col-md-1 ver-align">
				<span> ${contact.group.description} </span>
			</div>
		</div>
		<div class="col-md-2"></div>
	</div>
</c:forEach>
</form>

<script src="../resources/js/menu_action.js"></script>
<script>
	$(function(){

		$(".btn-del").click(function(){
			console.log("add delete action");
			$('#contactDisplayForm .actionLabel').val("delete");
			$('#contactDisplayForm').submit();
			//window.location="../contacts/trigger";
		});
		
		$(".move").click(function(){
			$('#contactDisplayForm .actionLabel').val("move");
			$('#contactDisplayForm .to').val($(this).text());
			
			$('#contactDisplayForm').submit();
		});
		
		$('.chk').click(function(){
			var isChecked=$(this).prop('checked');
			MenuAction.toggleSelect(isChecked);
		})
		
		$("#selectAll").click(function(){
			MenuAction.selectAll();
		});
		
		$("#selectNone").click(function(){
			MenuAction.selectNone();
		});
	});
</script>