package it.eng.municipa.carryonweb.bean;

import java.math.BigDecimal;

import it.eng.municipia.carryon.services.model.StagingMaster;

public class CompositeTabellaStagingMaster
{	public CompositeTabellaStagingMaster(BigDecimal pkDichiarazioneTares, StagingMaster stagingMaster)
	{
		super();
		this.pkDichiarazioneTares = pkDichiarazioneTares;
		this.stagingMaster = stagingMaster;
	}
private BigDecimal pkDichiarazioneTares;
	private StagingMaster stagingMaster;
	
	
	public BigDecimal getPkDichiarazioneTares()
	{
		return pkDichiarazioneTares;
	}
	public void setPkDichiarazioneTares(BigDecimal pkDichiarazioneTares)
	{
		this.pkDichiarazioneTares = pkDichiarazioneTares;
	}
	public StagingMaster getStagingMaster()
	{
		return stagingMaster;
	}
	public void setStagingMaster(StagingMaster stagingMaster)
	{
		this.stagingMaster = stagingMaster;
	}
}
