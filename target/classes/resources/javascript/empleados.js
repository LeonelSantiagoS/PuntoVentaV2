$(document).ready(function(){ // Esta parte  es para realizar la carga de la pagina *DESDE EL INICIO*
	
//	alert("Yo aparezco al cargar a pagina desde el inicio"); // mensaje emergenete -> Es visible --- esto aveces se utitliza para mensajes para el usuario
//	console.log("Yo soy un mensaje desde la consola del navegador"); // Es para el programador imprimir mensajes de ayuda
	llenarTablaEmpleados();
	
	$('#btn_AbrirModal').click(function () {
    	$('#muestraModal').modal('show'); // muestra el modal
    });
});


function llenarTablaEmpleados(){

	console.log(" Estoy dentro de la function llenar tabla");
	
//	debugger; // esto lo que hace es ir saltando en el codigo linea x linea
	var table = $('#id_tablaEmpleados').DataTable();
	table.destroy(); // Destruir la informacion que ya contiene la tabla
	
	$.ajax({// Ajax = nos ayuda a realizar el consumo de nuestros servicios que se encuentran en el controlador / Hacer el enlace del FrontEnd - BackEnd
		type: "GET",
		url: '/PuntoVentaV2/misEmpleados/getMisEmpleados',
		dataType: "json",
		success: function(response){  // response =  listUsuarios que vienen desde la DB, code=200 y mensaje="......."
			
//			console.log(response);
			console.log(response.content);	
			
			$("#id_tablaEmpleados").DataTable({
				data: response.content,
				resposive: true,
				columns:[
					{
						data: "idEmpleado",
						"searchable" : false, // no se puede filtrar por el ide xq esta en false
						"visible": false,	// Este columna no es visible					
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
							a = `<a href="#" id="eliminar_empleado" class="btn btn-danger btn-remove" value="${row.idUser}"><i class="fas fa-trash-alt"></i></a>`;
							
							return a;
						}						
					},
					{
						className: "text-center",
						"orderable" : false,
						data: function(row, type, set){
							var a;
							a = `<a href="#" id="editar_empleado" class="btn btn-success" value="${row.idUser}"><i class="fa fa-edit"></i></a>`;
							
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

//Function para registrar informacion del usuario cuando se de clic en el boton de guardar
$(document).on("click", "#btn_guardar", function(e) {
	e.preventDefault();

//	alert("Soy el botont guardar");

//	debugger; // mostrarte como se ejecuta el codigo linea x linea

	// las variables de nombreCompleto - edad- direccion - estado - rol -> como estan en el DTO <<<<<<-------- DTO
	var datosUsuario = {
		nombreCompleto : $('#nombre_completo_view').val(), // <-- se recoje la informacion ingresada en el campo de texto
		edad : $('#edad_view').val(),
		direccion : $('#direccion_view').val(),
		estado : $('#estado_view').val(),
		rol : $('#rol_view').val()
	}

	console.log(datosUsuario);

	// Ajax: Conexion del frontEnd conectarlo con el BackEnd
	$.ajax({
		type : "POST",
		url : "/PuntoVentaV2/usuariosAdminHibernate2/insertUsuarios2",
		data : JSON.stringify(datosUsuario), // -> es la informacion que se le manda al controller por ser una peticion de tipo POST
		contentType : "application/json",
		dataType : "json",
		success : function(response) {

			console.log(response);

			alert(response.message);
//			llenarTablaUsuariosAdmin(); // Esta es la function que hace el llenado de la tabla entonces hay que
										// llamarlo una vez eliminado el registro de la peticion sea correcta

			// Hacer el codigo para que el modal se oculte

		}
	});

});

