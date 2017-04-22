import java.util.Scanner;

import com.hurricanedevelopment.cryptography.VigenereCrack;
import com.hurricanedevelopment.cryptography.VigenereDecrypt;
import com.hurricanedevelopment.cryptography.VigenereEncrypt;
import com.hurricanedevelopment.util.StringFormat;
import com.hurricanedevelopment.util.StringOperations;

public class Tester {
//	public static void main(String[] args) {
//		// Get info
//		Scanner sc = new Scanner(System.in);
//		System.out.print("Enter message to encrypt: ");
//		String message = sc.nextLine();
//		System.out.print("Enter key: ");
//		String key = sc.nextLine();
//		
//		// Encrypt
//		String encryptedMessage = VigenereEncrypt.encrypt(message, key);
//		System.out.println("Encrypted message: " + StringOperations.format(encryptedMessage,StringFormat.CRYPTOGRAPHY));
//		
//		// Decrypt
//		String decryptedMessage = VigenereDecrypt.decrypt(encryptedMessage, key);
//		System.out.println("Decrypted message: " + StringOperations.format(decryptedMessage,StringFormat.CRYPTOGRAPHY));
//		
//		VigenereCrack cracker = new VigenereCrack(encryptedMessage);
//		cracker.crack();
//		System.out.println("Key: " + cracker.getKey());
//		String crackedMessage = cracker.getDecryptedMessage();
//		if (crackedMessage == null)  return;
//		System.out.println("Cracked Message: " + StringOperations.format(crackedMessage,StringFormat.CRYPTOGRAPHY));
//		sc.close();
//	}
	
	public static void main(String[] args) {
		// Get info
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter message to crack: ");
		String encryptedMessage = sc.nextLine();
		
		VigenereCrack cracker = new VigenereCrack(encryptedMessage);
		cracker.crack();
		System.out.println("Key: " + cracker.getKey());
		String crackedMessage = cracker.getDecryptedMessage();
		if (crackedMessage == null)  return;
		System.out.println("Cracked Message: " + StringOperations.format(crackedMessage,StringFormat.CRYPTOGRAPHY));
		sc.close();
	}
}
