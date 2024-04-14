package com.javaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
@Primary
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Hàm này để tạo ra câu join trong sql
	 * 
	 * @param builder - các giá trị trả về
	 * @param sql     - câu sql gốc
	 */
	public void createJoinQuery(BuildingSearchBuilder builder, StringBuilder sql) {
		// MinArea và MaxArea
		String minArea = (String) builder.getMinArea();
		String maxArea = (String) builder.getMaxArea();
		if (maxArea != null || minArea != null) {
			sql.append(" INNER JOIN rentarea r ON b.id = r.buildingid ");
		}

		// StaffID
		Integer staffID = builder.getStaffID();
		if (staffID != null) {
			sql.append(" INNER JOIN assignmentbuilding asm on b.id = asm.buildingid ");
		}

		// TypeCode
		List<String> typeCode = builder.getTypeCode();
		if (typeCode != null && !typeCode.isEmpty()) {
			sql.append(" INNER JOIN buildingrenttype brt on b.id = brt.buildingid "
					+ " INNER JOIN renttype rt on brt.renttypeid = rt.id ");
		}
}

	private void createWhereQueryNormal(BuildingSearchBuilder builder, StringBuilder where) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if (!fieldName.equals("staffid") && !fieldName.equals("typeCode") && !fieldName.startsWith("min")
						&& !fieldName.startsWith("max")) {
					Object value = item.get(builder);
					if (value != null) {
						if (item.getType().getName().equals("java.lang.Integer")) {
							where.append(" AND b." + fieldName + " = " + value + " ");
						} else if (item.getType().getName().equals("java.lang.Long")) {
							where.append(" AND b." + fieldName + " = " + value + " ");
						} else if (item.getType().getName().equals("java.lang.String")) {
							where.append(" AND b." + fieldName + " like '%" + value + "%' ");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createWhereQuerySpecial(BuildingSearchBuilder builder, StringBuilder where) {
		String minArea = (String) builder.getMinArea();
		String maxArea = (String) builder.getMaxArea();
		if (maxArea != null) {
			where.append(" AND r.value <= " + maxArea);
		}
		if (minArea != null) {
			where.append(" AND r.value >= " + minArea);
		}

		Integer minPrice = builder.getMinPrice();
		Integer maxPrice = builder.getMaxPrice();
		if (maxPrice != null) {
			where.append(" AND b.rentprice <= " + maxPrice.toString());
		}
		if (minPrice != null) {
			where.append(" AND b.rentprice >= " + minPrice.toString());
		}

		Integer staffID = builder.getStaffID();
		if (staffID != null) {
			where.append(" AND asm.staffid = " + staffID.toString());
		}

		List<String> typeCode = builder.getTypeCode();
		if (typeCode != null && !typeCode.isEmpty()) {
			where.append(" AND rt.code IN (");
			String sqlJoin = typeCode.stream().map(item -> "'" + item + "'").collect(Collectors.joining(","));
			where.append(sqlJoin + ") ");
		}
	}

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder builder) {
		StringBuilder sql = new StringBuilder("SELECT b.* FROM building b ");
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		createJoinQuery(builder, sql);
		createWhereQueryNormal(builder, where);
		createWhereQuerySpecial(builder, where);
		sql.append(where);
		sql.append(" GROUP BY b.id ");
		Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList();
	}
}


