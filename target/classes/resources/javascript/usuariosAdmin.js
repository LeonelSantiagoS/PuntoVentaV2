//Objetivo JavaScript = js = Es para movimiento, funcion=(clic boton, llenar de informacion una tabla, mostrar/ocultar componente boton, una tabla)

$(document).ready(function(){ // Esta parte  es para realizar la carga de la pagina *DESDE EL INICIO*
	
//	alert("Yo aparezco al cargar a pagina desde el inicio"); // mensaje emergenete -> Es visible --- esto aveces se utitliza para mensajes para el usuario
	console.log("Yo soy un mensaje desde la consola del navegador"); // Es para el programador imprimir mensajes de ayuda
	
	llenarTablaUsuariosAdmin(); // Esto es para que al iniciar la pagina la tabla se convierta en un dataTable()
	
	
	
	$('#btn_AbrirModal').click(function () {
		
		llenar_combo_lista_de_roles('#listaDeRoles'); // llama la function que hace la peticion de los roles
		llenar_combo_lista_de_estados('#listaDeEstados');// llama la function que hace la peticion de los estados
		
    	$('#muestraModal').modal('show'); // muestra el modal
    });
	
	
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

// Function para registrar informacion del usuario cuando se de clic en el boton
// de guardar
$(document).on("click", "#btn_guardar", function(e) {
	e.preventDefault();

//	alert("Soy el boton guardar");

//	debugger; // mostrarte como se ejecuta el codigo linea x linea

	// las variables de nombreCompleto - edad- direccion - estado - rol -> como estan en el DTO <<<<<<-------- DTO
	var datosUsuario = {
		nombreCompleto : $('#nombre_completo_view').val(), // <-- se recoje la informacion ingresada en el campo de texto
		edad : $('#edad_view').val(),
		direccion : $('#direccion_view').val(),
//		estado : $('#estado_view').val(), cajas de texto
//		rol : $('#rol_view').val()
		estado : $('#listaDeEstados').val(),
		rol : $('#listaDeRoles').val()
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
			llenarTablaUsuariosAdmin(); // Esta es la function que hace el llenado de la tabla entonces hay que
										// llamarlo una vez eliminado el registro de la peticion sea correcta
			
			// Borrar los datos de los campos
		    $('#nombre_completo_view').val('');
		    $('#edad_view').val('');
		    $('#direccion_view').val('');
		    $('#estado_view').val('');
		    $('#rol_view').val('');

		    // Ocultar el modal
		    $('#muestraModal').modal('hide');

		}
	});

});

/*
 1.- Click - dar clic en el boton 
 2.- Ochange - seleccionar datos en un ComboBox
 */

//Al momento de dar click sobre el icono se ejecuta esta funcion para ejecutar la peticion y se elimne el registro.
$(document).on("click","#eliminar_usuario", function (e) {
	 e.preventDefault();
	
//	 alert("Estoy desde la function eliminar");
	
//	debugger; // mostrarte como se ejecuta el codigo linea x linea	 

	var idUsuario = {
//						: -> id=1	 // -> $(this).attr("value") -> Es el valor que recojemos de la tabla o sea es el id de cada registro
		idUser: $(this).attr("value")// Asignado el id a la variable -> idUser que viene de la clase DTO
	}
	
	console.log(idUsuario);

	// Ajax: Conexion del frontEnd conectarlo con el BackEnd
	$.ajax({												 // Todo esto contiene el valor del id desde la tabla -> $(this).attr("value");
		type: "POST",
		url: "/PuntoVentaV2/usuariosAdminHibernate2/eliminarUsuario2",
		data : JSON.stringify(idUsuario), //-> es la informacion que se le manda al controller por ser una peticion de tipo POST	
		contentType: "application/json",
		dataType: "json", 
		success: function(response){

			console.log(response); 
			alert(response.message);
			llenarTablaUsuariosAdmin(); // Esta es la function que hace el llenado de la tabla entonces hay que llamarlo una vez eliminado el registro de la peticion ->eliminarUsuario
		}
	});
});



