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
@WebServlet("/admin/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {
    
        @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{  
        StudentDAO studentDao = new StudentDAO();
         try {
            List<Student> studentList = studentDao.getAllStudents();
             request.setAttribute("students", studentList);
         } catch (SQLException | ClassNotFoundException ex) {
             Logger.getLogger(StudentListServlet.class.getName()).log(Level.SEVERE, null, ex);
         }  
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/deleteStudent.jsp");
        dispatcher.forward(request, response);
    }    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";

        // get parameters from the request
        String studentID = request.getParameter("studentID");

        // store data in User object
        Student student = new Student(studentID);

        // validate the parameters
        String message = null;

        try {
                message = "Thank you, this student has been successfully deleted.";
                StudentDAO.delete(student);
        
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DeleteStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("student", student);
        request.setAttribute("message", message);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/thanks.jsp");
        dispatcher.forward(request, response);
    }

}
    

