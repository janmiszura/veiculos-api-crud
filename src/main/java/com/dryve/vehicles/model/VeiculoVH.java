package com.dryve.vehicles.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VeiculoVH {

	private String id;
	
	private String placa;
	
	private BigDecimal preco_anuncio;
	
	private Integer ano;
	
	private BigDecimal preco_kbb;
	
	private String data_cadastro;
	
	private String modelo;

	private String marca;
	
	
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

	public BigDecimal getPreco_anuncio() {
		return preco_anuncio;
	}

	public void setPreco_anuncio(BigDecimal preco_anuncio) {
		this.preco_anuncio = preco_anuncio;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public BigDecimal getPreco_kbb() {
		return preco_kbb;
	}

	public void setPreco_kbb(BigDecimal preco_kbb) {
		this.preco_kbb = preco_kbb;
	}

	public String getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(String data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "VeiculoVH [id=" + id + ", placa=" + placa + ", preco_anuncio=" + preco_anuncio + ", ano=" + ano
				+ ", preco_kbb=" + preco_kbb + ", data_cadastro=" + data_cadastro + ", modelo=" + modelo + ", marca="
				+ marca + "]";
	}
	
	public static Optional<VeiculoVH> from(Optional<Vehicle> vehicle) {
		
		if( ! vehicle.isPresent() ) {
			return Optional.empty();
		}
		
		VeiculoVH vh = VeiculoVH.from(vehicle.get());
		
		return Optional.of(vh);
	}
	
	public static List<VeiculoVH> from(List<Vehicle> vehicles) {
		
		List<VeiculoVH> veiculos = new ArrayList<>();
		
		for (Vehicle v : vehicles) {
			
			veiculos.add( VeiculoVH.from(v) );
			
		}
		
		return veiculos;
	}
	
	public static VeiculoVH from(Vehicle vehicle) {
		
		//TODO put in a DateUtil class
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		VeiculoVH vh = new VeiculoVH();
		
		vh.id = vehicle.getId();
		vh.placa = vehicle.getPlate();
		vh.preco_anuncio = vehicle.getAdPrice();
		vh.ano = vehicle.getModelYear().getYear();
		vh.preco_kbb = vehicle.getPriceKBB();
		vh.modelo = vehicle.getModelYear().getModel().getName();
		vh.marca = vehicle.getModelYear().getModel().getBrand().getName();
		vh.data_cadastro = sdf.format(vehicle.getCreatedAt());

		return vh;
	}
	
	
}