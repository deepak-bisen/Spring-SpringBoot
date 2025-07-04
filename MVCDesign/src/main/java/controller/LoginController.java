package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbcon.DBConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.User;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email1");
		String password = req.getParameter("password1");

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		try {
			Connection con = DBConnection.getConection();
			String query = "SELECT * FROM register WHERE email = ? and password = ?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setContact(rs.getString("contact"));
				user.setPassword(rs.getString("password"));

				HttpSession session = req.getSession();
				session.setAttribute("user_session", user);

				RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
				rd.forward(req, resp);

			} else {

				out.print("<h3 style ='color:red'>login failed</h3>");

				RequestDispatcher rd = req.getRequestDispatcher("/register.html");
				rd.include(req, resp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
