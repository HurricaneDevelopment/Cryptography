package com.hurricanedevelopment.cryptography;

import com.hurricanedevelopment.util.StringFormat;
import com.hurricanedevelopment.util.StringOperations;

public class VigenereEncrypt {
	
	public static String encrypt(String message,String key) {
		message = message.replaceAll("\\W", "").toUpperCase();
		key = key.replaceAll("\\W", "").toUpperCase();
		
		String encryptedMessage = "";
		
		for (int i = 0;i < message.length();i++) {
			encryptedMessage += charShift(message.charAt(i),key.charAt(i % (key.length())));
		}
		
		return encryptedMessage;
	}

	public static char charShift(char m,char k) {
		return (char) (((m - 'A') + (k - 'A')) % 26 + 'A');
	}
}