//select * from tabla where ID = idUser
//Esta parte es para consultar la informacion del usuario por su ID, de la fila que se selecciona dentro del dataTable esto para llenar los campos del modal presentar la inf.
$(document).on("click","#editar_usuario",function(e){
	e.preventDefault();
	
//	debugger;
	var idUsuario = {
		//			: -> id=1	 // -> $(this).attr("value") -> Es el valor que recojemos de la tabla o sea es el id de cada registro
			idUser: $(this).attr("value")// Asignado el id a la variable -> idUser que viene de la clase DTO
		}
	
	$.ajax({
		type: "POST",
		url: "/PuntoVentaV2/usuariosAdminHibernate2/getUsuariosPorId",
		data : JSON.stringify(idUsuario), //-> es la informacion que se le manda al controller por ser una peticion de tipo POST	
		contentType: "application/json",
		dataType: "json",
		success: function(response){
			
			console.log(response);
//			console.log(data.content.nombreCompleto);
			
//			llenar_combo_lista_de_roles('#listaDeRoles'); // llama la function que hace la peticion de los roles
//		    llenar_combo_lista_de_estados('#listaDeEstados');// llama la function que hace la peticion de los estados
			
			// Llama a las funciones para llenar los combobox
//            llenar_combo_lista_de_estados2('#estado_actualizar');
//            llenar_combo_lista_de_roles2('#rol_actualizar');
						
//			-->content: 
//			direccion: "CDMX"
//			edad: 28
//			estado: 4
//			idUser: 2
//			nombreCompleto: "Diego Hernandez Sanchez"
//			rol: 3
	       $('#idUser_actualizar').val(response.content.idUser);  //  
	       $('#nombre_completo_actualizar').val(response.content.nombreCompleto);
	       $('#edad_actualizar').val(response.content.edad);
	       $('#direccion_actualizar').val(response.content.direccion);
//	       $('#listaDeEstados').val(llenar_combo_lista_de_roles('#listaDeRoles'));
//	       $('#listaDeRoles').val(llenar_combo_lista_de_estados('#listaDeEstados'));
//	       $('#listaDeEstados').val(response.content.estado);
//	       $('#listaDeRoles').val(response.content.rol);
//	       $('#estado_actualizar').val(response.content.estado);
//	       $('#rol_actualizar').val(response.content.rol);
	       
	       // Llenar los nuevos combobox en el modal de actualización
           llenar_combo_lista_de_estados_actualizar('#listaDeEstados_actualizar');
           llenar_combo_lista_de_roles_actualizar('#listaDeRoles_actualizar');
	       
	       $('#modalActualizarUsuario').modal('show');// mostrar/presentar en la vista el modal
		}
});

});

//Function para actualizar informacion del usuario -> UPDATE  FROM SET ....... 
$(document).on("click","#BotonActualizarUsuario", function (e) {
	 e.preventDefault();
	
//	debugger; // mostrarte como se ejecuta el codigo linea x linea
	   
	var datosUsuario = {
			idUser : $('#idUser_actualizar').val(),
			nombreCompleto : $('#nombre_completo_actualizar').val(),
			edad : $('#edad_actualizar').val(),
			direccion : $('#direccion_actualizar').val(),
//			estado : $('#estado_actualizar').val(),
//			rol : $('#rol_actualizar').val()
			estado : $('#listaDeEstados_actualizar').val(),
			rol : $('#listaDeRoles_actualizar').val()
	}
	
	console.log(datosUsuario);

	// Ajax: Conexion del frontEnd conectarlo con el BackEnd
	
	$.ajax({
		type: "POST",
		url: "/PuntoVentaV2/usuariosAdminHibernate2/actualizarDatos2",
		data : JSON.stringify(datosUsuario), //-> es la informacion que se le manda al controller por ser una peticion de tipo POST	
		contentType: "application/json",
		dataType: "json",
		success: function(response){
			
			console.log(response);
			alert(response.message);
			
//			$('#modalActualizarUsuario').hide(); // ocultar el modal una vez que se actualize correctamente
			$('#modalActualizarUsuario').modal('hide');
			
			llenarTablaUsuariosAdmin(); // Esta es la function que hace el llenado de la tabla entonces hay que llamarlo una vez eliminado el registro de la peticion ->eliminarUsuario
			
		}
	});
	
});

//-- FUNCTIONES PARA LLENAR LOS COMBOS CON LOS CATALOGOS DE ESTADOS Y ROLES

//$.each -> esto equivalente a un for()  en JAVA

//hace el llenado de la lista de estados
function llenar_combo_lista_de_estados(combo) {
//	debugger
	$(combo).empty();
	    
		$.ajax({
	        type: "get",
	        url: "/PuntoVentaV2/catalogos/getEstados",
	        dataType: "json",
	        success: function (respose) {
	            console.log(respose);
	            $.each(respose, function (key, registro) {
	                $(combo).append('<option value=' + registro.idEstado + '>' + registro.nombre + '</option>');
	             });
	        },
	    });
		
	}




//Function para llenar el coboBox de la lista de roles

function llenar_combo_lista_de_roles(combo) {
//	debugger
	$(combo).empty();
	    $.ajax({
	        type: "get",
	        url: "/PuntoVentaV2/catalogos/getRoles",
	        dataType: "json",
	        success: function (data) {
	            console.log(data);
	            $.each(data, function (key, registro) {
	                $(combo).append('<option value=' + registro.idRol + '>' + registro.nombre + '</option>');
	             });
	        },
	    });
	}

// Function para llenar el combobox de la lista de estados en el modal de actualización
function llenar_combo_lista_de_estados_actualizar(combo) {
    $(combo).empty();
    $.ajax({
        type: "get",
        url: "/PuntoVentaV2/catalogos/getEstados",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $.each(data, function (key, registro) {
                $(combo).append('<option value=' + registro.idEstado + '>' + registro.nombre + '</option>');
            });
        },
    });
}

// Function para llenar el combobox de la lista de roles en el modal de actualización
function llenar_combo_lista_de_roles_actualizar(combo) {
    $(combo).empty();
    $.ajax({
        type: "get",
        url: "/PuntoVentaV2/catalogos/getRoles",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $.each(data, function (key, registro) {
                $(combo).append('<option value=' + registro.idRol + '>' + registro.nombre + '</option>');
            });
        },
    });
}


