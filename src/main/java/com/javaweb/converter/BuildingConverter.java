package com.javaweb.converter;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;

@Component
public class BuildingConverter {

//	@Autowired
//	private IDistrictRepository districtRepository;
//
//	@Autowired
//	private IRentAreaRepository rentAreaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Phương thức này để chuyển đổi từ BuildingEntity sang BuildingDTO
	 * @param item - dưới dạng Entity
	 * @return item - dưới dạng DTO
	 */
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
		building.setAddress(item.getStreet() + "," + item.getWard() + "," + item.getDistrict().getName());
		String rentAreas = item.getRentAreas().stream().map(i -> i.getValue().toString()).collect(Collectors.joining(", "));
		building.setRentArea(rentAreas);
		building.setFreeArea(null);
		return building;
	}
	
	
}
