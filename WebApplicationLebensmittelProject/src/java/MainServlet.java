/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author CMA
 */
@WebServlet(urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public String processTableRequest(String tableName) throws SQLException, IOException, ParseException{
        String retVal = "";
        
        
        
        DataBaseHelper y = new DataBaseHelper();
        Connection conn = y.connect();
        Statement sqlStatementTableContent = conn.createStatement();
        
        
        ResultSet rsTableContent;
        rsTableContent = sqlStatementTableContent.executeQuery("SELECT * FROM " + tableName);
        
        JSONArray jsonAr = new JSONArray();
        ResultSetMetaData  rsmd = rsTableContent.getMetaData();
        int columnCount = rsmd.getColumnCount();
        
        if (columnCount > 0){
            while(rsTableContent.next()){
                JSONObject obj = new JSONObject();
                for(int i = 1; i <= columnCount; i++){
                    String columnName = rsmd.getColumnName(i);
                    obj.put(columnName, rsTableContent.getObject(i));
                }
               jsonAr.add(obj);          
            }
        }
        
                   
                
        
                
                
                
        
        
        //Ausgabe des JSON-Strings
        retVal += jsonAr.toJSONString();     
        
        return retVal;
    }
    
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        
        out.println(processTableRequest(request.getParameter("table")));
        
        
        
        /*response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DataBaseHelper dbHelper = new DataBaseHelper();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            
            
            
            out.println(dbHelper.getTable("REZEPT"));
            out.println(dbHelper.getTable("EINHEIT"));
            out.println(dbHelper.getTable("LAGERART"));
            out.println(dbHelper.getTable("LEBENSMITTEL"));
            out.println(dbHelper.getTable("LEBENSMITTELLAGER"));
            out.println(dbHelper.getTable("REZEPTINHALT"));
            
            
            
            out.println("</body>");
            out.println("</html>");
        
    
        }
        */
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
