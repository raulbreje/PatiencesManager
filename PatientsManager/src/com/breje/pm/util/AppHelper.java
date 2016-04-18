package com.breje.pm.util;

import java.time.format.DateTimeFormatter;

/**
 * Created by Raul Breje on 03/22/2016.
 */
public class AppHelper {

	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

	public static boolean isNull(String s) {
		if (s == null) {
			return true;
		}
		return false;
	}

}
