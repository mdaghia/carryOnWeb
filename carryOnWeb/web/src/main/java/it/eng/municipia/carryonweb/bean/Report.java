package it.eng.municipia.carryonweb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.StreamedContent;

import it.eng.municipa.carryonweb.bean.CompositeStaging;
import it.eng.municipa.carryonweb.bean.CompositeTabellaStagingMaster;
import it.eng.municipa.carryonweb.ejb.IEJBNettunoTares;
import it.eng.municipa.carryonweb.ejb.IEJBReport;
import it.eng.municipia.carryon.services.model.StagingMaster;
import it.eng.municipia.carryonweb.helper.HelperDownload;
import it.eng.tributi.prodotti.nettuno.services.model.auto.DichiarazioneTares;

@ManagedBean(eager = true)
@ApplicationScoped
public class Report implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String ESITO_KO = "KO";

	private static final String ESITO_OK = "OK";

	@Inject
	IEJBReport ejbReport;

	@Inject
	IEJBNettunoTares ejbNettunoTares;

	private static Logger logger = Logger.getLogger(Report.class.getCanonicalName());

	private List<StagingMaster> listaStagingMasters;
	private List<StagingMaster> filteredStagingMasters;
	private List<CompositeStaging> listaCompositeStaging;
	private List<DichiarazioneTares> listaDichiarazioneTares;

	private List<CompositeTabellaStagingMaster> listaTabellaStagingMasters;
	private List<CompositeTabellaStagingMaster> filteredTabellaStagingMasters;

	public List<String> getStati()
	{
		ArrayList<String> listaStati = new ArrayList<String>();
		listaStati.add(ESITO_OK);
		listaStati.add(ESITO_KO);
		return listaStati;
	}

	public List<CompositeTabellaStagingMaster> getFilteredTabellaStagingMasters()
	{
		return filteredTabellaStagingMasters;
	}

	public List<CompositeTabellaStagingMaster> getListaTabellaStagingMasters()
	{
		return listaTabellaStagingMasters;
	}

	public void setListaTabellaStagingMasters(List<CompositeTabellaStagingMaster> listaTabellaStagingMasters)
	{
		this.listaTabellaStagingMasters = listaTabellaStagingMasters;
	}

	public void setFilteredTabellaStagingMasters(List<CompositeTabellaStagingMaster> filteredTabellaStagingMasters)
	{
		this.filteredTabellaStagingMasters = filteredTabellaStagingMasters;
	}

	public List<CompositeStaging> getListaCompositeStaging()
	{
		return listaCompositeStaging;
	}

	public void setListaCompositeStaging(List<CompositeStaging> listaCompositeStaging)
	{
		this.listaCompositeStaging = listaCompositeStaging;
	}

	public StreamedContent retrieveRichiesta(CompositeTabellaStagingMaster compositeTabellaStagingMaster)
	{
		StagingMaster stagingMaster = compositeTabellaStagingMaster.getStagingMaster();
		logger.log(Level.INFO, "retrieveRichiesta " + stagingMaster.getId().getPkIdentificativoModulo());
		return HelperDownload.download("" + stagingMaster.getRichiestaXml().length(), stagingMaster.getId().getPkIdentificativoModulo() + "_richiesta.xml", stagingMaster.getRichiestaXml());
	}

	public StreamedContent retrieveRisposta(CompositeTabellaStagingMaster compositeTabellaStagingMaster)
	{
		StagingMaster stagingMaster = compositeTabellaStagingMaster.getStagingMaster();
		logger.log(Level.INFO, "retrieveRisposta " + stagingMaster.getId().getPkIdentificativoModulo());
		return HelperDownload.download("" + stagingMaster.getRispostaXml().length(), stagingMaster.getId().getPkIdentificativoModulo() + "risposta.xml", stagingMaster.getRispostaXml());

	}

	public StreamedContent retrieveLog(CompositeTabellaStagingMaster compositeTabellaStagingMaster)
	{
		StagingMaster stagingMaster = compositeTabellaStagingMaster.getStagingMaster();
		logger.log(Level.INFO, "retrieveLog " + stagingMaster.getId().getPkIdentificativoModulo());
		return HelperDownload.download("" + stagingMaster.getLog().length(), stagingMaster.getId().getPkIdentificativoModulo() + ".log", stagingMaster.getLog());

	}

	@PostConstruct
	public void init()
	{

		popolaTabella();

	}

	public void popolaTabella()
	{
		listaTabellaStagingMasters = new ArrayList<CompositeTabellaStagingMaster>();

		List<StagingMaster> listaStagingMaster = ejbReport.retrieveListaStagingMaster();
		setListaCompositeStaging(ejbReport.retrieveListaCompositeStaging());
		List<StagingMaster> listaStagingMasterDaVisualizzare = new ArrayList<StagingMaster>();

		for (StagingMaster stagingMaster : listaStagingMaster)
		{

			for (CompositeStaging compositeStaging : listaCompositeStaging)
			{

				if (stagingMaster.getId().getPkIdentificativoModulo().equals(compositeStaging.getIdentificativoModulo()))
				{
					if (compositeStaging.getPkDichiarazioneTares() == null || compositeStaging.getPkDichiarazioneTares().equals(new BigDecimal(0)))
					{
						String errore = "Scarica il log";

						if (stagingMaster.getLog() != null && !stagingMaster.getLog().isEmpty() && stagingMaster.getLog().length() < 200)
						{
							errore = stagingMaster.getLog();
						}

						CompositeTabellaStagingMaster compositeTabellaStagingMaster = new CompositeTabellaStagingMaster(new BigDecimal(0), stagingMaster, "", new BigDecimal(0), errore);

						// listaStagingMasterDaVisualizzare.add(stagingMaster);
						listaTabellaStagingMasters.add(compositeTabellaStagingMaster);

					}
					else
					{
						String errore = "Scarica il log";

						if (stagingMaster.getLog() != null && !stagingMaster.getLog().isEmpty() && stagingMaster.getLog().length() < 200)
						{
							errore = stagingMaster.getLog();
						}
						setListaDichiarazioneTares(ejbNettunoTares.retrieveListaDichiarazioneTares(compositeStaging.getPkDichiarazioneTares().longValue()));
						for (DichiarazioneTares dichiarazioneTares : listaDichiarazioneTares)
						{
							CompositeTabellaStagingMaster compositeTabellaStagingMaster = new CompositeTabellaStagingMaster(new BigDecimal(dichiarazioneTares.getId().getPkDichiarazioneTares()), stagingMaster, dichiarazioneTares.getNumeroDocumento() + "/" + dichiarazioneTares.getAnnoDocumento(), dichiarazioneTares.getCodiceAcsorContribuente(), errore);

							listaTabellaStagingMasters.add(compositeTabellaStagingMaster);
							continue;
							// listaStagingMasterDaVisualizzare.add(stagingMaster);
						}
					}
				}
			}
		}

		setListaStagingMasters(listaStagingMasterDaVisualizzare);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operazione Completata", "Caricamento della tabella completata."));
	}

	public List<StagingMaster> getListaStagingMasters()
	{
		return listaStagingMasters;
	}

	public void setListaStagingMasters(List<StagingMaster> listaStagingMasters)
	{
		this.listaStagingMasters = listaStagingMasters;
	}

	public List<StagingMaster> getFilteredStagingMasters()
	{
		return filteredStagingMasters;
	}

	public void setFilteredStagingMasters(List<StagingMaster> filteredStagingMasters)
	{
		this.filteredStagingMasters = filteredStagingMasters;
	}

	public List<DichiarazioneTares> getListaDichiarazioneTares()
	{
		return listaDichiarazioneTares;
	}

	public void setListaDichiarazioneTares(List<DichiarazioneTares> listaDichiarazioneTares)
	{
		this.listaDichiarazioneTares = listaDichiarazioneTares;
	}

}
