package pe.com.hitss.sgp.core.util;

import java.math.BigDecimal;

public class Resultado {

	private BigDecimal resultado;
	private String mensaje;

	private Long id;
	private Object resNumeric;
	private Object resString;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public BigDecimal getResultado() {
		return resultado;
	}

	public void setResultado(BigDecimal resultado) {
		this.resultado = resultado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Object getResNumeric() {
		return resNumeric;
	}

	public void setResNumeric(Object resNumeric) {
		this.resNumeric = resNumeric;
	}

	public Object getResString() {
		return resString;
	}

	public void setResString(Object resString) {
		this.resString = resString;
	}

}
