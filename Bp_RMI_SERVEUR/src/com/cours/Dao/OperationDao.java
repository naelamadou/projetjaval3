package com.cours.Dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.ResultSet;

import com.cours.entities.Operation;

public class OperationDao extends UnicastRemoteObject implements IOperation {
	private DB db;
	private int ok;
	private ResultSet rs;
	 public OperationDao() throws RemoteException {
		db= new DB();
	}
	@Override
	public int addOperation(Operation op) throws RemoteException {
		String sql = "INSERT INTO operation VALUES(null, ?,?,?,?)";
		try {
			db.initPrepare(sql);
			db.getPst().setInt(1, op.getMontant());
			db.getPst().setDate(2, (Date)op.getDate());
			db.getPst().setString(3, op.getType());
			db.getPst().setInt(4, op.getCompte_id().getId());
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
