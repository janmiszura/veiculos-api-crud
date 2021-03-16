package com.dryve.vehicles.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dryve.exceptions.ModelYearNotFoundException;
import com.dryve.exceptions.PlacaFormatoInvalidoException;
import com.dryve.exceptions.PlacaJaExistenteException;
import com.dryve.exceptions.PlacaNulaException;
import com.dryve.util.VeiculoDTOBuilder;
import com.dryve.vehicles.model.Vehicle;
import com.dryve.vehicles.model.VeiculoSaveDTO;
import com.dryve.vehicles.repository.VehicleRepository;

@SpringBootTest
public class VehicleServiceTest {

	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	VeiculoSaveDTO veiculoDTO;
	
	Vehicle vehicle;
	
	public VehicleServiceTest() {
		super();
		veiculoDTO = new VeiculoDTOBuilder()
				.comPlaca("NNN9999")
				.comAno(2020)
				.comIdMarca("ca43ec74-5bb0-4288-ab11-5df094ca4dc4")
				.comIdModelo("5bc16064-d3ee-4aed-a264-a914233d0c4f")
				.comPrecoAnuncio(new BigDecimal(20000))
				.build();
	}

	@Test
	public void mustValidatePlacaNula() throws Exception {
		
		Assertions.assertThrows(PlacaNulaException.class, () -> {
		    
			VeiculoSaveDTO v = new VeiculoSaveDTO();
			vehicleService.save(v);
			
		});
		
	}
	
	@Test
	public void mustValidatePlacaVazia() throws Exception {
		
		Assertions.assertThrows(PlacaNulaException.class, () -> {
			
			VeiculoSaveDTO v = new VeiculoSaveDTO();
			v.setPlaca("");
			vehicleService.save(v);
			
		});
		
	}
	
	@Test
	public void mustValidatePlacaFormatoInvalido() throws Exception {
		
		Assertions.assertThrows(PlacaFormatoInvalidoException.class, () -> {
			
			VeiculoSaveDTO v = new VeiculoSaveDTO();
			v.setPlaca("NNN");
			vehicleService.save(v);
			
		});
		
	}
	
	@Test
	public void mustValidatePlacaJaExistente() throws Exception {
		
		vehicle = vehicleService.save(veiculoDTO);
		
		Assertions.assertThrows(PlacaJaExistenteException.class, () -> {
		    
			vehicleService.save(veiculoDTO);
			
		});
		
	}
	
	@Test
	public void mustValidateModelYearNotFound() throws Exception {
		
		VeiculoSaveDTO veiculoDTO = new VeiculoDTOBuilder()
				.comPlaca("NNN7777")
				.comAno(3000)
				.comIdMarca("ca43ec74-5bb0-4288-ab11-5df094ca4dc4")
				.comIdModelo("5bc16064-d3ee-4aed-a264-a914233d0c4f")
				.comPrecoAnuncio(new BigDecimal(20000))
				.build();
		
		Assertions.assertThrows(ModelYearNotFoundException.class, () -> {
		    
			vehicleService.save(veiculoDTO);
			
		});
		
	}
	
	@Test
	public void mustSaveSucess() throws Exception {
		
		vehicle = vehicleService.save(veiculoDTO);
		
		assertNotNull(vehicle.getId());
		
	}
	
	@AfterEach
	public void tearDown() {
		
		if( vehicle != null ) {
			vehicleRepository.delete(vehicle);			
		}
		
	}
	
}
