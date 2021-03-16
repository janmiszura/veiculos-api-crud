package com.dryve.vehicles.model;

import java.math.BigDecimal;

public class VeiculoSaveDTO {

	private String id;
	
	private String placa;
	
	private String idMarca;
	
	private String idModelo;
	
	private BigDecimal precoAnuncio;
	
	private Integer ano;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(String idMarca) {
		this.idMarca = idMarca;
	}

	public String getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(String idModelo) {
		this.idModelo = idModelo;
	}

	public BigDecimal getPrecoAnuncio() {
		return precoAnuncio;
	}

	public void setPrecoAnuncio(BigDecimal precoAnuncio) {
		this.precoAnuncio = precoAnuncio;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "VeiculoDTO [id=" + id + ", placa=" + placa + ", idMarca=" + idMarca + ", idModelo=" + idModelo
				+ ", precoAnuncio=" + precoAnuncio + ", ano=" + ano + "]";
	}
	
	
	
	
}