package distribuidos;

import java.io.*;
import java.net.*;
import java.util.ListIterator;
import javax.json.*;
import javax.json.Json;
import javax.json.JsonArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
    Object obj = new FileReader("/home/marcel/Documentos/Distribuidos/datos.json");

    JsonObject jo = (JsonObject) obj;      
    System.out.println("json object = " + jo.toString());
    JsonReader jsonReader = Json.createReader(in);
    JsonArray jsonArray = jsonReader.readArray();
    ListIterator l = jsonArray.listIterator();
    while ( l.hasNext() ) {
          JsonObject j = (JsonObject)l.next();
          JsonObject ciAttr = j.getJsonObject("tractores") ;
    }
            String altura="";
            String humedad = "";
            String peso= "";
            String temperatura= "";
            
            //JSON a enviar
            JSONObject objJsonEnviar = new JSONObject();
            JSONParser parser = new JSONParser();

            

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Mensaje recibido: " + inputLine);

                if (inputLine.equals("salir")) {
                    out.println(inputLine);
                    break;
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
