package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class MyConnection {

	Connection currentConnection = null;

	public MyConnection() {

	}

	public Connection getConexion(String url, String user, String password) {

		try {
			

			currentConnection = DriverManager.getConnection("jdbc:mysql://" + url, user, password);
			System.out.println("\tConexión con la bbdd establecida correctamente");

		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null,
					"IMPOSIBLE CONECTAR CON LA BBDD CON LOS DATOS INTRODUCIDOS\n"
					+ "EL PROGRAMA SE CERRARÁ\n"
					+ "PORFAVOR COMPRUEBE LOS DATOS DE ACCESO Y EJECUTE DE NUEVO",
					"ATENCIÓN", JOptionPane.DEFAULT_OPTION);
			System.exit(0);
		}

		return currentConnection;
	}

}