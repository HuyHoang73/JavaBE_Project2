package com.javaweb.utils;

public class NumberUtil {
	/**
	 * Phương thức này để kiểm tra chuỗi trả về có phải là 1 số hay không
	 * @param data - chuỗi cần kiểm tra
	 * @return true - có là số, false - không là số
	 */
	public static boolean checkNumber(String data) {
		try {
			Long number = Long.parseLong(data);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
