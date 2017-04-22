package com.hurricanedevelopment.util;

public class PrimitiveArrays {
	public static String print2DDoubleArray(double[][] arr) {
		String print = "[";
		for (int i = 0;i < arr.length;i++) {
			print += "[";
			for (int c = 0;c < arr[0].length;c++) {
				print += String.valueOf(arr[i][c]);
				print += (c < arr.length - 1) ? "," : "";
			}
			print += "]";
			print += (i < arr.length - 1) ? "," : "";
		}
		return print;
	}
}
