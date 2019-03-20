/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.student;

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

/**
 *
 * @author liamj
 */

@WebServlet("/teacher/listStudents")
public class StudentListServlet extends HttpServlet {
    
     @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{  
        StudentDAO studentDao = new StudentDAO();
         try {
            List<Student> studentList = studentDao.getAllStudents();
             request.setAttribute("students", studentList);
         } catch (SQLException | ClassNotFoundException ex) {
             Logger.getLogger(StudentListServlet.class.getName()).log(Level.SEVERE, null, ex);
         }  
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/listStudents.jsp");
        dispatcher.forward(request, response);
    }      
    
}
