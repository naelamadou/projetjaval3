package com.cours.entities;

import java.io.Serializable;
import java.util.Date;

public class Operation implements Serializable {
	private int idOp;
	private int montant;
	private String type;
	private Date date;
	private Compte compte_id;
	public int getIdOp() {
		return idOp;
	}
	public void setIdOp(int idOp) {
		this.idOp = idOp;
	}
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Compte getCompte_id() {
		return compte_id;
	}
	public void setCompte_id(Compte compte_id) {
		this.compte_id = compte_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}


}
