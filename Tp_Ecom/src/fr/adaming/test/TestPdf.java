package fr.adaming.test;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class TestPdf {

	public static final String chemin ="C:\\Users\\intiformation\\Desktop\\testpdf5.pdf";
	
	public static void main(String[] args) throws DocumentException, IOException{
		
		Document document = new Document();
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream(chemin));
			document.open();
			//ajout du contenu
			document.add(new Paragraph("EHEH new pdf incoming"));
			 PdfPTable table = new PdfPTable(8);
			table.addCell("olalal");
			table.addCell("ohoh");
			table.addCell("ohoh");
			table.addCell("ohoh");
			table.addCell("ohoh");
			table.addCell("ohoh");
			table.addCell("ohoh");
			table.addCell("ohoh");
			table.addCell("olalal");
			table.addCell("ohoh");
			table.addCell("ohoh");
			table.addCell("ohoh");
			table.addCell("ohoh");
			table.addCell("ohoh");
			table.addCell("ohoh");
			table.addCell("ohoh");
			 document.add(table);
			
		} catch (DocumentException de) {
			de.printStackTrace();
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}
		//Fermeture du document
		document.close();
	}
	
}
