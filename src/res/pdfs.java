package res;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class pdfs {

	public static void main(String[] args) {
		try {
			extraerTextoPdf();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void extraerTextoPdf () throws Exception {
		// Create a new empty document
		PDDocument dNuevo = new PDDocument();
        try {
            //cargo el documento
            PDDocument documento = PDDocument.load(new File("C:\\Users\\Emanuel\\Desktop\\boletasrawson.pdf"));
			
            //PDFTextStripper extraccion = new PDFTextStripper();	
            
            /*dNuevo.addPage(documento.getPage(0));
            dNuevo.addPage(documento.getPage(1));
            dNuevo.addPage(documento.getPage(2));
            dNuevo.save("chupala.pdf");*/
            if(documento.isEncrypted()) {
            	System.out.println("bloqueado ");
                // documento.setAllSecurityToBeRemoved(true);
            }
            //documento.setAllSecurityToBeRemoved(true);
            //System.out.println("paginas " + documento.getNumberOfPages());
            documento.close();
            dNuevo.close();
            } catch (IOException e) {
            	e.printStackTrace();
          }	
	}

}
