/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.teacher;

import controller.student.*;
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
import model.teacher.Teacher;
import model.teacher.TeacherDAO;
import util.DBConnection;

/**
 *
 * @author liamj
 */
@WebServlet("/admin/updateTeacher")
public class UpdateTeacherServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TeacherDAO teacherDao = new TeacherDAO();
            List<Teacher> teacherList = teacherDao.getAllTeachers();
            request.setAttribute("teachers", teacherList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/updateTeacher.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UpdateTeacherServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //receive values from drop down list and form
        
        String teacherID = request.getParameter("teacherID");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String phoneNumber = request.getParameter("phoneNumber");
        int salary = Integer.parseInt(request.getParameter("salary"));

        // store data in User object
        Teacher teacher = new Teacher(teacherID, firstName, lastName, address, city, country, phoneNumber, salary);

        // validate the parameters
        String message = null;

        try {
        TeacherDAO teacherDao = new TeacherDAO();
        List<Teacher> teacherList = teacherDao.getAllTeachers();
        request.setAttribute("teachers", teacherList);
        TeacherDAO.update(teacher);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdateTeacherServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        message = "Thank you, your update has been successful!";

        request.setAttribute("teacher", teacher);
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/updateTeacher.jsp");
        dispatcher.forward(request, response);
}

}