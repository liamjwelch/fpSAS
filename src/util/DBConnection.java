
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    
    private final static String USER= "student";
    private final static String PASSWORD= "ok";
    private final static String URL= "jdbc:oracle:thin:@localhost:1521:xe";
    private static Connection connection;
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(connection == null || connection.isClosed()){
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
    
    
}
