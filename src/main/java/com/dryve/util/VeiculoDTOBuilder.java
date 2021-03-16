package com.dryve.util;

import java.math.BigDecimal;

import com.dryve.vehicles.model.VeiculoSaveDTO;

public class VeiculoDTOBuilder {
	
	private VeiculoSaveDTO veiculoDTO = new VeiculoSaveDTO();
	
	public VeiculoDTOBuilder comPlaca(String placa) {
		
		this.veiculoDTO.setPlaca(placa);
		
		return this;
	}
	
	public VeiculoDTOBuilder comAno(Integer ano) {
		
		this.veiculoDTO.setAno(ano);
		
		return this;
	}
	
	public VeiculoDTOBuilder comIdMarca(String idMarca) {
		
		this.veiculoDTO.setIdMarca(idMarca);
		
		return this;
	}
	
	public VeiculoDTOBuilder comIdModelo(String idModelo) {
		
		this.veiculoDTO.setIdModelo(idModelo);
		
		return this;
	}
	
	public VeiculoDTOBuilder comPrecoAnuncio(BigDecimal precoAnuncio) {
		
		this.veiculoDTO.setPrecoAnuncio(precoAnuncio);
		
		return this;
	}
	
	public VeiculoSaveDTO build() {
		return this.veiculoDTO;
	}
}