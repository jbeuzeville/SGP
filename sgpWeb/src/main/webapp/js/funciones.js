/*
$(document).ready(function(){
	$(window).on('scroll', function(){
		altura = $('.dataTableMRC').offset().top;
		if ( $(window).scrollTop() > altura ){
			$('.dataTableMRC').addClass('menu-fixed');
		}
		altura2 = $('.ui-datatable ui-widget').offset().top;
		if ( $(window).scrollTop() < altura2 ){
			$('.dataTableMRC').removeClass('menu-fixed');
		}
	});
 
});*/

/*$(document).ready(function() {
	$('.claseToolTip .ui-datatable-data tr').each(function(index, element) {
		$(this).attr('id','id_' + index);
		$(this).attr('title','Esto es una prueba ' + index);
		$(this).attr('class', 'nueva clase');
	});
});*/

/*function toolTip(title) {
	$(document).ready(function() {
		$('.claseToolTip .ui-datatable-data tr').each(function(index, element) {
			$(this).attr(title);
		});
	});
}*/

var up = 0;

function activarTab(tab, indice){
	PF(tab).select(indice);
}

function enter(evt, it) {
	evt = (evt) ? evt : event;
	var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode : ((evt.which) ? evt.which : 0));
	if (charCode == 13) {
		jQuery(it).trigger('change');
		return false;
	}
	if (charCode == 1) {
		return true;
	}
}

function goBack() {
	var oldURL = document.referrer;
	var n = oldURL.search("login");
	if (n < 1) {
		window.history.back();
	}
}

function numerosYSigno(evt, v) {
	evt = (evt) ? evt : event;
	var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode : ((evt.which) ? evt.which : 0));
	var respuesta = false;
	if (charCode == 60 || charCode == 62) {
		if (v == "") {
			return true;
		}
	}
	if (charCode >= 48 && charCode <= 57) {
		if (v.search("<") != -1 || v.search(">") != -1) {
			return false;
		}
		respuesta = true;
	}
	return respuesta;
}

function soloLetras(e) {
	key = e.keyCode || e.which;
	tecla = String.fromCharCode(key).toLowerCase();
	letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
	especiales = [ 8, 38, 39, 46 ];
	tecla_especial = false;
	for ( var i in especiales) {
		if (key == especiales[i]) {
			tecla_especial = true;
			break;
		}
	}
	if (letras.indexOf(tecla) == -1 && !tecla_especial) {
		return false;
	}
}

function soloNumeros(evt) {   
	evt = (evt) ? evt : event;
	var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode : ((evt.which) ? evt.which : 0));   
	var respuesta = false;  
	if ((charCode >= 48 && charCode <= 57) || charCode == 8 || charCode == 127 || charCode == 13) {
		respuesta = true;
	}
	return respuesta;
}

function soloDecimales(target, event) {
	var s = target.value;
	var length = target.maxLength;
	if (event.keyCode == 8) {
		s = "0" + s.replace(/^(.*).$/, "$1");
	} else {
		if (s.length >= length)
			return;
		if ((event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105))
			return;
		s = (s + (event.keyCode <= 57 ? event.keyCode - 48 : event.keyCode - 96));
	}
	target.value = s.replace(".", "").replace(/^0*(\d+\d\d)$/, "$1").replace(/^(.*)(..)$/, "$1.$2");
}

function caracteresPermitidos(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 8 || tecla == 0 || tecla == 46 || tecla == 40 || tecla == 41 || tecla == 58 || tecla == 59 || tecla == 44 || tecla == 64)
		return true;
	patron = /^[A-Za-z0-9\s\u00f1\u00d1\u00e1\u00c1\u00e9\u00c9\u00ed\u00cd\u00f3\u00d3\u00fa\u00da]$/;
	te = String.fromCharCode(tecla);
	return patron.test(te);
}

function formatoFecha(evt){  
	evt = (evt) ? evt : event;
	var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode : ((evt.which) ? evt.which : 0));
	var respuesta = false;
	if (charCode >= 47 && charCode <= 57 || charCode == 8) {
		respuesta = true;
	}
	return respuesta;
}

function soloNumerosYPunto(evt) {
	evt = (evt) ? evt : event;   
	var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode : ((evt.which) ? evt.which : 0));
	var respuesta = false;
	if ((charCode >= 48 && charCode <= 57) || charCode == 46 || charCode == 8 || charCode == 127 || charCode == 44) {
		respuesta = true;
	}
	return respuesta;
}

function numerico(evt) {
	evt = (evt) ? evt : event;
	var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode	: ((evt.which) ? evt.which : 0));
	var respuesta = false;
	if ((charCode >= 48 && charCode <= 57) || charCode == 8 || charCode == 127) {
		respuesta = true;
	}	
	return respuesta;
}
