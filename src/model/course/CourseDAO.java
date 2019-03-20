/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.teacher.Teacher;
import model.teacher.TeacherDAO;
import util.DBConnection;
import util.DBUtil;

/**
 *
 * @author liamj
 */
public class CourseDAO {
    
    //CREATE
    public static int insert(Course course) throws SQLException, ClassNotFoundException {
        //this area accomplishes the same things 

        PreparedStatement ps = null;

        String query
                = "INSERT INTO COURSE_FP (coursename, credit) "
                + "VALUES (?, ?)";
        try {
            Connection conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getCredit());
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);

        }
    
}
    //READ

    public static boolean courseExists(String courseName) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT coursename FROM course_fp "
                + "WHERE coursename = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, courseName);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
        }
    }

    public static boolean courseExistsII(String courseID) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT courseID FROM course_fp "
                + "WHERE courseID = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, courseID);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
        }
    }    
    
    public List<Course> getAllCourses() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        ArrayList<Course> courseList = new ArrayList();
        String query = "SELECT courseid, coursename, credit FROM course_fp";

        try {
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                Course course = new Course();
                course.setCourseID(result.getString(1));
                course.setCourseName(result.getString(2));
                course.setCredit(result.getInt(3));
                courseList.add(course);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courseList;
    }    
    
    public static Course selectCourse(String courseID) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
            
        String query = "SELECT * FROM course_fp WHERE courseID = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, courseID);
            rs = ps.executeQuery();
            Course course = null;
            if (rs.next()) {
                course = new Course();
                course.setCourseID(rs.getString("courseID"));
                course.setCourseName(rs.getString("courseName"));
                course.setCredit(rs.getInt("credit"));                
            }
           return course;
        } catch (SQLException e) {
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
        }
    }      
    
        public static boolean anyCourses() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * from course_fp";
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
    
    //UPDATE
    public static int update(Course course) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE course_fp SET COURSENAME = ?, CREDIT = ? "
                + "WHERE COURSEID = ?";
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getCredit());
            ps.setString (3, course.getCourseID());
            
        return    ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }  
    
//    public static int update(Course course) throws ClassNotFoundException, SQLException {
//        Connection conn = DBConnection.getConnection();
//        PreparedStatement ps = null;
//        String query = "UPDATE course_fp SET COURSENAME = ?, CREDIT = ? "
//                + "WHERE COURSEID = ?";
//        
//        try {
//            ps = conn.prepareStatement(query);
//            ps.setString(1, course.getCourseName());
//            ps.setInt(2, course.getCredit());
//            ps.setString (3, course.getCourseID());
//            
//        return    ps.executeUpdate();
//        } catch (SQLException e) {
//            return 0;
//        } finally {
//            DBUtil.closePreparedStatement(ps);
//        }
//    }       
    

    
    //DELETE
    public static int delete(Course course) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM course_fp "
                + "WHERE courseID = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, course.getCourseID());

            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }        
    
    
    
    
}