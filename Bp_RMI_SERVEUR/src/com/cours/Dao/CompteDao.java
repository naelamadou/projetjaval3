package com.cours.Dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cours.entities.Client;
import com.cours.entities.Compte;
import com.cours.entities.CompteBloque;
import com.cours.entities.CompteCourant;
import com.cours.entities.CompteEpargne;

import javafx.collections.ObservableList;

public class CompteDao extends UnicastRemoteObject implements ICompte {
	private DB db;
	private int ok;
	private ResultSet rs;

	public CompteDao() throws RemoteException {
		db = new DB();
	}

	@Override
	public String generateNumero()
	{
		try {
			String sql = "SELECT COUNT(*) FROM compte";
			db.initPrepare(sql);
			rs = db.executeSELECT();
			if (rs.next()) {
				String num = "COMPTE " + (rs.getInt(1) + 1);
				return num;
			}

		} catch (Exception e) {
			System.err.println("Erreur Dans genererNumero Commande :" + e);
		}
		return null;
	}

	@Override
	public int addCompte(Compte cpt) {
		int ok = 0;
		try {
			if (cpt.getType().equals("compte courant")) {
				String sql = "INSERT INTO compte(idcpte,numCompte,agence,cleRib,type,agio,raisonSocialEmploy,adresseEmploy,numIdentifieEmploy,idClient) "
						+ "VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				db.initPrepare(sql);
				db.getPst().setString(1, cpt.getNumCompte());
				db.getPst().setInt(2, cpt.getAgence().getIdAg());
				db.getPst().setString(3, cpt.getCleRib());
				db.getPst().setString(4, cpt.getType());
				db.getPst().setDouble(5, ((CompteCourant) cpt).getAgio());
				db.getPst().setString(6, ((CompteCourant) cpt).getRaisonSocialEmploy());
				db.getPst().setString(7, ((CompteCourant) cpt).getAdresseEmploy());
				db.getPst().setString(8, ((CompteCourant) cpt).getNumIdentifieEmploy());
				db.getPst().setInt(9, cpt.getClient().getIdCl());
			} else {
				String sql = "INSERT INTO compte(idcpte,numCompte,agence,cleRib,type,fraisDouverture,idClient) VALUES(null, ?, ?, ?, ?, ?, ?)";
				db.initPrepare(sql);
				db.getPst().setString(1, cpt.getNumCompte());
				db.getPst().setInt(2, cpt.getAgence().getIdAg());
				db.getPst().setString(3, cpt.getCleRib());
				db.getPst().setString(4, cpt.getType());
				if (cpt.getType().equals("compte bloquer")) {
					db.getPst().setDouble(5, ((CompteBloque) cpt).getFraisDouverture());
				}
				else
				{
					db.getPst().setDouble(5, ((CompteEpargne) cpt).getFraisOuverture());
				}
				db.getPst().setInt(6, cpt.getClient().getIdCl());
			}
			ok = db.executeMAJ();
			// Recuperation de l'ID AUTO_INCREMENT
			rs = db.getPst().getGeneratedKeys();

			db.closeConnexion();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public int delete(int idcpt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Compte cpt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Compte> liste() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compte> getServiceByServeur(Compte cpt) {
		// TODO Auto-generated method stub
		return null;
	}

	private IClient clDAO = new ClientDao();

	@Override
	public Compte getCompte(String numcompte) throws RemoteException {
		 Compte c=null;
		 try {
			String sql="select *from compte where numCompte=?";

			db.initPrepare(sql);
			db.getPst().setString(1, numcompte);
			rs=db.executeSELECT();
			if(rs.next()){
				c =new Compte();
				c.setId(rs.getInt(1));
				c.setClient(clDAO.getClient(rs.getInt(12)));
				c.setSolde(rs.getInt(7));
				c.setType(rs.getString(5));
			}
			rs.close();
			db.closeConnexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return c;
	}

	@Override
	public int UpdateSoldeDepot(int soldeRetrait, String numCompte) throws RemoteException {
		int n=0;
		try {

		String sql="UPDATE compte set solde= solde+? where numCompte=? ";
			db.initPrepare(sql);
				db.getPst().setInt(1, soldeRetrait);
				db.getPst().setString(2, numCompte);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			ok=db.executeMAJ();

			db.closeConnexion();

		return n;

	}

	@Override
	public int RetraitPossible(int soldeRetrait, String numCompte) throws RemoteException {
		int n=0;
		try {

		String sql="UPDATE compte set solde= solde-? where numCompte=? ";
			db.initPrepare(sql);
				db.getPst().setInt(1, soldeRetrait);
				db.getPst().setString(2, numCompte);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			ok=db.executeMAJ();

			db.closeConnexion();

		return n;
	}

}
