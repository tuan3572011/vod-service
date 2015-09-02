package com.vod.common;

import java.io.UnsupportedEncodingException;

public class VodCommon {
	public static String convertToUTF8(String str) {
		byte[] bytesData = str.getBytes();
		String decodedDataUsingUTF8 = str.toLowerCase();
		try {
			decodedDataUsingUTF8 = new String(bytesData, "UTF-8");
			System.out.println("Text Decryted using UTF-8 : "
					+ decodedDataUsingUTF8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return decodedDataUsingUTF8;
	}
}
