package com.cours.entities;

import java.io.Serializable;

public class User implements Serializable {
	private int idU;
	private String matricule;
	private String loginU;
	private String nomComplet;
	private String adresse, telephone;
	private String passwordU;
	private String profil;
	private Agence agence;

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}

	public int getIdU() {
		return idU;
	}

	public void setIdU(int idU) {
		this.idU = idU;
	}

	public String getLoginU() {
		return loginU;
	}

	public void setLoginU(String loginU) {
		this.loginU = loginU;
	}

	public String getPasswordU() {
		return passwordU;
	}

	public void setPasswordU(String passwordU) {
		this.passwordU = passwordU;
	}

	public String getNomComplet() {
		return nomComplet;
	}

	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	@Override
	public String toString() {
		return matricule + " - " + nomComplet;
	}
}
