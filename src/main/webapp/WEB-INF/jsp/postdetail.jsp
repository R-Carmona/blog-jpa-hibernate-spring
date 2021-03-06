<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>HbnPost</title>

<!-- Bootstrap core CSS -->
<link href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"
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
								<jsp:param name="usuario"
									value="${sessionScope.userLoggedIn.nombre}" />
							</jsp:include>

						</c:when>
						<c:otherwise>
							<jsp:include page="includes/menu.jsp">
								<jsp:param name="otro" value="otro" />
							</jsp:include>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
			<h3 class="text-muted">HbnPost</h3>
		</div>

		<div class="row">
			<div class="col-md-12 col-lg-12">
				<h1>${post.titulo}</h1>
				<div>
					<div class="pull-right" style="padding: 10px 0 0 5px;">${post.autor.nombre}</div>
					<img alt="User Pic"
						src="http://i.pravatar.cc/50?u=${post.autor.email}"
						class="img-circle img-responsive pull-right">
					<p></p>
				</div>
				<div style="clear: both; margin-bottom: 10px;"></div>
				<p>${post.contenido}</p>
				<div>
					<span class="badge">Escrito el <fmt:formatDate
							pattern="dd/MM/yyyy" value="${post.fecha}" /> a las <fmt:formatDate
							pattern="HH:mm:ss" value="${post.fecha}" /></span>
				</div>
				<hr>
			</div>
		</div>

		
		<div class="row comment">
			<div class="col-md-12 col-lg-12">
				<div class="well">
					
					<h4>??Qu?? te ha parecido este art??culo?</h4>
					<c:choose>
					<c:when test="${not empty sessionScope.userLoggedIn}">					
						<form:form method="POST" modelAttribute="commentForm" id="form-comment"  action="/submit/newComment" role="form" >
							<form:input type="hidden" id="post_id" name="post_id" path="post_id" value="${post.idPost}" />
							<div class="input-group">					
							<form:input type="text" class="form-control input-sm chat-input"
								placeholder="Escribe tu comentario aqu??" path="contenido" /> 
							<span class="input-group-btn" id="comment-button"> 
								<a href="#" class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-comment"></span> Comentar
								</a>
							</span>
							</div>
						</form:form>
					</c:when>
					<c:otherwise>
						<h5>Necesita iniciar sesi??n para poder comentar este art??culo</h5>
					</c:otherwise>
					</c:choose>
					<hr data-brackets-id="12673">
					<ul data-brackets-id="12674" id="sortable"
						class="list-unstyled ui-sortable">						
						<c:if test="${not empty post.comentarios }">
							<c:forEach items="${post.comentarios}" var="comment">
							<li class="ui-state-default">
								<strong class="pull-left primary-font">${comment.usuario.nombre}</strong>
								<small class="pull-right text-muted"> 
									<span class="glyphicon glyphicon-time"></span>
									<fmt:formatDate pattern="dd/MM/yyyy" value="${comment.fecha}" /> a las 
									<fmt:formatDate pattern="H:m:s" value="${comment.fecha}" />
								</small>
								<br/>
								${comment.texto}
								<br/>
							</li>						
							</c:forEach>
						</c:if>
					</ul>
				</div>
			</div>
		</div>

			<footer class="footer"> </footer>

		</div>
		<!-- /container -->
		<script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
		<script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
		<script>
		$(document).ready(function() {
		    $("#comment-button").click(function() {
				$("#form-comment").submit();
		    });
		});
		
		</script>
</body>
</html>
