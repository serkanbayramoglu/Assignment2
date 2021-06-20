package com.pojos;

import java.util.Set;

public class Subjects {
	
	private long ID;
	private SubjectCodes subjectCode;
	private Teachers teacherID;
	
	public Subjects() {
		
	}

	public Subjects(SubjectCodes subjectCode, Teachers teacherID) {
		super();
		this.subjectCode = subjectCode;
		this.teacherID = teacherID;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public SubjectCodes getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(SubjectCodes subjectCode) {
		this.subjectCode = subjectCode;
	}

	public Teachers getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Teachers teacherID) {
		this.teacherID = teacherID;
	}

	@Override
	public String toString() {
		return "ID= " + ID + ", Teacher ID=" + teacherID.getTeacherId() + ", Teacher Name=" + teacherID.getTeacherName() + ", Teacher Surname=" + teacherID.getTeacherSurname();
	}
	
	

}
