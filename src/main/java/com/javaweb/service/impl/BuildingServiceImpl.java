package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.DTO.BuildingRequestDTO;
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
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {
		BuildingSearchBuilder builder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCode);
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(builder);
//		List<BuildingEntity> buildingEntities = buildingRepository.findByNameContaining(builder.getName());
//		BuildingEntity buildingEntity2 = buildingRepository.findById(2).get();
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for (BuildingEntity item : buildingEntities) {
			BuildingDTO building = buildingConverter.toBuildingDTO(item);
			result.add(building);
		}
		return result;
	}

	@Override
	public void createBuilding(BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity buildingEntity =  modelMapper.map(buildingRequestDTO, BuildingEntity.class);
		//Đối với hàm save(), nếu có id là update, không có id là thêm mới
		buildingRepository.save(buildingEntity);
	}

	@Override
	public void deleteBuilding(Integer[] ids) {
		buildingRepository.deleteByIdIn(ids);
		
	}

}
