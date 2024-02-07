package InterfazProyecto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Dom {
	
	public static ArrayList<Nave> leerXml(ArrayList<Nave> listaNaves) {
		File file = new File("naves.xml");
		
		try {
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  Document doc = dBuilder.parse(file);
			  
			  NodeList nList = doc.getElementsByTagName("nave");
			  for(int temp = 0; temp < nList.getLength(); temp++) {
				  Node nNode = nList.item(temp);

				  if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				    Element eElement = (Element) nNode;
				    
				    Nave nuevaNave = new Nave("", "", "", "", "", "", "", "", "", "");
					nuevaNave.setName(eElement.getElementsByTagName("nombre").item(0).getTextContent());
					nuevaNave.setModel(eElement.getElementsByTagName("modelo").item(0).getTextContent());
					nuevaNave.setManufacturer(eElement.getElementsByTagName("fabricante").item(0).getTextContent());
					nuevaNave.setLength(eElement.getElementsByTagName("longitud").item(0).getTextContent());
					nuevaNave.setCrew(eElement.getElementsByTagName("tripulacion").item(0).getTextContent());
					nuevaNave.setCost_in_credits(eElement.getElementsByTagName("coste").item(0).getTextContent());
					nuevaNave.setPassengers(eElement.getElementsByTagName("pasajeros").item(0).getTextContent());
					nuevaNave.setCargo_capacity(eElement.getElementsByTagName("carga").item(0).getTextContent());
					nuevaNave.setStarship_class(eElement.getElementsByTagName("clase").item(0).getTextContent());
					nuevaNave.setImage(eElement.getElementsByTagName("imagen").item(0).getTextContent());
					
					listaNaves.add(nuevaNave);
				  }
				}
			  
			} catch(Exception e) {
			  e.printStackTrace();
			}
		
		return listaNaves;
	}

	public static ArrayList<Nave> leerJson(ArrayList<Nave> listaNaves) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("naves.json"));
		String linea = "";
		
		StringBuilder content = new StringBuilder();
		
		while (linea != null) {
	        linea = br.readLine();
	        if (linea != null) {
	        	content.append(linea).append("\n");
			}
	    }
		
		String jsonString = content.toString();
	    br.close();
	    
	    if(jsonString != null) {
	    	Gson gson = new Gson();
	    	listaNaves = gson.fromJson(jsonString, new TypeToken<ArrayList<Nave>>() {}.getType());
	    }
	    
		return listaNaves;
	}
	
	public static void escribirXml(ArrayList<Nave> listaNaves) {
		try {
			  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  DocumentBuilder db = dbf.newDocumentBuilder();
			  Document doc = db.newDocument();

			  Element eRaiz = doc.createElement("naves");
			  doc.appendChild(eRaiz);

			  for (Nave nave : listaNaves) {
				  Element eNave = doc.createElement("nave");
				  eRaiz.appendChild(eNave);
	
				  Element eNombre = doc.createElement("nombre");
				  eNombre.appendChild(doc.createTextNode(nave.getName()));
				  eNave.appendChild(eNombre);
				  
				  Element eModelo = doc.createElement("modelo");
				  eModelo.appendChild(doc.createTextNode(nave.getModel()));
				  eNave.appendChild(eModelo);
				  
				  Element eFabricante = doc.createElement("fabricante");
				  eFabricante.appendChild(doc.createTextNode(nave.getManufacturer()));
				  eNave.appendChild(eFabricante);
				  
				  Element eLongitud = doc.createElement("longitud");
				  eLongitud.appendChild(doc.createTextNode(nave.getLength()));
				  eNave.appendChild(eLongitud);
				  
				  Element eTripulacion = doc.createElement("tripulacion");
				  eTripulacion.appendChild(doc.createTextNode(nave.getCrew()));
				  eNave.appendChild(eTripulacion);
				  
				  Element eCoste = doc.createElement("coste");
				  eCoste.appendChild(doc.createTextNode(nave.getCost_in_credits()));
				  eNave.appendChild(eCoste);
				  
				  Element ePasajeros = doc.createElement("pasajeros");
				  ePasajeros.appendChild(doc.createTextNode(nave.getPassengers()));
				  eNave.appendChild(ePasajeros);
				  
				  Element eCarga = doc.createElement("carga");
				  eCarga.appendChild(doc.createTextNode(nave.getCargo_capacity()));
				  eNave.appendChild(eCarga);
				  
				  Element eClase = doc.createElement("clase");
				  eClase.appendChild(doc.createTextNode(nave.getStarship_class()));
				  eNave.appendChild(eClase);
				  
				  Element eImagen = doc.createElement("imagen");
				  eImagen.appendChild(doc.createTextNode(nave.getImage()));
				  eNave.appendChild(eImagen);
			  }
			  
			  TransformerFactory transformerFactory = TransformerFactory.newInstance();
			  Transformer transformer = transformerFactory.newTransformer();
			  DOMSource source = new DOMSource(doc);
			  StreamResult result = new StreamResult(new File("naves.xml"));

			  transformer.transform(source, result);
			} catch(Exception e) {
			  e.printStackTrace();
			}
	}
	
	public static void escribirJson(ArrayList<Nave> listaNaves) throws IOException {
		FileWriter fichero = new FileWriter("naves.json");
		BufferedWriter bw = new BufferedWriter(fichero);
		Gson gson = new Gson();
		String json = gson.toJson(listaNaves);
		
		bw.write(json);
		bw.close();
	}

	public static void exportarJasper(ArrayList<Nave> listaNaves) throws FileNotFoundException, JRException {
		JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listaNaves);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("CollectionBeanParam", itemsJRBean);
		
		FileInputStream input = new FileInputStream(new File("JasperNavesStarWars_A4.jrxml"));
		JasperDesign jasperDesign = JRXmlLoader.load(input);
		
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		
		JasperViewer.viewReport(jasperPrint);
		
		FileOutputStream outputStream = new FileOutputStream(new File("JasperReport.pdf"));
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}
}
