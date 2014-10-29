/** 
 * Autor: Diego Bocanegra
 * Fecha: 19 Fecbrero de 2009
 * Descripcion: Este archivo almacena funciones 
 *              para el manejo de eventos en los formularios
 */


/******************************************************
* Función que fija el foco en el campo que se pasa por 
* parametro
*******************************************************/
function fijarFoco( campo )
{
	var Campo = document.getElementById(campo);
	//Campo.focus();
}

/*******************************************
* Valida que la tecla presionada sea enter* 
********************************************/
function presionarEnter( e ) 
{
	var keycode;

	if ( window.event ) 
		keycode = window.event.keycode;

	else 
		if ( e ) 
		  keycode = e.which;
		else 
			return true;

	if ( keycode == 13 ) 
	{
	   return true;
	}
	else
	   return false;
}