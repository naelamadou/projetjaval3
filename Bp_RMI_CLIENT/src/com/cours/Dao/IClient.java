 package com.cours.Dao;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.cours.entities.Client;


public interface IClient extends Remote{
	public int addClient(Client cli) throws RemoteException;
	public int delete(int idCli) throws RemoteException;
	public int update(Client cli) throws RemoteException;
	public List<Client> liste() throws RemoteException;
	public List<Client> getServiceByServeur(Client cli) throws RemoteException;
	public Client getClient( String numCni)throws RemoteException;
	public Client getClient( int numCni)throws RemoteException;
}