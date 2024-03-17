package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.IRentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.utils.ConnectionUtils;

@Repository
public class RentAreaRepositoryImpl implements IRentAreaRepository{

	@Override
	public List<Integer> findValueByBuildingID(Integer buildingID) {
		String sql = "SELECT value FROM rentarea ";
		String where = " WHERE 1 = 1 ";
		if (buildingID != null) {
			where += " AND buildingid = " + buildingID + " ";
		}
		sql += where;
		List<Integer> result = new ArrayList<Integer>();
		try (Connection conn = ConnectionUtils.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {
			while(rs.next()) {
				RentAreaEntity rentAreaEntity = new RentAreaEntity();
				rentAreaEntity.setValue(rs.getInt("value"));
				result.add(rentAreaEntity.getValue());
			}
			System.out.println("Connected successfully...");
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected failed...");
		}
		return null;
	}
	
}
