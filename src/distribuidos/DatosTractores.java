/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
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
        Gson gson = new Gson();
        String formatoJSON=""; 
        if(miConexion != null){
            String tractor_id = datoRecibido;
            System.out.println("id tractor = " + tractor_id);
            double dato = Integer.valueOf(tractor_id);
             
            Statement s = miConexion.createStatement(); 
            ResultSet rs = s.executeQuery ("select * from tractores where codigo_tractor = " + dato);
            boolean isRecord = false;
            ArrayList<Tractor> lista = new ArrayList();
            
            while (rs.next()){       
                Tractor tractor=new Tractor();
                tractor.setAltura(rs.getString("altura"));
                tractor.setHumedad(rs.getString("humedad"));
                tractor.setPeso(rs.getString("peso"));
                tractor.setTemperatura(rs.getString("temperatura"));
                tractor.setCodigo_tractor(rs.getInt("codigo_tractor"));
                lista.add(tractor);
                isRecord = true;
              
            }
            formatoJSON = gson.toJson(lista);
            
            if (!isRecord){
                formatoJSON="null";
            }
        }
        return formatoJSON;
    }



}
