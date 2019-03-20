/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.student;

import java.io.IOException;
import java.sql.Connection;
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
import model.student.Student;
import model.student.StudentDAO;
import util.DBConnection;

/**
 *
 * @author liamj
 */
@WebServlet("/admin/updateStudent")
public class UpdateStudentServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = null;
        try {
            if (StudentDAO.anyStudents()) {
                try {
                    StudentDAO studentDao = new StudentDAO();
                    List<Student> studentList = studentDao.getAllStudents();
                    request.setAttribute("students", studentList);
                    url = "/view/updateStudent.jsp";
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(UpdateStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
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

        //receive values from drop down list and form
        String studentID = request.getParameter("studentID");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String phoneNumber = request.getParameter("phoneNumber");
        String major = request.getParameter("major");

        // store data in User object
        Student student = new Student(studentID, firstName, lastName, address, city, country, phoneNumber, major);

        // validate the parameters
        String message = null;

        try {
            StudentDAO.update(student);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdateStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        message = "Thank you, your update has been successful!";

        request.setAttribute("student", student);
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/addStudent.jsp");
        dispatcher.forward(request, response);
    }

}
