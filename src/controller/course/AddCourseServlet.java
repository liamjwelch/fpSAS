/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.course;

import controller.teacher.AddTeacherServlet;
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
@WebServlet("/teacher/addCourse")
public class AddCourseServlet extends HttpServlet{
    
    
        
     @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{  

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/addCourse.jsp");
        dispatcher.forward(request, response);
    }      

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String url = "";
            // get parameters from the request
            String courseName = request.getParameter("courseName");
            int credit = Integer.parseInt(request.getParameter("credit"));
            
            //store data
            Course course = new Course(courseName, credit);

            // validate the parameters
            String message2 = null;
            try {
                if (CourseDAO.courseExists(course.getCourseName())) {
                    message2 = "A course with this name already exists.<br>";
                } 
                else {
                message2 = "Thank you, the course '" + course.getCourseName() + "' has been successfuly added to the database.";
                    CourseDAO.insert(course);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("course", course);
            request.setAttribute("message2", message2);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/addCourse.jsp");
        dispatcher.forward(request, response);    
    
}
}
