package distribuidos;

import java.net.*;
import java.io.*;
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
