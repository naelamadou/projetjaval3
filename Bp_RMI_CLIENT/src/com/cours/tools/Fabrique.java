package com.cours.tools;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.cours.Dao.IAgence;
import com.cours.Dao.IClient;
import com.cours.Dao.ICompte;
import com.cours.Dao.IEmploye;
import com.cours.Dao.IOperation;
import com.cours.Dao.IUser;

public class Fabrique {
 public static IUser udb;
 public static IClient cldb;
 public static ICompte cptdb;
 public static IAgence agdb;
 public static IEmploye empdb;
 public static IOperation operdb;
 public static void init(){
	 try {
		 Registry registry= LocateRegistry.getRegistry("localhost", 1246);
		 udb=(IUser)registry.lookup("user_db");
		 cldb=(IClient)registry.lookup("client_db");
		 cptdb=(ICompte)registry.lookup("compte_db");
		 agdb=(IAgence)registry.lookup("agence_db");
		 empdb=(IEmploye)registry.lookup("employe_db");
		 operdb=(IOperation)registry.lookup("operation_db");
	} catch (Exception e) {
		e.printStackTrace();
	}
 }


}
