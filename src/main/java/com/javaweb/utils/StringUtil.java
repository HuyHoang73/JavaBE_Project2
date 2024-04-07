package com.javaweb.utils;

public class StringUtil {
	/**
	 * Phương thức này để kiểm tra xem chuỗi trả về có khác null và khác rỗng hay không?
	 * @param data - chuỗi cần kiểm tra
	 * @return true là không null và không là chuỗi rỗng, false là null hoặc chuỗi rỗng
	 */
	public static boolean checkString(String data) {
		return data != null && !data.equals("");
	}
}
