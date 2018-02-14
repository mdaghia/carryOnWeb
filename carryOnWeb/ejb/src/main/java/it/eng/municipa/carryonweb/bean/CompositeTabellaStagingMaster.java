package it.eng.municipa.carryonweb.bean;

import java.math.BigDecimal;

import it.eng.municipia.carryon.services.model.StagingMaster;

public class CompositeTabellaStagingMaster
{
	 
	public CompositeTabellaStagingMaster(BigDecimal progressivoDocumento, StagingMaster stagingMaster, String protocollo, BigDecimal codiceContribuente, String erroreToolTip)
	{
		super();
		this.progressivoDocumento = progressivoDocumento;
		this.stagingMaster = stagingMaster;
		this.protocollo = protocollo;
		this.codiceContribuente = codiceContribuente;
		this.erroreToolTip = erroreToolTip;
	}
	public String getErroreToolTip()
	{
		return erroreToolTip;
	}
	public void setErroreToolTip(String erroreToolTip)
	{
		this.erroreToolTip = erroreToolTip;
	}
	public BigDecimal getProgressivoDocumento()
	{
		return progressivoDocumento;
	}
	public void setProgressivoDocumento(BigDecimal progressivoDocumento)
	{
		this.progressivoDocumento = progressivoDocumento;
	}
	public StagingMaster getStagingMaster()
	{
		return stagingMaster;
	}
	public void setStagingMaster(StagingMaster stagingMaster)
	{
		this.stagingMaster = stagingMaster;
	}
	public String getProtocollo()
	{
		return protocollo;
	}
	public void setProtocollo(String protocollo)
	{
		this.protocollo = protocollo;
	}
	public BigDecimal getCodiceContribuente()
	{
		return codiceContribuente;
	}
	public void setCodiceContribuente(BigDecimal codiceContribuente)
	{
		this.codiceContribuente = codiceContribuente;
	}
	private BigDecimal progressivoDocumento;
	private StagingMaster stagingMaster;
	private String protocollo;
	private BigDecimal codiceContribuente;
	private String erroreToolTip;
	
 
}
