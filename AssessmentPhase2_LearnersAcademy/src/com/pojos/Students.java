package com.pojos;

import java.util.Date;

public class Students {

	private long ID;
	private long studentID;
	private String nationalID;
	private String studentName;
	private String studentSurname;
	private String studentGender;
	private Date birthdate;
	private String entryYear;
	private Classes classID;

	public Students() 
	{
		
	}

	public Students(long studentID, String nationalID, String studentName, String studentSurname, String studentGender,
			Date birthdate, String entryYear, Classes classID) {
		super();
		this.studentID = studentID;
		this.nationalID = nationalID;
		this.studentName = studentName;
		this.studentSurname = studentSurname;
		this.studentGender = studentGender;
		this.birthdate = birthdate;
		this.entryYear = entryYear;
		this.classID = classID;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public long getStudentID() {
		return studentID;
	}

	public void setStudentID(long studentID) {
		this.studentID = studentID;
	}

	public String getNationalID() {
		return nationalID;
	}

	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentSurname() {
		return studentSurname;
	}

	public void setStudentSurname(String studentSurname) {
		this.studentSurname = studentSurname;
	}

	public String getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEntryYear() {
		return entryYear;
	}

	public void setEntryYear(String entryYear) {
		this.entryYear = entryYear;
	}

	public Classes getClassID() {
		return classID;
	}

	public void setClassID(Classes classID) {
		this.classID = classID;
	}

	@Override
	public String toString() {
		return "Student ID=" + studentID + ", National ID=" + nationalID + ", Name="
				+ studentName + ", Surname=" + studentSurname + ", Gender=" + studentGender
				+ ", Birthdate=" + birthdate.toString() + ", Entry Year=" + entryYear + ", Class=" + classID.getName();
	}

	
		
	
}
