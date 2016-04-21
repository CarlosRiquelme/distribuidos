
package distribuidos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class TCPClienteTractor {


    public static void main(String[] args) throws IOException, SQLException {
        Socket unSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String datosTractor;
        try {
            unSocket = new Socket("localhost", 4444);
            // enviamos nosotros
            out = new PrintWriter(unSocket.getOutputStream(), true);

            //viene del servidor
            in = new BufferedReader(new InputStreamReader(unSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host desconocido");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error de I/O en la conexion al host");
            System.exit(1);
        }


        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;
        //ReadPersona leerPer = new ReadPersona();
        boolean continuar = true;
        
        fromServer = in.readLine();
        System.out.println("Servidor: " + fromServer);
        
        System.out.print("Ingrese Codigo del Tractor: ");
        fromUser = stdIn.readLine();
        datosTractor=DatosTractores.obtenerDB(fromServer);
        
        if (fromUser != null) {
            //System.out.println("Ingrese Cedula: " + fromUser);

            //escribimos al servidor
            out.println(datosTractor);
        }
        fromServer = in.readLine();
      
        
        fromServer = in.readLine();
        System.out.println(fromServer);
        
              
        fromServer = "exit";
        out.close();
        in.close();
        stdIn.close();
        unSocket.close();
    }
    
}
