package it.eng.municipia.carryonweb.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;

public class HelperDownload
{
	
	private static Logger logger = Logger.getLogger(HelperDownload.class.getCanonicalName());
	public static StreamedContent download(String lunghezza, String nomeFile, String contenuto)
	{
		StreamedContent download = null;
		try
		{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			externalContext.setResponseHeader("Content-Type", "text/xml;charset=UTF-8");
			externalContext.setResponseHeader("Content-Length", lunghezza);
			externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\"" + nomeFile);
			externalContext.getResponseOutputStream().write(contenuto.getBytes("UTF-8"));
			facesContext.responseComplete();

		}
		catch (Exception e)
		{
			logger.log(Level.SEVERE, e.getMessage(), e);

			FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore Download", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, errorMsg);
		}
		return download;
	}
}
