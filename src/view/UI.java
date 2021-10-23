package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Model.MyConnection;

import controller.SuperWarriorControllerRepoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JTable;


public class UI {

	private static Connection setConnect;
	private JFrame frame;
	private static SuperWarriorControllerRepoImpl controller;
	private static StyledDocument doc;
	private static SimpleAttributeSet keyWord;
	private JTable table;
	private JPanel noWrapPanel;
	private boolean tableIsShowed;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					connectDb();
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	public UI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("SuperWarrior DB");

		frame.setBounds(100, 100, 795, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		CustomJPanel panel = new CustomJPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(284, 12, 197, 24);
		panel.add(comboBox);
		loadTables(comboBox);

		JTextPane textPane = new JTextPane();
		textPane.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		giveMeBorder(textPane);
		prepareJTextPane(textPane, panel);

		/***************************************/

		JButton btnNewSuperspecies = new JButton("New SuperSpecies");

		btnNewSuperspecies.setBounds(47, 12, 188, 25);
		giveMeBorder(btnNewSuperspecies);
		panel.add(btnNewSuperspecies);
		btnNewSuperspecies.addActionListener(e -> {
			if(tableIsShowed) {
				noWrapPanel.remove(table);
				tableIsShowed=false;
			}
			displayShow(controller.createQuery(JOptionPane.showInputDialog("Input the name of new SuperSpecies")),textPane);
			loadTables(comboBox);
		});

		/****************************************************/
		JButton btnDeleteSuperspecies = new JButton("Delete SuperSpecies");
		btnDeleteSuperspecies.setBounds(551, 12, 188, 25);
		giveMeBorder(btnDeleteSuperspecies);
		panel.add(btnDeleteSuperspecies);
		btnDeleteSuperspecies.addActionListener(e->{
			if(tableIsShowed) {
				noWrapPanel.remove(table);
				tableIsShowed=false;
			}
			displayShow(controller.dropTableQuery(comboBox.getSelectedItem().toString()),textPane);
			loadTables(comboBox);
		});

		/*****************************************************/
		JButton btnNewSuperwarrior = new JButton("New SuperWarrior");
		btnNewSuperwarrior.setBounds(47, 58, 188, 25);
		giveMeBorder(btnNewSuperwarrior);
		
		panel.add(btnNewSuperwarrior);
		btnNewSuperwarrior.addActionListener(e->{
			if(tableIsShowed) {
				noWrapPanel.remove(table);
				tableIsShowed=false;
			}
			displayShow(controller.insertQuery(comboBox.getSelectedItem().toString(), 
					JOptionPane.showInputDialog("Input the name of new SuperWarrior"),
					JOptionPane.showInputDialog("Input the description of new SuperWarrior"),
					Integer.parseInt(JOptionPane.showInputDialog("Input Wind power:")), 
					Integer.parseInt(JOptionPane.showInputDialog("Input water power")), 
					Integer.parseInt(JOptionPane.showInputDialog("Input fire power"))),
					textPane);
			
		});

		/**************************************************************/
		JButton btnDeleteSuperwarrior = new JButton("Delete SuperWarrior");
		btnDeleteSuperwarrior.setBounds(47, 102, 188, 25);
		giveMeBorder(btnDeleteSuperwarrior);
		panel.add(btnDeleteSuperwarrior);
		
		btnDeleteSuperwarrior.addActionListener(e->{
			if(tableIsShowed) {
				noWrapPanel.remove(table);
				tableIsShowed=false;
			}
			displayShow(controller.deleteQuery(comboBox.getSelectedItem().toString(), JOptionPane.showInputDialog("Input the name of  SuperWarrior")), textPane);
			
		});

		/*******************************************************************/
		JButton btnDeleteSuperwarrior_1 = new JButton("Set Powders");
		btnDeleteSuperwarrior_1.setBounds(551, 102, 188, 25);
		giveMeBorder(btnDeleteSuperwarrior_1);
		panel.add(btnDeleteSuperwarrior_1);

		btnDeleteSuperwarrior_1.addActionListener(e->{
			if(tableIsShowed) {
				noWrapPanel.remove(table);
				tableIsShowed=false;
			}
			displayShow(controller.givePowerQuery(comboBox.getSelectedItem().toString(),
					JOptionPane.showInputDialog("Input the description of  SuperWarrior"), 
					Integer.parseInt(JOptionPane.showInputDialog("Input Wind power:")),
					Integer.parseInt(JOptionPane.showInputDialog("Input water power")),
					Integer.parseInt(JOptionPane.showInputDialog("Input fire power"))),textPane);
		});
		/*********************************************************************/
		JButton btnResetSuperWarrior = new JButton("Reset SuperWarrior");
		btnResetSuperWarrior.setBounds(551, 58, 188, 25);
		giveMeBorder(btnResetSuperWarrior);
		panel.add(btnResetSuperWarrior);
		btnResetSuperWarrior.addActionListener(e->{
			if(tableIsShowed) {
				noWrapPanel.remove(table);
				tableIsShowed=false;
			}
			displayShow(controller.resetQuery(comboBox.getSelectedItem().toString(), JOptionPane.showInputDialog("Input the name of  SuperWarrior"), 0, 0, 0),textPane);
		});

		/****************************************************************/
		JButton btnSearchWarrior = new JButton("Search Warrior");
		btnSearchWarrior.setBounds(306, 58, 141, 25);
		giveMeBorder(btnSearchWarrior);
		panel.add(btnSearchWarrior);
		btnSearchWarrior.addActionListener(e->{
			if(tableIsShowed) {
				noWrapPanel.remove(table);
				tableIsShowed=false;
			}
			ResultSet rs=controller.searchQuery(comboBox.getSelectedItem().toString(), JOptionPane.showInputDialog("Input the name of  SuperWarrior"));
			printTable(rs,table,noWrapPanel);
		});

		/************************************************************/
		JButton btnListAll = new JButton("List All");
		btnListAll.setBounds(306, 102, 141, 25);
		giveMeBorder(btnListAll);
		panel.add(btnListAll);
		btnListAll.addActionListener(e->{
			textPane.setText("");
			ResultSet rs=controller.selectQuery(comboBox.getSelectedItem().toString());
			printTable(rs,table,noWrapPanel);
		});

		JLabel lblDaniel = new JLabel("Daniel Giménez González IFP");
		lblDaniel.setForeground(Color.WHITE);
		lblDaniel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 23));
		lblDaniel.setBounds(217, 318, 386, 40);
		panel.add(lblDaniel);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void printTable(ResultSet rs, JTable table2,JPanel panel) {
		// TODO Auto-generated method stub
		try {
			
			 Vector data = new Vector();
			    while (rs.next()) {
			        @SuppressWarnings("rawtypes")
					Vector row = new Vector();
			        row.add(rs.getString("name"));
			        row.add(rs.getString("description"));
			        row.add(rs.getInt("wind"));
			        row.add(rs.getInt("water"));
			        row.add(rs.getInt("fire"));
			        data.add(row);
			    }

			    // initialize the column names
			    Vector columnNames = new Vector();
			    columnNames.add("Name");
			    columnNames.add("Description");
			    columnNames.add("Wind");
			    columnNames.add("Water");
			    columnNames.add("Fire");

			    // create the jtable with the data and the column names
			   table = new JTable(data, columnNames);
			   panel.add(table, BorderLayout.NORTH);
			   panel.revalidate();
			   panel.repaint();
			   tableIsShowed=true;
			   
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		    
		   
	}

	private static void connectDb() {
		MyConnection connection = new MyConnection();

		/*
		 * JOptionPane.showConfirmDialog(null,
		 * "LA URL DE LA BASE DE DATOS DEBE SER ÚNICAMENTE LA IP:Puerto/base de datos \nEJEMPLO:192.168.1.69:3306/Tienda"
		 * + "\n\tEJEMPLO : localhost:3306/Tienda", "ATENCIÓN",
		 * JOptionPane.DEFAULT_OPTION);
		 * 
		 * String url = JOptionPane.showInputDialog("Introduzca la url del servidor");
		 * String useR =
		 * JOptionPane.showInputDialog("Introduzca el usuario de la base de datos");
		 * String password =
		 * JOptionPane.showInputDialog("Introduzca la contraseña de la base de datos");
		 * 
		 * 
		 * setConnect = connection.getConexion(url, useR, password);
		 */
		setConnect = connection.getConexion("192.168.1.13:3306/acceso", "admin", "Erestont1988!");
		controller = new SuperWarriorControllerRepoImpl(setConnect);

	}

	private void prepareJTextPane(JTextPane text2, JPanel panel) {

		doc = text2.getStyledDocument();
		Font displayFont = new Font("Courier", Font.BOLD, 18);
		text2.setFont(displayFont);

		keyWord = new SimpleAttributeSet();
		StyleConstants.setForeground(keyWord, Color.WHITE);

		StyleConstants.setAlignment(keyWord, StyleConstants.ALIGN_CENTER);
		StyleConstants.setBold(keyWord, true);
		doc.setParagraphAttributes(0, doc.getLength(), keyWord, false);
		noWrapPanel = new JPanel(new BorderLayout());
		noWrapPanel.add(text2);
		JScrollPane scrollPane = new JScrollPane(noWrapPanel);
		
		
		
		scrollPane.setBounds(47, 139, 685, 172);
		panel.add(scrollPane);
		try {

			doc.insertString(doc.getLength(), "Welcome to SuperWarrior DB", keyWord);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void giveMeBorder(JComponent b) {
		 b.setBorder(new RoundedBorder(8));
	}
	
	private void loadTables(JComboBox<String> jcb) {
		jcb.removeAllItems();
		for (String table : controller.getTableNames()) {
			jcb.addItem(table);
		}
	}

	private static void displayShow(String msg,JTextPane t) {
		
		t.setText("");
		try {

			doc.insertString(doc.getLength(), msg, keyWord);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}