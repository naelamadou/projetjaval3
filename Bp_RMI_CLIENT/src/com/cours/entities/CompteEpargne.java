package com.cours.entities;

import java.io.Serializable;
/* Epargne simple et xeewel : frais d�ouverture obligatoire. Ce type de compte est r�mun�r�
annuellement. */
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
