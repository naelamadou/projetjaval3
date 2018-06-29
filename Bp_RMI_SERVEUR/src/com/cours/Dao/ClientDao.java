package com.cours.Dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cours.entities.Client;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientDao extends UnicastRemoteObject implements IClient {
	private DB db;
	private int ok;
	private ResultSet rs;
	 public ClientDao() throws RemoteException {
		db= new DB();
	}
	@Override
	public int addClient(Client cli) {
		String sql = "INSERT INTO client VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			db.initPrepare(sql);
			db.getPst().setString(1, cli.getNomCli());
			db.getPst().setString(2, cli.getPrenomCli());
			db.getPst().setString(3, cli.getAdresseCli());
			db.getPst().setString(4, cli.getEmailCli());
			db.getPst().setString(5, cli.getTelCli());
			db.getPst().setDouble(6, cli.getSalactCli());
			db.getPst().setString(7, cli.getProfessionCli() );
			db.getPst().setString(8, cli.getInfoEmployeurCli() );
			db.getPst().setString(9, cli.getNumCNi());
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

	@Override
	public int delete(int idCli) {
		String sql="DELETE FROM Client WHERE idCli=?";
		try {
			db.initPrepare(sql);
			db.getPst().setInt(1, idCli);
			ok=db.executeMAJ();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public int update(Client cli) {
		String sql="UPDATE client SET nomCli= ?,prenomCli= ?,adresseCli=?,emailCli=?,telCli=?,salactCli=?,professionCli=?,"
				+ "infoEmployeurCli=?,numCNi=? where idCli= ?";
		try {
			db.initPrepare(sql);
			db.getPst().setString(1, cli.getNomCli());
			db.getPst().setString(2, cli.getPrenomCli());
			db.getPst().setString(3, cli.getAdresseCli());
			db.getPst().setString(4, cli.getEmailCli());
			db.getPst().setString(5, cli.getTelCli());
			db.getPst().setDouble(6, cli.getSalactCli());
			db.getPst().setString(7, cli.getProfessionCli() );
			db.getPst().setString(8, cli.getInfoEmployeurCli() );
			db.getPst().setString(9, cli.getNumCNi());
			ok=db.executeMAJ();
			//recuperation de l'id AUTO_INCREMENT
			rs=db.getPst().getGeneratedKeys();
			while(rs.next()){
				ok=rs.getInt(1);
			}
			db.closeConnexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public List<Client> liste() {
		List<Client> lc = new ArrayList<Client>();
		String sql="SELECT * FROM client";
		try {
			db.initPrepare(sql);
			rs=db.executeSELECT();
			while(rs.next()){
				Client c =new Client();
				c.setIdCl(rs.getInt(1));
				c.setNomCli(rs.getString(2));
				c.setPrenomCli(rs.getString(3));
				c.setAdresseCli(rs.getString(4));
				c.setEmailCli(rs.getString(5));
				c.setTelCli(rs.getString(6));
				c.setSalactCli(rs.getDouble(7));
				c.setProfessionCli(rs.getString(8));
				c.setInfoEmployeurCli(rs.getString(9));
				c.setNumCNi(rs.getString(10));
				 lc.add(c);
			}
			rs.close();
			db.closeConnexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lc;
	}

	@Override
	public List<Client> getServiceByServeur(Client cli) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Client getClient(String numCni){
		 Client c=null;
		 try {
			String sql="select *from client where numCNi=?";

			db.initPrepare(sql);
			db.getPst().setString(1, numCni);
			rs=db.executeSELECT();
			if(rs.next()){
				c =new Client();
				c.setIdCl(rs.getInt(1));
				c.setNomCli(rs.getString(2));
				c.setPrenomCli(rs.getString(3));
				c.setAdresseCli(rs.getString(4));
				c.setEmailCli(rs.getString(5));
				c.setTelCli(rs.getString(6));
				c.setSalactCli(rs.getDouble(7));
				c.setProfessionCli(rs.getString(8));
				c.setInfoEmployeurCli(rs.getString(9));
				c.setNumCNi(rs.getString(10));

			}
			rs.close();
			db.closeConnexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return c;

	}
	@Override
	public Client getClient(int numCni) throws RemoteException{
		 Client c=null;
		 try {
			String sql="select *from client where idCli=?";

			db.initPrepare(sql);
			db.getPst().setInt(1, numCni);
			rs=db.executeSELECT();
			if(rs.next()){
				c =new Client();
				c.setIdCl(rs.getInt(1));
				c.setNomCli(rs.getString(2));
				c.setPrenomCli(rs.getString(3));
				c.setAdresseCli(rs.getString(4));
				c.setEmailCli(rs.getString(5));
				c.setTelCli(rs.getString(6));
				c.setSalactCli(rs.getDouble(7));
				c.setProfessionCli(rs.getString(8));
				c.setInfoEmployeurCli(rs.getString(9));
				c.setNumCNi(rs.getString(10));

			}
			rs.close();
			db.closeConnexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return c;

	}

}
