package com.javaweb.repository;

import java.util.List;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.entity.BuildingEntity;

public interface IBuildingRepository {
	List<BuildingEntity> findAll(BuildingSearchBuilder builder);
}
