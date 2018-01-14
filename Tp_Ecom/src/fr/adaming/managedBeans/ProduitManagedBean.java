package fr.adaming.managedBeans;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.itextpdf.awt.geom.CubicCurve2D.Float;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.CategorieServiceImpl;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;
import fr.adaming.service.ProduitServiceImpl;

@ManagedBean(name = "pMb")
@ViewScoped
public class ProduitManagedBean implements Serializable {

	@EJB
	private IProduitService pService;

	private ICategorieService cService = new CategorieServiceImpl();
	private Produit produit;
	private Admin admin;
	private List<Produit> listeProduits;
	private List<Produit> listeProduitsParCategorie;
	private HttpSession maSession;
	private UploadedFile file;

	private String image;

	public ProduitManagedBean() {

		// Instancier un produit
		this.produit = new Produit();
		this.listeProduits = new ArrayList<Produit>();
	}

	// Methode qui s'exectute apres l'instanciation du ManagedBean
	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.admin = (Admin) maSession.getAttribute("adminSession");
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public void setpService(IProduitService pService) {
		this.pService = pService;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// Les methodes metiers

	public String addProduit() throws Exception {
		// //Appel de la methode service

		this.produit = pService.addProduit(this.produit);

		System.out.println("------------------- " + this.produit.getIdProduit());
		if (this.produit.getIdProduit() != 0) {
			// Recuperer la nouvelle liste de la bd
			this.listeProduits = pService.getAllProduit();
			// Mettre a jour la liste des produits dans la session
			maSession.setAttribute("produitList", this.listeProduits);
			pService.confirmAddProd(this.produit);
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue lors de l'ajout."));
			return "ajout_produit";
		}
	}

	public void upload(FileUploadEvent event) {
		UploadedFile ufile = event.getFile();
		byte[] contents = ufile.getContents();
		this.produit.setPhoto(contents);
		this.image = "data:image/png;base64," + Base64.encodeBase64String(contents);
	}

	public String updateProduit() {
		Produit pModif = pService.updateProduit(this.produit);
		if (pModif != null) {
			// Recuperer la nouvelle liste de la bd
			List<Produit> listeP = pService.getAllProduit();
			// Mettre a jour la liste des voitures dans la session
			maSession.setAttribute("produitList", listeP);
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue lors de la modification."));
			return "modif_produit";
		}
	}

	public String getProduitbyId() {
		Produit pFind = pService.getProduitbyId(this.produit.getIdProduit());
		pFind.setImage("data:image/png;base64," + Base64.encodeBase64String(pFind.getPhoto()));
		if (pFind != null) {
			this.produit = pFind;
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue lors de la recherche."));
		}
		return "rechercher_produit";
	}

	public String getProduitbyName() {
		Produit pFind = pService.getProduitbyName(this.produit.getDesignation());
		pFind.setImage("data:image/png;base64," + Base64.encodeBase64String(pFind.getPhoto()));
		if (pFind != null) {
			this.produit = pFind;
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue lors de la recherche."));
		}
		return "rechercher_produit";
	}

	public String deleteProduit() {
		pService.deleteProduit(this.produit.getIdProduit());
		// Recuperer la nouvelle liste de la bd
		List<Produit> listeP = pService.getAllProduit();
		// Mettre a jour la liste des voitures dans la session
		maSession.setAttribute("produitList", listeP);
		return "accueilAdmin";
	}

	public String deleteProduitByName() {
		pService.deleteProduitByName(this.produit.getDesignation());
		// Recuperer la nouvelle liste de la bd
		List<Produit> listeP = pService.getAllProduit();
		// Mettre a jour la liste des voitures dans la session
		maSession.setAttribute("produitList", listeP);
		return "accueilAdmin";
	}

	public String getAllProduits() {
		this.listeProduits = pService.getAllProduit();
		if (listeProduits.size() > 0) {

			List<Produit> listeTemp = new ArrayList<Produit>();
			for (Produit prod : listeProduits) {
				prod.setImage("data:image/png;base64," + Base64.encodeBase64String(prod.getPhoto()));
				listeTemp.add(prod);
			}
			this.setListeProduits(listeTemp);

			maSession.setAttribute("produitList", this.listeProduits);
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue du chargement de la liste."));
		}
		return "affiche_produits";
	}

	public void createPdf() throws IOException {
		
		Document document = new Document();
		String chemin = "C:\\Users\\intiformation\\Desktop\\testlistProd.pdf";

		try {
			PdfWriter.getInstance(document, new FileOutputStream(chemin));
			document.open();
			addInfoPage(document);
			document.add(tableauProduit());
			// document.add(Paragraph1());
			Paragraph preface = new Paragraph();
			preface.setAlignment(Element.ALIGN_CENTER);
			preface.add(" Ecom Carnaval");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {

			e.printStackTrace();
		}
		// Fermeture du document
		document.close();

	}

	private static void addInfoPage(Document document) throws DocumentException,  IOException {
		Paragraph preface = new Paragraph();
		
		Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD, BaseColor.RED);
		Font catFont2 = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL, BaseColor.BLACK);
		Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.NORMAL, BaseColor.RED);
		Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		
		addEmptyLine(preface, 1);
		
		preface.add(new Paragraph("Ecom Carnaval", catFont));
		preface.add(new Paragraph("Date : " + new Date(), smallBold));
		preface.add(new Paragraph("Voici le récapitulatif des produits enregistrés :", catFont2));
		addEmptyLine(preface, 1);
		
		addEmptyLine(preface, 2);
		document.add(preface);
	}

	public PdfPTable tableauProduit() {
		List<Produit> prodtot = pService.getAllProduit();
		// On créer un objet table dans lequel on intialise sa taille.
		PdfPTable tableProd = new PdfPTable(3);

		// On créer l'objet cellule.
		PdfPCell cell;

		cell = new PdfPCell(new Phrase("Information Liste Produit"));
		cell.setColspan(3);
		tableProd.addCell(cell);

		// contenu du tableau.
		for (Produit p : prodtot) {
			// ajout colonne produit
			cell = new PdfPCell(new Phrase("Produit " + p.getIdProduit()));
			cell.setRowspan(2);
			tableProd.addCell(cell);
			// ajout colonne specifique au produit
			tableProd.addCell("Designation " + p.getDesignation());
			tableProd.addCell("Description " + p.getDescription());
			tableProd.addCell("Prix " + p.getPrix());
			tableProd.addCell("Quantité " + p.getQuantite());
		}
 
		return tableProd;

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	

//	public String getProduitsByCategorie() {
//		Categorie catOut = (Categorie) maSession.getAttribute("selectedCat");
//		this.listeProduitsParCategorie = pService.getAllProduitByCategorie(catOut.getIdCategorie());
//		if (listeProduitsParCategorie.size() > 0) {
//
//			List<Produit> listeTemp = new ArrayList<Produit>();
//			for (Produit prod : listeProduits) {
//				prod.setImage("data:image/png;base64," + Base64.encodeBase64String(prod.getPhoto()));
//				listeTemp.add(prod);
//			}
//			this.setListeProduits(listeTemp);
//
//			maSession.setAttribute("produitListParCateg", this.listeProduitsParCategorie);
//		} else {
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage("Une erreur est survenue du chargement de la liste."));
//		}
//		return "affiche_produits";
//					
//	}
}
