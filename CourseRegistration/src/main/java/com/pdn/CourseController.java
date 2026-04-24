package com.pdn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {

	@RequestMapping("/courses")
	public ModelAndView showCourses() {
		 ModelAndView mv = new ModelAndView();
		 ArrayList<String> courseList = new ArrayList<>();
		 
		 try {
			 
			 Class.forName("com.mysql.cj.jdbc.Driver");
	         Connection con = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/university_db",
	                    "root",
	                    "pt.ME#21"
	            );
	         
	         Statement st = con.createStatement();
	         ResultSet rs = st.executeQuery("SELECT * FROM courses");
	         
	         while(rs.next()) {
	        	 int id = rs.getInt("course_id");
	        	 String name = rs.getString("name");
	        	 courseList.add(id + ":" + name);
	         }
			 
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 mv.setViewName("courses.jsp");
		 mv.addObject("courses",courseList);
		 
		 return mv;
	}
	
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest req) {
		String courseId = req.getParameter("courseId");
		ModelAndView mv = new ModelAndView();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/university_db",
	                "root",
	                "pt.ME#21"
	        );
	        
	        PreparedStatement  ps = con.prepareStatement(
	        		"INSERT INTO registrations(student_id, course_id, date) VALUES (?, ?, CURDATE())"
	        		);
	        
	        ps.setInt(1, 1);
	        ps.setInt(2, Integer.parseInt(courseId));
	        
	        ps.executeUpdate();
	        mv.setViewName("success.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	
	
	
	
}
