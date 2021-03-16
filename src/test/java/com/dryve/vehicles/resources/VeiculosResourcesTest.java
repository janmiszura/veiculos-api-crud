package com.dryve.vehicles.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.dryve.util.VeiculoDTOBuilder;
import com.dryve.vehicles.model.Vehicle;
import com.dryve.vehicles.model.VeiculoSaveDTO;
import com.dryve.vehicles.model.VeiculoVH;
import com.dryve.vehicles.repository.VehicleRepository;
import com.dryve.vehicles.services.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class VeiculosResourcesTest {
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	Vehicle vehicle;
	
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void mustSave() throws Exception {
		
		VeiculoSaveDTO veiculoDTO = new VeiculoDTOBuilder()
				.comPlaca("NNN0000")
				.comAno(2020)
				.comIdMarca("ca43ec74-5bb0-4288-ab11-5df094ca4dc4")
				.comIdModelo("5bc16064-d3ee-4aed-a264-a914233d0c4f")
				.comPrecoAnuncio(new BigDecimal(20000))
				.build();
		
		String json = mapper.writeValueAsString(veiculoDTO);
		
		this.mockMvc.perform(
				post("/v1/vehicles")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.data.id").exists())
		;
		
	}
	
	@Test
	public void mustList() throws Exception {
		
		VeiculoSaveDTO veiculoDTO = new VeiculoDTOBuilder()
				.comPlaca("NNN1111")
				.comAno(2020)
				.comIdMarca("ca43ec74-5bb0-4288-ab11-5df094ca4dc4")
				.comIdModelo("5bc16064-d3ee-4aed-a264-a914233d0c4f")
				.comPrecoAnuncio(new BigDecimal(20000))
				.build();
		
		vehicle = vehicleService.save(veiculoDTO);
		
		VeiculoVH veiculoVH = VeiculoVH.from(vehicle);
		
		this.mockMvc.perform(
				get("/v1/vehicles")
				)
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.data[0].id").exists())
		.andExpect(jsonPath("$.data[0].placa").value(veiculoVH.getPlaca()))
		.andExpect(jsonPath("$.data[0].modelo").value(veiculoVH.getModelo()))
		.andExpect(jsonPath("$.data[0].marca").value(veiculoVH.getMarca()))
		.andExpect(jsonPath("$.data[0].data_cadastro").exists())
		;
		
	}
	
	@Test
	public void mustFindById() throws Exception {
		
		String placa = "NNN2222";
		
		VeiculoSaveDTO veiculoDTO = new VeiculoDTOBuilder()
				.comPlaca(placa)
				.comAno(2020)
				.comIdMarca("ca43ec74-5bb0-4288-ab11-5df094ca4dc4")
				.comIdModelo("5bc16064-d3ee-4aed-a264-a914233d0c4f")
				.comPrecoAnuncio(new BigDecimal(20000))
				.build();
		
		vehicle = vehicleService.save(veiculoDTO);
		
		VeiculoVH veiculoVH = VeiculoVH.from(vehicle);
		
		this.mockMvc.perform(
				get("/v1/vehicles/"+vehicle.getId())
				)
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.data.id").value(veiculoVH.getId()))
		.andExpect(jsonPath("$.data.placa").value(placa))
		.andExpect(jsonPath("$.data.modelo").value(veiculoVH.getModelo()))
		.andExpect(jsonPath("$.data.marca").value(veiculoVH.getMarca()))
		.andExpect(jsonPath("$.data.data_cadastro").exists())
		;
		
	}
	
	//TODO testar na consulta por ID: a situação de ID não encontrado/existente
	//TODO testar na inclusão de veículo: as mensagens de validação 
	
	@AfterEach
	public void tearDown() {
		
		if( vehicle != null ) {
			vehicleRepository.delete(vehicle);			
		}
		
	}
}