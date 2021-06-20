package com.learnersAcademy;

import java.io.IOException;


import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pojos.Assignments;
import com.pojos.ClassYears;
import com.pojos.Classes;
import com.pojos.Students;
import com.pojos.SubjectCodes;
import com.pojos.Subjects;
import com.pojos.Teachers;

/**
 * Servlet implementation class ActionServlet
 */
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();		
		HttpSession hSession = request.getSession(false);

		Map<String, String[]> parameters = request.getParameterMap();

		
		String studentsB = request.getParameter("Students");
		String classesB = request.getParameter("Classes");
		String teachersB = request.getParameter("Teachers");
		String subjectsB = request.getParameter("Subjects");
		String viewClassReport = request.getParameter("View Class Report");
		String assignClasses = request.getParameter("Assign Classes");
		String assignTeachers = request.getParameter("Assign Teachers");
		String queryEntry = null;
		String queryConfirmation = null;
		String subjectsDelete = null;
		String RecordID= null;
		String assignmentID = null;
		String noteToPrint =  null;
		
				
		try{queryEntry = request.getParameter("QueryEntry");} catch (Exception e) {}
		try{queryConfirmation = request.getParameter("queryConfirmation");} catch (Exception e) {}
		try{subjectsDelete = request.getParameter("subjectsDelete");} catch (Exception e) {}
		try{RecordID = request.getParameter("RecordID");} catch (Exception e) {}
		try{assignmentID = request.getParameter("assignmentID");} catch (Exception e) {}
		try{noteToPrint = request.getParameter("NoteToPrint");} catch (Exception e) {}

		try {
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.openSession();
	
			
			if (studentsB != null) {
				if (studentsB.equals("View")) {
					System.out.println("St View");
	
					List<Students> list = session.createQuery("from Students ORDER BY studentID").list();
					
					out.println("<html><body>");
					out.println("</br><h2>View Students Table</h2><br><hr>");
							
					out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
	
					for (Students st : list) {
						out.println(st.toString() + "</br>");
					}
					
					out.println("<hr>");
					out.println("</body></html>");
	
				} else if (studentsB.equals("Add")) {
					System.out.println("St Add");
	
						List<Classes> listClass = session.createQuery("from Classes").list();
		
						String addText = ""; 
						
						if (noteToPrint != null) {
							addText = "<br><span style='color:red'>"+ noteToPrint + "</span><br>";
						}
						
						out.println("<html><body>");
						out.println("<h2>Add Record to Students Table</h2>" + addText + "<hr><form action=\"AddNewRecord\" method=\"post\"><input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"addStudent\">");
	
	
						
						out.println("		<table>\r\n" + 
								"			<tr><td>Student ID:</td><td><input type=\"number\" name=\"StudentID\" /></td><td>Number</td></tr>\r\n" + 
								"			<tr><td>National ID:</td><td><input type=\"text\" name=\"NationalID\" /></td><td>Text</td></tr>\r\n" + 
								"			<tr><td>Student Name:</td><td><input type=\"text\" name=\"Name\" /></td><td>Text</td></tr>\r\n" + 
								"			<tr><td>Student Surname:</td><td><input type=\"text\" name=\"Surname\" /></td><td>Text</td></tr>\r\n" + 
								"			<tr><td>Student Birth Date:</td><td><input type=\"date\" name=\"BirthDate\" /></td><td>Date Format: YYYY-MM-DD</td></tr>\r\n" + 
								"			<tr><td>Student Entry Year:</td><td><input type=\"text\" name=\"EntryYear\"  /></td><td>Year Format: YYYY</td></tr>\r\n" + 
								"		</table>\r\n" + 
								"		<hr>\r\n" + 
								"		<p><b>Please select gender:</b></p>\r\n" + 
								"		<input type=\"radio\" id=\"boy\" name=\"gender\" value=\"B\"><label for=\"boy\">Boy</label><br>\r\n" + 
								"		<input type=\"radio\" id=\"girl\" name=\"gender\" value=\"G\"><label for=\"girl\">Girl</label>\r\n" + 
								"		<hr>\r\n" + 
								"");
						
						out.println("<p><b>Please select class of the student:</b></p>\r\n");				
						for (Classes cl : listClass) {		
							out.println("<input type=\"radio\" id=\"" + cl.getID() + "\"name=\"Class\" value=\"" + cl.getName() + "\"><label for=\"" + cl.getID() + "\">" + cl.getName() + "</label><br>\r\n");
						}
		
						out.println("<hr><input type=\"submit\" value=\"Submit\"><hr></form>");
						
						out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						out.println("</body></html>");
	
				} else if (studentsB.equals("Delete")) {
	
					out.println("<html><body>");
	
					if (queryEntry != null) {
						try {
							Students list = (Students) session.createQuery("from Students Where studentID='" + queryEntry + "'").uniqueResult();
							
							out.println("Student entry to be deleted <br></br>");
		
							out.println(list.toString() + "</br>");
					
							out.println("<hr></br>");
							out.println("Please click to complete deleting the entry <br></br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"hidden\" name=\"queryConfirmation\" value =" + list.getID() + " /><input type=\"submit\" name=\"Students\" value=\"Delete\" /></form></br></hr>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
	
						} catch (java.lang.NullPointerException e) {
							out.println("Student with Student ID of " + queryEntry + " could not be found.</br>");
							out.println("Please try again ot return to Main Screen");
							out.println("Please enter Student ID of the student entry to be deleted <br></br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"text\" name=\"QueryEntry\"  /><br><br><input type=\"submit\" name=\"Students\" value=\"Delete\" /></form>");
							out.println("<br><form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
	
						}
					} else {
						
						if (queryConfirmation != null) {
							
							session.beginTransaction();
							Students student = (Students)session.load(Students.class, Long.valueOf(queryConfirmation));
							session.delete(student);
							out.println("Deleted Successfully<br><br>");
							session.getTransaction().commit();						
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
	
						} else {
							out.println("Please enter Student ID of the student entry to be deleted <br></br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"text\" name=\"QueryEntry\" /><br><br><input type=\"submit\" name=\"Students\" value=\"Delete\" /></form>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						}
					}
	
					
					out.println("</body></html>");
	
					System.out.println("St Delete");
					
				} else if (studentsB.equals("Amend")) {
					
					
	
					List<Classes> listClass = session.createQuery("from Classes").list();
						
					out.println("<html><body>");
		
					if (RecordID != null) {
						try {
							Students studentToAmend = (Students) session.createQuery("from Students WHERE studentID ='" + RecordID +  "'").uniqueResult();
							String bGender = "";
							String gGender = "";
							if(studentToAmend.getStudentGender().equals("B")){bGender="checked";} else {gGender="checked";}
	
							String addText = ""; 
							
							if (noteToPrint != null) {
								addText = "<br><span style='color:red'>"+ noteToPrint + "</span><br>";
							}
							
							out.println("<h2>Ammend Record in Students Table</h2>" + addText + "<hr><h3>Please amend Students entry with ID= " + RecordID + " on the below table</h3><br>");
							out.println("<form action=\"AddNewRecord\" method=\"post\">");
							out.println("<input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"addStudent\">");
							out.println("<input type=\"hidden\" name=\"RecordID\" id=\"RecordID\" value='" + RecordID + "'>");
							
							out.println("		<table>\r\n" + 
									"			<tr><td>National ID:</td><td><input type=\"text\" name=\"NationalID\" value= '" + studentToAmend.getNationalID() + "' /></td><td>Text</td></tr>\r\n" + 
									"			<tr><td>Student Name:</td><td><input type=\"text\" name=\"Name\" value= '" + studentToAmend.getStudentName() + "' /></td><td>Text</td></tr>\r\n" + 
									"			<tr><td>Student Surname:</td><td><input type=\"text\" name=\"Surname\" value= '" + studentToAmend.getStudentSurname() + "' /></td><td>Text</td></tr>\r\n" + 
									"			<tr><td>Student Birth Date:</td><td><input type=\"date\" name=\"BirthDate\" value= '" + studentToAmend.getBirthdate() + "' /></td><td>Date Format: YYYY-MM-DD</td></tr>\r\n" + 
									"			<tr><td>Student Entry Year:</td><td><input type=\"text\" name=\"EntryYear\" value= '" + studentToAmend.getEntryYear() + "'  /></td><td>Year Format: YYYY</td></tr>\r\n" + 
									"		</table>\r\n" + 
									"		<hr>\r\n" + 
									"		<p><b>Gender:</b></p>\r\n" + 
									"		<input type=\"radio\" id=\"boy\" name=\"gender\" value=\"B \"" + bGender + "><label for=\"boy\">Boy</label><br>\r\n" + 
									"		<input type=\"radio\" id=\"girl\" name=\"gender\" value=\"G \"" + gGender + "><label for=\"girl\">Girl</label>\r\n" + 
									"		<hr>\r\n" + 
									"");
							
							out.println("<p><b>Class of the student:</b></p>\r\n");				
							
							String studentClass;
							
							for (Classes cl : listClass) {	
								if(studentToAmend.getClassID() == cl){studentClass="checked";} else {studentClass="";}
								out.println("<input type=\"radio\" id=\"" + cl.getID() + "\"name=\"Class\" value=\"" + cl.getName() + "\" " + studentClass + "><label for=\"" + cl.getID() + "\">" + cl.getName() + "</label><br>\r\n");
							}
											
							out.println("		<hr>\r\n" + 
									"		<input type=\"submit\" value=\"Amend\">\r\n" + 
									"		<hr>\r\n" + 
									"	</form>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
							out.println("</body></html>");
						
						} catch (java.lang.NullPointerException e) {
							out.println("<h2>Ammend Record in Students Table</h2><br><hr>");
							out.println("Student with Student ID of " + RecordID + " could not be found.</br>");
							out.println("Please try again or return to Main Screen");
							out.println("<h3>Please enter the Student ID to be amended</h3><br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"addStudent\">");
							out.println("<input type=\"text\" name=\"RecordID\" id=\"RecordID\">");
							out.println("<input type=\"hidden\" name=\"Students\" value=\"Amend\"/>");
							out.println("<input type=\"submit\" value=\"Retrieve Record\"></form>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						}		
						
					} else {
						
							out.println("<h2>Ammend Record in Students Table</h2><br><hr>");
							out.println("<h3>Please enter the Student ID to be amended</h3><br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"addStudent\">");
							out.println("<input type=\"text\" name=\"RecordID\" id=\"RecordID\">");
							out.println("<input type=\"hidden\" name=\"Students\" value=\"Amend\"/>");
							out.println("<input type=\"submit\" value=\"Retrieve Record\"></form>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
			
					}
					
					System.out.println("St Amend");
				}
			} else if (classesB != null) {
				if (classesB.equals("View")) {
					System.out.println("C View");
	
					List<Classes> list = session.createQuery("from Classes ORDER BY name").list();
					
					out.println("</br><h2>View Classes Table</h2><br><hr>");
					
					out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
							
					for (Classes cl : list) {
						if (cl.getID() != 1) {
							out.println("&nbsp;" + cl.toString() + "</br>");
						}
					}
					out.println("<hr>");
	
				} else if (classesB.equals("Add")) {
					System.out.println("C Add");
	
					List<ClassYears> listClassYears = session.createQuery("from ClassYears ORDER BY ClassYearId").list();
	
					String addText = ""; 
					
					if (noteToPrint != null) {
						addText = "<br><span style='color:red'>"+ noteToPrint + "</span><br>";
					}
					
					out.println("<html><body>");
					out.println("<h2>Add Record to Classes Table</h2>" + noteToPrint + "<hr><form action=\"AddNewRecord\" method=\"post\"><input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"addClass\">");
	
					out.println("		<table>\r\n" + 
							"			<tr><td>Class Division:</td><td><input type=\"text\" name=\"classDiv\" /></td><td>If Class Name is 4D, Class Year is 4 and Class Division is D </td></tr>\r\n" + 
							"		</table>\r\n" + 
							"		<hr>");
					
					out.println("<p><b>Please select year of the class:</b></p>\r\n");				
					for (ClassYears cl : listClassYears) {					
						if (cl.getClassYearId() != 1) {
							out.println("<input type=\"radio\" id=\"" + cl.getClassYear() + "\"name=\"classYear\" value=\"" + cl.getClassYear() + "\"><label for=\"" + cl.getClassYear() + "\">" + cl.getClassYear() + "</label><br>\r\n");
						}
					}
					out.println("<input type=\"radio\" id=\"newYear\" name=\"classYear\" value=\"newYear\">Enter New Year <input type=\"number\" name = \"newYear\"><br>\r\n");
	
					out.println("	<hr>\r\n" + 
							"		<input type=\"submit\" value=\"Submit\">\r\n" + 
							"		<hr>\r\n" + 
							"	</form>");
					
					out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form></body></html>");
				} else if (classesB.equals("Delete")) {
	
	
					out.println("<html><body>");
					
					if (queryEntry != null) {
						try {
							if (queryEntry.equals("NA")) {
								queryEntry = "";
							}
							
							Classes list = (Classes) session.createQuery("from Classes Where name='" + queryEntry + "'").uniqueResult();
							List<Students> students = session.createQuery("from Students Where classID='" + list.getID() + "' ORDER BY studentID").list();
							
							out.println("Classes entry to be deleted <br></br>");
		
							out.println(list.toString() + "</br>");
					
							out.println("<hr></br>");
							
							if (students.size() > 0) {
								out.println("The following Class Name entries of Students table will become \"NA (NOT ASSIGNED)\" <br><br>");
								
								for (Students st : students) {
									out.println(st.toString() + "</br>");
								}
								out.println("<hr></br>");
							}
	
							List<Assignments> assignments = session.createQuery("from Assignments Where classID='" + list.getID() + "' ORDER BY subjectId").list();
	
							if (assignments.size() > 0) {
								out.println("The following Assignments will be deleted <br><br>");
								
								String teacherInfo = null;
								
								for (Assignments asst : assignments) {
									if (asst.getTeacherId().getID() == 1) {teacherInfo = "NO TEACHER ASSIGNED";} else {teacherInfo = asst.getTeacherId().toString();}
									out.println("ID= " + asst.getID() + ", Class Name= " + asst.getClassId().getName() + ", Subject Code= " + asst.getSubjectId().getSubjectCode() + ", Subject Name= "+ asst.getSubjectId().getSubjectName() + teacherInfo + "</br>");
								}
								out.println("<hr></br>");
							}
							
							out.println("<br></br>Please click to complete deleting the entry <br></br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"hidden\" name=\"queryConfirmation\" value =" + list.getID() + " /><input type=\"submit\" name=\"Classes\" value=\"Delete\" /></form></br></hr>");
							out.println("<br><form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
	
						
						} catch (java.lang.NullPointerException e) {
							out.println("Class with Class Name " + queryEntry + " could not be found.</br>");
							out.println("Please try again or return to Main Screen");
							out.println("Please enter name of the Class entry to be deleted <br></br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"text\" name=\"QueryEntry\" /><br><br><input type=\"submit\" name=\"Classes\" value=\"Delete\" /></form>");
							out.println("<br><form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
	
						}
					} else {
						
						if (queryConfirmation != null) {
							
							List<Students> students = session.createQuery("from Students Where classID='" + queryConfirmation + "'").list();
							Classes class0 = (Classes) session.createQuery("from Classes Where ID= 1").uniqueResult();
							List<Assignments> assignments = session.createQuery("from Assignments Where classID='" + queryConfirmation + "'").list();
	
	
															
							for (Students st : students) {
								Session session1 = factory.openSession();
								session1.beginTransaction();
								Students stu = (Students)session1.get(Students.class, st.getID());
								stu.setClassID(class0);
								out.println("Students" + st.getID() + " Updated Successfully<br>");
								session1.getTransaction().commit();
								session1.close();
							} 
	
							for (Assignments asst : assignments) {
								Session session1 = factory.openSession();
								session1.beginTransaction();
								Assignments assit = (Assignments)session1.get(Assignments.class, asst.getID());
								session1.delete(assit);
								session1.getTransaction().commit();
								session1.close();
							} 
	
							Session session1 = factory.openSession();
							session1.beginTransaction();
							Classes class1 = (Classes)session1.load(Classes.class, Long.valueOf(queryConfirmation));
							session1.delete(class1);
							session1.getTransaction().commit();						
							session1.close();
							out.println("Class " + class1.getName() + " Deleted Successfully<br><br>");
							
							//Clean ClassYears
							try {
								session.beginTransaction();
								ClassYears classYear0 = (ClassYears)session.load(ClassYears.class, Long.valueOf(class0.getYear().getClassYearId()));
								session.delete(classYear0);
								out.println("ClassYear " + classYear0.getClassYear() + " Deleted Successfully<br><br>");
								session.getTransaction().commit();
							} catch (Exception e) {
								System.out.println("no deletion of Year entry, ID still in use in Subjects or Classes database ");
							}
							
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
							
						} else {
							out.println("Please enter name of the Class to be deleted <br></br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"text\" name=\"QueryEntry\" /><br><br><input type=\"submit\" name=\"Classes\" value=\"Delete\" /></form>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						}
						
						
					}
					
					out.println("</body></html>");
	
					System.out.println("C Delete");				
				} 
				
			} else if (teachersB != null) {
				if (teachersB.equals("View")) {
					System.out.println("T View");
	
					List<Teachers> list = session.createQuery("from Teachers ORDER BY teacherId").list();
					
						out.println("</br><h2>View Teachers Table</h2><br><hr>");
						
						out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
								
						for (Teachers te : list) {
							if (te.getID() != 1) {
								out.println(te.toString() + "</br>");
							}
						}
						out.println("<hr>");
	
				} else if (teachersB.equals("Add")) {
					System.out.println("T Add");
	
					String addText = ""; 
					
					if (noteToPrint != null) {
						addText = "<br><span style='color:red'>"+ noteToPrint + "</span><br>";
					}
					
					
					out.println("<html><body>");
					out.println("<h2>Add Record to Teachers Table</h2>" + noteToPrint + "<hr><form action=\"AddNewRecord\" method=\"post\"><input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"addTeacher\">");
					out.println("		<table>\r\n" + 
							"			<tr><td>Teacher ID</td><td><input type=\"number\" name=\"teacherId\" /></td><td>Number</td></tr>\r\n" + 
							"			<tr><td>Name</td><td><input type=\"text\" name=\"teacherName\" /></td><td>Text</td></tr>\r\n" + 
							"			<tr><td>Surname</td><td><input type=\"text\" name=\"teacherSurname\" /></td><td>Text</td></tr>\r\n" + 
							"			<tr><td>Contact</td><td><input type=\"text\" name=\"teacherContact\"  /></td><td>Text</td></tr>\r\n" + 
							"		</table>\r\n" + 
							"		<hr>\r\n" + 
							"		<input type=\"submit\" value=\"Submit\">\r\n" + 
							"		<hr>\r\n" + 
							"	</form>");
					out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
					out.println("</body></html>");
					
				} else if (teachersB.equals("Delete")) {
	
					out.println("<html><body>");
					
					if (queryEntry != null) {
						try {
							
							if (queryEntry.equals("0")) {
								queryEntry = "";
							}
							long queryEntryAdjusted = Long.valueOf(queryEntry);
	
							Teachers list = (Teachers) session.createQuery("from Teachers Where teacherId='" + queryEntryAdjusted + "'").uniqueResult();
							System.out.println(list.toString());
							List<Subjects> subjects = session.createQuery("from Subjects Where teacherID='" + list.getID() + "' ORDER BY ID").list();
							List<Assignments> assignments = session.createQuery("from Assignments Where teacherID='" + list.getID() + "' ORDER BY ID").list();
							
							out.println("Teacher entry to be deleted <br></br>");
		
							out.println(list.toString() + "</br>");
					
							if (subjects.size() > 0) {
								out.println("<hr></br>");
								out.println("The following entries of Subjects table which have this Teacher ID will be deleted <br><br>");
								
								for (Subjects su : subjects) {
									out.println(su.getSubjectCode().toString() + "</br>");
								}
							}
							
							if (assignments.size() > 0) {						
								out.println("<hr></br>");
								out.println("The following entries of Assignments table which have this Teacher ID will become \"NOT ASSIGNED\" <br><br>");
								
								for (Assignments asst : assignments) {
									out.println("ID= " + asst.getID() + ", Class Name= " + asst.getClassId().getName()+ ", Subject Code= "+asst.getSubjectId().getSubjectCode()+", Subject Name="+asst.getSubjectId().getSubjectName() + "</br>");
								}
							}
							out.println("<hr></br>");
							
							out.println("<br></br>Please click to complete deleting the entry <br></br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"hidden\" name=\"queryConfirmation\" value =" + list.getID() + " /><input type=\"submit\" name=\"Teachers\" value=\"Delete\" /></form></br></hr>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						} catch (java.lang.NullPointerException e) {
							out.println("Teacher with Teacher ID of " + queryEntry + " could not be found.</br>");
							out.println("Please try again ot return to Main Screen");
							out.println("Please enter Teacher ID of the teacher entry to be deleted <br></br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"text\" name=\"QueryEntry\" /><br><br><input type=\"submit\" name=\"Teachers\" value=\"Delete\" /></form>");
							out.println("<br><form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						}	catch (java.lang.NumberFormatException e) {
							out.println("Format of Teacher ID needs to be a number larger than 0.</br>");
							out.println("Please try again ot return to Main Screen");
							out.println("Please enter Teacher ID of the teacher entry to be deleted <br></br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"text\" name=\"QueryEntry\" /><br><br><input type=\"submit\" name=\"Teachers\" value=\"Delete\" /></form>");
							out.println("<br><form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						}
					} else {
						
						if (queryConfirmation != null) {
							
							List<Subjects> subjects = session.createQuery("from Subjects Where teacherID='" + queryConfirmation + "'").list();
							Teachers teacher = (Teachers) session.createQuery("from Teachers Where ID= 1").uniqueResult();
							List<Assignments> assignments = session.createQuery("from Assignments Where teacherID='" + queryConfirmation + "'").list();
	
							for (Assignments asst : assignments) {
								asst.setTeacherId(teacher);
								session.beginTransaction();
								Assignments assin = (Assignments)session.get(Assignments.class, asst.getID());
								assin = asst;
								session.getTransaction().commit();
							}
							
							
							
							for (Subjects su : subjects) {
								session.beginTransaction();
								Subjects sub = (Subjects)session.get(Subjects.class, su.getID());
								session.delete(sub);
								session.getTransaction().commit();
							} 
	
	
							session.beginTransaction();
							teacher = (Teachers)session.load(Teachers.class, Long.valueOf(queryConfirmation));
							session.delete(teacher);
							out.println("Teacher" + teacher.getTeacherId() + " Deleted Successfully<br><br>");
							session.getTransaction().commit();						
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
							
							
						} else {
							out.println("Please enter Teacher ID of the teacher entry to be deleted <br></br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"number\" name=\"QueryEntry\" /><br><br><input type=\"submit\" name=\"Teachers\" value=\"Delete\" /></form>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						}
					}
	
					
					out.println("</body></html>");
					
					System.out.println("T Delete");
					
				} else if (teachersB.equals("Amend")) {
	
					out.println("<html><body>");
	
					if (RecordID != null) {
						
						try {
							long RecordIDTest = Long.valueOf(request.getParameter("RecordID"));
	
							if (Long.valueOf(RecordID) == 0) {
								RecordIDTest = Long.valueOf("a");
							}
							
							Teachers teacherToAmend = (Teachers) session.createQuery("from Teachers WHERE teacherId ='" + RecordID +  "'").uniqueResult();
							
							String teacherName = teacherToAmend.getTeacherName();
							String teacherSurname = teacherToAmend.getTeacherSurname();
							String teacherContact = teacherToAmend.getTeacherContact();
							
							String addText = ""; 
							
							if (noteToPrint != null) {
								addText = "<br><span style='color:red'>"+ noteToPrint + "</span><br>";
							}
							
							out.println("<h2>Ammend Record in Teachers Table</h2>" + addText + "<hr><h3>Please amend teacher entry with ID= " + RecordID + " on the below table</h3><br>");
							out.println("<form action=\"AddNewRecord\" method=\"post\">");
							out.println("<input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"addTeacher\">");
							out.println("<input type=\"hidden\" name=\"RecordID\" id=\"RecordID\" value='" + RecordID + "'>");
							out.println("		<table>\r\n" + 
									"			<tr><td>Name</td><td><input type=\"text\" name=\"teacherName\" value= '" + teacherName + "' /></td><td>Text</td></tr>\r\n" + 
									"			<tr><td>Surname</td><td><input type=\"text\" name=\"teacherSurname\" value= '" + teacherSurname + "' /></td><td>Text</td></tr>\r\n" + 
									"			<tr><td>Contact</td><td><input type=\"text\" name=\"teacherContact\" value= '" + teacherContact + "'  /></td><td>Text</td></tr>\r\n" + 
									"		</table>\r\n" + 
									"		<hr>\r\n" + 
									"		<input type=\"submit\" value=\"Amend\">\r\n" + 
									"		<hr>\r\n" + 
									"	</form>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
							out.println("</body></html>");
						} catch (java.lang.NullPointerException e) {
							out.println("<h2>Ammend Record in Teachers Table</h2><br><hr>");
							out.println("Teacher with Teacher ID of " + RecordID + " could not be found.</br>");
							out.println("Please try again or return to Main Screen");
							out.println("<h3>Please enter the Teacher ID to be amended</h3><br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"addTeacher\">");
							out.println("<input type=\"text\" name=\"RecordID\" id=\"RecordID\">");
							out.println("<input type=\"hidden\" name=\"Teachers\" value=\"Amend\"/>");
							out.println("<input type=\"submit\" value=\"Retrieve Record\"></form>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");	
						} catch (NumberFormatException e) {
							out.println("<h2>Ammend Record in Teachers Table</h2><br><hr>");
							out.println("Teacher ID needs to be a number larger than 0.</br>");
							out.println("Please try again or return to Main Screen");
							out.println("<h3>Please enter the Teacher ID to be amended</h3><br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"addTeacher\">");
							out.println("<input type=\"text\" name=\"RecordID\" id=\"RecordID\">");
							out.println("<input type=\"hidden\" name=\"Teachers\" value=\"Amend\"/>");
							out.println("<input type=\"submit\" value=\"Retrieve Record\"></form>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");							
						}
					} else {
						
						out.println("<h2>Ammend Record in Teachers Table</h2><br><hr>");
						out.println("<h3>Please enter the Teacher ID to be amended</h3><br>");
						out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"addTeacher\">");
						out.println("<input type=\"text\" name=\"RecordID\" id=\"RecordID\">");
						out.println("<input type=\"hidden\" name=\"Teachers\" value=\"Amend\"/>");
						out.println("<input type=\"submit\" value=\"Retrieve Record\"></form>");
						out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						
					}
		
					System.out.println("T Amend");
				}
			} else if (subjectsB != null) {
				if (subjectsB.equals("View")) {
					System.out.println("Su View");
	
					List<SubjectCodes> list = session.createQuery("from SubjectCodes ORDER BY subjectCode").list();
	
					out.println("</br><h2>View Subjects Table</h2><br><hr>");
	
					out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
	
					for (SubjectCodes sc : list) {
						out.println(sc.toString() + "</br>");
						Set<Subjects> list2 = sc.getSubjects();
	
						out.println("Teachers for the Subject:<br>");
						
						if (list2.size()>1) {
							for (Subjects su : list2) {
								if (su.getTeacherID().getID() != 1) {
									out.println("&nbsp;" + su.getTeacherID().toString() + "</br>");
								}
							}
						} else {
								out.println("&nbsp; NO TEACHER ADDED</br>");
						}
						out.println("<hr>");
					}
					
	
					out.println("<hr>");
	
				} else if (subjectsB.equals("Add")) {
					System.out.println("Su Add");
	
					List<ClassYears> listClassYears = session.createQuery("from ClassYears ORDER BY ClassYearId").list();
					List<Teachers> listTeachers = session.createQuery("from Teachers ORDER BY teacherId").list();
	
					String addText = ""; 
					
					if (noteToPrint != null) {
						addText = "<br><span style='color:red'>"+ noteToPrint + "</span><br>";
					}
	
					out.println("<html><body>");
					out.println("<h2>Add Record to Subjects Table</h2>" + noteToPrint + "<hr><form action=\"AddNewRecord\" method=\"post\"><input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"addSubject\">");
	
					out.println("		<table>\r\n" + 
							"			<tr><td>Subject Code</td><td><input type=\"text\" name=\"subjectCode\" /></td><td>Text</td></tr>\r\n" + 
							"			<tr><td>Subject Name</td><td><input type=\"text\" name=\"subjectName\" /></td><td>Text</td></tr>\r\n" + 
							"		</table>\r\n" + 
							"		<hr>");
					
					out.println("<p><b>Please select the year the subject is taught:</b></p>\r\n");				
					for (ClassYears cl : listClassYears) {		
						out.println("<input type=\"radio\" id=\"" + cl.getClassYear() + "\"name=\"classYear\" value=\"" + cl.getClassYear() + "\"><label for=\"" + cl.getClassYear() + "\">" + cl.getClassYear() + "</label><br>\r\n");
					}
	
					out.println("<p><b>Please select all the teachers teaching this subject or leave blank:</b></p>\r\n");				
					for (Teachers te : listTeachers) {
						if (te.getID() != 1) {
							out.println("<input type=\"checkbox\" id=\"" + te.getID() + "\" name=\"teachers\" value=\"" + te.getID() + "\"><label for=\"" + te.getID() + "\">" + te.toString() + "</label><br>\r\n");
						} else {
							out.println("<input type=\"checkbox\" id=\"" + te.getID() + "\" name=\"teachers\" value=\"" + te.getID() + "\" checked hidden=\"true\" ><br>\r\n");
						}
					}
					
					out.println("	<hr>\r\n" + 
							"		<input type=\"submit\" value=\"Submit\">\r\n" + 
							"		<hr>\r\n" + 
							"	</form></body></html>");
	
					out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
	
				} else if (subjectsB.equals("Delete")) {
	
					out.println("<html><body>");
					
					if (queryEntry != null) {
						try {
							SubjectCodes subjectToDelete = (SubjectCodes) session.createQuery("from SubjectCodes WHERE subjectCode ='" + queryEntry +  "'").uniqueResult();
							Set<Subjects> list = subjectToDelete.getSubjects();
							
							out.println("The Subject under Code= " + queryEntry+ " together with the below entries will be deleted <br></br>");
		
							out.println("<form action=\"ActionServlet\" method=\"post\">");
							for (Subjects su : list) {
								out.println(su.toString() + "<br>");
							}
							out.println("<hr></br>");
							
							List<Assignments> assignments = session.createQuery("from Assignments Where subjectId='" + subjectToDelete.getID() + "' ORDER BY classId").list();
	
							if (assignments.size() > 0) {
								out.println("The following Assignments will be deleted <br><br>");
								
								String teacherInfo = null;
								
								for (Assignments asst : assignments) {
									if (asst.getTeacherId().getID() == 1) {teacherInfo = "NO TEACHER ASSIGNED";} else {teacherInfo = asst.getTeacherId().toString();}
									out.println("ID= " + asst.getID() + ", Class Name= " + asst.getClassId().getName() + ", Subject Code= " + asst.getSubjectId().getSubjectCode() + ", Subject Name= "+ asst.getSubjectId().getSubjectName() + teacherInfo + "</br>");
								}
								out.println("<hr></br>");
							}
							
							out.println("Please click to complete deleting the selected entries <br></br>");
							out.println("<input type=\"hidden\" name=\"queryConfirmation\" value =\"Delete\" /><input type=\"hidden\" name=\"subjectsDelete\" value =\"" + queryEntry + "\" /><input type=\"submit\" name=\"Subjects\" value=\"Delete\" /></form></br></hr>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						} catch (java.lang.NullPointerException e) {
							out.println("Subject with Subject Code of " + queryEntry + " could not be found.</br>");
							out.println("Please try again or return to Main Screen");
							out.println("Please enter Subject Code of the subject entry to be deleted <br></br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"text\" name=\"QueryEntry\" /><br><br><input type=\"submit\" name=\"Subjects\" value=\"Delete\" /></form>");
							out.println("<br><form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
	
						}
					} else {
						
						if (queryConfirmation != null) {
	
							SubjectCodes subjectToDelete = (SubjectCodes) session.createQuery("from SubjectCodes WHERE subjectCode ='" + subjectsDelete +  "'").uniqueResult();
							Long iDtoDelete = subjectToDelete.getID();
							
							Set<Subjects> list = subjectToDelete.getSubjects();
													
							for (Subjects su : list) {   
								Session session1 = factory.openSession();
								session1.beginTransaction();
								Subjects subject = (Subjects)session1.load(Subjects.class, su.getID());
								session1.delete(subject);
								session1.getTransaction().commit();	
								session1.close();
							}
							
							List<Assignments> assignments = session.createQuery("from Assignments Where subjectId='" + subjectToDelete.getID() + "'").list();
	
							for (Assignments asst : assignments) {
								Session session1 = factory.openSession();
								session1.beginTransaction();
								Assignments assit = (Assignments)session1.get(Assignments.class, asst.getID());
								session1.delete(assit);
								session1.getTransaction().commit();
								session1.close();
							} 
							
	
							Session session1 = factory.openSession();
							session1.beginTransaction();
							SubjectCodes subject = (SubjectCodes)session1.load(SubjectCodes.class, iDtoDelete);
							session1.delete(subject);
							out.println("ID = " + subjectsDelete + " deleted Successfully<br>");
							session1.getTransaction().commit();						
						
							
							out.println("<br><form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
	
						} else {
							out.println("Please enter Subject Code of the subject entry to be deleted <br></br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"text\" name=\"QueryEntry\"  /><br><br><input type=\"submit\" name=\"Subjects\" value=\"Delete\" /></form>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						}
					}
	
					
					out.println("</body></html>");
	
					System.out.println("Su Delete");
					
				} else if (subjectsB.equals("Amend")) {
					
					List<Teachers> listTeachers = session.createQuery("from Teachers ORDER BY teacherId").list();
					List<ClassYears> listClassYears = session.createQuery("from ClassYears ORDER BY ClassYearId").list();
					
					out.println("<html><body>");
	
					if (RecordID != null) {
						
						try {
						
							SubjectCodes subjectToAmend = (SubjectCodes) session.createQuery("from SubjectCodes WHERE subjectCode ='" + RecordID +  "'").uniqueResult();
							
							String subjectName = subjectToAmend.getSubjectName();
							
							String addText = ""; 
							
							if (noteToPrint != null) {
								addText = "<br><span style='color:red'>"+ noteToPrint + "</span><br>";
							}
							
							out.println("<h2>Ammend Record in Subjects Table</h2>" + addText + "<hr><h3>Please amend Subjects entry with Subject Code= " + RecordID + " on the below table</h3><br>");
							out.println("<form action=\"AddNewRecord\" method=\"post\">");
							out.println("<input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"addSubject\">");
							out.println("<input type=\"hidden\" name=\"RecordID\" id=\"RecordID\" value='" + RecordID + "'>");
							
							out.println("		<table>\r\n" + 
									"			<tr><td>Subject Name:</td><td><input type=\"text\" name=\"subjectName\" value= '" + subjectToAmend.getSubjectName() + "' /></td><td>Text</td></tr>\r\n" + 
									"		</table>\r\n" + 
									"		<hr>\r\n" +  
									"");
							
							out.println("<p><b>Please select the year the subject is taught:</b></p>\r\n");				
							out.println("<p>Please note if the year is changed, all the assignments on this subject will be deleted!</p>\r\n");				
												
							
							String classYearCheck;
							for (ClassYears cl : listClassYears) {	
								if(subjectToAmend.getClassYear() == cl){classYearCheck="checked";} else {classYearCheck="";}						
								out.println("<input type=\"radio\" id=\"" + cl.getClassYear() + "\"name=\"classYear\" value=\"" + cl.getClassYear() + "\" " + classYearCheck + "><label for=\"" + cl.getClassYear() + "\">" + cl.getClassYear() + "</label><br>\r\n");
							}
							
							out.println("<p><b>Teachers for the Subject:</b></p>\r\n");				
							out.println("<p>Please note the teacjers you will uncheck is assigned to a class and subject, the assigned teacher will become unassigned!</p>\r\n");				
							
							String subjectTeacher;
							
							for (Teachers te : listTeachers) {	
								subjectTeacher="";
								Set<Subjects> subjectTeachers = subjectToAmend.getSubjects();
								if (te.getID() == 1) {
									out.println("<input type=\"checkbox\" id=\"" + te.getID() + "\"name=\"teachers\" value=\"" + te.getID() + "\" checked hidden=\"true\"><br>\r\n");
								} else {
									for (Subjects stt : subjectTeachers) {
										if(stt.getTeacherID() == te){subjectTeacher="checked";}
									}
									out.println("<input type=\"checkbox\" id=\"" + te.getID() + "\"name=\"teachers\" value=\"" + te.getID() + "\" " + subjectTeacher + "><label for=\"" + te.getID() + "\">" + te.toString() + "</label><br>\r\n");
								}
							}
											
							out.println("		<hr>\r\n" + 
									"		<input type=\"submit\" value=\"Amend\">\r\n" + 
									"		<hr>\r\n" + 
									"	</form>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
							out.println("</body></html>");
						} catch (java.lang.NullPointerException e) {
							out.println("<h2>Ammend Record in Subjects Table</h2><br><hr>");
							out.println("Subject with Subject Code of \"" + RecordID + "\" could not be found.</br>");
							out.println("Please try again or return to Main Screen<br>");
							out.println("<h3>Please enter the Subject Code to be amended</h3><br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"addSubject\">");
							out.println("<input type=\"text\" name=\"RecordID\" id=\"RecordID\">");
							out.println("<input type=\"hidden\" name=\"Subjects\" value=\"Amend\"/>");
							out.println("<input type=\"submit\" value=\"Retrieve Record\"></form>");	
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");					
							}
					} else {
						out.println("<h2>Ammend Record in Subjects Table</h2><br><hr><h3>");
						out.println("<h3>Please enter the Subject Code to be amended</h3><br>");
						out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"addSubject\">");
						out.println("<input type=\"text\" name=\"RecordID\" id=\"RecordID\">");
						out.println("<input type=\"hidden\" name=\"Subjects\" value=\"Amend\"/>");
						out.println("<input type=\"submit\" value=\"Retrieve Record\"></form>");	
						out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
					}			
					
					System.out.println("Su Amend");
				}
				
			} else if (viewClassReport != null) {
				
				List<Classes> list = session.createQuery("from Classes ORDER BY name").list();  
				
				out.println("<html><body>");
				out.println("</br><h2>Class Report (Students per Class)</h2><br>");
				out.println("<hr>");
				
				out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
								
				for (Classes p : list) {
					if (p.getID() != 1) {
						out.println("<b>ID: " + String.valueOf(p.getID()) + ", Class Name: " + p.getName() + ", Class Year: "
								+ p.getYear() + ", Class Div: " + p.getDiv()+"</b>");
			
						Set<Students> students = p.getStudents();
						out.println("</br>Students: </br>");
								
						for (Students s : students) {
							out.print("&nbsp;" + s.toString());
							out.print("</br>");
						}
					}					
				}
				
				out.println("<hr>");			
				out.println("</br><h2>Class Report (Subjects and Teachers per Class)</h2><br>");
				out.println("<hr>");
								
				for (Classes p : list) {
					if (p.getID() != 1) {						
						out.println("<b>ID: " + String.valueOf(p.getID()) + ", Class Name: " + p.getName() + ", Class Year: "
								+ p.getYear() + ", Class Div: " + p.getDiv()+"</b>");
			
						List<Assignments> assignmentsList = session.createQuery("from Assignments WHERE classId = '" + p.getID() + "' ORDER BY subjectId").list();  					
						out.println("</br>Assigned Subjects and Teachers:</br>");
								
						if (assignmentsList.size() == 0) {
							out.println("&nbsp;NO SUBJECT ASSIGNED FOR THIS CLASS<br>");
						} else {
							for (Assignments asst : assignmentsList) {
								
								String subjectInfo = "Subject Code= " + asst.getSubjectId().getSubjectCode() + ", Subject Name= " + asst.getSubjectId().getSubjectName();
								String teacherInfo = ", Teacher ID: " + asst.getTeacherId().getTeacherId() + ", Name: " + asst.getTeacherId().getTeacherName() + ", Surname: " +asst.getTeacherId().getTeacherSurname();
								if (asst.getTeacherId().getID() == 1) {teacherInfo = ", NO TEACHER ASSIGNED";}
								out.print("&nbsp;" + subjectInfo + teacherInfo);
								out.print("</br>");
							}
						}
						out.print("</hr>");
					}
				}	
						
				out.println("</body></html>");
	
	
				
				System.out.println("View Class Report");
			} else if (assignClasses != null) {
				
				if (assignClasses.equals("Assign")) {
	
					out.println("<html><body>");
	
					System.out.println("ActionServlet = " + queryEntry);
					
					if (queryEntry != null) {
						try {
							Classes class0 = (Classes) session.createQuery("from Classes WHERE className ='" + queryEntry +  "'").uniqueResult();
							List<SubjectCodes> subjectCodes = session.createQuery("from SubjectCodes WHERE classYear ='" + class0.getYear().getClassYearId() +  "' ORDER BY subjectCode").list();
							
							String addText = ""; 
							
							if (noteToPrint != null) {
								addText = "<br><span style='color:red'>"+ noteToPrint + "</span><br>";
							}
							
							out.println("<h2>Assign Subject to Class</h2>" + addText + "<hr><h3>Please select Subject to assign to the Class " + queryEntry + " on the below list</h3><br>");
							out.println("<form action=\"AddNewRecord\" method=\"post\">");
							out.println("<input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"assignClass\">");
							out.println("<input type=\"hidden\" name=\"classID\" id=\"classID\" value='" + class0.getID() + "'>");
							out.println("<input type=\"hidden\" name=\"QueryEntry\" id=\"QueryEntry\" value='" + queryEntry + "'>");
																											
							int counter = 0;					
							for (SubjectCodes su : subjectCodes) {
								List<Assignments> assign = session.createQuery("from Assignments WHERE classId ='" + class0.getID() +  "'").list();
								boolean included = false;
								for (Assignments asn : assign) {
									if (asn.getSubjectId().getSubjectCode() == su.getSubjectCode()) {included = true;}
								}
								if (!included) {
									out.println("<input type=\"radio\" id=\"subjectID\"name=\"subjectID\" value=\"" + su.getID() + "\"><label for=\"subjectID\">" + su.toString() + "</label><br>\r\n");
									counter++;
								}
								included = false;
							}
							
							if (counter == 0) {
								out.println("<br>All available subjects are already assigned to this class. No other subject available for this class.<br>");
							} else {
								out.println("<hr><input type=\"submit\" value=\"Assign\"><hr></form>");								
							}
											
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
							out.println("</body></html>");
						} catch (java.lang.NullPointerException e) {
							out.println("<h2>Assign Subject to a Class</h2><br><hr>");
							out.println("Class with the Name of " + queryEntry + " could not be found.</br>");
							out.println("Please try again or return to Main Screen<br>");
							out.println("<h3>Please enter the Class Name to assign a Subject</h3><br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"assignClass\">");
							out.println("<input type=\"text\" name=\"QueryEntry\" id=\"QueryEntry\">");
							out.println("<input type=\"hidden\" name=\"Assign Classes\" value=\"Assign\"/>");
							out.println("<input type=\"submit\" value=\"Retrieve Record\"></form>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						}
					} else {
						
						out.println("<h2>Assign Subject to a Class</h2><br><hr>");
						out.println("<h3>Please enter the Class Name to assign a Subject</h3><br>");
						out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"assignClass\">");
						out.println("<input type=\"text\" name=\"QueryEntry\" id=\"QueryEntry\">");
						out.println("<input type=\"hidden\" name=\"Assign Classes\" value=\"Assign\"/>");
						out.println("<input type=\"submit\" value=\"Retrieve Record\"></form>");
						out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
					
					}
			
					System.out.println("Assign Classes");
				} else if (assignClasses.equals("Delete")) {
				
					out.println("<html><body>");
					
					if (queryEntry != null) {
						try {
							Classes class0 = (Classes) session.createQuery("from Classes WHERE className ='" + queryEntry +  "'").uniqueResult();
							List<Assignments> assignments = session.createQuery("from Assignments WHERE classId ='" + class0.getID() +  "'").list();
							
							out.println("<h2>Delete Assignment</h2><br><hr>");
							out.println("Please select the assignment to be deleted");
		
							out.println("<form action=\"ActionServlet\" method=\"post\">");
							for (Assignments asst : assignments) {	
								out.println("<input type=\"radio\" id=\"assignmentID\"name=\"assignmentID\" value=\"" + asst.getID() + "\"><label for=\"assignmentID\">" + asst.toString() + "</label><br>\r\n");
							}
							
							out.println("<hr></br>");
							out.println("Please click to complete deleting the selected assignment <br></br>");
							out.println("<input type=\"hidden\" name=\"queryConfirmation\" value =\"Delete\" /><input type=\"hidden\" name=\"assignmentDelete\" value =\"" + queryEntry + "\" /><input type=\"submit\" name=\"Assign Classes\" value=\"Delete\" /></form></br></hr>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						} catch (java.lang.NullPointerException e) {
							out.println("<h2>Delete Assignment</h2><br><hr>");
							out.println("Assignment with Class Name of " + queryEntry + " could not be found.</br>");
							out.println("Please try again or return to Main Screen<br>");
							out.println("Please enter Class Name of the assignment entry to be deleted <br></br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"text\" name=\"QueryEntry\" /><br><br><input type=\"submit\" name=\"Assign Classes\" value=\"Delete\" /></form>");
							out.println("<br><form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						}
					} else {					
						if (queryConfirmation != null) {
							Session session1 = factory.openSession();
							session1.beginTransaction();
							Assignments assignment = (Assignments)session1.load(Assignments.class, Long.valueOf(assignmentID));
							session1.delete(assignment);
							out.println("Assignment deleted Successfully<br>");
							session1.getTransaction().commit();											
							out.println("<br><form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						} else {
							out.println("<h2>Delete Assignment</h2><br><hr>");
							out.println("Please enter Class Name of the assignment entry to be deleted <br></br>");
							out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"text\" name=\"QueryEntry\"  /><br><br><input type=\"submit\" name=\"Assign Classes\" value=\"Delete\" /></form>");
							out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						}
					}
					
					out.println("</body></html>");	
					
					System.out.println("Delete Classes");
				}
			} else if (assignTeachers != null) {
	
				out.println("<html><body>");
				
				if (queryEntry != null) {
	
					try {
						Classes class0 = (Classes) session.createQuery("from Classes WHERE className ='" + queryEntry +  "'").uniqueResult();
						List<Assignments> assignments = session.createQuery("from Assignments WHERE classId ='" + class0.getID() +  "'").list();
						
						out.println("Please select the assignment to assign / change teacher");
	
						out.println("<form action=\"ActionServlet\" method=\"post\">");
						for (Assignments asst : assignments) {	
							out.println("<input type=\"radio\" id=\"assignmentID\"name=\"assignmentID\" value=\"" + asst.getID() + "\"><label for=\"assignmentID\">" + asst.toString() + "</label><br>\r\n");
						}
						
						out.println("<hr></br>");
						out.println("Please click to select the teacher<br></br>");
						out.println("<input type=\"hidden\" name=\"queryConfirmation\" value =\"Delete\" /><input type=\"hidden\" name=\"assignTeacher\" value =\"" + queryEntry + "\" /><input type=\"submit\" name=\"Assign Teachers\" value=\"Assign/Change\" /></form></br></hr>");
						out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
					} catch (java.lang.NullPointerException e) {
						out.println("Assignment with Class Name of " + queryEntry + " could not be found.</br>");
						out.println("Please try again or return to Main Screen");
						out.println("Please enter Class Name of the entry to assign/change teacher <br></br>");
						out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"text\" name=\"QueryEntry\" /><br><br><input type=\"submit\" name=\"Assign Teachers\" value=\"Assign/Change\" /></form>");
						out.println("<br><form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
					}
				} else {					
					if (queryConfirmation != null) {
							
						out.println("<html><body>");
							
						Assignments assignment = (Assignments) session.createQuery("from Assignments WHERE ID ='" + assignmentID +  "'").uniqueResult();
						List<Subjects> subjects = session.createQuery("from Subjects WHERE subjectCode ='" + assignment.getSubjectId().getID() +  "'").list();
						
						out.println("<h2>Assign / change teacher for assignment no: " + assignment.getID() + " class name: "+ assignment.getClassId().getName()+ " subject code: "+ assignment.getSubjectId().getSubjectCode()  + "</h2><hr><h3>Please select teacher</h3><br>");
						out.println("<form action=\"AddNewRecord\" method=\"post\">");
						out.println("<input type=\"hidden\" name=\"actionName\" id=\"actionName\" value=\"assignTeacher\">");
						out.println("<input type=\"hidden\" name=\"RecordID\" id=\"RecordID\" value='" + assignmentID + "'>");
							
						Teachers naTeacher = (Teachers) session.createQuery("from Teachers WHERE ID = 1").uniqueResult();
							
						String teacherCheck = " ";
	
						for (Subjects su : subjects) {	
							if(assignment.getTeacherId().getID() == su.getTeacherID().getID()){teacherCheck="checked";} else {teacherCheck=" ";}
							out.println("<input type=\"radio\" id=\"teacher\"name=\"teacher\" value=\"" + su.getTeacherID().getID() + "\" " + teacherCheck + "><label for=\"teacher\">" + su.getTeacherID().toString() + "</label><br>\r\n");
						}
																	
						out.println("		<hr>\r\n" + 
								"		<input type=\"submit\" value=\"Assign/Change\">\r\n" + 
								"		<hr>\r\n" + 
								"	</form>");
						out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
						out.println("</body></html>");
						
					} else {
	
						out.println("Please enter Class Name of the entry to assign/change teacher <br></br>");
						out.println("<form action=\"ActionServlet\" method=\"post\"><input type=\"text\" name=\"QueryEntry\"  /><br><br><input type=\"submit\" name=\"Assign Teachers\" value=\"Assign/Change\" /></form>");
						out.println("<form action=\"mainScreen.html\"><input type=\"submit\" value=\"Return to Main Screen\"></form>");
				}
				}
				
				out.println("</body></html>");		
				
					System.out.println("Assign Teachers");
			} 
	
			
		session.close();
	
		} catch (Exception e) {
				e.printStackTrace();
	
		}

					
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}