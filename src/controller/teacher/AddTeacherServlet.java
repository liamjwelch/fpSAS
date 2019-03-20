/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.teacher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.teacher.Teacher;
import model.teacher.TeacherDAO;

/**
 *
 * @author liamj
 */

    
    
@WebServlet("/admin/addTeacher")
public class AddTeacherServlet extends HttpServlet {
    
         @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{  

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/addTeacher.jsp");
        dispatcher.forward(request, response);
    }      


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String url = "";
            // get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String country = request.getParameter("country");
            String phoneNumber = request.getParameter("phoneNumber");
            int salary = Integer.parseInt(request.getParameter("salary"));
            
            Teacher teacher = new Teacher(firstName, lastName, address, city, country, phoneNumber, salary);


            String message = null;
            try {
                if (TeacherDAO.teacherExistsII(teacher.getPhoneNumber())) {
                    message = "A teacher with this phone number already exists.<br>";
                    url = "/view/addTeacher.jsp";
                }
                else {
                message = "Thank you, '" + teacher.getFirstName() + "' has been successfuly added to the database.";
//                    url = "/thanks.jsp";
                    TeacherDAO.insert(teacher);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AddTeacherServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("teacher", teacher);
            request.setAttribute("message", message);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/addTeacher.jsp");
        dispatcher.forward(request, response);
    }    
    
}

