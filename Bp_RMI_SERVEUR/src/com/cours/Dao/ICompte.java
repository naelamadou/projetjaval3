package com.cours.Dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import com.cours.entities.Compte;
public interface ICompte extends Remote {
	public int addCompte(Compte cpt) throws RemoteException;
	public int delete(int idcpt)throws RemoteException;
	public int update(Compte cpt)throws RemoteException;
	public List<Compte> liste()throws RemoteException;
	public List<Compte> getServiceByServeur(Compte cpt)throws RemoteException;
	public String generateNumero() throws RemoteException;
	public Compte getCompte(String numcompte) throws RemoteException;
	public int UpdateSoldeDepot(int soldeRetrait, String numCompte) throws RemoteException;
	public int RetraitPossible(int soldeRetrait, String numCompte) throws RemoteException;
}
