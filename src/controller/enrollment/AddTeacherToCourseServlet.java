/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.enrollment;

import controller.course.UpdateCourseServlet;
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
import model.enrollment.EnrollmentDAO;
import model.teacher.Teacher;
import model.teacher.TeacherDAO;

/**
 *
 * @author liamj
 */
@WebServlet("/admin/addTeacherToCourse")
public class AddTeacherToCourseServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = null;
        try {
            if (TeacherDAO.anyTeachers() && CourseDAO.anyCourses()) {
                TeacherDAO teacherDao = new TeacherDAO();
                List<Teacher> teacherList = teacherDao.getAllTeachers();
                request.setAttribute("teachers", teacherList);
                CourseDAO courseDao = new CourseDAO();
                List<Course> courseList = courseDao.getAllCourses();
                request.setAttribute("courses", courseList);
                url = "/view/addTeacherToCourse.jsp";
            } else if (TeacherDAO.anyTeachers() == false && CourseDAO.anyCourses() == false) {
                String message = "There aren't any teachers or courses in the database... anybody home?";
                url = "/view/sorry.jsp";
                request.setAttribute("message", message);
            } else if (CourseDAO.anyCourses() == false) {
                String message = "Sorry, there aren't any courses currently in the database...";
                url = "/view/addCourse.jsp";
                request.setAttribute("message", message);
            } else if (TeacherDAO.anyTeachers() == false) {
                String message = "Sorry, there aren't any teachers currently in the database...";
                url = "/view/addTeacher.jsp";
                request.setAttribute("message", message);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AddTeacherToCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message = null;

        String courseID = request.getParameter("courseID");
        String teacherID = request.getParameter("teacherID");
        Teacher teacher = new Teacher(teacherID);
        Course course = new Course(courseID);

        try {
            EnrollmentDAO.insert(teacher, course);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdateCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        message = "Thank you, the enrollment was successful!";

        request.setAttribute("course", course);
        request.setAttribute("teacher", teacher);
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/thanks.jsp");
        dispatcher.forward(request, response);
    }

}
