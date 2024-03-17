package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.repository.IBuildingRepository;
import com.javaweb.repository.IDistrictRepository;
import com.javaweb.repository.IRentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.IBuildingService;

@Service
public class BuildingServiceImpl implements IBuildingService {
	@Autowired
	private IDistrictRepository districtRepository;
	
	@Autowired
	private IBuildingRepository buildingRepository;
	
	@Autowired
	private IRentAreaRepository rentAreaRepository;
	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(params, typeCode);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for (BuildingEntity item : buildingEntities) {
			BuildingDTO building = new BuildingDTO();
			building.setName(item.getName());
			String districtName = districtRepository.findNameByID(item.getDistrictID());
			building.setAddress(districtName + "," + item.getWard() + "," + item.getStreet());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhone(item.getManagerPhonenumber());
			building.setFloorArea(item.getFloorArea());
			List<Integer> listRentArea = new ArrayList<Integer>();
			listRentArea.addAll(rentAreaRepository.findValueByBuildingID(item.getId()));
			building.setRentArea(listRentArea);
			building.setDeposit(item.getDeposit());
			building.setRentPrice(item.getRentPrice());
			building.setServicefee(item.getServiceFee());
			result.add(building);
		}
		return result;
	}

}
