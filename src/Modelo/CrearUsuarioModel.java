/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joang
 */
public class CrearUsuarioModel {
    
    private List<Usuario> listaUsuarios;
    private final int limite = 2;
    
    public CrearUsuarioModel(){
        this.listaUsuarios = new ArrayList<>();
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    public String agregarUsuario(String name, String lname, String cui, int cantidadCuentas){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
        String fechaFormateada = ahora.format(formato);
        
        if(name.equals("") || lname.equals("") || cui.equals("")){
            Inicio.Inicio.getModeloBitacora().agregar("- REGISTRO DE USUARIO" ,fechaFormateada, "AdministradorIPC1E", "Registro de usuario", "Error", "Campos sin rellenar");
            return "Campos sin rellenar";
        }
        
        if(cui.length() != 13){
            Inicio.Inicio.getModeloBitacora().agregar("- REGISTRO DE USUARIO" ,fechaFormateada, "AdministradorIPC1E", "Registro de usuario", "Error", "Longitud del CUI invalida");
            return "Longitud del CUI invalida";
        }
        
        for (Usuario usuario : listaUsuarios){
            if(usuario.getCui().equals(cui)){
                Inicio.Inicio.getModeloBitacora().agregar("- REGISTRO DE USUARIO" ,fechaFormateada, "AdministradorIPC1E", "Registro de usuario", "Error", "CUI Repetido");
                return "CUI repetido";
            }
        }
        
        if (listaUsuarios.size() > limite){
            Inicio.Inicio.getModeloBitacora().agregar("- REGISTRO DE USUARIO" ,fechaFormateada, "AdministradorIPC1E", "Registro de usuario", "Error", "Limite de usuarios");
            return "No se permiten mas usuarios";
        }
        
        String mname = name.substring(0,1).toUpperCase() + name.substring(1,name.length());
        String mlname = lname.substring(0,1).toUpperCase() + lname.substring(1,lname.length());
                       
        Usuario nuevoUsuario = new Usuario(mname, mlname, cui, cantidadCuentas);
        listaUsuarios.add(nuevoUsuario);
        Inicio.Inicio.getModeloBitacora().agregar("- REGISTRO DE USUARIO" ,fechaFormateada, "AdministradorIPC1E", "Registro de usuario", "Exito", "Usuario " + mname + " " + mlname + " registrado");
        return "Usuario agregado con exito";
    }
    
    public String cuentaNueva(int usuario){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
        String fechaFormateada = ahora.format(formato);
        
        if(listaUsuarios.get(usuario).cantidadCuentas < 6){
            int identificador = (int) ((Math.random()*9000)+1000);
            String cuenta = "E202E5" + String.valueOf(identificador);
            listaUsuarios.get(usuario).setCantidadCuentas(listaUsuarios.get(usuario).getCantidadCuentas() + 1);
            return cuenta;
        }
        Inicio.Inicio.getModeloBitacora().agregar("- CREACION DE CUENTA" ,fechaFormateada, "AdministradorIPC1E", "Creacion de Cuenta", "Error", "Limite de cuentas");
        return "Limite de cuentas";
    }
    
    public class Usuario{
        public String name = "";
        public String lname = "";
        public String cui = "";
        public int cantidadCuentas = 0;
        
        public Usuario(String name, String lname, String cui, int cantidadCuentas){
            this.name = name;
            this.lname = lname;
            this.cui = cui;
            this.cantidadCuentas = cantidadCuentas;
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

        public String getCui() {
            return cui;
        }

        public void setCui(String cui) {
            this.cui = cui;
        }

        public int getCantidadCuentas() {
            return cantidadCuentas;
        }

        public void setCantidadCuentas(int cantidadCuentas) {
            this.cantidadCuentas = cantidadCuentas;
        }
        
        
        @Override
        public String toString() {
            return cui +  " - " + name + " " + lname;
        }
        
    }   
    
}

