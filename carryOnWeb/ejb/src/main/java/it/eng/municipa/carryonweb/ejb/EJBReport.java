package it.eng.municipa.carryonweb.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.eng.municipa.carryonweb.bean.CompositeStaging;
import it.eng.municipia.carryon.services.model.StagingMaster;

public class EJBReport implements IEJBReport
{

	@PersistenceContext(unitName = "carryOn")
	private EntityManager em;

	private static Logger logger = Logger.getLogger(EJBReport.class.getCanonicalName());

	@Override
	public List<StagingMaster> retrieveListaStagingMaster()
	{
		List<StagingMaster> listaStagingMaster = new ArrayList<StagingMaster>();

		try
		{
			listaStagingMaster = em.createQuery("from StagingMaster t where"
				+ " t.id.codiceEnte ='A944' and t.flgLavorata='N'"
				+ "and t.stato in  ('OK','KO')" ).getResultList() ;
		}
		catch (NoResultException e)
		{
			 String msg = "non è stato estratto nessun record";
			logger.log(Level.SEVERE, msg, e);
		}

		return listaStagingMaster;
		
		
	}

	
	
	@Override
	public List<CompositeStaging> retrieveListaCompositeStaging()
	{
 	List<CompositeStaging> listaCompositeStaging = new ArrayList<CompositeStaging>();

		try
		{
			TypedQuery<CompositeStaging> q = em.createQuery(""
				+ "select distinct NEW it.eng.municipa.carryonweb.bean.CompositeStaging(t.idStagingMaster,s.fkIdentificativoModulo, s.prgDent) "
				+ "from StagingMaster t, StagingDettaglio s " 
				+ "where (( t.id.pkIdentificativoModulo = s.fkIdentificativoModulo or t.id.pkIdentificativoModulo is null "
				+ ")"
				+ "and ( s.id.codiceEnte = t.id.codiceEnte"
				+ " or  t.id.codiceEnte is null "
				+ "  ))and "
				+ "t.flgLavorata='N'" , CompositeStaging.class) ;
			
			listaCompositeStaging = q.getResultList();
			
			
			
		}
		catch (NoResultException e)
		{
			 String msg = "non è stato estratto nessun record";
			logger.log(Level.SEVERE, msg, e);
		}

		return listaCompositeStaging;
		
		
	}
}
