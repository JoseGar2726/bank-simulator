/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Inicio.*;
import Modelo.CrearCuentaModel;
import Modelo.CrearUsuarioModel;
import Modelo.MenuModel;
import Modelo.TransaccionesModel;
import Vista.FrmCrearUsuario;
import Vista.FrmMenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author joang
 */
public class CrearUsuarioController {
    private CrearUsuarioModel modelo;
    private FrmCrearUsuario vista;
    
    public CrearUsuarioController(CrearUsuarioModel modelo, FrmCrearUsuario vista){
        this.modelo = modelo;
        this.vista = vista;
        
        this.vista.btnCrear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                registrarUsuario();
            }
        });
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
    }
    
    private void registrarUsuario(){
        String verificar = this.modelo.agregarUsuario(this.vista.txtName.getText(), this.vista.txtLname.getText(), this.vista.txtCui.getText(), 0);
        if(verificar.equals("Usuario agregado con exito")){
            JOptionPane.showMessageDialog(null,verificar,"Registro Usuario", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,verificar,"Registro Usuario", JOptionPane.ERROR_MESSAGE);
        }
        this.vista.txtName.setText("");
        this.vista.txtLname.setText("");
        this.vista.txtCui.setText("");
    }
}
