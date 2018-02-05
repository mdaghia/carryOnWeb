package it.eng.municipa.carryonweb.ejb;

import java.util.List;

import it.eng.tributi.prodotti.nettuno.services.model.auto.DichiarazioneTares;

public interface IEJBNettunoTares
{
	public List<DichiarazioneTares> retrieveListaDichiarazioneTares(long pkDichiarazioneTares);
}
