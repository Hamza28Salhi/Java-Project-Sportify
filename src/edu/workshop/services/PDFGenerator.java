/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.services;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.worshop.model.Equipe;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;


/**
 *
 * @author Ace River
 */
public class PDFGenerator {
    Equipe p = new Equipe();
    

	public void GeneratePdf(String filename, Equipe p) throws FileNotFoundException, DocumentException,
			BadElementException, IOException, InterruptedException, SQLException {

		Document document = new Document() {
		};
		PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
		document.open();

Image img = Image.getInstance("C:\\Users\\Ace River\\Desktop\\logo.png");


                           img.scaleAbsoluteHeight(100);
       img.scaleAbsoluteWidth(150);
       img.setAlignment(Image.ALIGN_RIGHT);
       document.add(img);
		document.add(new Paragraph("Id de l'Equipe :" + p.getId()));
		document.add(new Paragraph("                      "));
		document.add(new Paragraph(
				"----------------------------------------------------------------------------------------------------------------------"));

		document.add(new Paragraph("nom de l'Equipe  :" + p.getNom()));
		document.add(new Paragraph("                      "));
		document.add(new Paragraph("Classement  :" + p.getClassement()));
		document.add(new Paragraph("                      "));
		document.add(new Paragraph("Les joueurs :" + p.getJoueurs()));
		

		document.add(new Paragraph(
				"---------------------------------------------------------------------------------------------------------------------------------- "));
		document.add(new Paragraph("                              Sportify the best Club!               "));

		document.close();
		Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
	}
    
}