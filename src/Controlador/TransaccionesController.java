/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Inicio.Inicio;
import Modelo.CrearCuentaModel;
import Modelo.CrearUsuarioModel;
import Modelo.MenuModel;
import Modelo.TransaccionesModel;
import Vista.FrmMenuPrincipal;
import Vista.FrmTransaccionesH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joang
 */
public class TransaccionesController {
    private TransaccionesModel modelo;
    private FrmTransaccionesH vista;
    private CrearCuentaModel modeloCrearCuenta;
    
    public TransaccionesController(TransaccionesModel modelo, FrmTransaccionesH vista, CrearCuentaModel modeloCrearCuenta){
        this.modelo = modelo;
        this.vista = vista;
        this.modeloCrearCuenta = Inicio.getModeloCrearCuenta();
        
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
        
        this.vista.btnMostrar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mostrarDatos();
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
            Inicio.getModeloBitacora().agregar("- HISTORIAL DE TRANSACCIONES" ,fechaFormateada, "AdministradorIPC1E", "Historial de transacciones", "Error", "No se han creado cuentas");
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
                vista.cbCuentas.addItem(cuenta.numeroCuenta);
            } 
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
        }
    }
    
    private void mostrarDatos(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
        String fechaFormateada = ahora.format(formato);
        
        List<CrearCuentaModel.Cuentas> cuentas = modeloCrearCuenta.getListaCuentas();
        List<TransaccionesModel.Transacciones> transacciones = this.modelo.getListaTransacciones();
        DefaultTableModel modeloTabla = (DefaultTableModel) this.vista.tbTransacciones.getModel();
        
        limpiarDatos(modeloTabla);
        
        this.vista.txtCui.setText(cuentas.get(this.vista.cbCuentas.getSelectedIndex()).getCui());
        this.vista.txtName.setText(cuentas.get(this.vista.cbCuentas.getSelectedIndex()).getName());
        this.vista.txtLname.setText(cuentas.get(this.vista.cbCuentas.getSelectedIndex()).getLname());
        
        
        if(cuentas.get(this.vista.cbCuentas.getSelectedIndex()).numeroTransacciones == 0){
            JOptionPane.showMessageDialog(null,"No se han realizado transacciones", "Historial Transacciones", JOptionPane.INFORMATION_MESSAGE);
            Inicio.getModeloBitacora().agregar("- HISTORIAL DE TRANSACCIONES" ,fechaFormateada, "AdministradorIPC1E", "Historial de transacciones", "Error", "No se encontraron transacciones asociadas a la cuenta '" + cuentas.get(this.vista.cbCuentas.getSelectedIndex()).numeroCuenta + "'");
        } else {
            for(TransaccionesModel.Transacciones agregar : transacciones){
                if(agregar.getCuentaGuid().equals(cuentas.get(this.vista.cbCuentas.getSelectedIndex()).getNumeroCuenta())){
                    modeloTabla.addRow(new Object[]{agregar.getGuid(),agregar.getFecha(),agregar.getDetalle(), agregar.getDebito(), agregar.getCredito(), agregar.getSaldo()});
                }
            }
            Inicio.getModeloBitacora().agregar("- HISTORIAL DE TRANSACCIONES" ,fechaFormateada, "AdministradorIPC1E", "Historial de transacciones", "Exito", "Se encontraron "  + cuentas.get(this.vista.cbCuentas.getSelectedIndex()).numeroTransacciones + " transacciones asociadas a la cuenta '" + cuentas.get(this.vista.cbCuentas.getSelectedIndex()).numeroCuenta + "'");
        }
    }
    
    private void limpiarDatos(DefaultTableModel modeloTabla){
        this.vista.txtCui.setText("");
        this.vista.txtName.setText("");
        this.vista.txtLname.setText("");
        modeloTabla.setRowCount(0);
    }
}
