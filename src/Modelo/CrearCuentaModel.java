/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Inicio.Inicio;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joang
 */
public class CrearCuentaModel {
    
    private List<Cuentas> listaCuentas;
    
    public CrearCuentaModel(){
        this.listaCuentas = new ArrayList<>();
    }

    public List<Cuentas> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(List<Cuentas> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }
    
    public String agregarCuenta(String cui, String name, String lname, String numeroCuenta, double saldo){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
        String fechaFormateada = ahora.format(formato);
       
        Cuentas nuevaCuenta = new Cuentas(cui,name,lname,numeroCuenta,saldo,0);
        listaCuentas.add(nuevaCuenta);
        Inicio.getModeloBitacora().agregar("- CREACION DE CUENTA" ,fechaFormateada, "AdministradorIPC1E", "Creacion de cuenta", "Exito", "Cuenta creada con numero " + "'" + numeroCuenta + "', " + "saldo inicial: Q. 0");
        return "Cuenta Creada Correctamente";
    }
    
    public double depositar(double monto, double saldo, int idCuenta){
        for(Cuentas cuenta: listaCuentas){
            if(cuenta.getNumeroCuenta().equals(listaCuentas.get(idCuenta).getNumeroCuenta())){
                cuenta.setSaldo(saldo + monto);
                break;
            }
        }
        return saldo + monto;
    }
    
    public double retirar(double monto, double saldo, int idCuenta){
        for(Cuentas cuenta: listaCuentas){
            if(cuenta.getNumeroCuenta().equals(listaCuentas.get(idCuenta).getNumeroCuenta())){
                cuenta.setSaldo(saldo - monto);
                break;
            }
        }
        return saldo - monto;
    }
    
    public class Cuentas{
        public String cui;
        public String name;
        public String lname;
        public String numeroCuenta;
        public double saldo;
        public int numeroTransacciones;
        
        public Cuentas(String cui, String name, String lname, String numeroCuenta, double saldo, int numeroTransacciones){
            this.cui = cui;
            this.name = name;
            this.lname = lname;
            this.numeroCuenta = numeroCuenta;
            this.saldo = saldo;
            this.numeroTransacciones = numeroTransacciones;
        }

        public String getCui() {
            return cui;
        }

        public void setCui(String cui) {
            this.cui = cui;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getNumeroCuenta() {
            return numeroCuenta;
        }

        public void setNumeroCuenta(String numeroCuenta) {
            this.numeroCuenta = numeroCuenta;
        }

        public double getSaldo() {
            return saldo;
        }

        public void setSaldo(double saldo) {
            this.saldo = saldo;
        }

        public int getNumeroTransacciones() {
            return numeroTransacciones;
        }

        public void setNumeroTransacciones(int numeroTransacciones) {
            this.numeroTransacciones = numeroTransacciones;
        }
        
        @Override
        public String toString() {
            return numeroCuenta + " - " + "Cuenta de " + name + " " + lname + " - " + "Q "+ saldo;
        }
        
    }
}