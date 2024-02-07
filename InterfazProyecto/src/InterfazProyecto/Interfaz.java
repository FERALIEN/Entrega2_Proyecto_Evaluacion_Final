package InterfazProyecto;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;

public class Interfaz {

	ArrayList<Nave> listaNaves = new ArrayList<>();
	
	private JFrame frame;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblRutaArchivo;
	private JTextField textFieldUrl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setBounds(100, 100, 950, 501);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane.setBounds(10, 149, 914, 239);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Nombre", "Modelo", "Fabricante", "Longitud", "Tripulacion", "Coste", "Pasajeros", "Carga", "Clase", "Imagen"
			}
		));
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("DATOS DE NAVES DE STAR WARS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(236, 0, 471, 64);
		frame.getContentPane().add(lblNewLabel);
		
		lblRutaArchivo = new JLabel("RUTA ARCHIVO:");
		lblRutaArchivo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRutaArchivo.setBounds(10, 94, 111, 34);
		frame.getContentPane().add(lblRutaArchivo);
		
		textFieldUrl = new JTextField();
		textFieldUrl.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		textFieldUrl.setBounds(131, 94, 507, 34);
		frame.getContentPane().add(textFieldUrl);
		textFieldUrl.setColumns(10);
		
		JButton btnSeleccionarUrl = new JButton("...");
		btnSeleccionarUrl.setBackground(new Color(255, 255, 255));
		btnSeleccionarUrl.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnSeleccionarUrl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSeleccionarUrl.setBounds(648, 94, 133, 34);
		frame.getContentPane().add(btnSeleccionarUrl);
		
		btnSeleccionarUrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser();
            	j.showSaveDialog(null);
            	textFieldUrl.setText(j.getSelectedFile().getAbsolutePath());
			}
		});
		
		
		JButton btnCargarUrl = new JButton("CARGAR");
		btnCargarUrl.setBackground(new Color(255, 255, 255));
		btnCargarUrl.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnCargarUrl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCargarUrl.setBounds(791, 94, 133, 34);
		frame.getContentPane().add(btnCargarUrl);
		
		btnCargarUrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldUrl.getText().isEmpty()) {
					JOptionPane.showMessageDialog(btnCargarUrl, "No se ha seleccionado nada");
				}else {
					String url = textFieldUrl.getText().split("\\.")[1];
					if(url.equals("xml")) {
						listaNaves = Dom.leerXml(listaNaves);
					}
					if(url.equals("json")) {
						try {
							listaNaves = Dom.leerJson(listaNaves);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					rellenarTabla(table, listaNaves);
				}
			}
		});
		
		JButton btnExportarSql = new JButton("EXPORTAR SQL");
		btnExportarSql.setBackground(new Color(255, 255, 255));
		btnExportarSql.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnExportarSql.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExportarSql.setBounds(251, 409, 172, 42);
		frame.getContentPane().add(btnExportarSql);
		
		btnExportarSql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listaNaves.isEmpty()) {
					JOptionPane.showMessageDialog(btnExportarSql, "No hay datos disponibles");
				}else {
					Conector.ejecutarConexionEscritura(listaNaves);
				}
			}
		});
		
		JButton btnExportarXml = new JButton("EXPORTAR XML");
		btnExportarXml.setBackground(new Color(255, 255, 255));
		btnExportarXml.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnExportarXml.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExportarXml.setBounds(10, 409, 172, 42);
		frame.getContentPane().add(btnExportarXml);
		
		btnExportarXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listaNaves.isEmpty()) {
					JOptionPane.showMessageDialog(btnExportarXml, "No hay datos disponibles");
				}else {
					Dom.escribirXml(listaNaves);
				}
			}
		});
		
		JButton btnExportarJson = new JButton("EXPORTAR JSON");
		btnExportarJson.setBackground(new Color(255, 255, 255));
		btnExportarJson.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnExportarJson.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExportarJson.setBounds(512, 409, 172, 42);
		frame.getContentPane().add(btnExportarJson);
		
		btnExportarJson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listaNaves.isEmpty()) {
					JOptionPane.showMessageDialog(btnExportarJson, "No hay datos disponibles");
				}else {
					try {
						Dom.escribirJson(listaNaves);
					} catch (IOException e1) {
						e1.printStackTrace();
					}	
				}
			}
		});
		
		JButton btnImportarSql = new JButton("IMPORTAR SQL");
		btnImportarSql.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnImportarSql.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnImportarSql.setBackground(Color.WHITE);
		btnImportarSql.setBounds(791, 53, 133, 34);
		frame.getContentPane().add(btnImportarSql);
		
		btnImportarSql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaNaves = Conector.ejecutarConexionLectura(listaNaves);
				rellenarTabla(table, listaNaves);
			}
		});
		
		JButton btnExportarInforme = new JButton("CREAR INFORME");
		btnExportarInforme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExportarInforme.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnExportarInforme.setBackground(Color.WHITE);
		btnExportarInforme.setBounds(752, 409, 172, 42);
		frame.getContentPane().add(btnExportarInforme);
		
		btnExportarInforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listaNaves.isEmpty()) {
					JOptionPane.showMessageDialog(btnExportarInforme, "No hay datos disponibles");
				}else {
					try {
						Dom.exportarJasper(listaNaves);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (JRException e1) {
						e1.printStackTrace();
					}	
				}
			}
		});
	}
	
	public static void rellenarTabla(JTable table, ArrayList<Nave> listaNaves) {
	    DefaultTableModel modeloTabla = new DefaultTableModel();
	    modeloTabla.setColumnIdentifiers(new String[] {"Nombre", "Modelo", "Fabricante", "Longitud", "Tripulacion", "Coste", "Pasajeros", "Carga", "Clase", "Imagen"});

	    for (Nave nave : listaNaves) {
	        modeloTabla.addRow(new Object[] {
	            nave.getName(), nave.getModel(), nave.getManufacturer(), nave.getLength(),
	            nave.getCrew(), nave.getCost_in_credits(), nave.getPassengers(),
	            nave.getCargo_capacity(), nave.getStarship_class(), nave.getImage()
	        });
	    }

	    table.setModel(modeloTabla);
	}
}
