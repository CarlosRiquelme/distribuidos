package distribuidos;

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


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class TCPServerHiloTractores  extends Thread {
    
     private Socket socket = null;

    public TCPServerHiloTractores(Socket socket) {
        super("TCPServerHiloTractores");
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
    //Object obj = new FileReader("/home/marcel/Documentos/Distribuidos/datos.json");

    /*JsonObject jo = (JsonObject) obj;      
    System.out.println("json object = " + jo.toString());
    JsonReader jsonReader = Json.createReader(in);
    JsonArray jsonArray = jsonReader.readArray();
    ListIterator l = jsonArray.listIterator();
    while ( l.hasNext() ) {
          JsonObject j = (JsonObject)l.next();
          JsonObject ciAttr = j.getJsonObject("tractores") ;
    }*/
            String altura="";
            String humedad = "";
            String peso= "";
            String temperatura= "";
            
            //JSON a enviar
            //JSONObject objJsonEnviar = new JSONObject();
            //JSONParser parser = new JSONParser();
            Gson gson =new Gson();
            

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Mensaje recibido: " + inputLine);
                String mje= inputLine.toString();

                if (inputLine.equals("salir")) {
                    out.println(inputLine);
                    break;
                }
                
                ArrayList<Tractor> lista = new ArrayList();
                lista=gson.fromJson(inputLine, ArrayList.class);
                JSONArray array = new JSONArray(lista);
               
                
                //System.out.println("Soy objeto Tractor " +array);
                for (int i=0;i<array.length();i++) {
                    JSONObject jsonOb;
                    try {
                        jsonOb = array.getJSONObject(i);
                        Tractor tractor=new Tractor();
                        tractor.setAltura(jsonOb.getString("altura"));
                        tractor.setCodigo_tractor(jsonOb.getInt("codigo_tractor"));
                        tractor.setHumedad(jsonOb.getString("humedad"));
                        tractor.setPeso(jsonOb.getString("peso"));
                        tractor.setTemperatura(jsonOb.getString("temperatura"));
                        GuardarDB(tractor);
                    } catch (JSONException ex) {
                        Logger.getLogger(TCPServerHiloTractores.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(TCPServerHiloTractores.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                
                
                
                outputLine = "Se ha guardado sus datos en BD Maestro";
                out.println(outputLine);
            }
            out.close();
            in.close();
            socket.close();
            System.out.println("Finalizando Hilo");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void GuardarDB (Tractor tractor) throws SQLException{
    
        String servidor = "jdbc:postgresql://localhost/maestroDB";
        
        Connection miConexion = ConnectionDB.GetConnection(servidor);
                
        System.out.println(miConexion);
          
        //JSONObject objJson = new JSONObject();
        String humedad = "", peso ="", temperatura="", altura="";
        Integer  codigo_tractor=0;

        if(miConexion != null){
            humedad = tractor.getHumedad();
            peso = tractor.getPeso();
            temperatura = tractor.getTemperatura();
            altura = tractor.getAltura();
            codigo_tractor = tractor.getCodigo_tractor();
            Statement consul = miConexion.createStatement(); 
            String codigo = "INSERT INTO tractores (humedad, peso, temperatura, altura, codigo_tractor) VALUES ('"+ humedad + "','"+ peso +"','" + temperatura + "','" + altura + "','"+ Integer.toString(codigo_tractor) + "');";
            consul.executeUpdate (codigo);
            //consul.prepareStatement (codigo);
        }
        //return re;
    }

    
    
}
