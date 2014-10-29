
<!--
function decimales(objeto) {
	document.getElementById(objeto.id).onkeypress = function (e) {
		var keynum;
		var keychar;
		if (window.event) {		// IE	
			keynum = window.event.keyCode;
		} else {
			if (e.which) {	// firefox, opera y el resto
				keynum = e.which;
			}
		}
		if (keynum >= 32 && keynum <= 126) {	// es un char normal ??
			keychar = String.fromCharCode(keynum);
			var elTarget;
			if (window.event.srcElement) {	// IE
				elTarget = window.event.srcElement;
			} else {
				if (e.target) {	// firefox, opera y el resto
					elTarget = e.target;
				}
			}
			var newValue = elTarget.value + keychar;
				// mira si es un numero valido y retorna true o false
			return /^()?(\d+((\,)(\d{1,2})?)?)?$/.test(newValue);
		}
		return true;
	};
}
//-->

