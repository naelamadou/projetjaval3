package com.cours.Dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.cours.entities.Operation;

public interface IOperation extends Remote {
	public int addOperation(Operation op) throws RemoteException;

}
