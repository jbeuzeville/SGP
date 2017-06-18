package pe.com.hitss.sgp.web.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("all")
public class MessageFactory {

    private static String DEFAULT_DETAIL_SUFFIX = "_detail";

    private MessageFactory() {
    }

    public static String getMessage(final Locale locale, final String messageId,
            final FacesMessage.Severity severity, final Object... params) {
        final String facesMessage = getMessage(locale, messageId, params);
        //facesMessage.setSeverity(severity);
        return facesMessage;
    }

    public static String getMessage(final Locale locale, final String messageId,
            final Object... params) {
        String summary = null;
        String detail = null;
        final FacesContext context = FacesContext.getCurrentInstance();
        final ResourceBundle bundle = context.getApplication()
                .getResourceBundle(context, "messages");

        try {
            summary = getFormattedText(locale, bundle.getString(messageId),
                    params);
        }
        catch (final MissingResourceException e) {
            summary = messageId;
        }

        try {
            detail = getFormattedText(locale,
                    bundle.getString(messageId + DEFAULT_DETAIL_SUFFIX), params);
        }
        catch (final MissingResourceException e) {
            
        }

        return summary;
    }

    public static String getMessage(final String messageId,
            final FacesMessage.Severity severity, final Object... params) {
        final String facesMessage = getMessage(getLocale(), messageId, params);
        //facesMessage.setSeverity(severity);
        return facesMessage;
    }

    /*inicia*/
    public static String getMessage(final String messageId, final Object... params) {
        return getMessage(getLocale(), messageId, params);
    }

    private static String getFormattedText(final Locale locale, final String message,
            final Object params[]) {
        MessageFormat messageFormat = null;

        if (params == null || message == null) {
            return message;
        }

        messageFormat = locale == null ? new MessageFormat(message)
                : new MessageFormat(message, locale);
        return messageFormat.format(params);
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
