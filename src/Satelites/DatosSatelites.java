/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Satelites;

import com.google.gson.Gson;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream;
import distribuidos.ConnectionDB;
import model.Tractor;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.json.simple.JSONObject;


public class DatosSatelites {
    
    public static String obtenerDB(String datoRecibido) throws SQLException
    {
        String servidor = "jdbc:postgresql://localhost/distribuidosDB";
        
        Connection miConexion = ConnectionDB.GetConnection(servidor);
                
        System.out.println(miConexion);
          
        JSONObject objJson = new JSONObject();
        Gson gson = new Gson();
        String formatoJSON=""; 
        if(miConexion != null){
            String cultivo_id = datoRecibido;
            System.out.println("Codigo Cultivo = " + cultivo_id);
            Integer dato = Integer.valueOf(cultivo_id);
            InputStream is; 
            Statement s = miConexion.createStatement(); 
            ResultSet rs = s.executeQuery("select departamento,distrito,posicion,imagen  from satelites where codigo_cultivo = " + dato);
            boolean isRecord = false;
            ArrayList<Satelite> lista = new ArrayList();
            
            while (rs.next()){       
                Satelite satelite=new Satelite();
                satelite.setCodigo_cultivo(dato);
                satelite.setDepartamento(rs.getString(1));
                satelite.setDistrito(rs.getString(2));
                satelite.setPosicion(rs.getString(3));
                
                    is=rs.getBinaryStream(4);
                    
                    //Image img=ConvertirImagen(rs.getBytes("imagen"));
                    satelite.setImagen(is.toString());
                
              
                
                lista.add(satelite);
                isRecord = true;
              
            }
            formatoJSON = gson.toJson(lista);
            
            if (!isRecord){
                formatoJSON="null";
            }
        }
        return formatoJSON;
    }
    /*
        Metodo que convierte una cadena de bytes en una Imagen
    */
    public Image ConvertirImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");    
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis;
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        return reader.read(0, param);
    }

    
}
