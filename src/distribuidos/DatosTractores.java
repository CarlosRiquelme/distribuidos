/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONObject;


/**
 *
 * @author toshiba
 */
public class DatosTractores {
    
    
    public static String obtenerDB(String datoRecibido) throws SQLException
    {
        String servidor = "jdbc:postgresql://localhost/distribuidosDB";
        
        Connection miConexion = ConnectionDB.GetConnection(servidor);
                
        System.out.println(miConexion);
          
        JSONObject objJson = new JSONObject();

        if(miConexion != null){
            String tractor_id = datoRecibido;
            System.out.println("id tractor = " + tractor_id);
            double dato = Integer.valueOf(tractor_id);
             
            Statement s = miConexion.createStatement(); 
            ResultSet rs = s.executeQuery ("select * from tractores where codigo_tractor = " + dato);
            
            boolean isRecord = false;
            while (rs.next()){
                objJson.put("Altura", rs.getString("altura"));
                
                objJson.put("Temperatura",  rs.getString("temperatura"));
                objJson.put("Peso",  rs.getString("peso"));
                objJson.put("Humedad",  rs.getString("humedad"));
                System.out.println("hola soy altura    "+rs.getString("temperatura"));
                isRecord = true;
                System.out.println("hola soy alguine");
            }
            
            if (!isRecord){
                objJson.put("Altura", "");
                objJson.put("Temperatura",  "");
                objJson.put("Peso",  "");
                objJson.put("Humedad",  "");
            }
        }
        System.out.println(objJson.toString());
        String datosPersonales = objJson.toString();
        return datosPersonales;
    }



}
