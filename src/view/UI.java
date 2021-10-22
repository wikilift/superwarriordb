package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
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

public class UI {

	private static Connection setConnect;
	private JFrame frame;
	private static SuperWarriorControllerRepoImpl controller;
	private StyledDocument doc;

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
		prepareJTextPane(textPane, panel);

		/***************************************/

		JButton btnNewSuperspecies = new JButton("New SuperSpecies");

		btnNewSuperspecies.setBounds(47, 12, 188, 25);
		panel.add(btnNewSuperspecies);

		/****************************************************/
		JButton btnDeleteSuperspecies = new JButton("Delete SuperSpecies");
		btnDeleteSuperspecies.setBounds(551, 12, 188, 25);
		panel.add(btnDeleteSuperspecies);

		/*****************************************************/
		JButton btnNewSuperwarrior = new JButton("New SuperWarrior");
		btnNewSuperwarrior.setBounds(47, 58, 188, 25);
		panel.add(btnNewSuperwarrior);

		/**************************************************************/
		JButton btnDeleteSuperwarrior = new JButton("Delete SuperWarrior");
		btnDeleteSuperwarrior.setBounds(47, 102, 188, 25);
		panel.add(btnDeleteSuperwarrior);

		/*******************************************************************/
		JButton btnDeleteSuperwarrior_1 = new JButton("Delete SuperWarrior");
		btnDeleteSuperwarrior_1.setBounds(551, 102, 188, 25);
		panel.add(btnDeleteSuperwarrior_1);

		/*********************************************************************/
		JButton btnNewSuperwarrior_1 = new JButton("New SuperWarrior");
		btnNewSuperwarrior_1.setBounds(551, 58, 188, 25);
		panel.add(btnNewSuperwarrior_1);

		/****************************************************************/
		JButton btnSearchWarrior = new JButton("Search Warrior");
		btnSearchWarrior.setBounds(306, 58, 141, 25);
		panel.add(btnSearchWarrior);

		/************************************************************/
		JButton btnListAll = new JButton("List All");
		btnListAll.setBounds(306, 102, 141, 25);
		panel.add(btnListAll);

		JLabel lblDaniel = new JLabel("Daniel Giménez González IFP");
		lblDaniel.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		lblDaniel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
		lblDaniel.setBounds(238, 330, 386, 40);
		panel.add(lblDaniel);
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

		StyledDocument doc = text2.getStyledDocument();
		Font displayFont = new Font("Courier", Font.BOLD, 40);
		text2.setFont(displayFont);

		SimpleAttributeSet keyWord = new SimpleAttributeSet();
		StyleConstants.setForeground(keyWord, Color.RED);

		StyleConstants.setAlignment(keyWord, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setBold(keyWord, true);
		doc.setParagraphAttributes(0, doc.getLength(), keyWord, false);
		JPanel noWrapPanel = new JPanel(new BorderLayout());
		noWrapPanel.add(text2);
		JScrollPane scrollPane = new JScrollPane(noWrapPanel);
		scrollPane.setBounds(47, 139, 685, 172);
		panel.add(scrollPane);
		try {

			doc.insertString(doc.getLength(), "0", keyWord);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void loadTables(JComboBox jcb) {
		jcb.removeAllItems();
		for (String table : controller.getTableNames()) {
			jcb.addItem(table);
		}
	}
}