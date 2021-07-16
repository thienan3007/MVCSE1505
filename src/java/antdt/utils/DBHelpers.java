/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBHelpers implements Serializable{
    public static Connection makeConnection() 
            throws /*ClassNotFoundException*/ NamingException,SQLException {

        //get current context
            Context context = new InitialContext();
        // get container context - tomcat
            Context tomCatContext = (Context) context.lookup("java:comp/env");
        // get DataSource
            DataSource ds = (DataSource) tomCatContext.lookup("SE140048");
        // get connection
            Connection con = ds.getConnection();
            return con;
//        //1. Load driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create Connection String
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=Web;instanceName=SQLEXPRESS";
//        //3. Open Connection
//        Connection con = DriverManager.getConnection(url,"sa","12345");
//        
        
    }
}
