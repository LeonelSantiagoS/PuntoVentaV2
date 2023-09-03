//Objetivo JavaScript = js = Es para movimiento, funcion=(clic boton, llenar de informacion una tabla, mostrar/ocultar componente boton, una tabla)

$(document).ready(function(){ // Esta parte  es para realizar la carga de la pagina *DESDE EL INICIO*
	
//	alert("Yo aparezco al cargar a pagina desde el inicio"); // mensaje emergenete -> Es visible --- esto aveces se utitliza para mensajes para el usuario
	console.log("Yo soy un mensaje desde la consola del navegador"); // Es para el programador imprimir mensajes de ayuda
	
	llenarTablaUsuariosAdmin(); // Esto es para que al iniciar la pagina la tabla se convierta en un dataTable()
	
	
	
//	$('#btn_AbrirModal').click(function () {
//    	$('#muestraModal').modal('show'); // muestra el modal
//    });
	
	
//	var tabla = $('#id_tablaUsuariosAdmin').DataTable(); // Esta linea convierte la tabla en -< DataTable()
});

//EN JAVASCRIPT
//function() => Lo correspondiente a clases java es un Metodo
//en ves de usar System.out.prin --> alert("mensaje"); --> ventana emergente / console.log("Mensaje")--> consola del navegador


//---------------    FUNCTION DE EJEMPLO BASICO  ----------------
//function llenarTablaUsuariosAdmin(){
//	 //var e = 0; //<- la forma de declarar variables en javascript
//	
//	var tabla = $('#id_tablaUsuariosAdmin').DataTable(); // esta linea lo convierte en dataTable()	
//}

//01.- Se va a consumir el servicios de -> getUsuariosAdmin2 del controlador -> UsuariosAdminController2
function llenarTablaUsuariosAdmin(){

	console.log(" Estoy dentro de la function llenar tabla");
	
//	debugger; // esto lo que hace es ir saltando en el codigo linea x linea
	
	var table = $('#id_tablaUsuariosAdmin').DataTable();
	table.destroy(); // Destruir la informacion que ya contiene la tabla
	
	$.ajax({// Ajax = nos ayuda a realizar el consumo de nuestros servicios que se encuentran en el controlador / Hacer el enlace del FrontEnd - BackEnd
		type: "GET",
		url: '/PuntoVentaV2/usuariosAdminHibernate2/getUsuariosAdmin2',
		dataType: "json",
		success: function(response){  // response =  listUsuarios que vienen desde la DB, code=200 y mensaje="......."
			
//			console.log(response);
			console.log(response.content);
			
			
			$("#id_tablaUsuariosAdmin").DataTable({
				data: response.content,
				resposive: true,
				columns:[
					{
						data: "idUser",
						"searchable" : false, // no se puede filtrar por el ide xq esta en false
						"visible": false,	// Este columna no es visible					
					},					
					{
						data: "nombreCompleto",
					},
					{
						data: "edad",
					},
					{
						data: "direccion",
					},
					{
						data: "estado",
					},
					{
						data: "rol",
					},
					
					{
						className: "text-center", // centrar
						"orderable" : false, // no se puedan ordenas por los botones
						data: function(row, type, set){
							var a;
							a = `<a href="#" id="eliminar_usuario" class="btn btn-danger btn-remove" value="${row.idUser}"><i class="fas fa-trash-alt"></i></a>`;
							
							return a;
						}						
					},
					{
						className: "text-center",
						"orderable" : false,
						data: function(row, type, set){
							var a;
							a = `<a href="#" id="editar_usuario" class="btn btn-success" value="${row.idUser}"><i class="fa fa-edit"></i></a>`;
							
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

/*
1.- Click - dar clic en el boton
2.- Ochange - seleccionar datos en un ComboBox
*/

