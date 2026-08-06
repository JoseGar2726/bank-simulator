/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Inicio.*;
import Modelo.BitacoraModel;
import Modelo.CrearCuentaModel;
import Modelo.CrearUsuarioModel;
import Modelo.LoginModel;
import Modelo.LoginModel.UsuarioNombre;
import Modelo.MenuModel;
import Modelo.TransaccionesModel;
import Vista.FrmLogin;
import Vista.FrmMenuPrincipal;
import javax.swing.JOptionPane;
import java.awt.event.*;

/**
 *
 * @author joang
 */
public class LoginController {
    private LoginModel modelo;
    private FrmLogin vista;

    public LoginController(LoginModel modelo, FrmLogin vista) {
        
        this.modelo = modelo;
        this.vista = vista;
        
        String usuarioNuevo;
        usuarioNuevo = this.vista.txtUser.getText();
    }
    
    public void inicializarLogin(){
        this.vista.setVisible(true);
        this.vista.setLocationRelativeTo(null);
        this.vista.btnLogin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                iniciarSesion();
            }
        });
    }
    
    private void iniciarSesion(){
        String user = this.vista.txtUser.getText();
        String password = this.vista.txtPassword.getText();
        boolean verificar = this.modelo.logeo(user, password);
        UsuarioNombre.setNombreUsuario(user);
        if(verificar){
            FrmMenuPrincipal vistaMenu = new FrmMenuPrincipal();
            MenuModel modeloMenu = Inicio.getModeloMenu();
            CrearUsuarioModel modeloCrearUsuario = Inicio.getModeloCrearUsuario();
            CrearCuentaModel modeloCrearCuenta = Inicio.getModeloCrearCuenta();
            TransaccionesModel modeloTransacciones = Inicio.getModeloTransacciones();
            MenuController controladorMenu = new MenuController(modeloMenu, vistaMenu, modeloCrearUsuario, modeloCrearCuenta, modeloTransacciones);
            this.vista.dispose();
            vistaMenu.setVisible(true);
            vistaMenu.setLocationRelativeTo(null);
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales Incorrectas. Inténtalo de nuevo.", "Inicio de Sesion", JOptionPane.ERROR_MESSAGE);
        }
    }
}
