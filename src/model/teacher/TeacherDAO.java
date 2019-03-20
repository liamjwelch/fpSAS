package model.teacher;

import model.student.*;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.DBConnection;

public class TeacherDAO {

    public static int insert(Teacher teacher) throws SQLException, ClassNotFoundException {
        //this area accomplishes the same things 

        PreparedStatement ps = null;

        String query
                = "INSERT INTO TEACHER_FP (firstname, lastname, address, city, country, phonenumber, salary) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, teacher.getFirstName());
            ps.setString(2, teacher.getLastName());
            ps.setString(3, teacher.getAddress());
            ps.setString(4, teacher.getCity());
            ps.setString(5, teacher.getCountry());
            ps.setString(6, teacher.getPhoneNumber());
            ps.setInt(7, teacher.getSalary());
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);

        }
    }

    public static int update(Teacher teacher) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE teacher_fp SET FIRSTNAME = ?, LASTNAME = ?, ADDRESS = ?, CITY = ?, COUNTRY = ?, PHONENUMBER = ?, SALARY = ? "
                + "WHERE TEACHERID = ?";
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, teacher.getFirstName());
            ps.setString(2, teacher.getLastName());
            ps.setString (3, teacher.getAddress());
            ps.setString (4, teacher.getCity());
            ps.setString (5, teacher.getCountry());
            ps.setString (6, teacher.getPhoneNumber());
            ps.setInt (7, teacher.getSalary());
            ps.setString (8, teacher.getTeacherID());
            
        return    ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }


    public static int delete(Teacher teacher) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM teacher_fp "
                + "WHERE teacherID = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, teacher.getTeacherID());

            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }

    public static boolean teacherExists(String teacherID) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT teacherid FROM teacher_fp "
                + "WHERE teacherid = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, teacherID);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
        }
    }
    
    public static boolean teacherExistsII(String phoneNumber) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT phonenumber FROM teacher_fp "
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

    public static Teacher selectTeacher(String teacherID) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
            
        String query = "SELECT * FROM teacher_fp WHERE teacherID = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, teacherID);
            rs = ps.executeQuery();
            Teacher teacher = null;
            if (rs.next()) {
                teacher = new Teacher();
                teacher.setFirstName(rs.getString("firstName"));
                teacher.setLastName(rs.getString("lastName"));
                teacher.setAddress(rs.getString("address"));
                teacher.setCity(rs.getString("city"));
                teacher.setCountry(rs.getString("country"));
                teacher.setPhoneNumber(rs.getString("phoneNumber"));
                teacher.setSalary(rs.getInt("salary"));                
            }
           return teacher;
        } catch (SQLException e) {
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
        }
    }

    public List<Teacher> getAllTeachers() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        ArrayList<Teacher> teacherList = new ArrayList();
        String query = "SELECT teacherid, firstname, lastname, address, city, country, phonenumber, salary FROM teacher_fp";

        try {
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherID(result.getString(1));
                teacher.setFirstName(result.getString(2));
                teacher.setLastName(result.getString(3));
                teacher.setAddress(result.getString(4));
                teacher.setCity(result.getString(5));
                teacher.setCountry(result.getString(6));                
                teacher.setPhoneNumber(result.getString(7));
                teacher.setSalary(result.getInt(8));
                teacherList.add(teacher);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return teacherList;
    }

        public static boolean anyTeachers() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * from teacher_fp";
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
    
}
