package com.cours.Dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import com.cours.entities.Agence;
public interface IAgence extends Remote {
	public int addAgence(Agence ag) throws RemoteException;
	public List<Agence> listeAgence() throws RemoteException;
}
