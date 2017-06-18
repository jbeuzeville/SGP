package pe.com.hitss.sgp.web.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import pe.com.hitss.sgp.core.util.MessageException;

public final class UtilWeb {

	@Autowired
	private static final Logger LOGGER = Logger.getLogger(UtilWeb.class);

	private UtilWeb() {

	}

	public static String getLocale(){
		return (String) getObjetoSesion("locale");
	}
	
	public static Object getObjetoSesion(String objectName) {
		Object enviar=null;
		HttpServletRequest request = (HttpServletRequest) getInstancia()
				.getExternalContext().getRequest();
		if(request.getSession()!=null){
			enviar=request.getSession(false).getAttribute(objectName);
		}
		return enviar;
	}

	private static FacesContext getInstancia() {
		return FacesContext.getCurrentInstance();
	}
	
	public static final FacesContext getFacesContext()
    {
        return FacesContext.getCurrentInstance();
    }

	public static HttpSession getSesion() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}
	
	public static String getIdSesion() {
		HttpServletRequest request = (HttpServletRequest)getInstancia().getExternalContext().getRequest();
		return request.getSession().getId().toString();
	}
	
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
	}

	public static void sendRedirect(String ruta) {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		try {
			FacesContext.getCurrentInstance().responseComplete();
			response.sendRedirect(FacesContext.getCurrentInstance()
					.getExternalContext().getRequestContextPath()
					+ "/faces/" + ruta);
		} catch (IOException e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,e.getMessage());LOGGER.error(errores[1]);
		}
	}

	public static void irIniciarSesion() {
		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		try {
			FacesContext.getCurrentInstance().responseComplete();
			response.sendRedirect(FacesContext.getCurrentInstance()
					.getExternalContext().getRequestContextPath());
		} catch (IOException e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			LOGGER.error(errores[1]);
		}
	}

	public static void setObjetoSesion(String objectName, Object object) {
		LOGGER.info("SE PUSO EN SESION EL OBJETO " + objectName + " DEL TIPO " + object.getClass());
		HttpServletRequest request = (HttpServletRequest)getInstancia().getExternalContext().getRequest();
		request.getSession(false).setAttribute(objectName, object);
	}

	public static void removeObjetoSesion(String objectName) {
		LOGGER.info("SE QUITO DE SESION EL OBJETO " + objectName);
		HttpServletRequest request = (HttpServletRequest) getInstancia()
			.getExternalContext().getRequest();
		if(request.getSession(false)!=null && request.getSession(false).getAttribute(objectName)!=null){
			request.getSession(false).removeAttribute(objectName);
			if(objectName.equalsIgnoreCase(ConstantesWeb.USUARIO_LOGUEADO)){
				irIniciarSesion();
			}
		}
    }

	public static String obtenerFechaActual(String formato) {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat(formato);
		return formateador.format(ahora);
	}

	public static String formatearFecha(String formato, Date fecha) {
		SimpleDateFormat formateador = new SimpleDateFormat(formato);
		return formateador.format(fecha);
	}

    public static String darFormatoDosDecimales(Object obj) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setGroupingUsed(false);
		numberFormat.setMaximumFractionDigits(ConstantesWeb.N2);
		numberFormat.setRoundingMode(RoundingMode.DOWN);
		numberFormat.setMinimumFractionDigits(ConstantesWeb.N2);
		return numberFormat.format(obj);
    }

    public static BigDecimal darFormatoBigDecimal(BigDecimal numero) {
		return numero.round(new MathContext(numero.precision() - numero.scale()
			+ ConstantesWeb.N2, RoundingMode.HALF_UP));
    }

	public static void mensajeError(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));
	}

	public static void mensajeInformacion(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, ""));
	}

	public static void mensajeAdvertencia(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, ""));
	}

	public static void mensajeFatal(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, mensaje, ""));
	}

	public static boolean esNumericoDecimal(String cadena) {
		boolean retorno;
		try {
			if (cadena == null) {
				retorno = false;
			} else {
				String cadenaNumero = cadena.replaceAll(",", ".");
				Double.parseDouble(cadenaNumero.trim());
				retorno = true;
			}
		} catch (NumberFormatException nfe) {
			retorno = false;
		}
		return retorno;
	}
	
	public static Object obtenerParametroFaces(String nombreParam){
		Map<String, String> params = FacesContext.getCurrentInstance()
			.getExternalContext().getRequestParameterMap();
		return params.get(nombreParam);
	}
	
	public static String formatearStringNulo(String cadena){
    	String retorno = "";
    	
    	if(cadena != null){
    		retorno = cadena;
    	}
    	
    	return retorno;
    }
	
	public static Integer validaNuloOVacioDevuelveInteger(Object objeto, Integer valorDefecto) {
		Integer valorRetorno;
		if (objeto == null || objeto.toString().equals("")) {
			valorRetorno = valorDefecto;
		} else {
			valorRetorno = Integer.parseInt(objeto.toString());
		}
		return valorRetorno;
	}
	
	public static String validaNuloOVacioDevuelveString(Object objeto) {
		String valorRetorno;
		if (objeto == null || objeto.toString().equals("")) {
			valorRetorno = "";
		} else {
			valorRetorno = objeto.toString();
		}
		return valorRetorno;
	}
	
	public static boolean esNuloOrVacio(Object valor) {
		if (valor == null) {
		    return true;
		}

		if (valor instanceof Set<?>) {
		    return true;
		}

		if (valor instanceof String &&
		    ((String) valor).trim().length() == 0 ){
			return true;
	    }
		

		return false;
	}
	
	/*public static String nombreArchivoHTML(String fileName){
		String nombre = "";
		if(fileName.indexOf(ConstantesWeb.EXTENSION_ARCHIVO_HTML) > 0){
			nombre = fileName;
		} else{
			nombre = fileName +  ConstantesWeb.EXTENSION_ARCHIVO_HTML;
		}
		return nombre;
	}*/
	
	public static StringBuffer leerPlantilla(String urlPlantilla) {
		String uri = urlPlantilla;
		File file = new File(uri);
		BufferedReader br = null;
		StringBuffer contenido = new StringBuffer();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"ISO-8859-1"));
			String linea;
			while ((linea = br.readLine()) != null) {
				contenido.append(linea);
			}
		} catch (Exception e) {
			LOGGER.info("Excepcion - " + e.toString());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					 String[] errores = MessageException.getMessageExceptionPrintAop(e,
				    		    e.getMessage());
				    	    LOGGER.error(errores[1]);
				    	    UtilWeb.mensajeFatal(errores[0]);
				}
			}
		}
		return contenido;
	}
	
