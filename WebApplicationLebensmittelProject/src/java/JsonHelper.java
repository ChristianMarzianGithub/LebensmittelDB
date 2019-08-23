
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CMA
 */
public class JsonHelper {
    
    public static String ResultSetToJsonString(ResultSet rs) throws SQLException{
        
        JSONArray jsonAr = new JSONArray();
        ResultSetMetaData  rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        
        
        if (columnCount > 0){
            while(rs.next()){
                JSONObject obj = new JSONObject();
                for(int i = 1; i <= columnCount; i++){
                    String columnName = rsmd.getColumnName(i);
                    obj.put(columnName, rs.getObject(i));
                }
               jsonAr.add(obj);          
            }
        }        
        
        
        return jsonAr.toJSONString();
    }
}
