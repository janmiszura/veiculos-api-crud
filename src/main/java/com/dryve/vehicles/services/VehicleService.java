package com.dryve.vehicles.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.dryve.exceptions.KBBPriceNotFoundException;
import com.dryve.exceptions.ModelYearNotFoundException;
import com.dryve.exceptions.PlacaFormatoInvalidoException;
import com.dryve.exceptions.PlacaJaExistenteException;
import com.dryve.exceptions.PlacaNulaException;
import com.dryve.vehicles.model.ModelYear;
import com.dryve.vehicles.model.Vehicle;
import com.dryve.vehicles.model.VeiculoSaveDTO;
import com.dryve.vehicles.repository.ModelYearRepository;
import com.dryve.vehicles.repository.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	@Autowired
	ModelYearRepository modelYearRepository;
	
	@Autowired
	KBBTablePriceFactory kbbTablePriceFactory;
	
	public Vehicle save(VeiculoSaveDTO veiculoDTO) throws Exception {
		
		Boolean placaTemTexto = StringUtils.hasText(veiculoDTO.getPlaca());
		
		if( ! placaTemTexto ) {
			throw new PlacaNulaException();
		}
		
		// TODO validar formato com regex
		Boolean placaFormatoValido = veiculoDTO.getPlaca().length() == 7;
		
		if( ! placaFormatoValido ) {
			throw new PlacaFormatoInvalidoException();
		}
		
		Boolean placaJaExistente = vehicleRepository.existsByPlate(veiculoDTO.getPlaca());
		
		if( placaJaExistente ) {
			throw new PlacaJaExistenteException();
		}
		
		ModelYear modelYear = modelYearRepository.findByYearModel(veiculoDTO.getAno(), veiculoDTO.getIdModelo());
		
		if( modelYear == null ) {
			throw new ModelYearNotFoundException();
		}
		
		KBBTablePrice kbbTablePrice = kbbTablePriceFactory.getImpl();
		
		KBBPrice kbbPrice = kbbTablePrice.findById(modelYear.getKbbId());
		
		if( kbbPrice == null ) {
			throw new KBBPriceNotFoundException();
		}
		
		Vehicle vehicle = new Vehicle();
		
		vehicle.setId( veiculoDTO.getId() != null ? veiculoDTO.getId() : UUID.randomUUID().toString() );
		
		vehicle.setAdPrice(veiculoDTO.getPrecoAnuncio());
		
		vehicle.setModelYear(modelYear);
		
		vehicle.setPlate(veiculoDTO.getPlaca());
		
		vehicle = vehicleRepository.save(vehicle);
		
		return vehicle;
	}

	public List<Vehicle> findAll() {
		return (List<Vehicle>) vehicleRepository.findAll();
	}
	
	public Optional<Vehicle> findById(String id) {
		
		Assert.hasText(id, "ID must be not null");
		
		return vehicleRepository.findById(id);
	}
	
	
}
