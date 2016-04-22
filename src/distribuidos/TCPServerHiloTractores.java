package distribuidos;

import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.json.*;
//import javax.json.Json;
//import javax.json.JsonArray;

import org.json.simple.parser.JSONParser;

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
                        System.out.println("Soy objeto Tractor " +jsonOb.getString("humedad"));
                    } catch (JSONException ex) {
                        Logger.getLogger(TCPServerHiloTractores.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }
                
                
                
                
                outputLine = "Eco : " + inputLine;
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

    
    
}
