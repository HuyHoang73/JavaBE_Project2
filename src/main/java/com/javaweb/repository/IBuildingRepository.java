package com.javaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;

public interface IBuildingRepository extends JpaRepository<BuildingEntity, Integer>, BuildingRepositoryCustom {
	List<BuildingEntity> findByNameContaining(String name);
	List<BuildingEntity> findByNameContainingAndWardContaining(String name, String ward);
	void deleteByIdIn(Integer[] ids);
}
