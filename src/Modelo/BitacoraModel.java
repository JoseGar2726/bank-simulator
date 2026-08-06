/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joang
 */
public class BitacoraModel {
    
    public List<DatoBitacora> bitacora;
    
    public BitacoraModel(){
        this.bitacora = new ArrayList<>();
    }

    public List<DatoBitacora> getBitacora() {
        return bitacora;
    }

    public void setBitacora(List<DatoBitacora> bitacora) {
        this.bitacora = bitacora;
    }
    
    public String agregar(String titulo ,String tiempo, String usuario, String accion, String resultado, String adicional){
        DatoBitacora dato = new DatoBitacora(titulo ,tiempo,usuario,accion,resultado,adicional);
        bitacora.add(dato);
        return "Lista Actualizada";
    }
    
    public class DatoBitacora{
        public String titulo;
        public String tiempo;
        public String usuario;
        public String accion;
        public String resultado;
        public String adicional;
        
        public DatoBitacora(String titulo ,String tiempo, String usuario, String accion, String resultado, String adicional){
            this.titulo = titulo;
            this.tiempo = tiempo;
            this.usuario = usuario;
            this.accion = accion;
            this.resultado = resultado;
            this.adicional = adicional;
        }

        public String getTiempo() {
            return tiempo;
        }

        public void setTiempo(String tiempo) {
            this.tiempo = tiempo;
        }

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public String getAccion() {
            return accion;
        }

        public void setAccion(String accion) {
            this.accion = accion;
        }

        public String getResultado() {
            return resultado;
        }

        public void setResultado(String resultado) {
            this.resultado = resultado;
        }

        public String getAdicional() {
            return adicional;
        }

        public void setAdicional(String adicional) {
            this.adicional = adicional;
        }

        @Override
        public String toString() {
            return titulo + "\n" + "[" + tiempo + "] Usuario: " + usuario + " - Accion: " + accion + " - Resultado: " + resultado + " - Detalles: " + adicional + "\n";
        }
        
    }
    
}
