/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.teacher;

import controller.student.*;
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
import model.student.Student;
import model.student.StudentDAO;
import model.teacher.Teacher;
import model.teacher.TeacherDAO;

/**
 *
 * @author liamj
 */
@WebServlet("/admin/deleteTeacher")
public class DeleteTeacherServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TeacherDAO teacherDao = new TeacherDAO();
            List<Teacher> teacherList = teacherDao.getAllTeachers();
            request.setAttribute("teachers", teacherList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/deleteTeacher.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UpdateTeacherServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get parameters from the request
        String teacherID = request.getParameter("teacherID");

        // store data in User object
        Teacher teacher = new Teacher(teacherID);

        // validate the parameters
        String message = null;

        try {
                message = "Thank you, this teacher has been successfully deleted.";
                TeacherDAO.delete(teacher);
        
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DeleteTeacherServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("teacher", teacher);
        request.setAttribute("message", message);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/thanks.jsp");
        dispatcher.forward(request, response);
    }

}
    

