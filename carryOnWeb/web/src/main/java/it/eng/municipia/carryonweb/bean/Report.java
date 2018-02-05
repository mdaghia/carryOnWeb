package it.eng.municipia.carryonweb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.primefaces.model.StreamedContent;

import it.eng.municipa.carryonweb.bean.CompositeStaging;
import it.eng.municipa.carryonweb.bean.CompositeTabellaStagingMaster;
import it.eng.municipa.carryonweb.ejb.IEJBNettunoTares;
import it.eng.municipa.carryonweb.ejb.IEJBReport;
import it.eng.municipia.carryon.services.model.StagingMaster;
import it.eng.municipia.carryonweb.helper.HelperDownload;
import it.eng.tributi.prodotti.nettuno.common.beans.documento.ICostanti;
import it.eng.tributi.prodotti.nettuno.services.model.auto.DichiarazioneTares;

@ManagedBean(eager = true)
@SessionScoped
public class Report implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	IEJBReport ejbReport;

	@Inject
	IEJBNettunoTares ejbNettunoTares;

	private static Logger logger = Logger.getLogger(Report.class.getCanonicalName());

	private List<StagingMaster> listaStagingMasters;
	private List<StagingMaster> filteredStagingMasters;
	private List<CompositeStaging> listaCompositeStaging;
	private List<DichiarazioneTares> listaDichiarazioneTares;
	
	private List <CompositeTabellaStagingMaster> listaTabellaStagingMasters;
	private List<CompositeTabellaStagingMaster> filteredTabellaStagingMasters;
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
					if(compositeStaging.getPkDichiarazioneTares().equals(new BigDecimal(0)))
					{
						CompositeTabellaStagingMaster compositeTabellaStagingMaster = new CompositeTabellaStagingMaster(compositeStaging.getPkDichiarazioneTares(), stagingMaster);
						//listaStagingMasterDaVisualizzare.add(stagingMaster);
						listaTabellaStagingMasters.add(compositeTabellaStagingMaster);
						
						
					}
					else
					{
						setListaDichiarazioneTares(ejbNettunoTares.retrieveListaDichiarazioneTares(compositeStaging.getPkDichiarazioneTares().longValue()));
						for (DichiarazioneTares dichiarazioneTares : listaDichiarazioneTares)
						{
							CompositeTabellaStagingMaster compositeTabellaStagingMaster = new CompositeTabellaStagingMaster(compositeStaging.getPkDichiarazioneTares(), stagingMaster);
							listaTabellaStagingMasters.add(compositeTabellaStagingMaster);
								//listaStagingMasterDaVisualizzare.add(stagingMaster);
						}
					}
				}
			}
		}

		setListaStagingMasters(listaStagingMasterDaVisualizzare);
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
