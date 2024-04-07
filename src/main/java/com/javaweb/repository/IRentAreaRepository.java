package com.javaweb.repository;

import java.util.List;

public interface IRentAreaRepository {
	List<String> findValueByBuildingID(Integer buildingID);
}
