/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.course;

import controller.teacher.TeacherListServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.course.Course;
import model.course.CourseDAO;
import model.teacher.Teacher;
import model.teacher.TeacherDAO;

/**
 *
 * @author liamj
 */

@WebServlet("/teacher/listCourses")

public class CourseListServlet extends HttpServlet{
    
     @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{  
            CourseDAO courseDao = new CourseDAO();
            try {
            List<Course> courseList = courseDao.getAllCourses();
             request.setAttribute("courses", courseList);
         } catch (SQLException | ClassNotFoundException ex) {
             Logger.getLogger(CourseListServlet.class.getName()).log(Level.SEVERE, null, ex);
         }  
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/listCourses.jsp");
        dispatcher.forward(request, response);
    }      
    
    
}
