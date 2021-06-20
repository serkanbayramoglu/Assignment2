package com.learnersAcademy;

import java.io.IOException;



import java.io.PrintWriter;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pojos.Admin;



/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		boolean validate=false;
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		String name = null;
		String surname = null;

		
		try {
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.openSession();

			List<Admin> list = session.createQuery("from Admin WHERE userId = '"+ userId + "'").list();
	
			session.close();
			
			for (Admin p : list) {
								
				if ((p.getUserId().contentEquals(userId)) && (p.getPassword().contentEquals(password))) {
					validate = true;
					name = p.getName();
					surname = p.getSurname();
				}
			}
			
		} catch (Exception e) {
				e.printStackTrace();

		}
		

		HttpSession hSession = request.getSession();
		hSession.setAttribute("userid", userId);

		
		
		
		if (validate) {
			out.println("Login Successful - Welcome " + name + " " + surname+"</br></br></br>");		
			request.getRequestDispatcher("mainScreen.html").include(request, response);
		} else {
			out.println("<SPAN style='color:red'>Username or password incorrect. Please try again!</SPAN>");			
			request.getRequestDispatcher("index.html").include(request, response);
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
