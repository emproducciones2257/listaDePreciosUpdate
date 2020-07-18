package control;

import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import conexionBD.DBDtos;
import conexionBD.DBGestionCategorias;
import conexionBD.dbGestionPrecios;
import modelo.categorias;
import modelo.constantes;
import modelo.preciosDocumento;
import views.Principal;
import views.pnlGestionPrecios;
import views.ventanasAvisos;

public class controlGestionPrecios implements ActionListener, MouseListener, KeyListener{
	
	private pnlGestionPrecios pnlPrecios;
	private File archivo = new File("");
	private ArrayList<preciosDocumento> precios;
	private dbGestionPrecios DBGP;
	private DBDtos DBDT;
	private ventanasAvisos avisos;
	private String fecha,categoriaSeleccionada="";
	private categorias indiceCatSelec;
	private preciosDocumento productoActualizar;
	private List<preciosDocumento> prepre;
	private ArrayList<categorias> categorias;
	private DBGestionCategorias DBCategorias;
	private FileNameExtensionFilter filPdf,filExcel1,filExcel2;
	
	public controlGestionPrecios(pnlGestionPrecios pnlPrecios) {
		this.pnlPrecios = pnlPrecios;
		pnlPrecios.getBtnProcesar().addActionListener(this);
		pnlPrecios.getTblListadoPrecios().addMouseListener(this);
		pnlPrecios.getBtnRegistrarPrecio().addActionListener(this);
		pnlPrecios.getTxtFiltrarProducto().addKeyListener(this);
		pnlPrecios.getJcmbCategorias().addActionListener(this);
		precios = new ArrayList<preciosDocumento>();
		DBGP = new dbGestionPrecios();
		DBCategorias = new DBGestionCategorias();
		DBDT = new DBDtos();
		avisos = new ventanasAvisos(pnlPrecios);
		cargarCategorias();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(pnlPrecios.getBtnBuscarArchivo())) {
			obtenerArchivo();		
		}
		
		if (e.getSource().equals(pnlPrecios.getBtnProcesar())) {
			
			if((!archivo.isFile())&&(pnlPrecios.getTxtPorcentaje().getText().isEmpty())) {
				avisos.faltanDatos(ventanasAvisos.FALTAN_DATOS);
			}
			
			if((archivo.isFile())&&(pnlPrecios.getTxtPorcentaje().getText().isEmpty())) {
				
				enviarArchivoBD(0);
				
				archivo = new File("");
				pnlPrecios.getLblEstadoArchivo().setText("Sin Archivo");
			}
			
			if((!archivo.isFile())&&(!pnlPrecios.getTxtPorcentaje().getText().isEmpty())) {
				DBDT.actualiazarPorcentaje(Integer.parseInt(pnlPrecios.getTxtPorcentaje().getText()));
			}
			
			if((archivo.isFile())&&(!pnlPrecios.getTxtPorcentaje().getText().isEmpty())) {
				
				enviarArchivoBD(Integer.parseInt(pnlPrecios.getTxtPorcentaje().getText()));
				
				archivo = new File("");
				pnlPrecios.getLblEstadoArchivo().setText("Sin Archivo");
			}
		}
		
		if (e.getSource().equals(pnlPrecios.getBtnRegistrarPrecio())) {
			
			if(!pnlPrecios.getTxtPrecio().getText().isEmpty()) {
				productoActualizar.setPrecio(Double.parseDouble(pnlPrecios.getTxtPrecio().getText()));
			}
			
			if(!pnlPrecios.getTxtDescripcion().getText().isEmpty()) {
				productoActualizar.setProd(pnlPrecios.getTxtDescripcion().getText());
			}
			
			DBGP.actualizarRegistro(productoActualizar);
			//pnlPrecios.limpiarTabla();
			//pnlPrecios.modeloTabla();
		}
		
