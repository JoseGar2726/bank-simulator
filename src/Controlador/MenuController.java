/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Inicio.*;
import Modelo.BitacoraModel.DatoBitacora;
import Modelo.BuscarCuentaModel;
import Modelo.CrearCuentaModel;
import Modelo.CrearUsuarioModel;
import Modelo.DepositoModel;
import Modelo.LoginModel;
import Modelo.MenuModel;
import Modelo.RetiroModel;
import Modelo.TransaccionesModel;
import Vista.FrmBuscarCuenta;
import Vista.FrmCrearCuenta;
import Vista.FrmCrearUsuario;
import Vista.FrmDeposito;
import Vista.FrmMenuPrincipal;
import Vista.FrmRetiro;
import Vista.FrmTransaccionesH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author joang
 */
public class MenuController {
    private MenuModel modelo;
    private FrmMenuPrincipal vista;
    private CrearUsuarioModel modeloCrearUsuario;
    private CrearCuentaModel modeloCrearCuenta;
    private TransaccionesModel modeloTransacciones;
    
    public MenuController(MenuModel modelo, FrmMenuPrincipal vista, CrearUsuarioModel modeloCrearUsuario, CrearCuentaModel modeloCrearCuenta, TransaccionesModel modeloTransacciones){
        this.modelo = modelo;
        this.vista = vista;
        this.modeloCrearUsuario = Inicio.getModeloCrearUsuario();
        this.modeloCrearCuenta = Inicio.getModeloCrearCuenta();
        this.modeloTransacciones = Inicio.getModeloTransacciones();
        
        this.vista.lblUser.setText("Bienvenido " + LoginModel.UsuarioNombre.getNombreUsuario());
        
        this.vista.mniDatos.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                LocalDateTime ahora = LocalDateTime.now();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
                String fechaFormateada = ahora.format(formato);
                
                JOptionPane.showMessageDialog(null,"José García \n202401166 \nIPC1 'E'","Datos Estudiante",JOptionPane.INFORMATION_MESSAGE);
                Inicio.getModeloBitacora().agregar("- DATOS DEL ESTUDIANTE" ,fechaFormateada, "AdministradorIPC1E", "Consulta de datos del estudiante", "Exito", "Nombre: Jose Garcia, Carnet: 202401166, Curso: Introduccion a la programacion y Computacion 1 'E'");
            }
        });
        
        this.vista.btnRegistrar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                FrmCrearUsuario vistaCrearUsuario = new FrmCrearUsuario();
                CrearUsuarioModel modeloCrearUsuario = Inicio.getModeloCrearUsuario();
                CrearUsuarioController controladorCrearUsuario = new CrearUsuarioController(modeloCrearUsuario, vistaCrearUsuario);
                vistaCrearUsuario.setVisible(true);
                vistaCrearUsuario.setLocationRelativeTo(null);
                vista.dispose();
            }
        });
        
        this.vista.btnCrearCuenta.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                FrmCrearCuenta vistaCrearCuenta = new FrmCrearCuenta();
                CrearCuentaModel modeloCrearCuenta = Inicio.getModeloCrearCuenta();
                CrearUsuarioModel modeloCrearUsuario = Inicio.getModeloCrearUsuario();
                CrearCuentaController controladorCrearCuenta = new CrearCuentaController(modeloCrearCuenta, vistaCrearCuenta, modeloCrearUsuario);
                vista.dispose();
            }
        });
        
        this.vista.btnBuscarCuenta.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                FrmBuscarCuenta vistaBuscarCuenta = new FrmBuscarCuenta();
                BuscarCuentaModel modeloBuscarCuenta = Inicio.getModeloBuscarCuenta();
                CrearCuentaModel modeloCrearCuenta = Inicio.getModeloCrearCuenta();
                CrearUsuarioModel modeloCrearUsuario = Inicio.getModeloCrearUsuario();
                BuscarCuentaController controladorBuscarCuenta = new BuscarCuentaController(modeloBuscarCuenta, vistaBuscarCuenta, modeloCrearCuenta, modeloCrearUsuario);
                vista.dispose();
            }
        });
        
        this.vista.btnDepositos.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                FrmDeposito vistaDeposito = new FrmDeposito();
                DepositoModel modeloDeposito = Inicio.getModeloDeposito();
                CrearCuentaModel modeloCrearCuenta = Inicio.getModeloCrearCuenta();
                TransaccionesModel modeloTransacciones = Inicio.getModeloTransacciones();
                DepositoController controladorDeposito = new DepositoController(modeloDeposito, vistaDeposito, modeloCrearCuenta, modeloTransacciones);
                vista.dispose();
            }
        });
        
        this.vista.btnRetiro.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                FrmRetiro vistaRetiro = new FrmRetiro();
                RetiroModel modeloRetiro = Inicio.getModeloRetiro();
                CrearCuentaModel modeloCrearCuenta = Inicio.getModeloCrearCuenta();
                TransaccionesModel modeloTransacciones = Inicio.getModeloTransacciones();
                RetiroController controladorRetiro = new RetiroController(modeloRetiro, vistaRetiro, modeloCrearCuenta, modeloTransacciones);
                vista.dispose();
            }
        });
        
        this.vista.btnTransacciones.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                FrmTransaccionesH vistaTransacciones = new FrmTransaccionesH();
                TransaccionesModel modeloTransacciones = Inicio.getModeloTransacciones();
                CrearCuentaModel modeloCrearCuenta = Inicio.getModeloCrearCuenta();
                TransaccionesController controladorTransacciones = new TransaccionesController(modeloTransacciones, vistaTransacciones, modeloCrearCuenta);
                vista.dispose();
            }
        });
        
        this.vista.btnReportes.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cargarUsuarios();
            }
        });
        
        this.vista.mniBitacora.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                crearBitacora();
            }
        });
    }  
    
    private void cargarUsuarios(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
        String fechaFormateada = ahora.format(formato);
        
        List<CrearUsuarioModel.Usuario> usuarios = modeloCrearUsuario.getListaUsuarios();
        List<TransaccionesModel.Transacciones> transacciones = modeloTransacciones.getListaTransacciones();
        List<CrearCuentaModel.Cuentas> cuentas = modeloCrearCuenta.getListaCuentas();
        
        if(usuarios.isEmpty()){
            JOptionPane.showMessageDialog(null, "No se han ingresado usuarios", "Menu", JOptionPane.ERROR_MESSAGE);
            Inicio.getModeloBitacora().agregar("- GENERACION DE REPORTES" ,fechaFormateada, "AdministradorIPC1E", "Generacion de reportes", "Error", "No se han creado usuarios");
        }
        else{
            JComboBox<String> comboBox = new JComboBox<>();
            for(CrearUsuarioModel.Usuario user : usuarios){
                comboBox.addItem(user.getCui());
            }
            int opcion = JOptionPane.showConfirmDialog(null, comboBox, "Seleccione un CUI", JOptionPane.OK_CANCEL_OPTION);
            if(opcion == JOptionPane.OK_OPTION){
                String nombre = usuarios.get(comboBox.getSelectedIndex()).getName() + " " + usuarios.get(comboBox.getSelectedIndex()).getLname();
                String seleccion = (String) comboBox.getSelectedItem();
                String mensaje = this.modelo.generarPdf(seleccion, nombre, transacciones, cuentas);
                JOptionPane.showMessageDialog(null,mensaje, "Generacion de reportes" , JOptionPane.INFORMATION_MESSAGE);
                Inicio.getModeloBitacora().agregar("- GENERACION DE REPORTES" ,fechaFormateada, "AdministradorIPC1E", "Generacion de reportes", "Exito", "Reportes(Transacciones, Depositos, Retiros) generados para la cuenta '" + seleccion + "'");
            } else {
                JOptionPane.showMessageDialog(null,"No se han generado los reportes", "Generacion de reportes" , JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void crearBitacora(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
        String fechaFormateada = ahora.format(formato);
        
        Inicio.getModeloBitacora().agregar("- GENERACION DE BITACORA" ,fechaFormateada, "AdministradorIPC1E", "Generacion de bitacora", "Exito", "Bitacora generada correctamente");
        
        List<DatoBitacora> bitacora =  Inicio.getModeloBitacora().getBitacora();
        for(DatoBitacora imprimir : bitacora){
            System.out.println(imprimir);
        }
        
        
    }
}
