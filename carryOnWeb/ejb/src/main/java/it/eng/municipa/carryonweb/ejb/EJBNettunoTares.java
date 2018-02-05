package it.eng.municipa.carryonweb.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import it.eng.tributi.prodotti.nettuno.common.beans.documento.ICostanti;
import it.eng.tributi.prodotti.nettuno.services.model.auto.DichiarazioneTares;

public class EJBNettunoTares implements IEJBNettunoTares
{

	@PersistenceContext(unitName = "nettunoPU")
	private EntityManager em;

	private static Logger logger = Logger.getLogger(EJBNettunoTares.class.getCanonicalName());

	@Override
	public List<DichiarazioneTares> retrieveListaDichiarazioneTares(long pkDichiarazioneTares)
	{
		List<DichiarazioneTares> listaDichiarazioneTares = new ArrayList<DichiarazioneTares>();

		try
		{
			listaDichiarazioneTares = em.createQuery("from DichiarazioneTares t "
				+ "where t.id.pkDichiarazioneTares = :pkDichiarazioneTares and t.codiceStatoOperativo = '" + ICostanti.TARES_CODICE_STATO_DOCUMENTO_PRESENTATO +"'").setParameter("pkDichiarazioneTares", pkDichiarazioneTares).getResultList();
		}
		catch (NoResultException e)
		{
			String msg = "non è stato estratto nessun record";
			logger.log(Level.SEVERE, msg, e);
		}

		return listaDichiarazioneTares;

	}

}
