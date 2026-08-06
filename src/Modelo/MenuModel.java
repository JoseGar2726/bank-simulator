/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.CrearCuentaModel.Cuentas;
import Modelo.TransaccionesModel.Transacciones;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joang
 */
public class MenuModel {
        public String generarPdf(String cui, String usuario, List<Transacciones> transacciones, List<Cuentas> cuentas) {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        String fechaFormateada = ahora.format(formato);
        Document document = new Document();
        Document documentdepo = new Document();
        Document documentreti = new Document();
        List<Transacciones> transaccionesFiltradas = new ArrayList<>();
        
        //FiltrarTransaccionesPorCuentasPorUsuario{}
        for(Cuentas cuentaUsuario: cuentas){
            if(cuentaUsuario.cui.equals(cui)){
                for(Transacciones usuarioTransaccion : transacciones){
                    if(usuarioTransaccion.cuentaGuid.equals(cuentaUsuario.numeroCuenta)){
                        transaccionesFiltradas.add(usuarioTransaccion);
                    }
                }
            }
        }
        
        //PDFTRANSACCIONESGENERALES
        try {
          
            PdfWriter.getInstance(document, new FileOutputStream("reportesTransacciones/" + "reporteTransacciones" + fechaFormateada + ".pdf"));

            document.open();

            document.add(new Paragraph("Reporte de Transacciones de " + usuario + " con CUI: " + cui));
            document.add(new Paragraph("\n\n"));
            
            
            //CrearTabla
            PdfPTable tabla = new PdfPTable(7);
            tabla.setWidthPercentage(100);  
            tabla.setHeaderRows(1);
            
            //Encabezados
            Color celesteClaro = new Color(173, 216, 230);
            String[] headers = {"GUID","ID","FECHA","DETALLE","DEBITO","CREDITO","SALDO DISPONIBLE"};
            for(String header : headers){
                PdfPCell cell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
                cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell.setBackgroundColor(celesteClaro);
                tabla.addCell(cell);
            }
            
            //Filas
            Font fila = FontFactory.getFont(FontFactory.HELVETICA,10);
            int colorFila = 0;
            
            for(Transacciones agregar : transaccionesFiltradas){
                PdfPCell c1 = new PdfPCell(new Phrase((agregar.cuentaGuid), fila));
                PdfPCell c2 = new PdfPCell(new Phrase(String.valueOf(agregar.guid), fila));
                PdfPCell c3 = new PdfPCell(new Phrase(agregar.fecha, fila));
                PdfPCell c4 = new PdfPCell(new Phrase(agregar.detalle, fila));
                PdfPCell c5 = new PdfPCell(new Phrase(String.valueOf(agregar.debito), fila));
                PdfPCell c6 = new PdfPCell(new Phrase(String.valueOf(agregar.credito), fila));
                PdfPCell c7 = new PdfPCell(new Phrase(String.valueOf(agregar.saldo), fila));
                
                
                c5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                c6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                c7.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                
                if(colorFila%2==1){
                    c1.setBackgroundColor(Color.CYAN);
                    c2.setBackgroundColor(Color.CYAN);
                    c3.setBackgroundColor(Color.CYAN);
                    c4.setBackgroundColor(Color.CYAN);
                    c5.setBackgroundColor(Color.CYAN);
                    c6.setBackgroundColor(Color.CYAN);
                    c7.setBackgroundColor(Color.CYAN);
                }
                colorFila = colorFila + 1;
                
                tabla.addCell(c1);
                tabla.addCell(c2);
                tabla.addCell(c3);
                tabla.addCell(c4);
                tabla.addCell(c5);
                tabla.addCell(c6);
                tabla.addCell(c7);
            }
            
            
            //Agregar al documento
            document.add(tabla);

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } finally {     
            // Cerrar el documento
            if (document.isOpen()) {
                document.close();
            }
        }
        
        //PDFTRANSACCIONESDEPOSITOS
        try {
          
            PdfWriter.getInstance(documentdepo, new FileOutputStream("reportesDepositos/" +"reporteDepositos" + fechaFormateada + ".pdf"));

            documentdepo.open();

            documentdepo.add(new Paragraph("Reporte de Depositos de " + usuario + " con CUI: " + cui));
            documentdepo.add(new Paragraph("\n\n"));
            
            
            //CrearTabla
            PdfPTable tabladepo = new PdfPTable(7);
            tabladepo.setWidthPercentage(100);  
            tabladepo.setHeaderRows(1);
            
            //Encabezados
            Color celesteClaro = new Color(173, 216, 230);
            String[] headersdepo = {"GUID","ID","FECHA","DETALLE","DEBITO","CREDITO","SALDO DISPONIBLE"};
            for(String header : headersdepo){
                PdfPCell celldepo = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
                celldepo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                celldepo.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                celldepo.setBackgroundColor(celesteClaro);
                tabladepo.addCell(celldepo);
            }
            
            //Filas
            Font filadepo = FontFactory.getFont(FontFactory.HELVETICA,10);
            int colorFiladepo = 0;
            
            for(Transacciones agregar : transaccionesFiltradas){
                if(agregar.detalle.equals("Deposito")){
                    PdfPCell c1 = new PdfPCell(new Phrase((agregar.cuentaGuid), filadepo));
                    PdfPCell c2 = new PdfPCell(new Phrase(String.valueOf(agregar.guid), filadepo));
                    PdfPCell c3 = new PdfPCell(new Phrase(agregar.fecha, filadepo));
                    PdfPCell c4 = new PdfPCell(new Phrase(agregar.detalle, filadepo));
                    PdfPCell c5 = new PdfPCell(new Phrase(String.valueOf(agregar.debito), filadepo));
                    PdfPCell c6 = new PdfPCell(new Phrase(String.valueOf(agregar.credito), filadepo));
                    PdfPCell c7 = new PdfPCell(new Phrase(String.valueOf(agregar.saldo), filadepo));
                
                
                    c5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    c6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    c7.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                
                    if(colorFiladepo%2==1){
                        c1.setBackgroundColor(Color.CYAN);
                        c2.setBackgroundColor(Color.CYAN);
                        c3.setBackgroundColor(Color.CYAN);
                        c4.setBackgroundColor(Color.CYAN);
                        c5.setBackgroundColor(Color.CYAN);
                        c6.setBackgroundColor(Color.CYAN);
                        c7.setBackgroundColor(Color.CYAN);
                    }
                    colorFiladepo = colorFiladepo + 1;
                
                    tabladepo.addCell(c1);
                    tabladepo.addCell(c2);
                    tabladepo.addCell(c3);
                    tabladepo.addCell(c4);
                    tabladepo.addCell(c5);
                    tabladepo.addCell(c6);
                    tabladepo.addCell(c7);
                }
            }
            
            //Agregar al documento
            documentdepo.add(tabladepo);

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } finally {     
            // Cerrar el documento
            if (documentdepo.isOpen()) {
                documentdepo.close();
            }
        }
        
        //PDFTRANSACCIONESRETIROS
        try {
          
            PdfWriter.getInstance(documentreti, new FileOutputStream("reportesRetiros/" + "reporteRetiros" + fechaFormateada + ".pdf"));

            documentreti.open();

            documentreti.add(new Paragraph("Reporte de Retiros de " + usuario + " con CUI: " + cui));
            documentreti.add(new Paragraph("\n\n"));
            
            
            //CrearTabla
            PdfPTable tablareti = new PdfPTable(7);
            tablareti.setWidthPercentage(100);  
            tablareti.setHeaderRows(1);
            
            //Encabezados
            Color celesteClaro = new Color(173, 216, 230);
            String[] headersreti = {"GUID","ID","FECHA","DETALLE","DEBITO","CREDITO","SALDO DISPONIBLE"};
            for(String header : headersreti){
                PdfPCell cellreti = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
                cellreti.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                cellreti.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cellreti.setBackgroundColor(celesteClaro);
                tablareti.addCell(cellreti);
            }
            
            //Filas
            Font filareti = FontFactory.getFont(FontFactory.HELVETICA,10);
            int colorFilareti = 0;
            
            for(Transacciones agregar : transaccionesFiltradas){
                if(agregar.detalle.equals("Retiro")){
                    PdfPCell c1 = new PdfPCell(new Phrase((agregar.cuentaGuid), filareti));
                    PdfPCell c2 = new PdfPCell(new Phrase(String.valueOf(agregar.guid), filareti));
                    PdfPCell c3 = new PdfPCell(new Phrase(agregar.fecha, filareti));
                    PdfPCell c4 = new PdfPCell(new Phrase(agregar.detalle, filareti));
                    PdfPCell c5 = new PdfPCell(new Phrase(String.valueOf(agregar.debito), filareti));
                    PdfPCell c6 = new PdfPCell(new Phrase(String.valueOf(agregar.credito), filareti));
                    PdfPCell c7 = new PdfPCell(new Phrase(String.valueOf(agregar.saldo), filareti));
                
                
                    c5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    c6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    c7.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                
                    if(colorFilareti%2==1){
                        c1.setBackgroundColor(Color.CYAN);
                        c2.setBackgroundColor(Color.CYAN);
                        c3.setBackgroundColor(Color.CYAN);
                        c4.setBackgroundColor(Color.CYAN);
                        c5.setBackgroundColor(Color.CYAN);
                        c6.setBackgroundColor(Color.CYAN);
                        c7.setBackgroundColor(Color.CYAN);
                    }
                    colorFilareti = colorFilareti + 1;
                
                    tablareti.addCell(c1);
                    tablareti.addCell(c2);
                    tablareti.addCell(c3);
                    tablareti.addCell(c4);
                    tablareti.addCell(c5);
                    tablareti.addCell(c6);
                    tablareti.addCell(c7);
                }
            }
            
            //Agregar al documento
            documentreti.add(tablareti);

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } finally {     
            // Cerrar el documento
            if (documentreti.isOpen()) {
                documentreti.close();
            }
        }
        
        
        return "Reportes Creados";
    }
}
