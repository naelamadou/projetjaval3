package com.cours.entities;

import java.io.Serializable;

public class CompteBloque extends Compte implements Serializable {
private Double fraisDouverture;

public Double getFraisDouverture() {
	return fraisDouverture;
}

public void setFraisDouverture(Double fraisDouverture) {
	this.fraisDouverture = fraisDouverture;
}

public CompteBloque() {
	super();
}





}
