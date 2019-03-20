package controller.student;

import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.student.Student;
import model.student.StudentDAO;

@WebServlet("/admin/addStudent")
public class AddStudentServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/addStudent.jsp");
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
        String major = request.getParameter("major");

        //store data
        Student student = new Student(firstName, lastName, address, city, country, phoneNumber, major);

        // validate the parameters
        String message = null;
        String message2 = null;
        try {
            if (StudentDAO.studentExists(student.getPhoneNumber())) {
                message2 = "A student with this phone number already exists.<br>";
                url = "/view/addStudent.jsp";
            } else {
                message2 = "Thank you, '" + student.getFirstName() + "' has been successfuly added to the database.";
//                    url = "/thanks.jsp";
                StudentDAO.insert(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("student", student);
        request.setAttribute("message", message);
        request.setAttribute("message2", message2);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/addStudent.jsp");
        dispatcher.forward(request, response);
    }
}
