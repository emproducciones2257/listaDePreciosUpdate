package control;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import conexionBD.DBDtos;
import conexionBD.dbGestionPrecios;
import modelo.preciosDocumento;
import views.Principal;
import views.pnlGestionPrecios;
import views.ventanasAvisos;

public class controlGestionPrecios implements ActionListener{
	
	private pnlGestionPrecios pnlPrecios;
	private File archivo = new File("");
	private ArrayList<preciosDocumento> precios;
	private dbGestionPrecios DBGP;
	private DBDtos DBDT;
	private ventanasAvisos avisos;
	private String fecha;
	
	public controlGestionPrecios(pnlGestionPrecios pnlPrecios) {
		// TODO Auto-generated constructor stub
		
		this.pnlPrecios = pnlPrecios;
		pnlPrecios.getBtnProcesar().addActionListener(this);
		precios = new ArrayList<preciosDocumento>();
		DBGP = new dbGestionPrecios();
		DBDT = new DBDtos();
		avisos = new ventanasAvisos(pnlPrecios);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource().equals(pnlPrecios.getBtnBuscarArchivo())) {
			obtenerArchivo();		
		}
		
		if (e.getSource().equals(pnlPrecios.getBtnProcesar())) {
			
			if((!archivo.isFile())&&(pnlPrecios.getTxtPorcentaje().getText().isEmpty())) {
				avisos.faltanDatos(ventanasAvisos.FALTAN_DATOS);
			}
			
			if((archivo.isFile())&&(pnlPrecios.getTxtPorcentaje().getText().isEmpty())) {
				extraerTextoPdf(archivo);
				DBGP.cargarADB(precios);
				archivo = new File("");
				pnlPrecios.getLblEstadoArchivo().setText("Sin Archivo");
			}
			
			if((!archivo.isFile())&&(!pnlPrecios.getTxtPorcentaje().getText().isEmpty())) {
				DBDT.actualiazarPorcentaje(Integer.parseInt(pnlPrecios.getTxtPorcentaje().getText()));
				
			}
			
			if((archivo.isFile())&&(!pnlPrecios.getTxtPorcentaje().getText().isEmpty())) {
				extraerTextoPdf(archivo);
				DBDT.actualiazarDtos(
						fecha,
						Integer.parseInt(pnlPrecios.getTxtPorcentaje().getText()));
				Principal.refrescarDatos();
				archivo = new File("");
				pnlPrecios.getLblEstadoArchivo().setText("Sin Archivo");
			}
		}
	}
	
	private void obtenerArchivo() {
		
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Ducumento PDF", "pdf");
		
		 JFileChooser b = new JFileChooser();
		 
		 b.setFileFilter(filtro);
		 
		 int returnVal = b.showOpenDialog(null);
		 
		 if(returnVal == JFileChooser.APPROVE_OPTION) {
	            archivo = b.getSelectedFile().getAbsoluteFile();
	            pnlPrecios.getLblEstadoArchivo().setText("Archivo cargado correctamente");
	     }else {
			avisos.CargaErronea(ventanasAvisos.ERROR_CARGA_ARCHIVO);
		}
	}
	
	private void extraerTextoPdf (File ruta) {

        try {
            //cargo el documento
            PDDocument documento = PDDocument.load(ruta);
			
            // creo una instancia de la clase que me permite extraer el texto del pdf
			
            PDFTextStripper extraccion = new PDFTextStripper();
			
            String textoRecuperado = extraccion.getText(documento);
			
            documento.close();
            
            fecha = obtenerFechaDocumento(textoRecuperado);
            
            String temp = quitarEncabezado(textoRecuperado);
        
            pruebaDelimitador(temp); 

            } catch (IOException e) {
                    // TODO Auto-generated catch block

            }	
	}
        
    private String obtenerFechaDocumento(String textoRecuperado) {
    	
    	Scanner escaner = new Scanner(textoRecuperado);
    	String deco;
    	
    	for (int i = 0; i < 3; i++) {
			escaner.nextLine();
		}
    	deco = escaner.nextLine().replace("CASA CENTRAL: LARREA 174  TEL/FAX: 4952-3908 4954-5827 4951-8033 ", "");
		escaner.close();
		return deco;
	}

	private String quitarEncabezado(String t){
            String textoListo="";
            
            String temp;
            
            Scanner escaner = new Scanner(t);

                while (escaner.hasNext()) {
                    temp=escaner.nextLine();
                    if ((temp).equals("Lista de Precios de Productos de ")){
                        escaner.nextLine();
                        escaner.nextLine();
                        escaner.nextLine();
                        escaner.nextLine();
                        escaner.nextLine();
                        escaner.nextLine();
                        temp=escaner.nextLine(); 
                        textoListo= textoListo+temp +"\n";
                    }else {
                        textoListo= textoListo+temp +"\n"; 
                    }
                }
                escaner.close();
        return textoListo; 
        }
        
    private void pruebaDelimitador (String t) throws IOException{
                    
            Scanner elEscaner = new Scanner(t);
            String codigo="";
            String descripcion="";
            String precio="";
            String Leido = "";
            Double precioPorcentaje=0.0;
            char c;
            byte contador = 0;
        
            while (elEscaner.hasNext()) {
            
                Leido=elEscaner.nextLine();
            
                c = Leido.charAt(contador);
                
                while ((c != ' ')|| (c != ' ')){
                    
                    codigo=codigo+c;
                    
                    contador++;
                    
                    c = Leido.charAt(contador);
                }
                contador=0;
                
                Leido = Leido.replaceFirst(codigo, "");

                c = Leido.charAt(contador);
                
                while (c !='$'){
                    
                    descripcion=descripcion+c;
                    
                    contador++;
                    
                    c = Leido.charAt(contador);
                }

                Leido = Leido.replace(descripcion, "");
                
                precio= (Leido.replace("$",""));
                
                precio= (precio.replace(".",""));
                
                precio= (precio.replace(",","."));
                              
                preciosDocumento temp = new preciosDocumento(Integer.parseInt(codigo),descripcion, Double.parseDouble(precio),precioPorcentaje);
                                
                precios.add(temp);
                               
                contador=0;
                codigo="";
                descripcion="";
                precio="";
                Leido = "";
            } 
        } 

}
