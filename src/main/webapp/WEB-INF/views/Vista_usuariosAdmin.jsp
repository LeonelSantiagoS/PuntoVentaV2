<%--
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usuarios Admin</title>

<!-- Integramos jquery para que funcione el javascript -->
<script type="text/javascript" lenguaje="javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>

<!--  INTEGRAMOS PLUGIN DE dataTable -->
<!-- integrar plugin de DataTable -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
<script type="text/javascript" lenguaje="javascript" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>


<!--  INTEGRACION DE BOOSTRAP Diseño -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


<!--  Asi se manda a llamar mi archivo javaScript -->
<script lenguaje="javascript" type="text/javascript" src="<c:url value="/resources/javascript/usuariosAdmin.js"/>"></script>

<!-- esto es para utilizar iconos en el proyecto -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

</head>
<body>
	<center><h2>Vista Usuarios Admin</h2></center>
	

	<table class="table" id="id_tablaUsuariosAdmin">
		<thead>
			<tr>
				<th scope="col">Id</th>
				<th scope="col">NOMBRE COMPLETO</th>
				<th scope="col">EDAD</th>
				<th scope="col">DIRECCION</th>
				<th scope="col">ESTADO</th>
				<th scope="col">ROL</th>
				<th scope="col">ELIMINAR</th>
				<th scope="col">ACTUALIZAR</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>



	<!-- javascript - movimiento -->
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
	
</body>
</html>