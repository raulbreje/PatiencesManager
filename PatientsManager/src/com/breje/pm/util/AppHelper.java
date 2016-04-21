package com.breje.pm.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

	public static void deleteLocalFile(String fileName) throws IOException {
		Files.deleteIfExists(Paths.get(fileName));
	}

	public static void createLocalFile(String fileName) throws IOException {
		File tempFile = new File(fileName);
		tempFile.createNewFile();
	}

}
