package it.eng.municipa.carryonweb.bean;

import java.math.BigDecimal;

public class CompositeStaging
{
	public CompositeStaging()
	{
	}

	public CompositeStaging(Long idStagingMaster, String identificativoModulo, BigDecimal pkDichiarazioneTares)
	{
		this.idStagingMaster = idStagingMaster;
		this.identificativoModulo = identificativoModulo;
		this.pkDichiarazioneTares = pkDichiarazioneTares;
	}

	private Long idStagingMaster;

	private String identificativoModulo;
	private BigDecimal pkDichiarazioneTares;

	 

	public Long getIdStagingMaster()
	{
		return idStagingMaster;
	}

	public void setIdStagingMaster(Long idStagingMaster)
	{
		this.idStagingMaster = idStagingMaster;
	}

	public String getIdentificativoModulo()
	{
		return identificativoModulo;
	}

	public void setIdentificativoModulo(String identificativoModulo)
	{
		this.identificativoModulo = identificativoModulo;
	}

	public BigDecimal getPkDichiarazioneTares()
	{
		return pkDichiarazioneTares;
	}

	public void setPkDichiarazioneTares(BigDecimal pkDichiarazioneTares)
	{
		this.pkDichiarazioneTares = pkDichiarazioneTares;
	}

	@Override
	public String toString()
	{
		return "CompositeStaging [" + (idStagingMaster != null ? "idStagingMaster=" + idStagingMaster + ", " : "") + (identificativoModulo != null ? "identificativoModulo=" + identificativoModulo + ", " : "") + (pkDichiarazioneTares != null ? "pkDichiarazioneTares=" + pkDichiarazioneTares : "") + "]";
	}
}
