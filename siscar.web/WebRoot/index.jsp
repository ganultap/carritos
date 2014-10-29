<html>
	<head>
		<title>SISCAR</title>
		<script language="javascript">
<!--
function fsbVersionBrowser()
{
	var sbRetorno = "";
	var nuVersion = parseInt(navigator.appVersion);
	var sbVersion = navigator.appName;
	if(navigator.appName == 'Microsoft Internet Explorer')
	{
		if(nuVersion == 4)
		{
			sbRetorno = "ie5up";
		}
		else
		{
			sbRetorno = "ie5";
		}
	}
	else if(navigator.appName == 'Netscape')
	{
		if(nuVersion == 5)
		{
			sbRetorno = "ns6";
		}
		else
		{
			sbRetorno = "ns4";
		}
	}
	else
	{
		sbRetorno = false;
	}
	return sbRetorno;
}

function fncAbrirCerrar()
{
	var url='JSP/login.iface';
	var w,h;
	var sbVersionBrowser = fsbVersionBrowser();
	if(sbVersionBrowser == "ie5" || sbVersionBrowser == "ns4")
	{
		document.location.href = url;
	}
	else
	{
	    h = (screen.availHeight - 40);
		w = (screen.availWidth - 10);
		var sbOpciones='top=0,left=0,toolbar=0,menubar=no,status=1,scrollbars=1,width='+w+',height='+h;
		Ventana = window.open(url,'AgendaForval',sbOpciones);
		Ventana.focus();
		window.opener=null;
		try {
		window.menubar.visible = false;
		} catch (err) {
		}
        window.close();
		return false;			
	}
}
//-->
</script>
	</head>
	<body onload="fncAbrirCerrar();">
	</body>
</html>

