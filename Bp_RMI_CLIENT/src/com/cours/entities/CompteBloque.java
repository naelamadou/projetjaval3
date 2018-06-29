package com.cours.entities;

import java.io.Serializable;
/*frais d’ouverture obligatoire. Le délai de blocage doit faire
 * minimum un an. Ce type de
compte est rémunéré annuellement. A la date d’échéance
 le compte est fermé moyennant un montant sur
le dépôt initial. */
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
