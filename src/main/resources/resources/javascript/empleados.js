$(document).ready(function(){ // Esta parte  es para realizar la carga de la pagina *DESDE EL INICIO*
	
//	alert("Yo aparezco al cargar a pagina desde el inicio"); // mensaje emergenete -> Es visible --- esto aveces se utitliza para mensajes para el usuario
//	console.log("Yo soy un mensaje desde la consola del navegador"); // Es para el programador imprimir mensajes de ayuda
	llenarTablaEmpleados();
//	
//	
//	
//	$('#btn_AbrirModal').click(function () {
//    	$('#muestraModal').modal('show'); // muestra el modal
//    });
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

// cambiar de idiom ingles - españo el datatble
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