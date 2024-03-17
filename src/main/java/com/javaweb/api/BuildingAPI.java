package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.customexception.FieldRequiredException;
import com.javaweb.service.IBuildingService;

@RestController
public class BuildingAPI {
	
	@Autowired
	private IBuildingService buildingService;
	//params	
	@GetMapping(value = "/api/building")
	public List<BuildingDTO> findBuilding(@RequestParam Map<String, Object> params,
			@RequestParam(name = "typeCode",required = false) List<String> typeCode) {
		List<BuildingDTO> result = buildingService.findAll(params, typeCode);
		return result;
	}
	
	public void validateData(BuildingDTO buildingDTO) {
		if(buildingDTO.getName() == null || buildingDTO.getName().equals("")) {
			throw new FieldRequiredException("Name or address is null");
		}
	}
	
//	@PostMapping(value = "/api/building")
//	public Object createBuilding(@RequestBody BuildingDTO buildingDTO) {
//		validateData(buildingDTO);
//		System.out.println("OK");
//		return buildingDTO;
//	}
//	
//	@DeleteMapping(value = "/api/building/{ids}/{name}")
//	public void deleteBuilding(@PathVariable Long[] ids) {
//		System.out.println(ids);
//	}
}
