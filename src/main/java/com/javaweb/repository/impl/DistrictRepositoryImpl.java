package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.IDistrictRepository;
import com.javaweb.utils.ConnectionUtils;

@Repository
public class DistrictRepositoryImpl implements IDistrictRepository {

	@Override
	public String findNameByID(Integer districtID) {
		String sql = "SELECT name FROM district ";
		String where = " WHERE 1 = 1 ";
		if (districtID != null) {
			where += " AND id = " + districtID + " ";
		}
		sql += where;
		String result = null;
		try (Connection conn = ConnectionUtils.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {
			if (rs.next()) {
				result = rs.getString("name");
			} else {
				result = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected failed...");
		}
		return result;
	}

}
