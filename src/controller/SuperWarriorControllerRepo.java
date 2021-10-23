package controller;

import java.sql.ResultSet;
import java.util.ArrayList;



public interface SuperWarriorControllerRepo {


	
	String createQuery(String nameOfTable);
	String insertQuery(String nameOfTable,String name,String description,int wind,int fire, int water);
	ResultSet selectQuery(String name);
	String resetQuery(String tableName,String name, int fire, int wind, int water);
	ResultSet searchQuery(String tableName,String nameOfWarrior);
	String givePowerQuery(String tableName,String name,int fire,int wind,int water);
	String deleteQuery(String tableName,String name);
	String dropTableQuery(String name);
	ArrayList<String> getTableNames();
	
}