package com.cours.entities;

import java.io.Serializable;

public class Agence implements Serializable {
	private int idAg;
	private String code;
	private String adresse;
	private String tel;
	public int getIdAg() {
		return idAg;
	}
	public void setIdAg(int idAg) {
		this.idAg = idAg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
    @Override
    public String toString() {
    	return getCode()+" - "+adresse;
    }


}
