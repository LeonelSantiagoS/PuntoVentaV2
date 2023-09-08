$(document).ready(function(){ // Esta parte  es para realizar la carga de la pagina *DESDE EL INICIO*
	
//	alert("Yo aparezco al cargar a pagina desde el inicio"); // mensaje emergenete -> Es visible --- esto aveces se utitliza para mensajes para el usuario
//	console.log("Yo soy un mensaje desde la consola del navegador"); // Es para el programador imprimir mensajes de ayuda
	llenarTablaEmpleados();
	
	$('#btn_AbrirModal').click(function () {
    	$('#modalEmpleados').modal('show'); // muestra el modal
    });
});


function llenarTablaEmpleados(){

	console.log(" Estoy dentro de la function llenar tabla");
	
//	debugger; // esto lo que hace es ir saltando en el codigo linea x linea
	var table = $('#id_tablaEmpleados').DataTable();
	table.destroy();
	
	$.ajax({
		type: "GET",
		url: '/PuntoVentaV2/misEmpleados/getMisEmpleados',
		dataType: "json",
		success: function(response){ 
			
//			console.log(response);
			console.log(response.content);	
			
			$("#id_tablaEmpleados").DataTable({
				data: response.content,
				resposive: true,
				columns:[
					{
						data: "idEmpleado",
						"searchable" : false, 
						"visible": false,				
					},					
					{
						data: "nombreCompleto",
					},
					{
						data: "rfc",
					},
					{
						data: "curp",
					},
					{
						data: "edad",
					},
					{
						data: "sexo",
					},
					{
						data: "direccion",
					},
					{
						data: "nss",
					},
					{
						data: "telefono",
					},
					{
						data: "activo",
					},
					
					{
						className: "text-center", // centrar
						"orderable" : false, // no se puedan ordenas por los botones
						data: function(row, type, set){
							var a;
							a = `<a href="#" id="eliminar_empleado" class="btn btn-danger btn-remove" value="${row.idEmpleado}"><i class="fas fa-trash-alt"></i></a>`;
							
							return a;
						}						
					},
					{
						className: "text-center",
						"orderable" : false,
						data: function(row, type, set){
							var a;
							a = `<a href="#" id="editar_empleado" class="btn btn-success" value="${row.idEmpleado}"><i class="fa fa-edit"></i></a>`;
							
							return a;
						}						
					}
					],
					"language" : configuracionLenguaje_es
					 
				});
			},					
		});
}

// cambiar de idioma ingles - españo el datatble
var configuracionLenguaje_es = {
		sLengthMenu: "Mostrar _MENU_ registros por Página",
		sLoadingRecords : "Cargando...",
		sProcessing : "Procesando...",
		sInfoEmpty : "Registros: 0",
		sZeroRecords : "Ningún dato disponible en esta tabla",
		sEmptyTable : "Ningún dato disponible en esta tabla",
		sInfo : " Registros: _TOTAL_ ",
		sSearch : "Filtrar por: ",
		sInfoFiltered : "filtrados",
		sUrl : "",
		sInfoThousands : ",",
		oPaginate : {
			sFirst : "Primero",
			sLast : "Último",
			sNext : "Siguiente",
			sPrevious : "Anterior"
		}
};

//Function para registrar empleado
$(document).on("click", "#btn_guardar", function(e) {
	e.preventDefault();
//	debugger; // mostrarte como se ejecuta el codigo linea x linea

	var datosUsuario = {
		nombreCompleto : $('#nombre_completo_view').val(),
		rfc : $('#rfc_view').val(),
		curp : $('#curp_view').val(),
		edad : $('#edad_view').val(),
		sexo : $('#sexo_view').val(),
		direccion : $('#direccion_view').val(),
		nss : $('#nss_view').val(),
		telefono : $('#telefono_view').val(),
		activo : $('#activo_view').val()
	}
	console.log(datosUsuario);
	// Ajax: Conexion del frontEnd conectarlo con el BackEnd
	$.ajax({
		type : "POST",
		url : "/PuntoVentaV2/misEmpleados/insertMisEmpleados",
		data : JSON.stringify(datosUsuario),
		contentType : "application/json",
		dataType : "json",
		success : function(response) {

			console.log(response);

			alert(response.message);
			llenarTablaEmpleados();
			
			$('#nombre_completo_view').val("");
			$('#rfc_view').val("");
			$('#curp_view').val("");
			$('#edad_view').val("");
			$('#sexo_view').val("");
			$('#direccion_view').val("");
			$('#nss_view').val("");
			$('#telefono_view').val("");
			$('#activo_view').val("");
			 $('#modalEmpleados').modal('hide');
		}
	});

});

