package com.learnersAcademy;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;

import com.pojos.*;

/**
 * Servlet implementation class AddNewRecord
 */
public class AddNewRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();		
		HttpSession hSession = request.getSession(false);

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();


		Map<String, String[]> parameters = request.getParameterMap();
		String actionName = parameters.get("actionName")[0];
		String RecordID= null;

		try{RecordID = request.getParameter("RecordID");} catch (Exception e) {}
		
		
		try {
			
			switch(actionName) {
				case "addStudent": 

					String StudentID1 = "";
					try{StudentID1 = parameters.get("StudentID")[0];} catch (Exception e){}
		
					if (
						((RecordID == null) && (parameters.get("StudentID")[0] == "")) ||
						(parameters.get("Class") == null) ||
						(parameters.get("BirthDate") == null) ||  		
						(parameters.get("NationalID")[0] == "") ||
						(parameters.get("Name")[0] == "") ||
						(parameters.get("Surname")[0] == "") ||
						(parameters.get("gender") == null) ||
						(parameters.get("EntryYear")[0] == "")
					) {
						if (RecordID != null) {
							RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Students=Amend" 
									+ "&NoteToPrint=Please fill/select all the entries"
									+ "&Class=" + parameters.get("Class")
									+ "&RecordID=" + RecordID
									+ "&BirthDate=" + parameters.get("BirthDate")
									+ "&NationalID=" + parameters.get("NationalID")
									+ "&Name=" + parameters.get("Name")
									+ "&Surname=" + parameters.get("Surname")
									+ "&gender=" + parameters.get("gender")
									+ "&EntryYear=" + parameters.get("EntryYear"));
							rd.forward(request, response);
						} else {
							RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Students=Add" + "&NoteToPrint=Please fill/select all the entries");
							rd.forward(request, response);  
						}
					} else {
					
						try {
							Students student = new Students();
							String currentClass = parameters.get("Class")[0];						
							Classes studentClass = (Classes) session.createQuery("from Classes WHERE name = '" + currentClass + "'").uniqueResult();    
				  					
							if (RecordID != null) {
								student = (Students) session.createQuery("from Students WHERE StudentID ='" + RecordID +  "'").uniqueResult();
							} else {					
								student.setStudentID(Long.valueOf(parameters.get("StudentID")[0]));
								Students studCheck = null;
								studCheck = (Students) session.createQuery("from Students WHERE StudentID ='" + Long.valueOf(parameters.get("StudentID")[0]) +  "'").uniqueResult();
								if (studCheck != null) {
									RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Students=Add" + "&NoteToPrint=The Student ID already exists in the Students table. Please use another Student ID");
									rd.forward(request, response);
								}
							}
							
							String sDate1=parameters.get("BirthDate")[0];  
							Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);  

							student.setNationalID(parameters.get("NationalID")[0]);
							student.setStudentName(parameters.get("Name")[0]);
							student.setStudentSurname(parameters.get("Surname")[0]);
							student.setStudentGender(parameters.get("gender")[0]);
							student.setBirthdate(date1);
							student.setEntryYear(parameters.get("EntryYear")[0]);
							student.setClassID(studentClass);
							
							session.beginTransaction();
							// update - amend
							if (RecordID !=null) {
								Students st = (Students)session.get(Students.class, student.getID());
								st = student;
							} else {					
								session.save(student);
							}
							session.getTransaction().commit();
							
							out.println("<html><body>");
							out.println("Inserted Successfully");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
							out.println("</html></body>");
						} catch (ParseException e) {
							if (RecordID != null) {
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Students=Amend" 
										+ "&NoteToPrint=Incorrect Date Format"
										+ "&Class=" + parameters.get("Class")
										+ "&RecordID=" + RecordID
										+ "&BirthDate=" + parameters.get("BirthDate")
										+ "&NationalID=" + parameters.get("NationalID")
										+ "&Name=" + parameters.get("Name")
										+ "&Surname=" + parameters.get("Surname")
										+ "&gender=" + parameters.get("gender")
										+ "&EntryYear=" + parameters.get("EntryYear"));								
								rd.forward(request, response);  
							} else {
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Students=Add" + "&NoteToPrint=Incorrect Date Format");
								rd.forward(request, response);
							}
						} catch (DataException e) {
							if (RecordID != null) {
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Students=Amend" 
										+ "&NoteToPrint=Incorrect format. Please try again."
										+ "&Class=" + parameters.get("Class")
										+ "&RecordID=" + RecordID
										+ "&BirthDate=" + parameters.get("BirthDate")
										+ "&NationalID=" + parameters.get("NationalID")
										+ "&Name=" + parameters.get("Name")
										+ "&Surname=" + parameters.get("Surname")
										+ "&gender=" + parameters.get("gender")
										+ "&EntryYear=" + parameters.get("EntryYear"));								
								rd.forward(request, response);  
							} else {
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Students=Add" + "&NoteToPrint=Incorrect format. Please try again.");
								rd.forward(request, response);  
							}
						} catch (ConstraintViolationException e) {				
						}
					}
				
						
						break;

				case "addClass": 
								
					if (
							(parameters.get("classYear") == null) ||
							(parameters.get("classDiv")[0] == "")
						) {
							RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Classes=Add" + "&NoteToPrint=Please fill/select all the entries");
							rd.forward(request, response);  
						} else {
						
							try {
								//Create ClassYears object
								ClassYears classYear = new ClassYears();
								String selectedYear = parameters.get("classYear")[0];						
								
								if (selectedYear.contentEquals("newYear")) {
									
									// classYear check
									ClassYears cyCheck = null;
									cyCheck = (ClassYears) session.createQuery("from ClassYears WHERE classYear = '" + parameters.get("newYear")[0] + "'").uniqueResult();    
								
									if (cyCheck != null) {
										System.out.println("checked");
										RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Classes=Add" + "&NoteToPrint=Entered new class year already exists. Please try again.");
										rd.forward(request, response);
									}									
									classYear.setClassYear(parameters.get("newYear")[0].toString());
									System.out.println("classYear to add: " + classYear.toString());
									session.beginTransaction();
									session.save(classYear);
									session.getTransaction().commit();
									out.println("Year: " + classYear.getClassYear() + " added successfully");
									
								} else {
									classYear = (ClassYears) session.createQuery("from ClassYears WHERE classYear = '" + selectedYear + "'").uniqueResult();    
								}
								
								//Create Class entity object
								Classes class0 = new Classes();
		
								String className = classYear.getClassYear() + parameters.get("classDiv")[0];
								
								// check classes 
								Classes cCheck = null;
								cCheck = (Classes) session.createQuery("from Classes WHERE name = '" + className + "'").uniqueResult();    
								if (cCheck != null) {
									RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Classes=Add" + "&NoteToPrint=Entered class already exists. Please try again.");
									rd.forward(request, response);
								}
														    			    
								class0.setName(className);
								class0.setDiv(parameters.get("classDiv")[0]);
								class0.setYear(classYear);
								
								
								session.beginTransaction();
								session.save(class0);
								session.getTransaction().commit();
								
								out.println("<html><body>");
								out.println("Inserted Successfully");
								out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
								out.println("</html></body>");  
							} catch (DataException e) {
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Classes=Add" + "&NoteToPrint=Incorrect format. Please try again.");
								rd.forward(request, response);  
							} catch (ConstraintViolationException e) {				
							}
						}
					break;
					
				case "addTeacher": 

					String teacherId1 = "";
					try{teacherId1 = parameters.get("teacherId")[0];} catch (Exception e){}
					if (
							((teacherId1 == "") && (RecordID == null)) ||
							(parameters.get("teacherName")[0] == "") ||
							(parameters.get("teacherSurname")[0] == "") ||
							(parameters.get("teacherContact")[0] == "")
						) {
						if (RecordID != null) {
							RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Teachers=Amend" 
									+ "&NoteToPrint=Please fill/select all the entries"
									+ "&teacherName=" + parameters.get("teacherName")
									+ "&RecordID=" + RecordID
									+ "&teacherSurname=" + parameters.get("teacherSurname")
									+ "&teacherContact=" + parameters.get("teacherContact"));
							rd.forward(request, response);
						} else {
							RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Teachers=Add" + "&NoteToPrint=Please fill/select all the entries");
							rd.forward(request, response);  
						}
						} else {
						
							try {
								
								//Create teacher entity object					
								Teachers teacher = new Teachers();
																
								if (RecordID != null) {
									teacher = (Teachers) session.createQuery("from Teachers WHERE teacherId ='" + RecordID +  "'").uniqueResult();
								} else {
									
									long teacherId = Long.valueOf(parameters.get("teacherId")[0]);

									teacher.setTeacherId(teacherId);
									// check teachers 
									Teachers tCheck = null;
									tCheck = (Teachers) session.createQuery("from Teachers WHERE teacherId = '" + teacherId + "'").uniqueResult();    
									if (tCheck != null) {
										RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Teachers=Add" + "&NoteToPrint=Entered teacher ID already exists. Please try again.");
										rd.forward(request, response);
									}
								}
								teacher.setTeacherName(parameters.get("teacherName")[0]);
								teacher.setTeacherSurname(parameters.get("teacherSurname")[0]);
								teacher.setTeacherContact(parameters.get("teacherContact")[0]);
								
								
								session.beginTransaction();
								
								// update - amend
								if (RecordID !=null) {
									Teachers te = (Teachers)session.get(Teachers.class, teacher.getID());
									te = teacher;
								} else {					
									session.save(teacher);
								}
								
								session.getTransaction().commit();	
								
								out.println("<html><body>");
								out.println("Transaction Completed Successfully");
								out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
								out.println("</html></body>");
							} catch (DataException e) {
								if (RecordID != null) {
									RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Teachers=Amend" 
											+ "&NoteToPrint=Incorrect format. Please try again."
											+ "&teacherName=" + parameters.get("teacherName")
											+ "&RecordID=" + RecordID
											+ "&teacherSurname=" + parameters.get("teacherSurname")
											+ "&teacherContact=" + parameters.get("teacherContact"));
									rd.forward(request, response);
								} else {
									RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Teachers=Add" + "&NoteToPrint=Incorrect format. Please try again.");
									rd.forward(request, response);  
								}
							} catch (ConstraintViolationException e) {				
							} catch (NumberFormatException e) {
							}
					}
					break;
					
				case "addSubject": 
					
					String subjectCode1 = "";
					try{subjectCode1 = parameters.get("subjectCode")[0];} catch (Exception e){}
					if (
							((subjectCode1 == "") && (RecordID == null)) ||
							(parameters.get("subjectName")[0] == "") ||
							(parameters.get("classYear") == null)
						) {
							if (RecordID != null) {
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Subjects=Amend" 
										+ "&NoteToPrint=Please fill/select all the entries"
										+ "&subjectName=" + parameters.get("subjectName")
										+ "&RecordID=" + RecordID
										+ "&classYear=" + parameters.get("classYear")
										+ "&teachers=" + parameters.get("teachers"));
								rd.forward(request, response);
							} else {
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Subjects=Add" + "&NoteToPrint=Please fill/select all the entries");
								rd.forward(request, response);
							}
						} else {
						
							try {

								//Create Subjects object
								Subjects subject = new Subjects();
								
								//Create SubjectCodes object
								SubjectCodes subjectCode = new SubjectCodes();
								
								//Create ClassYears object
								String selectedYears = parameters.get("classYear")[0];
								ClassYears classYear1 = (ClassYears) session.createQuery("from ClassYears WHERE classYear = '" + selectedYears + "'").uniqueResult();    
								 
								//Collect teacher data from parameters
								String[] selectedTeachers = parameters.get("teachers");
								String[] selectedTeachersAmend = selectedTeachers;
								
								Teachers subjectTeacher = new Teachers();
								out.println("<html><body>");
								
								// Delete Assignments for Subjects with changing ClassYear
								if (RecordID != null) {
									subjectCode = (SubjectCodes) session.createQuery("from SubjectCodes WHERE subjectCode ='" + RecordID +  "'").uniqueResult();
									Set<Assignments> list = subjectCode.getAssignments();
									
									for (Assignments assn : list) {
										if (!(assn.getClassId().getYear().getClassYear().equals(selectedYears))) {
											Session session1 = factory.openSession();
											session1.beginTransaction();
											Assignments subj = (Assignments)session1.load(Assignments.class, assn.getID());
											session1.delete(subj);
											session1.getTransaction().commit();	
											session1.close();
										}
									}
								// add / amend in SubjectCode table
								} else {					
									subjectCode.setSubjectCode(parameters.get("subjectCode")[0]);
									// check subjects 
									SubjectCodes sCheck = null;
									sCheck = (SubjectCodes) session.createQuery("from SubjectCodes WHERE subjectCode = '" + parameters.get("subjectCode")[0] + "'").uniqueResult();    
									if (sCheck != null) {
										RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Subjects=Add" + "&NoteToPrint=Entered Subject Code already exists. Please try again.");
										rd.forward(request, response);
									}

									
								}
								subjectCode.setSubjectName(parameters.get("subjectName")[0]);
								subjectCode.setClassYear(classYear1);					
								
								session.beginTransaction();
			
								if (RecordID !=null) {
									SubjectCodes suc = (SubjectCodes)session.get(SubjectCodes.class, subjectCode.getID());
									suc = subjectCode;
								} else {					
									session.save(subjectCode);
								}
								session.getTransaction().commit();
								
			
								subjectCode = (SubjectCodes) session.createQuery("from SubjectCodes WHERE subjectCode ='" + RecordID +  "'").uniqueResult();
			
								// add amend teachers in assignments
								if (RecordID !=null) {
									Set<Assignments> assignments = subjectCode.getAssignments();
									Teachers teacherNA = (Teachers) session.createQuery("from Teachers WHERE ID =1").uniqueResult();
			
			
									for (Assignments assn : assignments) {
										boolean equalityCheck = false;
										for (int i=0; i < selectedTeachers.length ; i++) {
											if (assn.getTeacherId().getID() == Long.valueOf(selectedTeachers[i])) {equalityCheck = true;}
										}		
			
										if (!equalityCheck) {
											
											// change the assigned teacher in the assignments to NA	
											
											assn.setTeacherId(teacherNA);
			
											session.beginTransaction();
											Assignments asst = (Assignments)session.get(Assignments.class, assn.getID());
											asst = assn;
											session.getTransaction().commit();	
										}
									}	
									
									List<Subjects> subjects = session.createQuery("from Subjects WHERE subjectCode ='" + subjectCode.getID() +  "'").list();
									
									System.out.println("subjects size= " + subjects.size());
			
									selectedTeachersAmend = selectedTeachers;
									System.out.println("selected teachers amend length at start = " + selectedTeachersAmend.length);
			
									// remove teacherID from Subjects
									for (Subjects su : subjects) {
										boolean equalityCheck = false;
										System.out.println("subject teacher ID = " + su.getTeacherID().getID());
			
										for (int i=0; i < selectedTeachersAmend.length ; i++) {
											System.out.println("selectedTeachersAmend no= " + i + " entry= " + Long.valueOf(selectedTeachersAmend[i]));
											System.out.println("subject teacher ID = selectedTeachersAmend? " + (su.getTeacherID().getID() == Long.valueOf(selectedTeachersAmend[i])));
			
											if (su.getTeacherID().getID() == Long.valueOf(selectedTeachersAmend[i])) {
												String [] newSelectedTeachers = new String [selectedTeachersAmend.length-1];
												equalityCheck = true;
												int t = 0;
												for (int m=0; m < newSelectedTeachers.length ; m++) {
													if (i == m) {t++;}	
													newSelectedTeachers[m] = selectedTeachersAmend[m+t];
												}
												selectedTeachersAmend = newSelectedTeachers;
												System.out.println("selected teachers amend length after iteration= " + selectedTeachersAmend.length);
											}
										}
				
										if (!equalityCheck) {								
											session.beginTransaction();
											Subjects subj = (Subjects)session.load(Subjects.class, su.getID());
											System.out.println("subj to delete = " + subj.getID());
											
											session.delete(subj);
											session.getTransaction().commit();	
										}
									}
							
									
									// add teacherID from Subjects						
									System.out.println("selected teachers amend length before addition iteration= " + selectedTeachersAmend.length);
									
									if (selectedTeachersAmend.length > 0) {
										for (int i=0; i < selectedTeachersAmend.length ; i++) {
											System.out.println("SELECTED TEACHERS AMEND I= " + i + " ENTRY = " + selectedTeachersAmend[i]);
											Teachers subjectTeacher1 = (Teachers) session.createQuery("from Teachers WHERE ID ='" +selectedTeachersAmend[i] + "'").uniqueResult();
											
											subject.setSubjectCode(subjectCode);
											subject.setTeacherID(subjectTeacher1);	
											System.out.println(subject.toString());
											
											Session session1 = factory.openSession(); 
											session1.beginTransaction();
											session1.save(subject);
											session1.getTransaction().commit();
											session1.close();
										}
									}
									
								} else {					
								
									// add subject	
									subjectCode = (SubjectCodes) session.createQuery("from SubjectCodes WHERE subjectCode ='" + parameters.get("subjectCode")[0] +  "'").uniqueResult();
									
									
									for (int i=0 ; i < selectedTeachers.length ; i++) {   
										subjectTeacher = (Teachers) session.createQuery("from Teachers WHERE ID ='" + selectedTeachers[i] +  "'").uniqueResult();
										subject.setSubjectCode(subjectCode);
										subject.setTeacherID(subjectTeacher);
			
										Session session1 = factory.openSession(); 
										session1.beginTransaction();
										session1.save(subject);
										session1.getTransaction().commit();
										session1.close();
									}
								}
								
								out.println("Transaction Completed Successfully");
								out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
								out.println("</html></body>");
							} catch (DataException e) {
								if (RecordID != null) {
									RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Subjects=Amend" 
											+ "&NoteToPrint=Incorrect format. Please try again."
											+ "&subjectName=" + parameters.get("subjectName")
											+ "&RecordID=" + RecordID
											+ "&classYear=" + parameters.get("classYear")
											+ "&teachers=" + parameters.get("teachers"));
									rd.forward(request, response);
								} else {
									RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Subjects=Add" + "&NoteToPrint=Incorrect format. Please try again.");
									rd.forward(request, response);  
								}
							} catch (ConstraintViolationException e) {				
							} 
					}
					
					break;	

				case "assignClass": 
					
					if (parameters.get("subjectID") == null){
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/ActionServlet?Assign Classes=Assign" 
										+ "&NoteToPrint=Please select a subject"
										+ "&QueryEntry=" + parameters.get("QueryEntry")[0]);
								rd.forward(request, response);
						} else {
						
						//Create teacher entity object					
						Assignments assignment = new Assignments();
						Classes class1 = (Classes) session.createQuery("from Classes WHERE ID ='" + parameters.get("classID")[0] +  "'").uniqueResult();
						SubjectCodes subject1 = (SubjectCodes) session.createQuery("from SubjectCodes WHERE ID ='" + parameters.get("subjectID")[0] +  "'").uniqueResult();
						Teachers defaultTeacher = (Teachers) session.createQuery("from Teachers WHERE ID = 1").uniqueResult();
	
					
						assignment.setClassId(class1);
						assignment.setSubjectId(subject1);
						assignment.setTeacherId(defaultTeacher);
						
						session.beginTransaction();
						session.save(assignment);
						session.getTransaction().commit();	
						
						out.println("<html><body>");
						out.println("Transaction Completed Successfully");
						out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						out.println("</html></body>");
						}
					
					break;
					
				case "assignTeacher": 
										
					String teacherRetrieved = parameters.get("teacher")[0];					
					Teachers teacherSelected = (Teachers) session.createQuery("from Teachers WHERE ID ='" + teacherRetrieved +  "'").uniqueResult();

					out.println("<html><body>");
										
					Assignments assignmentSelected = (Assignments) session.createQuery("from Assignments WHERE ID ='" + RecordID +  "'").uniqueResult();
					assignmentSelected.setTeacherId(teacherSelected);						
					
					session.beginTransaction();
					Assignments asst = (Assignments)session.get(Assignments.class, assignmentSelected.getID());
					asst = assignmentSelected;
					session.getTransaction().commit();					
										
					out.println("Transaction Completed Successfully");
					out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
					out.println("</html></body>");
					
					break;	
			}
			
			session.close();
			
		} catch (Exception e) {e.printStackTrace();}		

//         for (String i : parameters.keySet()) {
//             System.out.println(i + ": " + parameters.get(i)[0]);
//         }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
