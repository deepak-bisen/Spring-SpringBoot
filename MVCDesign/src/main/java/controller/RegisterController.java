package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import dbcon.DBConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/regForm")
public class RegisterController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name1");
		String email = req.getParameter("email1");
		String password = req.getParameter("password1");
		String contact = req.getParameter("contact1");

		PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
		
        try {
			Connection con = DBConnection.getConection();

			String insert_query = "INSERT INTO register VALUES(?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(insert_query);

			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, contact);

			int count = ps.executeUpdate();
			if (count > 0) {
				System.out.println("registration sucessfull");
				out.print("<h3 style ='color:green'>registration sucessfull</h3>");

				RequestDispatcher rd = req.getRequestDispatcher("/login.html");
				rd.include(req, resp);
			} else {
				
				out.print("<h3 style ='color:red'>registration failed</h3>");

				RequestDispatcher rd = req.getRequestDispatcher("/register.html");
				rd.include(req, resp);
			}
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
}
