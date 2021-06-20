package com.pojos;

import java.util.Set;

public class ClassYears {

	private long ClassYearId;
	private String classYear;
	private Set<Subjects> subjects;
	private Set<Classes> classes;
	
	public ClassYears() {
		
	}

	public ClassYears(String classYear) {
		super();
		this.classYear = classYear;
	}

	public String getClassYear() {
		return classYear;
	}

	public void setClassYear(String classYear) {
		this.classYear = classYear;
	}

	public Set<Subjects> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subjects> subjects) {
		this.subjects = subjects;
	}

	public Set<Classes> getClasses() {
		return classes;
	}

	public void setClasses(Set<Classes> classes) {
		this.classes = classes;
	}

	public long getClassYearId() {
		return ClassYearId;
	}

	public void setClassYearId(long classYearId) {
		ClassYearId = classYearId;
	}

	@Override
	public String toString() {
		return  "Class Year= " + classYear;
	}
	

}
