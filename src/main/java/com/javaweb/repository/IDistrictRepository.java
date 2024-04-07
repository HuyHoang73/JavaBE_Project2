package com.javaweb.repository;

import com.javaweb.repository.entity.DistrictEntity;

public interface IDistrictRepository {
	DistrictEntity findNameByID(Integer districtID);
}
