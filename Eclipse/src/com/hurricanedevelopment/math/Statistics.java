package com.hurricanedevelopment.math;

public class Statistics {

	public static double Chi2GOF(double[] expected, double[] observed) {
		if (expected.length != observed.length)
			return -1;
		
		double chi2 = 0;
		
		for (int i = 0;i < expected.length;i++)
			chi2 += Math.pow(observed[i] - expected[i],2) / expected[i];
		
		return chi2;
	}
	
	public static int Chi2GOFMath(double[] expected, double[][] observed) {
		int minChi = -1;
		double minChiVal = Integer.MAX_VALUE;
		
		for (int i = 0;i < observed.length;i++) {
			double chi = Chi2GOF(expected, observed[i]);
			if (chi < minChiVal) {
				minChiVal = chi;
				minChi = i;
			}
		}
		
		return minChi;
	}
}
