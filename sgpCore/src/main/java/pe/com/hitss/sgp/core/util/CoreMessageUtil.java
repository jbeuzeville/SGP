package pe.com.hitss.sgp.core.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class CoreMessageUtil {
	@Autowired
	private ResourceBundleMessageSource boundle;

	@Autowired
	private Locale locale;

	public String getMessage(String key, String[] args) {
		return this.boundle.getMessage(key, args, this.locale);
	}
}
