/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Tractor {
    private String altura;
    private String humedad;
    private String peso;
    private String temperatura;
    private Integer codigo_tractor;

    public Integer getCodigo_tractor() {
        return codigo_tractor;
    }

    public void setCodigo_tractor(Integer codigo_tractor) {
        this.codigo_tractor = codigo_tractor;
    }
    
     

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getHumedad() {
        return humedad;
    }

    public void setHumedad(String humedad) {
        this.humedad = humedad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }
    
    
    
}
