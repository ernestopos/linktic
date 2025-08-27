package com.linktic.api.utilities;

public class UtilitiesSources {
	public static String getCausas(Throwable ex) {
		StringBuilder sb = new StringBuilder();
		Throwable causa = ex;
		while (causa != null) {
			sb.append(causa.getMessage())
			.append("\n");
			causa = causa.getCause();
		}
		return sb.toString();
	}
}
