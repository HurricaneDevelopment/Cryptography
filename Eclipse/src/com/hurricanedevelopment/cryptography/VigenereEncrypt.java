package com.hurricanedevelopment.cryptography;

public class VigenereEncrypt {
	
	public static String encrypt(String message,String key) {
		message = message.replaceAll("\\W", "").toUpperCase();
		key = key.replaceAll("\\W", "").toUpperCase();
		
		String encryptedMessage = "";
		
		for (int i = 0;i < message.length();i++)
			encryptedMessage += (char) (((message.charAt(i) - 'A') + (key.charAt(i % (key.length())) - 'A')) % 26 + 'A');
		
		return encryptedMessage;
	}
	
}