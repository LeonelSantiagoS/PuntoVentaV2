<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">

<%--
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
--%>
<title>Empleados</title>

<!-- Integramos jquery para que funcione el javascript -->
<script type="text/javascript" lenguaje="javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<!-- plugin de DataTable -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
<script type="text/javascript" lenguaje="javascript" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<!--  INTEGRACION DE BOOSTRAP -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<!--  Archivo JS  -->
<script lenguaje="javascript" type="text/javascript" src="<c:url value="/resources/javascript/empleados.js"/>"></script>
<!-- esto es para utilizar iconos en el proyecto -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

</head>
<body>
	<center>
		<h2>Vista Empleados</h2>
	</center>
	
	<br>
	<button type="button" class="btn btn-primary" id="btn_AbrirModal">
		<i class="fas fa-user-plus"></i> Agregar Empleado
	</button>
	
<!--  ESTE ES EL MODAL PARA AGREGAR UN NUEVO REGISTRO -->
<div class="modal fade" id=modalEmpleados data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"> <i class="fas fa-user"></i> AGREGAR NUEVO EMPLEADO</h5>
        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">NOMBRE COMPLETO *:</label>
            <input type="text" class="form-control" id="nombre_completo_view">
          </div>
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">RFC *:</label>
            <input type="text" class="form-control" id="rfc_view">
          </div>
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">CURP *:</label>
            <input type="text" class="form-control" id="curp_view">
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">EDAD *:</label>
            <input type="text" class="form-control" id="edad_view">
          </div>
          <div class="form-group">
			  <label for="activo_view" class="col-form-label">SEXO *:</label>
			  <select class="form-select" id="sexo_view">
			    <option value="M">Masculino</option>
			    <option value="F">Femenino</option>
			  </select>
			</div>
          <!--
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">SEXO *:</label>
            <input type="text" class="form-control" id="sexo_view">
          </div>
          -->
          <div class="form-group">
            <label for="message-text" class="col-form-label">DIRECCION *:</label>
            <input type="text" class="form-control" id="direccion_view">
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">NSS *:</label>
            <input type="text" class="form-control" id="nss_view">
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">TELEFONO *:</label>
            <input type="text" class="form-control" id="telefono_view">
          </div>
          <!--
          <div class="form-group">
            <label for="message-text" class="col-form-label">ACTIVO *:</label>
            <input type="text" class="form-control" id="activo_view">
          </div>
          -->
          <div class="form-group">
			  <label for="activo_view" class="col-form-label">USUARIO *:</label>
			  <select class="form-select" id="activo_view">
			    <option value="1">Activo</option>
			    <option value="0">Inactivo</option>
			  </select>
			</div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
        <button type="button" class="btn btn-primary" id="btn_guardar" >Guardar registro</button>
      </div>
    </div>
  </div>
</div>
	
	<br><br>
	
	<table class="table" id="id_tablaEmpleados">
		<thead>
			<tr>
				<th scope="col">ID_EMPLEADO</th>
				<th scope="col">NOMBRE COMPLETO</th>
				<th scope="col">RFC</th>
				<th scope="col">CURP</th>
				<th scope="col">EDAD</th>
				<th scope="col">SEXO</th>
				<th scope="col">DIRECCION</th>
				<th scope="col">NSS</th>
				<th scope="col">TELEFONO</th>
				<th scope="col">ACTIVO</th>
				<th scope="col">ELIMINAR</th>
				<th scope="col">ACTUALIZAR</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>

<!--  Modal 2 ESTE MODAL ES PARA ACTUALIZAR INFORMACION  -->
		<div class="modal fade" id="modalActualizarEmpleado" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"> <i class="fas fa-user"></i> ACTUALIZAR EMPLEADO</h5>  
      </div>
      <input type="hidden" id="idEmpleado_actualizar">
      
      <div class="modal-body">
        <div class="mb-3">
          <label for="nombre_completo_actualizar" class="form-label">NOMBRE COMPLETO *:</label> 
          <input type="text" class="form-control" id="nombre_completo_actualizar">
        </div>
        
        <div class="mb-3">
          <label for="rfc_actualizar" class="form-label">RFC *:</label> 
          <input type="text" class="form-control" id="rfc_actualizar">
        </div>

        <div class="mb-3">
          <label for="curp_actualizar" class="form-label">CURP *:</label> 
          <input type="text" class="form-control" id="curp_actualizar">
        </div>

        <div class="mb-3">
          <label for="edad_actualizar" class="form-label">EDAD *:</label>
          <input type="text" class="form-control" id="edad_actualizar">
        </div>

        <div class="mb-3">
          <label for="sexo_actualizar" class="form-label">SEXO *:</label>
          <select class="form-select" id="sexo_actualizar">
            <option value="M">Masculino</option>
            <option value="F">Femenino</option>
          </select>
        </div>

        <div class="mb-3">
          <label for="direccion_actualizar" class="form-label">DIRECCION *:</label>
          <input type="text" class="form-control" id="direccion_actualizar">
        </div>

        <div class="mb-3">
          <label for="nss_actualizar" class="form-label">NSS *:</label>
          <input type="text" class="form-control" id="nss_actualizar">
        </div>

        <div class="mb-3">
          <label for="telefono_actualizar" class="form-label">TELEFONO *:</label>
          <input type="text" class="form-control" id="telefono_actualizar">
        </div>

        <div class="mb-3">
          <label for="activo_actualizar" class="form-label">ACTIVO *:</label>
          <select class="form-select" id="activo_actualizar">
            <option value="1">Activo</option>
            <option value="0">Inactivo</option>
          </select>
        </div>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
        <button type="button" class="btn btn-primary" id="BotonActualizarEmpleado" onclick="click">Actualizar</button>
      </div>
    </div>
  </div>
</div>
		
	


<!-- javascript - movimiento -->
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>
</html>