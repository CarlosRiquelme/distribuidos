/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Satelites;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64DecoderStream;
import java.awt.Image;
import java.sql.Blob;
import javax.naming.BinaryRefAddr;

public class Satelite {
    
    private Integer codigo_cultivo;
    private String departamento;
    private String distrito;
    private String posicion;
    private String imagen;

    public Integer getCodigo_cultivo() {
        return codigo_cultivo;
    }

    public void setCodigo_cultivo(Integer codigo_cultivo) {
        this.codigo_cultivo = codigo_cultivo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }



    
}
