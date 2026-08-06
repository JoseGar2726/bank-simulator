/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Inicio.Inicio;
import Modelo.CrearCuentaModel;
import Modelo.CrearCuentaModel.Cuentas;
import Modelo.CrearUsuarioModel;
import Modelo.DepositoModel;
import Modelo.MenuModel;
import Modelo.TransaccionesModel;
import Vista.FrmDeposito;
import Vista.FrmMenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joang
 */
public class DepositoController {
    private DepositoModel modelo;
    private FrmDeposito vista;
    private CrearCuentaModel modeloCrearCuenta;
    private TransaccionesModel modeloTransacciones;
    
    public DepositoController(DepositoModel modelo, FrmDeposito vista, CrearCuentaModel modeloCrearCuenta, TransaccionesModel modeloTransacciones){
        this.modelo = modelo;
        this.vista = vista;
        this.modeloCrearCuenta = Inicio.getModeloCrearCuenta();
        this.modeloTransacciones = Inicio.getModeloTransacciones();
        
        cargarCuentas();
        
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
        
        this.vista.btnDepositar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                depositar();
            }
        });
    }
    
    private void cargarCuentas(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
        String fechaFormateada = ahora.format(formato);
        
        List<Cuentas> cuentas = modeloCrearCuenta.getListaCuentas();
        
        if(cuentas.isEmpty()){
            JOptionPane.showMessageDialog(null,"No se han asociado cuentas", "Menu", JOptionPane.ERROR_MESSAGE);
            Inicio.getModeloBitacora().agregar("- DEPOSITO" ,fechaFormateada, "AdministradorIPC1E", "Deposito", "Error", "No se han creado cuentas");
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
            for(Cuentas cuenta: cuentas){
            vista.cbCuentas.addItem(cuenta.toString());
            } 
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
        }
    }
    
    private void depositar(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
        String fechaFormateada = ahora.format(formato);
        
        List<Cuentas> cuentas = modeloCrearCuenta.getListaCuentas();
        String nuevoSaldo = this.vista.txtMonto.getText();
        
        String verificar = this.modelo.deposito(nuevoSaldo, cuentas.get(this.vista.cbCuentas.getSelectedIndex()).getNumeroTransacciones());
        
        if(verificar.equals("Deposito realizado exitosamente")){
            double nuevoSaldoD = Double.parseDouble(nuevoSaldo);
            double saldo = this.modeloCrearCuenta.depositar(nuevoSaldoD, cuentas.get(this.vista.cbCuentas.getSelectedIndex()).saldo,this.vista.cbCuentas.getSelectedIndex());
            Inicio.getModeloBitacora().agregar("- DEPOSITO" ,fechaFormateada, "AdministradorIPC1E", "Deposito", "Exito", "Deposito de Q." + nuevoSaldo + " realizado. Saldo Actual: Q." + saldo);
            this.vista.txtMonto.setText("");
            JOptionPane.showMessageDialog(null,verificar, "Depositos", JOptionPane.INFORMATION_MESSAGE);
            int numero = this.modeloTransacciones.agregarTransaccion(cuentas.get(this.vista.cbCuentas.getSelectedIndex()).getNumeroCuenta(),LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), "Deposito", 0.00, nuevoSaldoD, saldo, cuentas.get(this.vista.cbCuentas.getSelectedIndex()).getNumeroTransacciones());
            if(numero > 25){
                
            } else {
                this.modeloCrearCuenta.getListaCuentas().get(this.vista.cbCuentas.getSelectedIndex()).setNumeroTransacciones(numero);
            }
            actualizarCuentas();
        } else {
            JOptionPane.showMessageDialog(null,verificar, "Depositos", JOptionPane.ERROR_MESSAGE);
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
