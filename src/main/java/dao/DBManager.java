package dao;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private static final Logger logger = LogManager.getLogger("DBManager");
    private DBManager(){}

    private static DataSource ds;

    public static Connection getConnection() throws SQLException{
        if(ds == null){
            try {
                Context context = new InitialContext();
                ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
            }catch (NamingException ex){
                logger.log(Level.ERROR,"Unable to get connection!",ex);
                throw new RuntimeException("Unable to get connection! "+ex.getMessage());
            }
        }
        return ds.getConnection();
    }

    public static void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException ex){
                logger.log(Level.ERROR,"Unable to  close connection",ex);
                throw new RuntimeException("Unable to close connection! "+ex.getMessage());
            }
        }
    }

    public static void rollback(Connection con){
        if(con != null) {
            try {
                con.rollback();
            }catch (SQLException ex){
                logger.log(Level.ERROR,"Unable to rollback!",ex);
                throw new RuntimeException("Unable to rollback! "+ex.getMessage());
            }
        }
    }
}
