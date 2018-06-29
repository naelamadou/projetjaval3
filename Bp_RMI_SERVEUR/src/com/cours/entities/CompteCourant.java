package com.cours.entities;

import java.io.Serializable;

public class CompteCourant extends Compte implements Serializable {
	private Double agio;
	private String raisonSocialEmploy;
	private String adresseEmploy;
	private String numIdentifieEmploy;
	public CompteCourant(){
		super();
	}



	public Double getAgio() {
		return agio;
	}
	public void setAgio(Double agio) {
		this.agio = agio;
	}
	public String getRaisonSocialEmploy() {
		return raisonSocialEmploy;
	}
	public void setRaisonSocialEmploy(String raisonSocialEmploy) {
		this.raisonSocialEmploy = raisonSocialEmploy;
	}
	public String getAdresseEmploy() {
		return adresseEmploy;
	}
	public void setAdresseEmploy(String adresseEmploy) {
		this.adresseEmploy = adresseEmploy;
	}
	public String getNumIdentifieEmploy() {
		return numIdentifieEmploy;
	}
	public void setNumIdentifieEmploy(String numIdentifieEmploy) {
		this.numIdentifieEmploy = numIdentifieEmploy;
	}


}
