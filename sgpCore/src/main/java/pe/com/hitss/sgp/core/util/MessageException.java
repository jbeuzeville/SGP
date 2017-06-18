package pe.com.hitss.sgp.core.util;

import static java.text.MessageFormat.format;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component("messageException")
public class MessageException {

	public static String[] getMessageExceptionPrintAop(Exception excepcion,
			String prefijo) {
		StringBuffer mensaje = new StringBuffer();
		String idError = getIdError();
		mensaje.append(format("Codigo Error: {0} \n", idError));
		mensaje.append(format("Exception: {0} \n", prefijo));
		StackTraceElement[] elem = excepcion.getStackTrace();
		mensaje.append(format("Message: {0} \n", excepcion.getMessage()));
		for (StackTraceElement ex : elem) {
			mensaje.append(format(
					"Class ''{0}'' in  line ''{1}'' in method ''{2}'' \n",
					ex.getClassName(), ex.getLineNumber(), ex.getMethodName()));
		}
		return new String[]{idError, mensaje.toString()};
	}

	public static String getIdError() {
		return new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
	}

	public String getCodigoError() {
		return getIdError();
	}
}
