package com.pdn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLogin() {
		return "login.jsp";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest req) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		ModelAndView mv = new ModelAndView();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/university_db",
					"root",
					"pt.ME#21"
					);
			
			PreparedStatement ps =  con.prepareStatement(
					"SELECT * FROM students WHERE email=? AND password=?"
					);
			
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				mv.setViewName("success.jsp") ;
			}else {
				mv.addObject("eror", "Invalid email or password!");
				mv.setViewName("login.jsp") ;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return mv;
	}
}
