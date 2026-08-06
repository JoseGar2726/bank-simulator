/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Inicio.Inicio;
import Modelo.CrearCuentaModel;
import Modelo.CrearUsuarioModel;
import Modelo.MenuModel;
import Modelo.RetiroModel;
import Modelo.TransaccionesModel;
import Vista.FrmMenuPrincipal;
import Vista.FrmRetiro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author joang
 */
public class RetiroController {
    private RetiroModel modelo;
    private FrmRetiro vista;
    private CrearCuentaModel modeloCrearCuenta;
    private TransaccionesModel modeloTransacciones;
    
    public RetiroController(RetiroModel modelo, FrmRetiro vista, CrearCuentaModel modeloCrearCuenta, TransaccionesModel modeloTransacciones){
        this.modelo = modelo;
        this.vista = vista;
        this.modeloCrearCuenta = Inicio.getModeloCrearCuenta();
        this.modeloTransacciones = Inicio.getModeloTransacciones();
        
        cargarCuentas();
        
        this.vista.btnRetirar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                retirar();
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
    
    private void cargarCuentas(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
        String fechaFormateada = ahora.format(formato);
        
        List<CrearCuentaModel.Cuentas> cuentas = modeloCrearCuenta.getListaCuentas();
        
        if(cuentas.isEmpty()){
            JOptionPane.showMessageDialog(null,"No se han asociado cuentas", "Menu", JOptionPane.ERROR_MESSAGE);
            Inicio.getModeloBitacora().agregar("- RETIRO" ,fechaFormateada, "AdministradorIPC1E", "Retiro", "Error", "No se han creado cuentas");
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
            for(CrearCuentaModel.Cuentas cuenta: cuentas){
            vista.cbCuentas.addItem(cuenta.toString());
            } 
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
        }
    }
    
    private void retirar(){
        List<CrearCuentaModel.Cuentas> cuentas = modeloCrearCuenta.getListaCuentas();
        String nuevoSaldo = this.vista.txtMonto.getText();
        
        String verificar = this.modelo.retiro(nuevoSaldo,cuentas.get(this.vista.cbCuentas.getSelectedIndex()).getSaldo(),cuentas.get(this.vista.cbCuentas.getSelectedIndex()).getNumeroTransacciones());
        
        if(verificar.equals("Retiro realizado exitosamente")){
            double nuevoSaldoD = Double.parseDouble(nuevoSaldo);
            double saldo = this.modeloCrearCuenta.retirar(nuevoSaldoD, cuentas.get(this.vista.cbCuentas.getSelectedIndex()).saldo,this.vista.cbCuentas.getSelectedIndex());
            JOptionPane.showMessageDialog(null,verificar, "Retiros", JOptionPane.INFORMATION_MESSAGE);
            int numero = this.modeloTransacciones.agregarTransaccion(cuentas.get(this.vista.cbCuentas.getSelectedIndex()).getNumeroCuenta(),LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), "Retiro", nuevoSaldoD, 0.00, saldo, cuentas.get(this.vista.cbCuentas.getSelectedIndex()).getNumeroTransacciones());
            if(numero > 25){
                
            } else {
                this.modeloCrearCuenta.getListaCuentas().get(this.vista.cbCuentas.getSelectedIndex()).setNumeroTransacciones(numero);
            }
            this.vista.txtMonto.setText("");
            actualizarCuentas();
        } else {
            JOptionPane.showMessageDialog(null,verificar, "Retiros", JOptionPane.ERROR_MESSAGE);
        }        
    }
    
    private void actualizarCuentas(){
        List<CrearCuentaModel.Cuentas> cuentas = modeloCrearCuenta.getListaCuentas();
        this.vista.cbCuentas.removeAllItems();
        for(CrearCuentaModel.Cuentas cuenta: cuentas){
            vista.cbCuentas.addItem(cuenta.toString());
            } 
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
    }
}
