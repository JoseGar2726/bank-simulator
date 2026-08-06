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
public class DepositoModel{
    
    public String deposito(String monto, int cantidadTransacciones){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
        String fechaFormateada = ahora.format(formato);
        
        if(monto.equals("")){
            Inicio.Inicio.getModeloBitacora().agregar("- DEPOSITO" ,fechaFormateada, "AdministradorIPC1E", "Deposito", "Error", "No se ha especificado el monto");
            return "No se ha ingresado un monto";
        }
        double montoD = Double.parseDouble(monto);
        if(montoD <= 0){
            Inicio.Inicio.getModeloBitacora().agregar("- DEPOSITO" ,fechaFormateada, "AdministradorIPC1E", "Deposito", "Error", "Monto ingresado no cumple con las especificaciones");
            return "El monto debe ser mayor a 0";
        }
        if (cantidadTransacciones < 25){
            return "Deposito realizado exitosamente";
        }
        Inicio.Inicio.getModeloBitacora().agregar("- DEPOSITO" ,fechaFormateada, "AdministradorIPC1E", "Deposito", "Error", "Limite de transacciones");
        return "No se pueden realizar mas transacciones";
    }
}
