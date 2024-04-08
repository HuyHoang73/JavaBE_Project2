package com.javaweb.converter;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.repository.IDistrictRepository;
import com.javaweb.repository.IRentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Component
public class BuildingConverter {
	@Autowired
	private IDistrictRepository districtRepository;

	@Autowired
	private IRentAreaRepository rentAreaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Phương thức này để chuyển đổi từ BuildingEntity sang BuildingDTO
	 * @param item - dưới dạng Entity
	 * @return item - dưới dạng DTO
	 */
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
		String districtName = districtRepository.findNameByID(item.getDistrictID()).getName();
		building.setAddress(item.getStreet() + "," + item.getWard() + "," + districtName);
		List<String> convertedRentAreas = rentAreaRepository.findValueByBuildingID(item.getId());
		building.setRentArea(String.join(", ", convertedRentAreas));
		building.setFreeArea(null);
		return building;
	}
	
	
}
