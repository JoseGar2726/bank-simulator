/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author joang
 */
public class RetiroModel {
    public String retiro(String monto, Double saldo, int cantidadTransacciones){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
        String fechaFormateada = ahora.format(formato);
        
        if(saldo == 0){
            Inicio.Inicio.getModeloBitacora().agregar("- RETIRO" ,fechaFormateada, "AdministradorIPC1E", "Retiro", "Error", "El saldo actual es de 0");
            return "No se puede hacer el retiro, saldo = 0";
        }
        if(monto.equals("")){
            Inicio.Inicio.getModeloBitacora().agregar("- RETIRO" ,fechaFormateada, "AdministradorIPC1E", "Retiro", "Error", "No se ha especificado el monto");
            return "No se ha ingresado un monto";
        }
        double montoD = Double.parseDouble(monto);
        if(montoD <= 0){
            Inicio.Inicio.getModeloBitacora().agregar("- RETIRO" ,fechaFormateada, "AdministradorIPC1E", "Retiro", "Error", "Monto ingresado no cumple con las especificaciones");
            return "El monto debe ser mayor a 0";
        }
        if(montoD > saldo){
            Inicio.Inicio.getModeloBitacora().agregar("- RETIRO" ,fechaFormateada, "AdministradorIPC1E", "Retiro", "Error", "Saldo insuficiente. Monto Solicitado: Q." + montoD + ", saldo disponible: Q." + saldo);
            return "Saldo Insuficiente";
        }
        if (cantidadTransacciones < 25){
            Inicio.Inicio.getModeloBitacora().agregar("- RETIRO" ,fechaFormateada, "AdministradorIPC1E", "Retiro", "Exito", "Retiro de Q." + montoD + " realizado. Saldo actual: Q." + (saldo-montoD));
            return "Retiro realizado exitosamente";
        }
        Inicio.Inicio.getModeloBitacora().agregar("- RETIRO" ,fechaFormateada, "AdministradorIPC1E", "Retiro", "Error", "Limite de transacciones");
        return "No se pueden realizar mas transacciones";
    }
}
