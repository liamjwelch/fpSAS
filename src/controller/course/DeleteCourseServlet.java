/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.course;


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


/**
 *
 * @author liamj
 */
@WebServlet("/admin/deleteCourse")
public class DeleteCourseServlet extends HttpServlet {
 
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CourseDAO courseDao = new CourseDAO();
            List<Course> courseList = courseDao.getAllCourses();
            request.setAttribute("courses", courseList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/deleteCourse.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DeleteCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseID = request.getParameter("courseID");

        Course course = new Course(courseID);

        String message = null;

        try {
                CourseDAO.delete(course);
                message = "Thank you, this course has been successfully deleted.";
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DeleteCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("course", course);
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/deleteCourse.jsp");
        dispatcher.forward(request, response);
    }    
}
