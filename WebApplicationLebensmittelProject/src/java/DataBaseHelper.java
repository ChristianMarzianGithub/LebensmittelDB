
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CMA
 */
public class DataBaseHelper {
    public Connection connect(){
        Connection conn;
        conn = null;
        try {                
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();  
                String url ;
                url = "jdbc:sqlserver://LOCALHOST\\sqlexpress:1433;databasename=LebensmittelDB";
                
                conn = DriverManager.getConnection(url,"testUser","roller1@sql");
                

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        return conn;
    } 
    public String getTableOfRezept(){
    String retVal = "";
            Connection conn = this.connect();
            try{
            Statement sqlStatement = conn.createStatement();
            ResultSet rs;

            rs = sqlStatement.executeQuery("SELECT * FROM REZEPT");
            
            retVal = retVal + "<table border='1'>";
            retVal = retVal + "<th>";
            retVal = retVal + "ID";
            retVal = retVal + "</th>";
            retVal = retVal + "<th>";
            retVal = retVal + "Bezeichnung";
            retVal = retVal + "</th>";
            
            
            while(rs.next()){     
                retVal = retVal + "<tr>";
                retVal = retVal + "<td>";
                retVal = retVal + rs.getString(1);    
                retVal = retVal + "</td>";
                retVal = retVal + "<td>";
                retVal = retVal + rs.getString(2);    
                retVal = retVal + "</td>";
                retVal = retVal + "</tr>";
            }        
                    
            } catch (SQLException ex) {
                Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
            }  
            
            
            
            retVal = retVal + "</table>";
    return retVal;
    }
    
}
