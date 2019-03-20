/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.course;

import controller.student.StudentListServlet;
import controller.student.UpdateStudentServlet;
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
import model.student.Student;
import model.student.StudentDAO;

/**
 *
 * @author liamj
 */
@WebServlet("/teacher/findCourse")
public class FindCourseServlet extends HttpServlet{
    
       @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{  
        CourseDAO courseDao = new CourseDAO();
         try {
            List<Course> courseList = courseDao.getAllCourses();
             request.setAttribute("courses", courseList);
         } catch (SQLException | ClassNotFoundException ex) {
             Logger.getLogger(FindCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
         }  
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/findOneCourse.jsp");
        dispatcher.forward(request, response);
    }
    
    
  
 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
        Course course = new Course();
        String courseID = request.getParameter("courseID");
        String message = null;

        try {
        course =  CourseDAO.selectCourse(courseID);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FindCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        message = "Thank you, here is your result!";
        
        request.setAttribute("course", course);
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/findOneCourseB.jsp");
        dispatcher.forward(request, response);
}    
    
}
