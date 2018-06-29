package com.cours.Dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.cours.entities.Employe;
public interface IEmploye extends Remote {
	public int addEmploye(Employe emp) throws RemoteException;

}
