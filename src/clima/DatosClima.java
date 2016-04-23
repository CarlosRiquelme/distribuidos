
package clima;

import com.google.gson.Gson;
import distribuidos.ConnectionDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Clima;
import org.json.simple.JSONObject;

public class DatosClima {
    
     public static String obtenerDB(String datoRecibido) throws SQLException
    {
        String servidor = "jdbc:postgresql://localhost/distribuidosDB";
        
        Connection miConexion = ConnectionDB.GetConnection(servidor);
                
        System.out.println(miConexion);
          
        JSONObject objJson = new JSONObject();
        Gson gson = new Gson();
        String formatoJSON=""; 
        if(miConexion != null){
            String clima_id = datoRecibido;
            System.out.println("id clima = " + clima_id);
            double dato = Integer.valueOf(clima_id);
             
            Statement s = miConexion.createStatement(); 
            ResultSet rs = s.executeQuery ("select * from clima where codigo_clima = " + dato);
            boolean isRecord = false;
            ArrayList<Clima> lista = new ArrayList();
            
            while (rs.next()){       
                Clima clima=new Clima();
                clima.setZona(rs.getString("zona"));
                clima.setDepartamento(rs.getString("departamento"));
                clima.setDia(rs.getString("dia"));
                clima.setDistrito(rs.getString("distrito"));
                clima.setCodigo_clima(rs.getInt("codigo_clima"));
                lista.add(clima);
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
