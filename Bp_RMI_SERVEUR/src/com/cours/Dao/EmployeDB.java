package com.cours.Dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;

import com.cours.entities.Employe;

public class EmployeDB extends UnicastRemoteObject implements IEmploye {
	private DB db;
	private int ok;
	private ResultSet rs;
	public EmployeDB() throws RemoteException {
		db= new DB();
	}
	Employe emp=new Employe();

	@Override
	public int addEmploye(Employe emp) throws RemoteException {

		String sql = "INSERT INTO employe VALUES(null, ?, ?, ?,?)";
		try {
			db.initPrepare(sql);
			db.getPst().setString(1, emp.getNomComplet());
			db.getPst().setString(2, emp.getAdresse());
			db.getPst().setString(3, emp.getTel());
			db.getPst().setInt(4, emp.getAgence().getIdAg());
			ok = db.executeMAJ();
			//Recuperation de l'ID AUTO_INCREMENT
			rs = db.getPst().getGeneratedKeys();
			while(rs.next()){
				ok = rs.getInt(1);
			}
			db.closeConnexion();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

}
