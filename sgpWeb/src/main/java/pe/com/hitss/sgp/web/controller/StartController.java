package pe.com.hitss.sgp.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pe.com.hitss.sgp.web.base.controller.BaseController;
import pe.com.hitss.sgp.web.config.WebMessageUtil;
import pe.com.hitss.sgp.web.util.ConstantesWeb;
import pe.com.hitss.sgp.web.util.MessageFactory;
import pe.com.hitss.sgp.web.util.UtilWeb;
import pe.com.hitss.sgp.core.domain.Usuario;
import pe.com.hitss.sgp.core.util.MessageException;

@Controller
@Scope("session")
public class StartController extends BaseController {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(StartController.class);
	
	@Autowired
	private WebMessageUtil webMessageUtil;
	private DefaultStreamedContent downLoadManual;
	
	public StartController() {
		
	}

	public void irLogin(){
		UtilWeb.sendRedirect(ConstantesWeb.LOGIN);
	}
	
	public void irBienvenido(){
		UtilWeb.sendRedirect(ConstantesWeb.PAGE_BIENVENIDO);
	}
	
	public void irOpcionMenu(){
		int op = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("op").toString());
		
	}
	
	void ir(String page){
		UtilWeb.sendRedirect(page);
	}
	
	public void downLoadUserManual(){// throws ExceptionCore {
		try {
			setDownLoadManual(null);
			StringBuilder fileDirectoryName = new StringBuilder();
			String path = "";//MessageFactory.getMessage(ConstantesWeb.RUTA_DESCARGA, "");
			String manual = null;
			
			Usuario usuario = new Usuario();
			usuario = getUsuarioActual();
			
			manual = "";//MessageFactory.getMessage(ConstantesWeb.NOMBRE_MANUAL_AM, "");
			
			if (manual != null) {

				fileDirectoryName.append(path);
				fileDirectoryName.append("\\");
				fileDirectoryName.append(manual);

				File wordFile = new File(fileDirectoryName.toString());
				InputStream input = new FileInputStream(wordFile);
				ExternalContext externalContext = FacesContext
						.getCurrentInstance().getExternalContext();
				setDownLoadManual(new DefaultStreamedContent(input,
						externalContext.getMimeType(wordFile.getName()),
						wordFile.getName()));
			}
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			LOGGER.error(errores[ConstantesWeb.N1]);
			UtilWeb.mensajeFatal(errores[ConstantesWeb.N0]);
		}

	}

	public DefaultStreamedContent getDownLoadManual() {
		return downLoadManual;
	}

	public void setDownLoadManual(DefaultStreamedContent downLoadManual) {
		this.downLoadManual = downLoadManual;
	}
}
