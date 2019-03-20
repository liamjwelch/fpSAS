/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.enrollment;

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
import model.enrollment.Enrollment;
import model.enrollment.EnrollmentDAO;
import model.student.Student;
import model.student.StudentDAO;

/**
 *
 * @author liamj
 */
@WebServlet("/teacher/updateResult")
public class UpdateResultServlet extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = null;
        try {
            if (StudentDAO.anyStudents() && CourseDAO.anyCourses()){
            //get information from student
            StudentDAO studentDao = new StudentDAO();
            List<Student> studentList = studentDao.getAllStudents();
            request.setAttribute("students", studentList);
            //get information from course
            CourseDAO courseDao = new CourseDAO();
            List<Course> courseList = courseDao.getAllCourses();
            request.setAttribute("courses", courseList);
            url = "/view/updateResult.jsp";
            } else if (StudentDAO.anyStudents() == false && CourseDAO.anyCourses() == false){
                String message = "There aren't any students or courses in the database... anybody home?";
                url = "/view/sorry.jsp";
                request.setAttribute("message", message);
            } else if(CourseDAO.anyCourses() == false) {
                String message = "Sorry, there aren't any courses currently in the database...";
                url = "/view/addCourse.jsp";
                request.setAttribute("message", message);
            } else if (StudentDAO.anyStudents() == false){
                String message = "Sorry, there aren't any students currently in the database...";
                url = "/view/addStudent.jsp";
                request.setAttribute("message", message);                
            } 

            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UpdateStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
    }    

    
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String message = null;
        String url = null;
        
        String studentID = request.getParameter("studentID");
        String courseID = request.getParameter("courseID");
        int mark = Integer.parseInt(request.getParameter("mark"));
        int sessionNumber = Integer.parseInt(request.getParameter("sessionNumber"));
        
        Student student = new Student(studentID);
        Course course = new Course(courseID);
        Enrollment enrollment = new Enrollment(mark, sessionNumber);
        
        request.setAttribute("student", student);
        request.setAttribute("course", course);
        request.setAttribute("enrollment", enrollment);  

        try { if(EnrollmentDAO.studentInCourse(studentID, courseID) == false) {
        message = "That student isn't enrolled in that course.";
        url = "/view/sorry.jsp";
        } else {
            EnrollmentDAO.updateResult(student, course, enrollment);
            message = "Thank you, the grade was updated successfully!";
            url = "/view/thanks.jsp";            
        }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddResultServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
                     
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
}    

    
    
}
