package com.reader.domain;

import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = -5018262153179612338L;

	/**
	 * id of the employee
	 */
	private String id;

	/**
	 * age of the employee
	 */
	private String age;

	/**
	 * salary of the employee
	 */
	private String salary;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return the salary
	 */
	public String getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}


}
