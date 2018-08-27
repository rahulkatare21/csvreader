package com.reader;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

public class CsvParser {

	/**
	 * 
	 * @param <T>
	 * @param csvFilepath
	 * @param classObj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Collection<?> parse(String csvFileLocation, Class<?> classObj) {
		
		Path csvFilepath = Paths.get(csvFileLocation);
		List<Object> listObj = new ArrayList<>();

		try {
			Supplier<Stream<String>> streamSupplier = () -> {
				Stream<String> stream = null;
				try {
					stream = Files.lines(csvFilepath);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return stream;
			};
			List<Field> listOfFeilds = new ArrayList<>();
			String[] dataHeader = streamSupplier.get().findFirst().get().split(",");
			T o = (T) classObj.newInstance();

			for (int i = 0; i < dataHeader.length; i++) {
				listOfFeilds.add(Class.forName(o.getClass().getName()).getDeclaredField(dataHeader[i]));
			}
			
			streamSupplier.get().distinct().skip(1).forEach(line -> {
				String[] data = line.split(",");// a CSV has comma separated lines
				try {
					T object = (T) classObj.newInstance();
					for (int i = 0; i < data.length; i++) {
						listOfFeilds.get(i).setAccessible(true);
						listOfFeilds.get(i).set(object, data[i]);
					}
					listObj.add(object);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listObj;
	}
}
