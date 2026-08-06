/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 *
 * @author joang
 */
public class TransaccionesModel{
    
    private List<Transacciones> listaTransacciones;
    
    public TransaccionesModel(){
        this.listaTransacciones = new ArrayList<>();
    }

    public List<Transacciones> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(List<Transacciones> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }
    
    public int agregarTransaccion(String cuentaGuid,String fecha, String detalle, double debito, double credito, double saldo, int numeroTransacciones){
        if(numeroTransacciones < 26){
            numeroTransacciones = numeroTransacciones + 1;
            int identificador = (int) ((Math.random()*9000)+1000);
            Transacciones nuevaTransaccion = new Transacciones(cuentaGuid ,identificador, fecha, detalle, debito, credito, saldo);
            listaTransacciones.add(nuevaTransaccion);
        }
        System.out.println("");
        return numeroTransacciones;
    }
    
    public class Transacciones{
        public String cuentaGuid;
        public int guid;
        public String fecha;
        public String detalle;
        public double debito;
        public double credito;
        public double saldo;
        
        public Transacciones(String cuentaGuid,int guid, String fecha, String detalle, double debito, double credito, double saldo){
            this.cuentaGuid = cuentaGuid;
            this.guid = guid;
            this.fecha = fecha;
            this.detalle = detalle;
            this.debito = debito;
            this.credito = credito;
            this.saldo = saldo;
        }

        public String getCuentaGuid() {
            return cuentaGuid;
        }

        public void setCuentaGuid(String cuentaGuid) {
            this.cuentaGuid = cuentaGuid;
        }
        

        public int getGuid() {
            return guid;
        }

        public void setGuid(int guid) {
            this.guid = guid;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public String getDetalle() {
            return detalle;
        }

        public void setDetalle(String detalle) {
            this.detalle = detalle;
        }

        public double getDebito() {
            return debito;
        }

        public void setDebito(double debito) {
            this.debito = debito;
        }

        public double getCredito() {
            return credito;
        }

        public void setCredito(double credito) {
            this.credito = credito;
        }

        public double getSaldo() {
            return saldo;
        }

        public void setSaldo(double saldo) {
            this.saldo = saldo;
        }

        @Override
        public String toString() {
            return "Transacciones{" + "guid=" + guid + ", fecha=" + fecha + ", detalle=" + detalle + ", debito=" + debito + ", credito=" + credito + ", saldo=" + saldo + '}';
        }

        
    }
    
}
