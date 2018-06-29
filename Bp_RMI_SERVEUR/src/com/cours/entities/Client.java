package com.cours.entities;

import java.io.Serializable;

public class Client implements Serializable {
	private int idCl;
	private String nomCli;
	private String prenomCli;
	private String adresseCli;
	private String emailCli;
	private String telCli;
	private Double salactCli;
	private String professionCli;
	private String infoEmployeurCli;
	private String numCNi;
	public int getIdCl() {
		return idCl;
	}
	public void setIdCl(int idCl) {
		this.idCl = idCl;
	}
	public String getNomCli() {
		return nomCli;
	}
	public void setNomCli(String nomCli) {
		this.nomCli = nomCli;
	}
	public String getPrenomCli() {
		return prenomCli;
	}
	public void setPrenomCli(String prenomCli) {
		this.prenomCli = prenomCli;
	}
	public String getAdresseCli() {
		return adresseCli;
	}
	public void setAdresseCli(String adresseCli) {
		this.adresseCli = adresseCli;
	}
	public String getEmailCli() {
		return emailCli;
	}
	public void setEmailCli(String emailCli) {
		this.emailCli = emailCli;
	}
	public String getTelCli() {
		return telCli;
	}
	public void setTelCli(String telCli) {
		this.telCli = telCli;
	}
	public Double getSalactCli() {
		return salactCli;
	}
	public void setSalactCli(Double salactCli) {
		this.salactCli = salactCli;
	}
	public String getProfessionCli() {
		return professionCli;
	}
	public void setProfessionCli(String professionCli) {
		this.professionCli = professionCli;
	}
	public String getInfoEmployeurCli() {
		return infoEmployeurCli;
	}
	public void setInfoEmployeurCli(String infoEmployeurCli) {
		this.infoEmployeurCli = infoEmployeurCli;
	}
	public String getNumCNi() {
		return numCNi;
	}
	public void setNumCNi(String numCNi) {
		this.numCNi = numCNi;
	}


}
