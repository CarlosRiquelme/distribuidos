
package clima;

import distribuidos.*;
import java.net.*;
import java.io.*;

public class TCPMultiServerClima {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) throws IOException {
        String inputLine, outputLine;
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(4445);
        } catch (IOException e) {
            System.err.println("No se puede abrir el puerto: 4445.");
            System.exit(1);
        }
        System.out.println("Puerto abierto: 4445.");

        while (listening) {
            new TCPServerHiloClima(serverSocket.accept()).start();
        }

        serverSocket.close();
    }
    
}
