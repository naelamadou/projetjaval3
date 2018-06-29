package com.cours.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {

	private Connection cnx;
	private PreparedStatement pst;
	private ResultSet rs;
	private int ok;
	//ouverture de la connexion a la base de donn�es
	public void getConnexion(){
		String url = "jdbc:mysql://localhost:3306/bp";
		String user = "naelamadou";
		String password = "Passer@_123";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnx = DriverManager.getConnection(url, user, password);
			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("non ok");
		}
	}
	//preparation des requetes sql
	public PreparedStatement initPrepare(String sql){
		try {
			getConnexion();
			if(sql.toLowerCase().startsWith("insert")){
				pst = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}else{
				pst = cnx.prepareStatement(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pst;
	}
	//execution des requetes de type MAJ (UPDATE - DELETE - INSERT)
	public int executeMAJ(){
		try {
			ok = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}
	//execution des requetes de type SELECT
	public ResultSet executeSELECT(){
		try {
			rs = pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	public PreparedStatement getPst() {
		return pst;
	}
	public void closeConnexion() {
		try {
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
