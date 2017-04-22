package com.hurricanedevelopment.cryptography;

import com.hurricanedevelopment.util.StringFormat;
import com.hurricanedevelopment.util.StringOperations;

public class VigenereDecrypt {
	
	public static String decrypt(String encryptedMessage,String key) {
		encryptedMessage = encryptedMessage.replaceAll("\\W", "").toUpperCase(); 
		key = key.replaceAll("\\W", "").toUpperCase();
		
		String message = "";
		
		for (int i = 0;i < encryptedMessage.length();i++) {
			message += charShift(encryptedMessage.charAt(i),key.charAt(i % (key.length())));
		}
		
		return message;
	}

	public static char charShift(char m,char k) {
		return (char) (((m - 'A') - (k - 'A') + 26) % 26 + 'A');
	}
}
