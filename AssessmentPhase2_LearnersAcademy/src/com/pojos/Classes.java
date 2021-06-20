package com.pojos;

import java.util.Set;


public class Classes {

	private long ID;
	private String name;
	private ClassYears year;
	private String div;
    private Set<Students> students;
    private Set<Assignments> assignments;
	
	public Classes()
	{
		
	}

	public Classes(String name, ClassYears year, String div, Set<Students> students, Set<Assignments> assignments) {
		super();
		this.name = name;
		this.year = year;
		this.div = div;
		this.students = students;
		this.assignments = assignments;
	}




	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassYears getYear() {
		return year;
	}

	public void setYear(ClassYears year) {
		this.year = year;
	}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	public Set<Students> getStudents() {
		return students;
	}

	public void setStudents(Set<Students> students) {
		this.students = students;
	}

	public Set<Assignments> getAssignments() {
		return assignments;
	}

	public void setAssignments(Set<Assignments> assignments) {
		this.assignments = assignments;
	}

	@Override
	public String toString() {
		return "Class Name=" + name + ", Year=" + year.getClassYear() + ", Div=" + div;
	}
	
		
}
