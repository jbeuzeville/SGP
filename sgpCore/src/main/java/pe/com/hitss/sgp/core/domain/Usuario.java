package pe.com.hitss.sgp.core.domain;

import java.io.Serializable;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idUsuario;
	private String primerApellido;
	private String segundoApellido;
	private String nombres;
	private String nombreCompleto;
	private String codigoUsuario;
	private String nombreUsuario;
	private String correoPersonal;
	private String correoCliente;
	private String telefono;
	private String dni;
	private int estado;

	private String perfil;

	private boolean flagFechaPasada;

	public Usuario() {

	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getCorreoPersonal() {
		return correoPersonal;
	}

	public void setCorreoPersonal(String correoPersonal) {
		this.correoPersonal = correoPersonal;
	}

	public String getCorreoCliente() {
		return correoCliente;
	}

	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public boolean isFlagFechaPasada() {
		return flagFechaPasada;
	}

	public void setFlagFechaPasada(boolean flagFechaPasada) {
		this.flagFechaPasada = flagFechaPasada;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", primerApellido="
				+ primerApellido + ", segundoApellido=" + segundoApellido
				+ ", nombres=" + nombres + ", nombreCompleto=" + nombreCompleto
				+ ", codigoUsuario=" + codigoUsuario + ", nombreUsuario="
				+ nombreUsuario + ", correoPersonal=" + correoPersonal
				+ ", correoCliente=" + correoCliente + ", telefono=" + telefono
				+ ", dni=" + dni + ", estado=" + estado + ", perfil=" + perfil
				+ ", flagFechaPasada=" + flagFechaPasada + "]";
	}

}
