package com.pojos;


public class Assignments {

	private long ID;
	private Classes classId;
	private SubjectCodes subjectId;
	private Teachers teacherId;

	
	public Assignments() {
		
	}


	public Assignments(Classes classId, SubjectCodes subjectId, Teachers teacherId) {
		super();
		this.classId = classId;
		this.subjectId = subjectId;
		this.teacherId = teacherId;
	}


	public long getID() {
		return ID;
	}


	public void setID(long iD) {
		ID = iD;
	}


	public Classes getClassId() {
		return classId;
	}


	public void setClassId(Classes classId) {
		this.classId = classId;
	}


	public SubjectCodes getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(SubjectCodes subjectId) {
		this.subjectId = subjectId;
	}


	public Teachers getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(Teachers teacherId) {
		this.teacherId = teacherId;
	}


	@Override
	public String toString() {
		return "ID=" + ID + ", Class " + classId.toString() + ", " + subjectId.toString() + ", "
				+ teacherId.toString();
	}



}
