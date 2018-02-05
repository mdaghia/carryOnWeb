package it.eng.municipa.carryonweb.ejb;

import java.util.List;

import it.eng.municipa.carryonweb.bean.CompositeStaging;
import it.eng.municipia.carryon.services.model.StagingMaster;

public interface IEJBReport
{
	public List<StagingMaster> retrieveListaStagingMaster();

	List<CompositeStaging> retrieveListaCompositeStaging();
}
