
package distribuidos;

import java.net.*;
import java.io.*;

public class TCPMultiServerTractores {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) throws IOException {
        String inputLine, outputLine;
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("No se puede abrir el puerto: 4444.");
            System.exit(1);
        }
        System.out.println("Puerto abierto: 4444.");

        while (listening) {
            new TCPServerHiloTractores(serverSocket.accept()).start();
        }

        serverSocket.close();
    }
    
}
