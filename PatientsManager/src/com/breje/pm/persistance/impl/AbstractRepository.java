package com.breje.pm.persistance.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.breje.pm.exception.AbstractRepositoryException;
import com.breje.pm.util.AppHelper;

public class AbstractRepository {

	public void cleanFile(String fileName) throws AbstractRepositoryException {
		try {
			AppHelper.cleanFileContent(fileName);
		} catch (IOException e) {
			throw new AbstractRepositoryException("Cannot clean the storage file. Contact your administrator");
		}
	}

	public void save(List<String> listOfObjects, String fileName) throws AbstractRepositoryException {
		try {
			AppHelper.cleanFileContent(fileName);
			Files.write(Paths.get(fileName), listOfObjects);
		} catch (IOException e) {
			throw new AbstractRepositoryException("Cannot write into storage file. Contact your administrator.");
		}
	}

	public List<String> load(String fileName) throws AbstractRepositoryException {
		List<String> listOfObjects = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(listOfObjects::add);
		} catch (IOException e) {
			throw new AbstractRepositoryException("Cannot read from storage file. Contact your administrator");
		}
		return listOfObjects;
	}

}
