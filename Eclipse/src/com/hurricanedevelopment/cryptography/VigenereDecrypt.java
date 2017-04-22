package com.hurricanedevelopment.cryptography;

public class VigenereDecrypt {
	
	public static String decrypt(String encryptedMessage,String key) {
		encryptedMessage = encryptedMessage.replaceAll("\\W", "").toUpperCase(); 
		key = key.replaceAll("\\W", "").toUpperCase();
		
		String message = "";
		
		for (int i = 0;i < encryptedMessage.length();i++)
			message += (char) (((encryptedMessage.charAt(i) - 'A') - (key.charAt(i % (key.length())) - 'A') + 26) % 26 + 'A');
		
		return message;
	}

}
