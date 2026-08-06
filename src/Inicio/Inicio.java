/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inicio;

import Controlador.LoginController;
import Modelo.BitacoraModel;
import Modelo.BuscarCuentaModel;
import Modelo.CrearCuentaModel;
import Modelo.CrearUsuarioModel;
import Modelo.DepositoModel;
import Modelo.LoginModel;
import Modelo.MenuModel;
import Modelo.RetiroModel;
import Modelo.TransaccionesModel;
import Vista.FrmLogin;

/**
 *
 * @author joang
 */
public class Inicio {
    
    private final static LoginModel modeloLogin = new LoginModel();
    private final static MenuModel modeloMenu = new MenuModel();
    private final static CrearUsuarioModel modeloCrearUsuario = new CrearUsuarioModel();
    private final static CrearCuentaModel modeloCrearCuenta = new CrearCuentaModel();
    private final static BuscarCuentaModel modeloBuscarCuenta = new BuscarCuentaModel();
    private final static DepositoModel modeloDeposito = new DepositoModel();
    private final static RetiroModel modeloRetiro = new RetiroModel();
    private final static TransaccionesModel modeloTransacciones = new TransaccionesModel();
    private final static BitacoraModel modeloBitacora = new BitacoraModel();
    
    public static void main(String[] args){
        FrmLogin vistaLogin = new FrmLogin();
        BitacoraModel bitacora = new BitacoraModel();
        LoginController controladorLogin = new LoginController(modeloLogin, vistaLogin);
        controladorLogin.inicializarLogin();
    }

    public static LoginModel getModeloLogin() {
        return modeloLogin;
    }

    public static MenuModel getModeloMenu() {
        return modeloMenu;
    }
    
    public static CrearUsuarioModel getModeloCrearUsuario() {
        return modeloCrearUsuario;
    }

    public static CrearCuentaModel getModeloCrearCuenta() {
        return modeloCrearCuenta;
    }
    
    public static BuscarCuentaModel getModeloBuscarCuenta(){
        return modeloBuscarCuenta;
    }

    public static DepositoModel getModeloDeposito() {
        return modeloDeposito;
    }

    public static RetiroModel getModeloRetiro() {
        return modeloRetiro;
    }

    public static TransaccionesModel getModeloTransacciones() {
        return modeloTransacciones;
    }

    public static BitacoraModel getModeloBitacora() {
        return modeloBitacora;
    }
    
}
