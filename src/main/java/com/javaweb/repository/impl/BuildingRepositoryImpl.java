package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.IBuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtils;

@Repository
public class BuildingRepositoryImpl implements IBuildingRepository {
	private String createJoinQuery(Map<String, Object> params, List<String> typeCode) {
		String join = "";
		// DistrictID
		if (params.containsKey("districtid") && params.get("districtid") instanceof Integer) {
			join += " INNER JOIN district on building.districtid = district.id ";
		}

		// MinArea
		if (params.containsKey("minarea") && params.get("minarea") instanceof Integer) {
			join += " INNER JOIN rentarea on building.id = rentarea.buildingid ";
		}

		// MaxArea
		if (params.containsKey("maxarea") && params.get("maxarea") instanceof Integer) {
			join += " INNER JOIN rentarea on building.id = rentarea.buildingid ";
		}

		// StaffID
		if (params.containsKey("staffid") && params.get("staffid") instanceof Integer) {
			join += " INNER JOIN assignmentbuilding on building.id = assignmentbuilding.buildingid "
					+ " INNER JOIN user on assignmentbuilding.staffid = user.id ";
		}

		// TypeCode
		if (typeCode != null && !typeCode.isEmpty()) {
			join += " INNER JOIN buildingrenttype on building.id = buildingrenttype.buildingid "
					+ " INNER JOIN renttype on buildingrenttype.renttypeid = renttype.id";
		}
		return join;
	}

	private String createWhereQuery(Map<String, Object> params, List<String> typeCode) {
		String where = "WHERE 1 = 1 ";
		// Name
		if (params.containsKey("name") && params.get("name") != null && !params.get("name").toString().equals("")) {
			String name = params.get("name").toString();
			where += " AND name LIKE '%" + name + "%' ";
		}

		// FloorArea
		if (params.containsKey("floorarea") && params.get("floorarea") instanceof Integer) {
			int floorArea = (Integer) params.get("floorarea");
			where += " AND floorarea = " + floorArea + " ";
		}

		// DistrictID
		if (params.containsKey("districtid") && params.get("districtid") instanceof Integer) {
			int districtID = (Integer) params.get("districtid");
			where += " AND districtid = " + districtID + " ";
		}

		// Ward
		if (params.containsKey("ward") && params.get("ward") != null && !params.get("ward").toString().equals("")) {
			String ward = params.get("ward").toString();
			where += " AND ward LIKE '%" + ward + "%' ";
		}

		// Street
		if (params.containsKey("street") && params.get("street") != null
				&& !params.get("street").toString().equals("")) {
			String street = params.get("street").toString();
			where += " AND street LIKE '%" + street + "%' ";
		}

		// NumberOfBasement
		if (params.containsKey("numberofbasement") && params.get("numberofbasement") instanceof Integer) {
			int numberOfBasement = (Integer) params.get("numberofbasement");
			where += " AND numberofbasement = " + numberOfBasement + " ";
		}

		// Direction
		if (params.containsKey("direction") && params.get("direction") != null
				&& !params.get("direction").toString().equals("")) {
			String direction = params.get("direction").toString();
			where += " AND direction LIKE '%" + direction + "%' ";
		}

		// Level
		if (params.containsKey("level") && params.get("level") != null && !params.get("level").toString().equals("")) {
			String level = params.get("level").toString();
			where += " AND level LIKE '%" + level + "%' ";
		}

		// MinArea
		if (params.containsKey("minarea") && params.get("minarea") instanceof Integer) {
			int minArea = (Integer) params.get("minarea");
			where += " AND value >= " + minArea + " ";
		}

		// MaxArea
		if (params.containsKey("maxarea") && params.get("maxarea") instanceof Integer) {
			int maxArea = (Integer) params.get("maxarea");
			where += " AND value <= " + maxArea + " ";
		}

		// MinPrice
		if (params.containsKey("minprice") && params.get("minprice") instanceof Integer) {
			Integer minPrice = ((Integer) params.get("minprice")).intValue();
			where += " AND rentprice >= " + minPrice + " ";
		}

		// MaxPrice
		if (params.containsKey("maxprice") && params.get("maxprice") instanceof Integer) {
			int maxPrice = (int) params.get("maxprice");
			where += " AND rentprice <= " + maxPrice + " ";
		}

		// ManagerName
		if (params.containsKey("managername") && params.get("managername") != null
				&& !params.get("managername").toString().equals("")) {
			String managerName = params.get("managername").toString();
			where += " AND managername LIKE '%" + managerName + "%' ";
		}

		// ManagerPhoneNumber
		if (params.containsKey("managerphonenumber") && params.get("managerphonenumber") != null
				&& !params.get("managerphonenumber").toString().equals("")) {
			String managerPhonenumber = params.get("managerphonenumber").toString();
			where += " AND managerphonenumber LIKE '%" + managerPhonenumber + "%' ";
		}

		// StaffID
		if (params.containsKey("staffid") && params.get("staffid") instanceof Integer) {
			int staffID = ((Integer) params.get("staffid")).intValue();
			where += " AND staffid = " + staffID + " ";
		}

		// TypeCode
		if (typeCode != null && !typeCode.isEmpty()) {
			StringBuilder typeCondition = new StringBuilder(" AND code IN (");
			for (String code : typeCode) {
				typeCondition.append("'").append(code).append("',");
			}
			typeCondition.deleteCharAt(typeCondition.length() - 1);
			typeCondition.append(")");
			where += typeCondition.toString();
		}
		return where;
	}

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {
		String sql = "SELECT * FROM building ";
		String where = createWhereQuery(params, typeCode);
		String join = createJoinQuery(params, typeCode);
		sql = sql + join + where;

		List<BuildingEntity> result = new ArrayList<BuildingEntity>();
		try (Connection conn = ConnectionUtils.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {
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
