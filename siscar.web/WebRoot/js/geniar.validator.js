<!--
var Geniar;

(function() { 

    if (Geniar == null) {
        Geniar = new Object();
	}
    if (Geniar.Validator == null) {
        Geniar.Validator = new Object();
	}

	Geniar.Validator.isCurrency = function (value) {
		return /^[-+]?\$?(0|[1-9]\d{0,2}(,?\d\d\d)*)((\.\d)(\d)*)?$/.test(value);	
	}
	
	Geniar.Validator.isValidNumber = function (value) {
		return /^[-+]?(0|[1-9]\d*)(\.\d+)?([eE][-+]?(0|[1-9]\d*))?$/.test(value);	
	 }

})();
function replace(str, original, replacement) {
       var result;
       result = "";
       while(str.indexOf(original) != -1) {
               if (str.indexOf(original) > 0){
                       result = result + str.substring(0, str.indexOf(original)) + replacement;
               }
               else{
                       result = result + replacement;
               }
               str = str.substring(str.indexOf(original) + original.length, str.length);
       }
       return result + str;
}


function mailText(obj, maxlength) {
	var text = obj.value;
	var variable = "";

	
		

	for (var i = 0; i < obj.value.length; i++) {
		caracter = obj.value.charAt(i);
		if (theSpecialCharacters(caracter)) {
			if(caracter!='!' && caracter!='ñ' && caracter!='?' && caracter!='#' && caracter!='$' && 
	   	   caracter!='%' && caracter!='^' && caracter!='&' && caracter!='*' && caracter!='(' &&  
                   caracter!=')' &&  caracter!='+' && caracter!='{' && 
	   	   caracter!='}' && caracter!='[' && caracter!=']' && caracter!='|' && caracter!='~' && 
	   	   caracter!='/' && caracter!=':' && caracter!=';' && caracter!=',' && caracter!='Ñ' && 
	   	   caracter!='<' && caracter!='>' && caracter!='\''&& caracter!='\\'&& caracter!='¿' && 
	   	   caracter!='¡' && caracter!='º' &&  caracter!='ª' && caracter!='á' && caracter!='é' && 
		   caracter!='í' && caracter!='ó' && caracter!='ú' && caracter!='Á' && caracter!='É' && 
		   caracter!='Í' && caracter!='Ó' && caracter!='Ú' &&  caracter!='Ü' && caracter!='ü'&& caracter!='¡' &&  caracter!='¿' ){
		   		if(obj.value.length<maxlength){
					variable += caracter;
				}
			}
			
		}
	}
	obj.value = variable;
}





function formatNmb(nNmb){
	var numero=nNmb.value;
	numero=replace(numero,".","");
	var sRes = "";
	for (var j, i = numero.length - 1, j = 0; i >= 0; i--, j++){
		sRes = numero.charAt(i) + ((j > 0) && (j % 3 == 0)? ".": "") + sRes;
	}
	if(numero<=0 && numero!=''){
		nNmb.value="";
	}
	else{
		nNmb.value=sRes;
	}
}
//-->