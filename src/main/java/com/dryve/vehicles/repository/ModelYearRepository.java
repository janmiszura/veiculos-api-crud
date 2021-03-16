package com.dryve.vehicles.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dryve.vehicles.model.ModelYear;

@Repository
public interface ModelYearRepository extends CrudRepository<ModelYear, String> {

	//ModelYear findByYearModel(Integer year, Model model);
	
	@Query("SELECT m FROM ModelYear m where m.year = :year and m.model.id = :modelId")
    ModelYear findByYearModel(@Param("year") Integer year, @Param("modelId") String modelId);
	
}
