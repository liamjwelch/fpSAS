/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.course;

import controller.teacher.UpdateTeacherServlet;
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
@WebServlet("/admin/updateCourse")
public class UpdateCourseServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CourseDAO courseDao = new CourseDAO();
            List<Course> courseList = courseDao.getAllCourses();
            request.setAttribute("courses", courseList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/updateCourse.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UpdateCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //receive values from drop down list and form
        
        String courseID = request.getParameter("courseID");
        String courseName = request.getParameter("courseName");
        int credit = Integer.parseInt(request.getParameter("credit"));


        // store data in User object
        Course course = new Course(courseID, courseName, credit);

        // validate the parameters
        String message = null;

        try {
            CourseDAO.update(course);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdateCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        message = "Thank you, your update has been successful!";
        
        request.setAttribute("course", course);
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/thanks.jsp");
        dispatcher.forward(request, response);
}    
    
}
