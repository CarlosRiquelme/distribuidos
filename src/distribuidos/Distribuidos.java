/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;

import java.sql.SQLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author toshiba
 */
public class Distribuidos {


    public static void main(String[] args) throws SQLException {
        
        String uno="2";
        String datoEnviar = DatosTractores.obtenerDB(uno);
        System.out.println(datoEnviar);
    }
    
}
