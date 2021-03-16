package com.dryve.vehicles.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dryve.vehicles.model.Vehicle;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, String> {

	
	Boolean existsByPlate(String plate);
	
}
