
<!--
document.onkeydown = function () {
	if (window.event && window.event.keyCode == 116) {
		window.event.keyCode = 505;
	}
	if (window.event && window.event.keyCode == 505) {
		return false;
	}
	if (window.event.keyCode == 8 && (window.event.srcElement.type != "text" && window.event.srcElement.type != "textarea" && window.event.srcElement.type != "password")) {
		return false;
	}
	if (window.event.ctrlKey && (window.event.keyCode == 78 || window.event.keyCode == 82)) {
		return false;
	}
	if (navigator.appName == "Netscape") {
		document.addEventListener("keypress", showDown, true);
	}
};
var message = "";
function clickIE() {
	if (document.all) {
		(message);
		return false;
	}
}
function clickNS(e) {
	if (document.layers || (document.getElementById && !document.all)) {
		if (e.which == 1 || e.which == 2 || e.which == 3) {
			(message);
			return false;
		}
	}
}
if (document.layers) {
	document.captureEvents(Event.MOUSEDOWN);
	document.onmousedown = clickNS;
} else {
	document.onmouseup = clickNS;
	document.oncontextmenu = clickIE;
}
document.oncontextmenu = new Function("return false");
function disableselect(e) {
	return false;
}
function reEnable() {
	return true;
}
//document.onselectstart = new Function("return false");
if (window.sidebar) {
	document.onmousedown = disableselect;
	document.onclick = reEnable;
}
//-->

