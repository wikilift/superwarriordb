package controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import com.mysql.jdbc.DatabaseMetaData;

import data.CommandsSQL;

public class SuperWarriorControllerRepoImpl implements SuperWarriorControllerRepo{

	private Connection con;
	private  PreparedStatement sendData;

	public SuperWarriorControllerRepoImpl(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public String createQuery(String nameOfTable) {
		
		try {
			Statement createQuery=con.createStatement();
			createQuery.execute(MessageFormat.format(CommandsSQL.CREATE_QUERY.toString(), nameOfTable));
			createQuery.close();
			return "The new superspecie was created correctly";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "Error has ocurred";
			
		}
		
		
	}

	@Override
	public boolean insertQuery(String nameOfTable,String name, String description, int wind, int fire, int water) {
		int alteredRows=0;
		try {
			if(wind>5)wind=5;
			else if(wind <0)wind=0;
			if(fire>5)fire=5;
			else if(fire <0)fire=0;
			if(water>5)water=5;
			else if(water <0)water=0;
			Statement createQuery=con.createStatement();
			createQuery.execute(MessageFormat.format(CommandsSQL.INSERT_QUERY.toString(),nameOfTable, name,description,wind,water,fire));
			alteredRows=createQuery.getResultSetConcurrency();
			createQuery.close();
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return (alteredRows>0)?true:false;
	}

	@Override
	public ResultSet selectQuery(String name) {
		
		try {
			Statement createQuery=con.createStatement();
			ResultSet rs =createQuery.executeQuery(MessageFormat.format(CommandsSQL.SELECT_QUERY.toString(),name));
			return rs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String resetQuery(String tableName,String name, int fire, int wind, int water) {
		int  alteredRows=0;
		try {
			
			Statement createQuery=con.createStatement();
			createQuery.execute(MessageFormat.format(CommandsSQL.RESET_QUERY.toString(),tableName,name));
			alteredRows=createQuery.getResultSetConcurrency();
			createQuery.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return (alteredRows>0)?"The warrior powders was reseted correctly":"Error has ocurred";
	}

	@Override
	public String givePowerQuery(String tableName,String name, int fire, int wind, int water) {
		int  alteredRows=0;
		try {
			if(wind>5)wind=5;
			else if(wind <0)wind=0;
			if(fire>5)fire=5;
			else if(fire <0)fire=0;
			if(water>5)water=5;
			else if(water <0)water=0;
			Statement createQuery=con.createStatement();
			createQuery.execute(MessageFormat.format(CommandsSQL.GIVE_POWER_QUERY.toString(),tableName,fire,wind,water,name));
			alteredRows=createQuery.getResultSetConcurrency();
			createQuery.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return (alteredRows>0)?"The warrior powders was changed correctly":"Error has ocurred";
	}

	@Override
	public String deleteQuery(String tableName,String name) {
		int  alteredRows=0;
		try {
			Statement createQuery=con.createStatement();
			createQuery.execute(MessageFormat.format(CommandsSQL.DELETE_QUERY.toString(),tableName,name));
			alteredRows=createQuery.getResultSetConcurrency();
			createQuery.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return (alteredRows>0)?"The warrior was removed correctly":"Error has ocurred";
	}

	@Override
	public String dropTableQuery(String name) {
		
		try {
			Statement createQuery=con.createStatement();
			createQuery.execute(MessageFormat.format(CommandsSQL.DROP_TABLE_QUERY.toString(),name));
			createQuery.close();
			return "The SuperSpecies was removed correctly";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error has ocurred";
		}
		
	}

	@Override
	public ResultSet searchQuery(String tableName, String nameOfWarrior) {
		// TODO Auto-generated method stub
		try {
			Statement createQuery=con.createStatement();
			ResultSet rs =createQuery.executeQuery(MessageFormat.format(CommandsSQL.SEARCH_QUERY.toString(),tableName,nameOfWarrior));
			return rs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public ArrayList<String> getTableNames() {
		 ArrayList<String> tables = new ArrayList<String>();
		try {
	        DatabaseMetaData dbmd = (DatabaseMetaData) con.getMetaData();
	      
	        ResultSet rs = dbmd.getTables(null, null, "%", null);
	        while (rs.next()) {
	        	tables.add(rs.getString("TABLE_NAME"));
	           
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return tables;
	}
	
}
	
	