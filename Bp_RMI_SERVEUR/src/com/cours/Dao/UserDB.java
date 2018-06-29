package com.cours.Dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;

import com.cours.entities.User;

public class UserDB extends UnicastRemoteObject implements IUser {
	private DB db;
	public UserDB() throws RemoteException{
		db=new DB();
		}
	@Override
	public User getConnexion(String login,String password) {
		String sql="Select * FROM user where loginU = ? AND passwordU = ?";
		User u=null;
		try {
			//initialisation de la requete sql
			db.initPrepare(sql);
			//pasage de valeur a la requete
			db.getPst().setString(1, login);
			db.getPst().setString(2, password);
			//executer la requete
			ResultSet rs=db.executeSELECT();
			if(rs.next()){
				u=new User();
				u.setIdU(rs.getInt(1));
				u.setLoginU(rs.getString(2));
				u.setPasswordU(rs.getString(3));
				u.setProfil(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

}
