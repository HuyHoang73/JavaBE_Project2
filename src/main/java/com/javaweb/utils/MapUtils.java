package com.javaweb.utils;

import java.util.Map;

public class MapUtils {
	/**
	 * Phương thức này dùng để biến đổi kiểu dữ liệu của các trường đẩy về thành kiểu dữ liệu mong muốn
	 * @param <T>
	 * @param params - các trường trả về
	 * @param key - key trả về
	 * @param tClass - kiểu dữ liệu mong muốn
	 * @return object sau khi đã được chuyển đổi kiểu dữ liệu
	 */
	public static<T> T getObject(Map<String, Object> params, String key, Class<T> tClass) {
		Object obj = params.getOrDefault(key, null);
		if(obj != null) {
			if(tClass.getTypeName().equals("java.lang.Integer")) {
				obj = obj != "" ? Integer.valueOf(obj.toString()) : null;
			}
			else if(tClass.getTypeName().equals("java.lang.Long")) {
				obj = obj != "" ? Long.valueOf(obj.toString()) : null;
			}
			else if(tClass.getTypeName().equals("java.lang.String")) {
				obj = obj != "" ? obj.toString() : null;
			}
			else if(tClass.getTypeName().equals("java.lang.Double")) {
				obj = obj != null ? Double.valueOf(obj.toString()) : null;
			}
			return tClass.cast(obj);
		}
		return null;
	}
}
