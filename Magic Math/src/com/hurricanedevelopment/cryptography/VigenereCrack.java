package com.hurricanedevelopment.cryptography;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.hurricanedevelopment.math.Statistics;
import com.hurricanedevelopment.util.PrimitiveArrays;
import com.hurricanedevelopment.util.datastructures.KeyPair;

public class VigenereCrack {
	
	public static final double[] expectedLetterDistribution = {
		0.08167,
		0.01492,
		0.02782,
		0.04253,
		0.12702,
		0.02228,
		0.02015,
		0.06094,
		0.06966,
		0.00153,
		0.00772,
		0.04025,
		0.02406,
		0.06749,
		0.07507,
		0.01929,
		0.00095,
		0.05987,
		0.06327,
		0.09056,
		0.02758,
		0.00978,
		0.02360,
		0.00150,
		0.01974,
		0.00074
	};
	
	private String encryptedMessage = null;
	private String decryptedMessage = null;
	private String key = null;
	
	public VigenereCrack(String encryptedMessage) {
		this.encryptedMessage = encryptedMessage.replaceAll("\\W*\\d*", "").toUpperCase();
	}
	
	public void crack() {
		List<KeyPair<String,List<Boolean>>> tokenFactors = getFactors(getTokenDistances(encryptedMessage,3));
		if (tokenFactors.size() == 0) return;
		int keyLength = getKeyLength(rankKeyLengths(tokenFactors));
		double[][] charDist = createCharacterDistribution(encryptedMessage,keyLength);
		this.key = computeKey(charDist,keyLength);
		this.decryptedMessage = VigenereDecrypt.decrypt(encryptedMessage, key);
	}
	
	public String getDecryptedMessage() {
		return this.decryptedMessage;
	}
	
	public String getEncryptedMessage() {
		return this.encryptedMessage;
	}
	
	public String getKey() {
		return key;
	}
	
	/*
	 * Public Static Methods
	 */
	
	public static List<KeyPair<String,Integer>> getTokenDistances(String message,int tLength) {
		List<KeyPair<String,Integer>> tokens = new ArrayList<KeyPair<String,Integer>>();
		
		for (int i = 0;i < message.length() - tLength;i++) {
			String token = message.substring(i, i + tLength);
			for (int c = i + 1;c <= message.length() - tLength;c++) {
				String testToken = message.substring(c, c + tLength);
				if (testToken.equalsIgnoreCase(token)) {
					tokens.add(new KeyPair<String,Integer>(token,c - i));
					break;
				}
			}
		}
		
		return tokens;
	}
	
	public static List<KeyPair<String,List<Boolean>>> getFactors(List<KeyPair<String,Integer>> tokens) {
		List<KeyPair<String,List<Boolean>>> tokenFactors = new ArrayList<KeyPair<String,List<Boolean>>>();
		
		for (KeyPair<String, Integer> kp : tokens) {
			Boolean[] factors = new Boolean[kp.getValue()];
			
			for (int i = 1;i <= kp.getValue();i++)
				factors[i - 1] = (kp.getValue() % i == 0);
			
			tokenFactors.add(new KeyPair<String,List<Boolean>>(kp.getKey(),new ArrayList<Boolean>(Arrays.asList(factors))));
			
		}
		
		return tokenFactors;
	}
	
	public static List<Integer> rankKeyLengths(List<KeyPair<String,List<Boolean>>> aTokenFactors) {
		List<KeyPair<String,List<Boolean>>> tokenFactors = new ArrayList<KeyPair<String,List<Boolean>>>(aTokenFactors);
		
		int maxFactor = Integer.MIN_VALUE;
		
		for (KeyPair<String,List<Boolean>> token : tokenFactors) {
			if (token.getValue().size() > maxFactor)
				maxFactor = token.getValue().size();
		}
		
		Integer[] factorCounts = new Integer[maxFactor];
		
		for (int i = 0;i < factorCounts.length;i++) {
			factorCounts[i] = 0;
		}
			
		for (int i = 0;i < tokenFactors.size();i++) {
			List<Boolean> booleanFactors = tokenFactors.get(i).getValue();
			for (int c = 0;c < factorCounts.length;c++) {
				if (c < booleanFactors.size() && booleanFactors.get(c))
					factorCounts[c] += 1;
			}
		}
		
		return new ArrayList<Integer>(Arrays.asList(factorCounts));
	}
	
	public static int getKeyLength(List<Integer> keyLengthCounts) {
		boolean preCheck = true;
		
		int tmp1 = Integer.MAX_VALUE;
		int tmp2 = -1;
		
		for (int i = 0;i < keyLengthCounts.size();i++) {
			if (preCheck) {
				if (keyLengthCounts.get(i) < tmp1) {
					tmp1 = keyLengthCounts.get(i);
				} else {
					tmp1 = Integer.MIN_VALUE;
					preCheck = false;
				}
			} else {
				if (keyLengthCounts.get(i) > tmp1) {
					tmp1 = keyLengthCounts.get(i);
					tmp2 = i;
				}
			}
		}
		
		return tmp2 + 1;
	}
	
	public static double[][] createCharacterDistribution(String message, int keyLength) {
		double[][] characterFrequencyDistribution = new double[keyLength][26];
		
		for (int i = 0;i < message.length();i++)
			characterFrequencyDistribution[i % keyLength][message.charAt(i) - 'A']++;
		
		for (int i = 0;i < keyLength;i++) {
			int total = 0;
			for (int l = 0;l < 26;l++) {
				total += characterFrequencyDistribution[i][l];
			}
			for (int l = 0;l < 26;l++) {
				characterFrequencyDistribution[i][l] /= total;
			}
		}
		
		return characterFrequencyDistribution;
	}
	
	public static String computeKey(double[][] characterDistribtuion, int keyLength) {
		String key = "";
		
		for (int i = 0;i < keyLength;i++) {
			double[][] dists = new double[26][26];
			
			for (int d = 0;d < 26;d++) {
				for (int lm = d,l = 0;lm < d + 26;lm++,l++) {
					dists[d][l] = characterDistribtuion[i][lm % 26];
				}
			}
			
			int matchingDist = Statistics.Chi2GOFMath(expectedLetterDistribution,dists);
			
			key += (char) (matchingDist + 'A');
		}
		
		return key;
	}
}