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
	public List<String> findValueByBuildingID(Integer buildingID) {
		StringBuilder sql = new StringBuilder("SELECT value FROM rentarea ");
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		if (buildingID != null) {
			where.append(" AND buildingid = " + buildingID + " ");
		}
		sql.append(where);
		
		List<String> result = new ArrayList<String>();
		try (Connection conn = ConnectionUtils.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql.toString())) {
			while(rs.next()) {
				RentAreaEntity rentAreaEntity = new RentAreaEntity();
				rentAreaEntity.setValue(rs.getLong("value"));
				result.add(rentAreaEntity.getValue().toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
