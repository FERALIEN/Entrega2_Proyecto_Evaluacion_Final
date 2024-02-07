package InterfazProyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Conector {
	
	//Metodo para iniciar la conexion
	private static Connection iniciarConexion() {
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/starwars", "root", "admin");
			 System.out.println("Conexión a la BD");
			 return con;
			 }
		 catch (Exception e) {
		 System.out.println("Error en conexión");
		 }
		return null;
	}
	
	//Metodo leer datos base
	public static ArrayList<Nave> ejecutarConexionLectura(ArrayList<Nave> listaNaves) {
		Connection con = iniciarConexion();
		
		try {
			Statement st = con.createStatement();
			ResultSet rs;
			
			rs = st.executeQuery("SELECT * FROM naves");
			
			while(rs.next()) {
				Nave nuevaNave = new Nave("", "", "", "", "", "", "", "", "", "");
				nuevaNave.setName(rs.getString(1));
				nuevaNave.setModel(rs.getString(2));
				nuevaNave.setManufacturer(rs.getString(3));
				nuevaNave.setLength(rs.getString(4));
				nuevaNave.setCrew(rs.getString(5));
				nuevaNave.setCost_in_credits(rs.getString(6));
				nuevaNave.setPassengers(rs.getString(7));
				nuevaNave.setCargo_capacity(rs.getString(8));
				nuevaNave.setStarship_class(rs.getString(9));
				nuevaNave.setImage(rs.getString(10));
				
				listaNaves.add(nuevaNave);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cerrarConexion(con);
		return listaNaves;
	}
	
	//Metodo introducir datos base
	public static void ejecutarConexionEscritura(ArrayList<Nave> listaNaves) {
		Connection con = iniciarConexion();
		
		try {
			Statement st = con.createStatement();
			for (Nave nave : listaNaves) {
			st.execute("INSERT INTO naves (nombre, modelo, fabricante, longitud, tripulacion, coste, pasajeros, carga, clase, imagen) values " + 
					"('"+nave.getName()+"','"+nave.getModel()+"','"+nave.getManufacturer()+"','"+nave.getLength()+"','"+nave.getCrew()+"','"+nave.getCost_in_credits()+"','"+nave.getPassengers()+"','"+nave.getCargo_capacity()+"','"+nave.getStarship_class()+"','"+nave.getImage()+"');");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cerrarConexion(con);
	}
	
	//Metodo para cerrar la conexion
	private static void cerrarConexion(Connection con) {
		try {
			 con.close();
			 System.out.println("Conexión cerrada");
			 }
		catch (SQLException e) {
		System.out.println("Error al cerrar conexión");
		} 
	}
}
