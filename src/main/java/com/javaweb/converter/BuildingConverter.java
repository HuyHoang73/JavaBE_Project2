package com.javaweb.converter;

import java.util.List;

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
	
	/**
	 * Phương thức này để chuyển đổi từ BuildingEntity sang BuildingDTO
	 * @param item - dưới dạng Entity
	 * @return item - dưới dạng DTO
	 */
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = new BuildingDTO();
		building.setName(item.getName());
		String districtName = districtRepository.findNameByID(item.getDistrictID()).getName();
		building.setAddress(item.getStreet() + "," + item.getWard() + "," + districtName);
		building.setNumberOfBasement(item.getNumberOfBasement());
		building.setManagerName(item.getManagerName());
		building.setManagerPhone(item.getManagerPhonenumber());
		building.setFloorArea(item.getFloorArea());
		List<String> convertedRentAreas = rentAreaRepository.findValueByBuildingID(item.getId());
		building.setRentArea(String.join(", ", convertedRentAreas));
		building.setDeposit(item.getDeposit());
		building.setRentPrice(item.getRentPrice());
		building.setServicefee(item.getServiceFee());
		building.setFreeArea(null);
		return building;
	}
	
	
}
