/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.enrollment;

import controller.student.StudentListServlet;
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
import model.enrollment.Enrollment;
import model.enrollment.EnrollmentDAO;

/**
 *
 * @author liamj
 */
@WebServlet("/teacher/listResults")
public class ListResultServlet extends HttpServlet{

     @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{  
        EnrollmentDAO enrollmentDao = new EnrollmentDAO();
         try {
            List<Enrollment> resultList = enrollmentDao.getAllResults();
             request.setAttribute("results", resultList);
         } catch (SQLException | ClassNotFoundException ex) {
             Logger.getLogger(StudentListServlet.class.getName()).log(Level.SEVERE, null, ex);
         }  
        RequestDispatcher dispatcher = request.getRequestDispatcher("//view/listResults.jsp");
        dispatcher.forward(request, response);
    }          
    
    
}
