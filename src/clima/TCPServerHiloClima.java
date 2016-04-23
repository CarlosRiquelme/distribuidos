package clima;

import distribuidos.*;
import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.json.*;
//import javax.json.Json;
//import javax.json.JsonArray;

import org.json.simple.parser.JSONParser;
import java.sql.Connection;
import java.sql.Statement;
import model.Clima;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class TCPServerHiloClima  extends Thread {
    
     private Socket socket = null;

    public TCPServerHiloClima(Socket socket) {
        super("TCPServerHiloClima");
        this.socket = socket;
    }

    public void run() {

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    socket.getInputStream()));
            out.println("Bienvenido!");
            String inputLine, outputLine;
            String zona ="";
            String departamento ="";
            String distrito ="";
            String dia="";
            
            Gson gson =new Gson();
            int ban=0;
            

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Mensaje recibido: " + inputLine);
                String mje= inputLine.toString();

                if (inputLine.equals("salir")) {
                    out.println(inputLine);
                    break;
                }
                
                ArrayList<Clima> lista = new ArrayList();
                lista=gson.fromJson(inputLine, ArrayList.class);
                JSONArray array = new JSONArray(lista);
               
                
                //System.out.println("Soy objeto Clima " +array);
                for (int i=0;i<array.length();i++) {
                    JSONObject jsonOb;
                    try {
                        jsonOb = array.getJSONObject(i);
                        Clima clima=new Clima();
                        clima.setDepartamento(jsonOb.getString("departamento"));
                        clima.setZona(jsonOb.getString("zona"));
                        clima.setDistrito(jsonOb.getString("distrito"));
                        clima.setDia(jsonOb.getString("dia"));
                        clima.setCodigo_clima(jsonOb.getInt("codigo_clima"));
                        GuardarDB(clima);
                    } catch (JSONException ex) {
                        Logger.getLogger(TCPServerHiloClima.class.getName()).log(Level.SEVERE, null, ex);
                        ban=1;
                    } catch (SQLException ex) {
                        Logger.getLogger(TCPServerHiloClima.class.getName()).log(Level.SEVERE, null, ex);
                        ban=1;
                    }
                }
                if (ban==0){
                    outputLine = "Se ha enviado a la Base de Datos";
                    out.println(outputLine);
                }
                else{
                    outputLine = "ERROR: NO SE PUDO ENVIAR A LA BASE DE DATO MAESTRO";
                    out.println(outputLine);
                }
                    
            }
            out.close();
            in.close();
            socket.close();
            System.out.println("Finalizando Hilo");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void GuardarDB (Clima clima) throws SQLException{
    
        String servidor = "jdbc:postgresql://localhost/maestroDB";
        
        Connection miConexion = ConnectionDB.GetConnection(servidor);
                
        System.out.println(miConexion);
          
        //JSONObject objJson = new JSONObject();
        String zona = "", distrito ="", departamento="", dia="";
        Integer  codigo_clima=0;

        if(miConexion != null){
            zona = clima.getZona();
            distrito = clima.getDistrito();
            departamento = clima.getDepartamento();
            dia = clima.getDia();
            codigo_clima=clima.getCodigo_clima();
            
            Statement consul = miConexion.createStatement(); 
            String codigo = "INSERT INTO clima (zona, distrito, departamento,codigo_clima,dia) VALUES ('"+ zona + "','"+ distrito +"','" + departamento + "','"+ Integer.toString(codigo_clima) + "','" + dia + "')";
            consul.executeUpdate (codigo);
            //consul.prepareStatement (codigo);
        }
        //return re;
    }

    
    
}
