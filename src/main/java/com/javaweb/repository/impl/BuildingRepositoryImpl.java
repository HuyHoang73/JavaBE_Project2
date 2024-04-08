package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.IBuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtils;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements IBuildingRepository {
	/**
	 * Hàm này để tạo ra câu join trong sql
	 * 
	 * @param params   - các trường thông thường
	 * @param typeCode - trường dạng list
	 * @param sql      - câu sql gốc
	 */
	public void createJoinQuery(Map<String, Object> params, List<String> typeCode, StringBuilder sql) {
		// MinArea và MaxArea
		String minArea = (String) params.get("minarea");
		String maxArea = (String) params.get("maxarea");
		if (StringUtil.checkString(maxArea) || StringUtil.checkString(minArea)) {
			sql.append(" INNER JOIN rentarea r ON b.id = r.buildingid ");
		}

		// StaffID
		String staffID = (String) params.get("staffid");
		if (StringUtil.checkString(staffID)) {
			sql.append(" INNER JOIN assignmentbuilding asm on b.id = asm.buildingid ");
		}

		// TypeCode
		if (typeCode != null && !typeCode.isEmpty()) {
			sql.append(" INNER JOIN buildingrenttype brt on b.id = brt.buildingid "
					+ " INNER JOIN renttype rt on brt.renttypeid = rt.id ");
		}
	}

	private void createWhereQueryNormal(Map<String, Object> params, StringBuilder where) {
		for (Map.Entry<String, Object> item : params.entrySet()) {
			if (!item.getKey().equals("staffid") && !item.getKey().equals("typeCode")
					&& !item.getKey().startsWith("min") && !item.getKey().startsWith("max")) {
				String data = item.getValue().toString(); // Do gia tri tra ve dang o kieu object nen phai chuyen ve
															// chuoi
				if (StringUtil.checkString(data)) {
					if (NumberUtil.checkNumber(data)) {
						where.append(" AND b." + item.getKey() + " = " + data + " ");
					} else {
						where.append(" AND b." + item.getKey() + " like '%" + data + "%' ");
					}
				}
			}
		}
	}

	private void createWhereQuerySpecial(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
		String minArea = (String) params.get("minarea");
		String maxArea = (String) params.get("maxarea");
		if (StringUtil.checkString(maxArea)) {
			where.append(" AND r.value <= " + maxArea);
		}
		if (StringUtil.checkString(minArea)) {
			where.append(" AND r.value >= " + minArea);
		}

		String minPrice = (String) params.get("minprice");
		String maxPrice = (String) params.get("maxprice");
		if (StringUtil.checkString(maxPrice)) {
			where.append(" AND b.rentprice <= " + maxPrice);
		}
		if (StringUtil.checkString(minPrice)) {
			where.append(" AND b.rentprice >= " + minPrice);
		}

		String staffID = (String) params.get("staffid");
		if (StringUtil.checkString(staffID)) {
			where.append(" AND asm.staffid = " + staffID);
		}
//		if(params.containsKey("typeCode")) {
//			if(typeCode != null && !typeCode.isEmpty()) {
//				where.append(" AND rt.code IN (");
//				for(int i = 0; i < typeCode.size(); i++) {
//					where.append("'" + typeCode.get(i) + "'");
//					if(i < typeCode.size() - 1) {
//						where.append(",");
//					}
//				}
//				where.append(") ");
//			}
//		}
		if (typeCode != null && !typeCode.isEmpty()) {
			where.append(" AND rt.code IN (");
			String sqlJoin = typeCode.stream().map(item -> "'" + item + "'").collect(Collectors.joining(","));
			where.append(sqlJoin + ") ");
		}
	}

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {
		StringBuilder sql = new StringBuilder(
				"SELECT b.id, b.name, b.districtid, b.street, b.ward, b.numberofbasement, b.managername, b.managerphonenumber, b.floorarea, b.rentprice, b.brokeragefee, b.servicefee, b.deposit FROM building b ");
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		createJoinQuery(params, typeCode, sql);
		createWhereQueryNormal(params, where);
		createWhereQuerySpecial(params, typeCode, where);
		sql.append(where);
		sql.append(" GROUP BY b.id ");

		List<BuildingEntity> result = new ArrayList<BuildingEntity>();
		try (Connection conn = ConnectionUtils.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql.toString())) {
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getInt("id"));
				building.setName(rs.getString("name"));
				building.setNumberOfBasement(rs.getInt("numberofbasement"));
				building.setFloorArea(rs.getInt("floorarea"));
				building.setDistrictID(rs.getInt("districtID"));
				building.setWard(rs.getString("ward"));
				building.setStreet(rs.getString("street"));
				building.setManagerName(rs.getString("managername"));
				building.setManagerPhonenumber(rs.getString("managerphonenumber"));
				building.setRentPrice(rs.getInt("rentprice"));
				building.setDeposit(rs.getString("deposit"));
				building.setServiceFee(rs.getString("servicefee"));
				result.add(building);
			}
			System.out.println("Connected successfully...");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected failed...");
		}
		return result;
	}
}
