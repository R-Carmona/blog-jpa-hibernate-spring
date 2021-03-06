<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link href="<c:url value="/assets/css/profile.css" />" rel="stylesheet">


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
					<!-- <li role="presentation" class="active"><a href="#">Home</a></li>
					<li role="presentation"><a href="#">About</a></li>
					<li role="presentation"><a href="/autores">Autores</a></li>-->
					<c:choose>
						<c:when test="${not empty sessionScope.userLoggedIn}">
							<jsp:include page="includes/menu_logged.jsp" flush="true">
								<jsp:param name="autores" value="autores" />
								<jsp:param name="usuario"
									value="${sessionScope.userLoggedIn.nombre}" />
							</jsp:include>

						</c:when>
						<c:otherwise>
							<jsp:include page="includes/menu.jsp">
								<jsp:param name="autores" value="autores" />
							</jsp:include>
						</c:otherwise>
					</c:choose>

				</ul>
			</nav>
			<h3 class="text-muted">HbnPost</h3>
		</div>

		<!-- Inicio del bucle -->
		<c:forEach items="${autores}" var="autor">
			<div class="row">
				<div
					class="col-xs-12 col-sm-12 col-md-10 col-lg-10 col-xs-offset-0 col-sm-offset-0 col-md-offset-1 col-lg-offset-1 toppad">


					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">${autor.nombre}</h3>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-3 col-lg-3 " align="center">
									<img alt="User Pic" src="http://i.pravatar.cc/300?u=${autor.email}"
										class="img-circle img-responsive">
								</div>

								<div class=" col-md-9 col-lg-9 ">
									<table class="table table-user-information">
										<tbody>
											<tr>
												<td>Fecha de alta:</td>
												<td><fmt:formatDate pattern="dd/MM/yyyy"
														value="${autor.fechaDeAlta}" /></td>
											</tr>

											<tr>
												<td>Ciudad</td>
												<td>${autor.ciudad}</td>
											</tr>
											<tr>
												<td>Email</td>
												<td><a href="mailto:${autor.email}">${autor.email}</a></td>
											</tr>
											<tr>
												<td>Actividad</td>
												<td>
													${fn:length(autor.posts)} ${(fn:length(autor.posts) == 1) ? 'art??culo ' : 'art??culos ' }y 
													${fn:length(autor.comentarios)} ${(fn:length(autor.comentarios) == 1) ? 'comentario' : 'comentarios' }
												
												</td>
											</tr>

										</tbody>
									</table>


								</div>
							</div>
						</div>
						<div class="panel-footer">
							<p></p>
						</div>

					</div>
				</div>
			</div>
		</c:forEach>
		<!-- Fin del bucle -->


		<footer class="footer">
			<p></p>
		</footer>


	</div>
	<!-- /container -->
	<script src="webjars/jquery/3.1.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

</body>
</html>
