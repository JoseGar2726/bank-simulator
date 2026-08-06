/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Inicio.*;
import Modelo.CrearCuentaModel;
import Modelo.CrearUsuarioModel;
import Modelo.CrearUsuarioModel.Usuario;
import Modelo.MenuModel;
import Modelo.TransaccionesModel;
import Vista.FrmCrearCuenta;
import Vista.FrmMenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author joang
 */
public class CrearCuentaController {
    private CrearCuentaModel modelo;
    private FrmCrearCuenta vista;
    private CrearUsuarioModel modeloCrearUsuario;
    
    public CrearCuentaController(CrearCuentaModel modelo, FrmCrearCuenta vista, CrearUsuarioModel modeloCrearUsuario){
        this.modelo = modelo;
        this.vista = vista;
        this.modeloCrearUsuario = Inicio.getModeloCrearUsuario();
        
        actualizarListaUsuarios();
        
        this.vista.btnSalir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                FrmMenuPrincipal vistaMenu = new FrmMenuPrincipal();
                MenuModel modeloMenu = Inicio.getModeloMenu();
                CrearUsuarioModel modeloCrearUsuario = Inicio.getModeloCrearUsuario();
                CrearCuentaModel modeloCrearCuenta = Inicio.getModeloCrearCuenta();
                TransaccionesModel modeloTransacciones = Inicio.getModeloTransacciones();
                MenuController controladorMenu = new MenuController(modeloMenu, vistaMenu, modeloCrearUsuario, modeloCrearCuenta, modeloTransacciones);
                vista.dispose();
                vistaMenu.setVisible(true);
                vistaMenu.setLocationRelativeTo(null);
            }
        });
        this.vista.btnCrear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                crearCuenta();
            }
        });
    }
    
    private void crearCuenta(){ 
        List<Usuario> usuarios = modeloCrearUsuario.getListaUsuarios();
        String mensaje = modeloCrearUsuario.cuentaNueva(vista.cbClientes.getSelectedIndex());
        if(mensaje.equals("Limite de cuentas")){
            JOptionPane.showMessageDialog(null,mensaje,"Creacion Cuenta", JOptionPane.ERROR_MESSAGE);
        }else{
            String verificar = modelo.agregarCuenta(usuarios.get(vista.cbClientes.getSelectedIndex()).cui, usuarios.get(vista.cbClientes.getSelectedIndex()).name, usuarios.get(vista.cbClientes.getSelectedIndex()).lname, mensaje, 0);
            JOptionPane.showMessageDialog(null,verificar,"Creacion Cuenta", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void actualizarListaUsuarios(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
        String fechaFormateada = ahora.format(formato);
        
        List<Usuario> usuarios = modeloCrearUsuario.getListaUsuarios();
        
        if(usuarios.isEmpty()){
            JOptionPane.showMessageDialog(null,"No se han ingresado usuarios","Menu", JOptionPane.ERROR_MESSAGE);
            Inicio.getModeloBitacora().agregar("- CREACION DE CUENTA" ,fechaFormateada, "AdministradorIPC1E", "Creacion de cuenta", "Error", "No se han creado usuarios");
            FrmMenuPrincipal vistaMenu = new FrmMenuPrincipal();
            MenuModel modeloMenu = Inicio.getModeloMenu();
            CrearUsuarioModel modeloCrearUsuario = Inicio.getModeloCrearUsuario();
            CrearCuentaModel modeloCrearCuenta = Inicio.getModeloCrearCuenta();
            TransaccionesModel modeloTransacciones = Inicio.getModeloTransacciones();
            MenuController controladorMenu = new MenuController(modeloMenu, vistaMenu, modeloCrearUsuario, modeloCrearCuenta, modeloTransacciones);
            vista.dispose();
            vistaMenu.setVisible(true);
            vistaMenu.setLocationRelativeTo(null);
        }else{
            for(Usuario usuario: usuarios){
            vista.cbClientes.addItem(usuario.toString());
            } 
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
        }
    }
}
