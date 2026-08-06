/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Inicio.*;
import Modelo.BuscarCuentaModel;
import Modelo.CrearCuentaModel;
import Modelo.CrearCuentaModel.Cuentas;
import Modelo.CrearUsuarioModel;
import Modelo.CrearUsuarioModel.Usuario;
import Modelo.MenuModel;
import Modelo.TransaccionesModel;
import Vista.FrmBuscarCuenta;
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
public class BuscarCuentaController {
    private BuscarCuentaModel modelo;
    private FrmBuscarCuenta vista;
    private CrearCuentaModel modeloCrearCuenta;
    private CrearUsuarioModel modeloCrearUsuario;
    
    
    public BuscarCuentaController(BuscarCuentaModel modelo, FrmBuscarCuenta vista, CrearCuentaModel modeloCrearCuenta, CrearUsuarioModel modeloCrearUsuario){
        this.modelo = modelo;
        this.vista = vista;
        this.modeloCrearCuenta = Inicio.getModeloCrearCuenta();
        this.modeloCrearUsuario = Inicio.getModeloCrearUsuario();
        
        cargarUsuarios();
        
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
        
        this.vista.btnBuscar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cargarCuentas();
            }
        });
    }
    
    private void cargarUsuarios(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
        String fechaFormateada = ahora.format(formato);
        
        List<Usuario> usuarios = modeloCrearUsuario.getListaUsuarios();
        if(usuarios.isEmpty()){
            JOptionPane.showMessageDialog(null,"No se han ingresado Usuarios", "Menu", JOptionPane.ERROR_MESSAGE);
            Inicio.getModeloBitacora().agregar("- BUSQUEDA DE CUENTAS ASOCIADAS" ,fechaFormateada, "AdministradorIPC1E", "Busqueda de cuentas", "Error", "No se han creado usuarios");
            FrmMenuPrincipal vistaMenu = new FrmMenuPrincipal();
            MenuModel modeloMenu = Inicio.getModeloMenu();
            CrearUsuarioModel modeloCrearUsuario = Inicio.getModeloCrearUsuario();
            CrearCuentaModel modeloCrearCuenta = Inicio.getModeloCrearCuenta();
            TransaccionesModel modeloTransacciones = Inicio.getModeloTransacciones();
            MenuController controladorMenu = new MenuController(modeloMenu, vistaMenu, modeloCrearUsuario, modeloCrearCuenta, modeloTransacciones);
            vista.dispose();
            vistaMenu.setVisible(true);
            vistaMenu.setLocationRelativeTo(null);
        } else{
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
            for (int i = 0; i < usuarios.size(); i++) {
            for(int j = 0; j < 3; j++){
                if(j==0){
                    this.vista.tblUsuarios.setValueAt(usuarios.get(i).cui, i, j);
                }
                if(j==1){
                    this.vista.tblUsuarios.setValueAt(usuarios.get(i).name, i, j);
                }
                if(j==2){
                    this.vista.tblUsuarios.setValueAt(usuarios.get(i).lname, i, j);
                }
            }
        }
        }  
    }
    
    private void cargarCuentas(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
        String fechaFormateada = ahora.format(formato);
        
        limpiarTabla();
        int existe = 0;
        int i;
        List<Cuentas> cuentas = modeloCrearCuenta.getListaCuentas();
        List<Usuario> usuarios = modeloCrearUsuario.getListaUsuarios();
        String cui = this.vista.txtCui.getText();
        i = 0;
        for(Usuario comparar: usuarios){
            if(comparar.cui.equals(cui)){
                existe = 1;
                if(comparar.cantidadCuentas == 0){
                    JOptionPane.showMessageDialog(null,"No se han asocidado cuentas al usuario", "Buscar Cuentas", JOptionPane.INFORMATION_MESSAGE);
                    Inicio.getModeloBitacora().agregar("- BUSQUEDA DE CUENTAS ASOCIADAS" ,fechaFormateada, "AdministradorIPC1E", "Busqueda de cuentas", "Error", "No se encontraron cuentas asociadas al titular '" + comparar.name + " " + comparar.lname + "'");
                }else{
                    for(Cuentas pertenece : cuentas){
                        if (cui.equals(pertenece.cui)){
                            this.vista.tblCuentas.setValueAt(pertenece.numeroCuenta, i, 0);
                            i++;
                        }
                    }
                    Inicio.getModeloBitacora().agregar("- BUSQUEDA DE CUENTAS ASOCIADAS" ,fechaFormateada, "AdministradorIPC1E", "Busqueda de cuentas", "Exito", "Se encontraron " + comparar.cantidadCuentas + " cuentas asociadas al titutlar '" + comparar.name + " " + comparar.lname + "'");
                }
                break;
            }
        }
        if(existe == 0){
            JOptionPane.showMessageDialog(null,"CUI inexistente", "Buscar Cuentas", JOptionPane.ERROR_MESSAGE);
            Inicio.getModeloBitacora().agregar("- BUSQUEDA DE CUENTAS ASOCIADAS" ,fechaFormateada, "AdministradorIPC1E", "Busqueda de cuentas", "Error", "CUI inexistente");
        }
    }
    
    private void limpiarTabla(){
        for(int i = 0; i<6; i++){
            this.vista.tblCuentas.setValueAt("", i, 0);
        }
    }
}
