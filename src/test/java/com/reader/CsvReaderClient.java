package com.reader;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.reader.domain.Employee;
import com.reader.domain.SalesItem;

@RunWith(MockitoJUnitRunner.class)
public class CsvReaderClient {
	
	private CsvParser csvParser = new CsvParser();

	@Test
	@SuppressWarnings("unchecked")
	public void employeeListFromCSV() {
		List<Employee> employees = (List<Employee>) csvParser
				.parse("/Users/rahulkatare/Documents/Learning/csvReader/27468_Employee_Records.csv", Employee.class);
		System.out.println("----size----->>>" + employees.size());
		System.out.println("----getAge----->>>" + employees.get(0).getAge());
		assertEquals(27468, employees.size());
		assertEquals("11", employees.get(0).getAge());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void salesOrderListFromCSV() {
		//Checking 1 million rows data from csv
		List<SalesItem> salesRecord = (List<SalesItem>) csvParser.parse("/Users/rahulkatare/Documents/Learning/csvReader/1000000_Sales_Records.csv",
				SalesItem.class);
		System.out.println("---salesRecord-size----->>>"+salesRecord.size());
		System.out.println("---salesRecord-getCountry----->>>"+salesRecord.get(0).getCountry());
		assertEquals(2220, salesRecord.size());
		assertEquals("South Africa", salesRecord.get(0).getCountry());
	}
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void duplicateCheck() {
		List<Employee> employees = (List<Employee>) csvParser.parse("/Users/rahulkatare/Documents/Learning/csvReader/duplicatechek.csv",
				Employee.class);
		System.out.println("----size---duplicateCheck-->>>"+employees.size());
		System.out.println("----getAge-duplicateCheck---->>>"+employees.get(0).getId());
		assertEquals(1, employees.size());
		assertEquals("1", employees.get(0).getId());
	}
}
