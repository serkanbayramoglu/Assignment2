package com.pojos;

import java.util.Set;

public class Teachers {

	private long ID;
	private long teacherId;
	private String teacherName;
	private String teacherSurname;
	private String teacherContact;
	private Set<Subjects> subjects;
	private Set<Assignments> assignments;

	public Teachers() {
		
	}

	

	public Teachers(long teacherId, String teacherName, String teacherSurname, String teacherContact,
			Set<Subjects> subjects, Set<Assignments> assignments) {
		super();
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.teacherSurname = teacherSurname;
		this.teacherContact = teacherContact;
		this.subjects = subjects;
		this.assignments = assignments;
	}



	public long getID() {
		return ID;
	}



	public void setID(long iD) {
		ID = iD;
	}



	public long getTeacherId() {
		return teacherId;
	}



	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}



	public String getTeacherName() {
		return teacherName;
	}



	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}



	public String getTeacherSurname() {
		return teacherSurname;
	}



	public void setTeacherSurname(String teacherSurname) {
		this.teacherSurname = teacherSurname;
	}



	public String getTeacherContact() {
		return teacherContact;
	}



	public void setTeacherContact(String teacherContact) {
		this.teacherContact = teacherContact;
	}



	public Set<Subjects> getSubjects() {
		return subjects;
	}



	public void setSubjects(Set<Subjects> subjects) {
		this.subjects = subjects;
	}



	public Set<Assignments> getAssignments() {
		return assignments;
	}



	public void setAssignments(Set<Assignments> assignments) {
		this.assignments = assignments;
	}



	@Override
	public String toString() {
		return "Teacher Id = " + teacherId + ", Teacher Name = " + teacherName + ", Teacher Surname = "
				+ teacherSurname + ", Teacher Contact = " + teacherContact;
	}


	
	
	
}
