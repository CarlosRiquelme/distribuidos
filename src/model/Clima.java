/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author marcel
 */
public class Clima {

    private String zona;
    private String departamento;
    private String distrito;
    private String dia;
    private Integer codigo_clima;

     public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
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
    
    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Integer getCodigo_clima() {
        return codigo_clima;
    }

    public void setCodigo_clima(Integer codigo_clima) {
        this.codigo_clima = codigo_clima;
    }
    
    
}