//funcion para eliminar empleado
$(document).on("click","#eliminar_empleado", function (e) {
	 e.preventDefault();
//	debugger; 

	var idEmpleado = {
			idEmpleado: $(this).attr("value")
	}
	
	console.log(idEmpleado);

	$.ajax({												 
		type: "POST",
		url: "/PuntoVentaV2/misEmpleados/eliminarMisEmpleados",
		data : JSON.stringify(idEmpleado),
		contentType: "application/json",
		dataType: "json", 
		success: function(response){
			console.log(response); 
			alert(response.message);
			llenarTablaEmpleados();
		}
	});
});

//Buscar empleado por Id
$(document).on("click","#editar_empleado",function(e){
	e.preventDefault();
	
//	debugger;
	var empleado = {
			idEmpleado: $(this).attr("value")
		}
	
	$.ajax({
		type: "POST",
		url: "/PuntoVentaV2/misEmpleados/getMisEmpleadosPorID",
		data : JSON.stringify(empleado),
		contentType: "application/json",
		dataType: "json",
		success: function(response){
			
			console.log(response);

	       $('#idEmpleado_actualizar').val(response.content.idEmpleado);  
	       $('#nombre_completo_actualizar').val(response.content.nombreCompleto);
	       $('#rfc_actualizar').val(response.content.rfc);
	       $('#curp_actualizar').val(response.content.curp);
	       $('#edad_actualizar').val(response.content.edad);
	      
	       var sexo = response.content.sexo;
	       if (sexo === "F") {
	         $('#sexo_actualizar').val("F");
	       } else if (sexo === "M") {
	         $('#sexo_actualizar').val("M");
	       }
	       
	       $('#direccion_actualizar').val(response.content.direccion);
	       $('#nss_actualizar').val(response.content.nss);
	       $('#telefono_actualizar').val(response.content.telefono);
	       
	       var activo = response.content.activo;
	       if (activo === 1) {
	         $('#activo_actualizar').val("1");
	       } else if (activo === 0) {
	         $('#activo_actualizar').val("0");
	       }
           	       
	       $('#modalActualizarEmpleado').modal('show');
		}
});

});

//Function para actualizar empleado
$(document).on("click","#BotonActualizarEmpleado", function (e) {
	 e.preventDefault();
	
//	debugger; // mostrarte como se ejecuta el codigo linea x linea
	   
	var datosEmpleado = {

			idEmpleado: $('#idEmpleado_actualizar').val(),
			  nombreCompleto: $('#nombre_completo_actualizar').val(),
			  rfc: $('#rfc_actualizar').val(),
			  curp: $('#curp_actualizar').val(),
			  edad: $('#edad_actualizar').val(),
			  sexo: $('#sexo_actualizar').val(),
			  direccion: $('#direccion_actualizar').val(),
			  nss: $('#nss_actualizar').val(),
			  telefono: $('#telefono_actualizar').val(),
			  activo: $('#activo_actualizar').val()
			
	}
	
	console.log(datosEmpleado);
	
	$.ajax({
		type: "POST",
		url: "/PuntoVentaV2/misEmpleados/actualizarMisEmpleados",
		data : JSON.stringify(datosEmpleado), 	
		contentType: "application/json",
		dataType: "json",
		success: function(response){
			
			console.log(response);
			alert(response.message);
			
			$('#modalActualizarEmpleado').modal('hide');
			
			llenarTablaEmpleados(); 
			
		}
	});
	
});

