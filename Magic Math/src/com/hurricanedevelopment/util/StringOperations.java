package com.hurricanedevelopment.util;

public class StringOperations {
	public static String format(String message,StringFormat form) {
		if (form == StringFormat.CRYPTOGRAPHY) {
			if (message.length() <= 5)
				return message;
			
			message = message.substring(0, 5) + " " + message.substring(5);
			
			int lastSpace = 0;
			
			while (message.indexOf(' ',lastSpace) + 5 < message.length() - 1) {
				message = message.substring(0,message.indexOf(' ',lastSpace) + 6) + " " +
						message.substring(message.indexOf(' ',lastSpace) + 6);
				
				lastSpace = message.indexOf(' ',lastSpace) + 1;
			}
			
			return message;
		} else {
			return null;
		}
	}
}
