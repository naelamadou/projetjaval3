package com.cours.entities;

import java.io.Serializable;

public class Compte implements Serializable {
	private String numCompte;
	private Agence agence;
	private String cleRib;
	private String type;
	private int id;
	private int solde;
	private User responsable;
	private Client client;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Compte() {

	}

	public Compte(String numCompte, Agence agence, String cleRib) {
		this.numCompte = numCompte;
		this.agence = agence;
		this.cleRib = cleRib;
	}

	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public String getCleRib() {
		return cleRib;
	}

	public void setCleRib(String cleRib) {
		this.cleRib = cleRib;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public User getResponsable() {
		return responsable;
	}

	public void setResponsable(User responsable) {
		this.responsable = responsable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSolde() {
		return solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}

}
