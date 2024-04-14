package com.javaweb.api;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.DTO.BuildingRequestDTO;
import com.javaweb.customexception.FieldRequiredException;
import com.javaweb.service.IBuildingService;

@RestController
@Transactional
public class BuildingAPI {

	@Autowired
	private IBuildingService buildingService;

	// params
	@GetMapping(value = "/api/building")
	public List<BuildingDTO> findBuilding(@RequestParam Map<String, Object> params,
			@RequestParam(name = "typeCode", required = false) List<String> typeCode) {
		List<BuildingDTO> result = buildingService.findAll(params, typeCode);
		return result;
	}

	public void validateData(BuildingRequestDTO buildingRequestDTO) {
		if (buildingRequestDTO.getName() == null || buildingRequestDTO.getName().equals("")) {
			throw new FieldRequiredException("Name or address is null");
		}
	}

	@PostMapping(value = "/api/building")
	public void createBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
//		validateData(buildingRequestDTO);
		buildingService.createBuilding(buildingRequestDTO);
	}

	@DeleteMapping(value = "/api/building/{id}")
	public void deleteBuilding(@PathVariable Integer[] ids) {
		buildingService.deleteBuilding(ids);
	}
}
