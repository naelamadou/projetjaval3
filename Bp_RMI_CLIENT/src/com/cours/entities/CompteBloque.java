package com.cours.entities;

import java.io.Serializable;
/*frais d�ouverture obligatoire. Le d�lai de blocage doit faire
 * minimum un an. Ce type de
compte est r�mun�r� annuellement. A la date d��ch�ance
 le compte est ferm� moyennant un montant sur
le d�p�t initial. */
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
