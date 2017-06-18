package pe.com.hitss.sgp.web.config;

import java.util.Locale;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class WebMessageUtil {
	
	@Autowired
	private ResourceBundleMessageSource boundle;
	/*@Autowired
	private Locale locale;*/
	
	public String getMessage(String key, String[] args) {
		/*Locale locale=null;
		if(UtilWeb.getObjetoSesion(ConstantesWeb.LOCALIDAD)!=null){
			locale = (Locale)UtilWeb.getObjetoSesion(ConstantesWeb.LOCALIDAD);
			System.out.println("--->>>" + locale.getCountry());
		}else{
			locale = this.locale;
		}
		return this.boundle.getMessage(key, args, locale);*/
		return this.boundle.getMessage(key, args, getLocale());
	}
	
	private static Locale getLocale() {
        Locale locale = null;
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null && facesContext.getViewRoot() != null) {
            locale = facesContext.getViewRoot().getLocale();
            if (locale == null) {
                locale = Locale.getDefault();
            }
        }
        else {
            locale = Locale.getDefault();
        }
        return locale;
    }
}
