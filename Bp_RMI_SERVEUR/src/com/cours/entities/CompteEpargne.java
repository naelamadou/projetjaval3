package com.cours.entities;

import java.io.Serializable;

public class CompteEpargne extends Compte implements Serializable {
private Double fraisOuverture;
public CompteEpargne()
{
	super();
}

public Double getFraisOuverture() {
	return fraisOuverture;
}
public void setFraisOuverture(Double fraisOuverture) {
	this.fraisOuverture = fraisOuverture;
}


}
