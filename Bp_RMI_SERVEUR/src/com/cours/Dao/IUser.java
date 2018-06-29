package com.cours.Dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.cours.entities.User;

public interface IUser extends Remote {
 public User getConnexion(String login,String password) throws RemoteException;
}
