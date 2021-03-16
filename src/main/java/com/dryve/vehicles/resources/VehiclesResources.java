package com.dryve.vehicles.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dryve.util.APIResponse;
import com.dryve.vehicles.model.Vehicle;
import com.dryve.vehicles.model.VeiculoSaveDTO;
import com.dryve.vehicles.model.VeiculoVH;
import com.dryve.vehicles.services.VehicleService;

@RestController
public class VehiclesResources {
	
	@Autowired
	private VehicleService vehicleService;
	
	@GetMapping("/v1/vehicles")
	public ResponseEntity<APIResponse> listar() {
		
		List<Vehicle> vehicles = vehicleService.findAll();
		
		List<VeiculoVH> veiculosVH = VeiculoVH.from(vehicles);
		
		return new ResponseEntity<APIResponse>(APIResponse.success(veiculosVH), HttpStatus.OK);
	}
	
	@GetMapping("/v1/vehicles/{id}")
	public ResponseEntity<APIResponse> listar(@PathVariable String id) {
		
		Optional<Vehicle> vehicle = vehicleService.findById(id);
		
		if( ! vehicle.isPresent() ) {
			return new ResponseEntity<APIResponse>(APIResponse.errorWithMessage("Veículo não encontrado."), HttpStatus.NOT_FOUND);
		}
		
		Optional<VeiculoVH> veiculoVH = VeiculoVH.from(vehicle);
		
		return new ResponseEntity<APIResponse>(APIResponse.success(veiculoVH.get()), HttpStatus.OK);
	}
	
	@PostMapping("/v1/vehicles")
	public ResponseEntity<APIResponse> save(@RequestBody VeiculoSaveDTO veiculoDTO) throws Exception {
		
		Vehicle vehicle = vehicleService.save(veiculoDTO);
		
		return new ResponseEntity<APIResponse>(APIResponse.success(vehicle), HttpStatus.CREATED);
	}
	
}