public static String createHtmlFile(String path, String fileName, String content){
		
		BufferedWriter bWriter = null;
		String newFileName = null;
		
		if(fileName == null || fileName.matches("")){
			LOGGER.info("NOT A VALID NAME->" + fileName);
			return null;
		}

		if(bWriter == null){
			try {
				
				String fileNameStr = path + fileName;
				
				File file = new File(fileNameStr);
				file.deleteOnExit(); 
						
				bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false),"ISO-8859-1"));
				newFileName = file.getAbsolutePath();				
				bWriter.write(content);			
			} catch (IOException e){
				LOGGER.error(e.getMessage());
			} finally{
				try {
					if(bWriter != null){
						bWriter.flush();
						bWriter.close();
					}
				} catch (IOException e) {
					 String[] errores = MessageException.getMessageExceptionPrintAop(e,
				    		    e.getMessage());
				    	    LOGGER.error(errores[1]);
				    	    UtilWeb.mensajeFatal(errores[0]);
				}
			}
		}
		bWriter = null;
		return newFileName;
	}

	public static String getFechaActual() {
	    Date ahora = new Date();
	    SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
	    return formateador.format(ahora);
	}
	
	public static Date deStringToDate(String fecha) {
	    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
	    Date fechaEnviar = null;
	    try {
	            fechaEnviar = formatoDelTexto.parse(fecha);
	            return fechaEnviar;
	    }catch (ParseException ex) {
	            ex.printStackTrace();
	            return null;
	    }
	}

	public static String getFechaExport() {
		return new SimpleDateFormat("MM-dd-yyyy HHmmss.SSS").format(new Date());
	}
	
	public static void eliminarArchivos() {
		/*try {
			StringBuilder fileDirectoryName = new StringBuilder();
			String path = MessageFactory.getMessage(ConstantesWeb.RUTA_DESCARGA_CTP, "");
	
			if (nombre != null) {
				fileDirectoryName.append(path);
				fileDirectoryName.append("\\");
				fileDirectoryName.append(nombre);
	
				File archivo = new File(path);
				if (archivo.delete()){
					System.out.println("Archivo " + nombre + " se eliminó con éxito.");
				} else {
					System.out.println("Archivo " + nombre + " no se pudo eliminar.");
				}
			}
			
			File[] archivos = new File(path).listFiles();
			for (File archivo : archivos) {
				archivo.delete();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}*/
		
	}
	
	public static BigDecimal validaNullretornoBigDecimal(BigDecimal value){
		BigDecimal ret = BigDecimal.ZERO;
		if (value != null) {
			ret = value;
		}
		return ret;
	} 
	
}
