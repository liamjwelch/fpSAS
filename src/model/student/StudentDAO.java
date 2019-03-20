package model.student;

import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.DBConnection;

public class StudentDAO {

    public static int insert(Student student) throws SQLException, ClassNotFoundException {
        //this area accomplishes the same things 

        PreparedStatement ps = null;

        String query
                = "INSERT INTO STUDENT_FP (firstname, lastname, address, city, country, phonenumber, major) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getAddress());
            ps.setString(4, student.getCity());
            ps.setString(5, student.getCountry());
            ps.setString(6, student.getPhoneNumber());
            ps.setString(7, student.getMajor());
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);

        }
    }

    public static int update(Student student) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE student_fp SET FIRSTNAME = ?, LASTNAME = ?, ADDRESS = ?, CITY = ?, COUNTRY = ?, PHONENUMBER = ?, MAJOR = ? "
                + "WHERE STUDENTID = ?";
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString (3, student.getAddress());
            ps.setString (4, student.getCity());
            ps.setString (5, student.getCountry());
            ps.setString (6, student.getPhoneNumber());
            ps.setString (7, student.getMajor());
            ps.setString (8, student.getStudentID());
            
        return    ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }


    public static int delete(Student student) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM student_fp "
                + "WHERE studentID = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, student.getStudentID());

            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }
//
    public static boolean studentExists(String phoneNumber) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT phonenumber FROM student_fp "
                + "WHERE phonenumber = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, phoneNumber);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
        }
    }
    
    public static boolean anyStudents() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * from student_fp";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
        }
        
    }

    public static Student selectStudent(String studentID) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
            
        String query = "SELECT * FROM student_fp WHERE studentID = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, studentID);
            rs = ps.executeQuery();
            Student student = null;
            if (rs.next()) {
                student = new Student();
                student.setFirstName(rs.getString("firstName"));
                student.setLastName(rs.getString("lastName"));
                student.setAddress(rs.getString("address"));
                student.setCity(rs.getString("city"));
                student.setCountry(rs.getString("country"));
                student.setPhoneNumber(rs.getString("phoneNumber"));
                student.setMajor(rs.getString("major"));                
            }
           return student;
        } catch (SQLException e) {
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
        }
    }

    public List<Student> getAllStudents() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        ArrayList<Student> studentList = new ArrayList();
        String query = "SELECT studentid, firstname, lastname, address, city, country, phonenumber, major FROM student_fp";

        try {
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                Student student = new Student();
                student.setStudentID(result.getString(1));
                student.setFirstName(result.getString(2));
                student.setLastName(result.getString(3));
                student.setAddress(result.getString(4));
                student.setCity(result.getString(5));
                student.setCountry(result.getString(6));                
                student.setPhoneNumber(result.getString(7));
                student.setMajor(result.getString(8));
                studentList.add(student);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return studentList;
    }

}
