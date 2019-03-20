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

@WebServlet("/teacher/listTeachers")
public class TeacherListServlet extends HttpServlet {
    
     @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{  
            TeacherDAO teacherDao = new TeacherDAO();
            try {
            List<Teacher> teacherList = teacherDao.getAllTeachers();
             request.setAttribute("teachers", teacherList);
         } catch (SQLException | ClassNotFoundException ex) {
             Logger.getLogger(TeacherListServlet.class.getName()).log(Level.SEVERE, null, ex);
         }  
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/listTeachers.jsp");
        dispatcher.forward(request, response);
    }      
    
}
