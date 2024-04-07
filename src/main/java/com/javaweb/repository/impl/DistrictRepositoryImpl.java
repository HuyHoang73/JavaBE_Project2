package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.IDistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.ConnectionUtils;

@Repository
public class DistrictRepositoryImpl implements IDistrictRepository {

	@Override
	public DistrictEntity findNameByID(Integer districtID) {
		StringBuilder sql = new StringBuilder("SELECT name FROM district ");
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		if (districtID != null) {
			where.append(" AND id = " + districtID + " ");
		}
		sql.append(where);
		
		DistrictEntity districtEntity = new DistrictEntity();
		try (Connection conn = ConnectionUtils.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql.toString())) {
			while(rs.next()) {
				districtEntity.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return districtEntity;
	}

}
