<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="navbar-header">
	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu-collapse">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand logo" href='/'>Contact Manager</a>
</div>
<div class="collapse navbar-collapse" id="menu-collapse">
<ul class="nav navbar-nav nav navbar-right">
	<li class="visible-xs visible-sm hidden-md hidden-lg"><a href="#">Account Setting</a></li>
    <li class="visible-xs visible-sm hidden-md hidden-lg"><a href="#">Edit Profile</a></li>
    <li class="visible-xs visible-sm hidden-md hidden-lg"><a href="#">Help</a></li>
    <li class="visible-xs visible-sm hidden-md hidden-lg"><a href="#">Logout</a></li>
	<li class="dropdown hidden-xs hidden-sm">
		<a href="#" class="dropdown-toggle" data-toggle="dropdown">
			<span class="glyphicon glyphicon-user"></span>
		</a>
		<ul class="dropdown-menu">
          <li><a href="#">Account Setting</a></li>
          <li><a href="#">Edit Profile</a></li>
          <li><a href="#">Help</a></li>
          <li class="divider"></li>
          <li><a href="../j_spring_security_logout">Logout</a></li>
        </ul>
	</li>
</ul>

<form class="navbar-form navbar-right">
	<div class="form-group">
		<a href="add" class="btn-primary btn btn-sm">Add New</a>
	</div>
</form>

<form action="../contacts/search" class="navbar-form navbar-left" method="get">
	<div class="form-group">
		<input type="text" class="form-control" name="txtSearch" placeholder="search" id="search"/>
	</div>
	<button  class="btn btn-primary">Search</button>
</form>

</div>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>