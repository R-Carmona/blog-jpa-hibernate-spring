<%@ page language="java" contentType="text/html; charset=UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>HbnPost</title>

<!-- Bootstrap core CSS -->
<link href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"
	rel="stylesheet">


<!-- Custom styles for this template -->

<link href="<c:url value="/assets/css/jumbotron-narrow.css" />"
	rel="stylesheet">


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div class="container">
		<div class="header clearfix">
			<nav>
				<ul class="nav nav-pills pull-right">
					<c:choose>
						<c:when test="${not empty sessionScope.userLoggedIn}">
							<jsp:include page="includes/menu_logged.jsp" flush="true">
								<jsp:param name="inicio" value="inicio" />
								<jsp:param name="usuario" value="${sessionScope.userLoggedIn.nombre}" />
							</jsp:include>

						</c:when>
						<c:otherwise>
							<jsp:include page="includes/menu.jsp">
								<jsp:param name="inicio" value="inicio" />
							</jsp:include>
						</c:otherwise>
					</c:choose>
	
				</ul>
			</nav>
			<h3 class="text-muted">HbnPost</h3>
		</div>

		<div class="jumbotron">
			<h1>HbnPost, un blog sobre Hibernate</h1>
			<p class="lead">Tutoriales, informaci??n y tips sobre la mejor
				tecnolog??a de persistencia para Java y .Net</p>
			<c:if test="${empty sessionScope.userLoggedIn}">	
			<p>
				<a class="btn btn-lg btn-success" href="/signup" role="button">Regr??strate</a>
			</p>
			</c:if>
		</div>
		
		<c:forEach items="${postList}" var="postItem">
		<div class="row">
			<div class="col-md-12 col-lg-12">
				<h1><a href="/post/${postItem.idPost}">${postItem.titulo}</a></h1>
				<div>
					<div class="pull-right" style="padding: 10px 0 0 5px;">${postItem.autor.nombre}</div>
					<img alt="User Pic" src="http://i.pravatar.cc/50?u=${postItem.autor.email}"
						class="img-circle img-responsive pull-right">
					<p></p>
				</div>
				<div style="clear: both; margin-bottom: 10px;"></div>
					<c:if test="${not empty postItem.url}">
						<p><a href="${postItem.url}">${postItem.url}</a></p>
					</c:if>
					<div class="dotdotdot">
						<p>${postItem.contenido}</p>				
					</div>
				<div>
					<span class="badge">Escrito el <fmt:formatDate pattern="dd/MM/yyyy" value="${postItem.fecha}" /> a las 
					<fmt:formatDate pattern="HH:mm:ss" value="${postItem.fecha}" /></span>
					<span class="label label-info">${(fn:length(postItem.comentarios) gt 0) ? fn:length(postItem.comentarios) : 'Sin '} 
						${(fn:length(postItem.comentarios) == 1) ? 'comentario' : 'comentarios'}</span>
				</div>
				<hr>
			</div>
		</div>
		</c:forEach>
		<footer class="footer"> </footer>

	</div>
	<!-- /container -->
	<script src="webjars/jquery/3.1.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
	<script src="/assets/js/jquery.dotdotdot.min.js"></script>
	<script>
	$(document).ready(function() {
	    $(".dotdotdot").dotdotdot({
	    	height: 150
	    });
	});
	
	
	</script>
</body>
</html>
