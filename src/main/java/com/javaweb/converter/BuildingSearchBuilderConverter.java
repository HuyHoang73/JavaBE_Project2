package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtils;

@Component
public class BuildingSearchBuilderConverter {
	/**
	 * Phương thức này dùng để chuyển các tham số  thành 1 kiểu dữ liệu builder. Điều này giúp cho từ nhiều tham số chuyển thành 1 tham số
	 * @param params - các trường trả về bình thường trên param
	 * @param typeCode - kiểu trả về dạng list trên param
	 * @return tất cả giá trị đẩy về nhưng dưới dạng kiểu dữ liệu builder
	 */
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCode) {
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
																	.setName(MapUtils.getObject(params, "name", String.class))
																	.setWard(MapUtils.getObject(params, "ward", String.class))
																	.setDistrictID(MapUtils.getObject(params, "districtid", Integer.class))
																	.setStreet(MapUtils.getObject(params, "street", String.class))
																	.setNumberOfBasement(MapUtils.getObject(params, "numberofbasement", Integer.class))
																	.setTypeCode(typeCode)
																	.setManagerName(MapUtils.getObject(params, "managername", String.class))
																	.setManagerPhonenumber(MapUtils.getObject(params, "managerphonenumber", String.class))
																	.setFloorArea(MapUtils.getObject(params, "floorarea", Integer.class))
																	.setDirection(MapUtils.getObject(params, "direction", String.class))
																	.setMinArea(MapUtils.getObject(params, "minarea", String.class))
																	.setMaxArea(MapUtils.getObject(params, "maxarea", String.class))
																	.setMinPrice(MapUtils.getObject(params, "minprice", Integer.class))
																	.setMaxPrice(MapUtils.getObject(params, "maxprice", Integer.class))
																	.setLevel(MapUtils.getObject(params, "level", String.class))
																	.setStaffID(MapUtils.getObject(params, "staffid", Integer.class))
																	.build();					
		return builder;
	}
}