		if(e.getSource().equals(pnlPrecios.getJcmbCategorias())) {
			
			categoriaSeleccionada=pnlPrecios.getJcmbCategorias().getSelectedItem().toString();

			if(!categoriaSeleccionada.equals(constantes.VALOR_DEFECTO_CATEGORIAS)) {
				indiceCatSelec = categorias.get(pnlPrecios.getJcmbCategorias().getSelectedIndex()-1);
			}else {
				
				if(pnlPrecios.getTblListadoPrecios().getRowCount()>0) {
					pnlPrecios.limpiarTabla();
				}
			}
			
			if(!categoriaSeleccionada.equals(constantes.VALOR_DEFECTO_CATEGORIAS)) {
				
				pnlPrecios.getBtnBuscarArchivo().setEnabled(true);
				
				if(categoriaSeleccionada.equals("LIBRERIA")) {
					mostrarTodosPreciosPorCategorias();
					//Guardo las preferencias de la extencion
					filPdf=new FileNameExtensionFilter(constantes.VALOR_TIPO_PDF, constantes.VALOR_EXTENCION_PDF);

				}else {
					mostrarTodosPreciosPorCategorias();

					filExcel1 = new FileNameExtensionFilter(constantes.VALOR_TIPO_EXCEL1, constantes.VALOR_EXTENCION_EXCEL1);
					filExcel2 = new FileNameExtensionFilter(constantes.VALOR_TIPO_EXCEL2, constantes.VALOR_EXTENCION_EXCEL2);
				}
				
			}else {
				pnlPrecios.getBtnBuscarArchivo().setEnabled(false);
			}
		}
	}

	private void mostrarTodosPreciosPorCategorias() {
		prepre = DBGP.obtenerListadoProductosPrecios(indiceCatSelec.getIdCategoria());
		
		if (!prepre.isEmpty()) {
			
			if(pnlPrecios.getTblListadoPrecios().getRowCount()>0) {
				pnlPrecios.limpiarTabla();
			}
			pnlPrecios.setLista(prepre);
			pnlPrecios.modeloTabla();
		}
	}

	private void enviarArchivoBD(int porcentaje) {
		
		if(categoriaSeleccionada.equals("LIBRERIA")) {
			extraerTextoPdf(archivo);
			DBDT.actualiazarDtos(fecha,porcentaje);
			Principal.refrescarDatos();
		}else {
			extraerDtosExcel(archivo);
		}
		
		DBGP.cargarADB(precios,categoriaSeleccionada);
		DBGP.cargarADB(precios,"LIBRERIA");
		mostrarTodosPreciosPorCategorias();
	}
	
	private void cargarCategorias() {
		categorias = DBCategorias.obtenerCategorias();
		
		if(pnlPrecios.getJcmbCategorias().getItemCount()>0) {
			pnlPrecios.getJcmbCategorias().removeAllItems();
		}
		
		pnlPrecios.getJcmbCategorias().addItem(constantes.VALOR_DEFECTO_CATEGORIAS);
		if (!categorias.isEmpty()) {
			
			for (categorias ca : categorias) {
				pnlPrecios.getJcmbCategorias().addItem(ca.getNomCat());
			}
		}
	}
	
	private void obtenerArchivo() {
		
		JFileChooser b = new JFileChooser();
		
		if(categoriaSeleccionada.equals("LIBRERIA")) {
			b.setFileFilter(filPdf);
		}else {
			b.setFileFilter(filExcel1);
			b.setFileFilter(filExcel2);
		}

		 int returnVal = b.showOpenDialog(null);
		 
		 if(returnVal == JFileChooser.APPROVE_OPTION) {
	            archivo = b.getSelectedFile().getAbsoluteFile();
	            pnlPrecios.getLblEstadoArchivo().setText(constantes.ARCHIVO_OK);
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

          }	
	}
	
	private void extraerDtosExcel(File ruta) {
		
		try {
			FileInputStream documentoExcel = new FileInputStream(ruta);
			//creo libro 
			Workbook libroExcell = WorkbookFactory.create(documentoExcel);
			
			//obtengo la primero hoja del libro
			Sheet HojaLibro = libroExcell.getSheetAt(0);
			
			ArrayList<Object> filasCelda;
			
			Cell celdaTemporal = null;
			
			Iterator<Row> filaIterator = HojaLibro.rowIterator();
			filaIterator.next();
			
	        while (filaIterator.hasNext()) {
	        	
	            Row fila = filaIterator.next();

	            Iterator<Cell> celdalIterator = fila.cellIterator();
	            
	            filasCelda = new ArrayList<>();

	            while (celdalIterator.hasNext()) {
	                celdaTemporal = celdalIterator.next();
	                
	                if(celdaTemporal.getCellType()==CellType.NUMERIC) {
				    	filasCelda.add(celdaTemporal.getNumericCellValue());
	            	}
	            	if (celdaTemporal.getCellType()==CellType.STRING) {
				    	filasCelda.add(celdaTemporal.getStringCellValue());
					}
	            }
	            procesarCelda(filasCelda);
	        }
			
			documentoExcel.close();
			libroExcell.close();
		} catch (Exception e) {
			avisos.CargaErronea(ventanasAvisos.ERROR_CARGA_ARCHIVO);
			e.printStackTrace();
		}
	}
	
	private void procesarCelda(ArrayList<Object> filasCelda) {
		if(filasCelda.size()==3) {
			preciosDocumento pTemp = new preciosDocumento(filasCelda.get(0).toString(),
					filasCelda.get(1).toString(),
					Double.valueOf(filasCelda.get(2).toString()),
					indiceCatSelec.getIdCategoria());
			precios.add(pTemp);
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
                              
                preciosDocumento temp = new preciosDocumento(codigo,
                												descripcion,
                												Double.parseDouble(precio),
                												indiceCatSelec.getIdCategoria());
                                
                precios.add(temp);
                               
                contador=0;
                codigo="";
                descripcion="";
                precio="";
                Leido = "";
            } 
        }

	@Override
	public void mouseClicked(MouseEvent e) {
		productoActualizar = pnlPrecios.retornarElemento(pnlPrecios.getTblListadoPrecios().getSelectedRow());
		pnlPrecios.getTxtIdProducto().setText(productoActualizar.getIdPrecio()+"");
		pnlPrecios.getTxtDescripcion().setText(productoActualizar.getProd());
		pnlPrecios.getTxtPrecio().setText(productoActualizar.getPrecio()+"");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		/*prepre = DBGP.obtenerListadoProductosPreciosFiltrados(pnlPrecios.getTxtFiltrarProducto().getText());
		
		if (!prepre.isEmpty()) {
			pnlPrecios.limpiarTabla();
			pnlPrecios.setLista(prepre);
			pnlPrecios.modeloTabla();
		}else {
			pnlPrecios.limpiarTabla();
		}	*/
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	} 

}
