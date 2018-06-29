package com.cours.Dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cours.entities.Agence;
public class AgenceDB extends UnicastRemoteObject implements IAgence {
	private DB db;
	private int ok;
	private ResultSet rs;
	 public AgenceDB() throws RemoteException {
		db= new DB();
	}
	@Override
	public List<Agence> listeAgence() {
		List<Agence> lag = new ArrayList<Agence>();
		String sql="SELECT * FROM agence";
		try {
			db.initPrepare(sql);
			rs=db.executeSELECT();
			while(rs.next()){
				Agence ag= new Agence();
				ag.setIdAg(rs.getInt(1));
				ag.setCode(rs.getString(2));
				ag.setAdresse(rs.getString(3));
				ag.setTel(rs.getString(4));
				lag.add(ag);
			}
			rs.close();
			db.closeConnexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lag;
	}
	@Override
	public int addAgence(Agence ag) {
		String sql = "INSERT INTO agence VALUES(null, ?, ?, ?)";
		try {
			db.initPrepare(sql);
			db.getPst().setString(1, ag.getCode());
			db.getPst().setString(2, ag.getAdresse());
			db.getPst().setString(3, ag.getTel());
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
