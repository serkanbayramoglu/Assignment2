package com.pojos;

import java.util.Set;

public class SubjectCodes {

	private long ID;
	private String subjectCode;
	private String subjectName;
	private ClassYears classYear;
    private Set<Subjects> subjects;
    private Set<Assignments> assignments;

	
	public SubjectCodes() {
		
	}
	
public SubjectCodes(String subjectCode, String subjectName, ClassYears classYear, Set<Subjects> subjects,
			Set<Assignments> assignments) {
		super();
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		this.classYear = classYear;
		this.subjects = subjects;
		this.assignments = assignments;
	}



public long getID() {
	return ID;
}

public void setID(long iD) {
	ID = iD;
}

public String getSubjectCode() {
	return subjectCode;
}

public void setSubjectCode(String subjectCode) {
	this.subjectCode = subjectCode;
}

public String getSubjectName() {
	return subjectName;
}

public void setSubjectName(String subjectName) {
	this.subjectName = subjectName;
}

public ClassYears getClassYear() {
	return classYear;
}

public void setClassYear(ClassYears classYear) {
	this.classYear = classYear;
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
	return "Subject Code= " + subjectCode + ", Subject Name= " + subjectName + ", Class Year= " + classYear.getClassYear();
}


		
}
