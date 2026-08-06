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
public class LoginModel {
    public String user = "AdministradorIPC1E";  //AdministradorIPC1E
    public String password = "ipc1E1s2025"; // ipc1E1s2025

    public boolean logeo(String user, String password ){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
        String fechaFormateada = ahora.format(formato);
        
        if (this.user.equals(user) && this.password.equals(password)) {
            Inicio.Inicio.getModeloBitacora().agregar("- INICIO DE SESION" ,fechaFormateada, "Sistema", "Inicio de Sesion", "Exito", "Sesion Iniciada Correctamente");
            return true;
        }
        Inicio.Inicio.getModeloBitacora().agregar("- INICIO DE SESION" ,fechaFormateada, "Sistema", "Inicio de Sesion", "Error", "Sesion Iniciada Incorrectamente");
        return false;
    }
    
    
    public class UsuarioNombre{
    private static String nombreUsuario;

    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    public static void setNombreUsuario(String nombreUsuario) {
        UsuarioNombre.nombreUsuario = nombreUsuario;
    }
    
}
}
