/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.login;

import controller.course.CourseListServlet;
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


/**
 *
 * @author liamj
 */

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    
         @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{  
 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/home.jsp");
        dispatcher.forward(request, response);
    }     
    

    
    
}
