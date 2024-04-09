package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.repository.IBuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.IBuildingService;

@Service
public class BuildingServiceImpl implements IBuildingService {
	@Autowired
	private IBuildingRepository buildingRepository;
	
	@Autowired
	private BuildingConverter buildingConverter;
	
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
	
	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {
		BuildingSearchBuilder builder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCode);
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(builder);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for (BuildingEntity item : buildingEntities) {
			BuildingDTO building = buildingConverter.toBuildingDTO(item);
			result.add(building);
		}
		return result;
	}

}
