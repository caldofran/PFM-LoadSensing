package com.uoc.loadsensing;

/**
 * 
 * Clase encargada de representar un item de lista
 * 
 * @author JSMP
 *
 */

public class ItemList {
	    
	/** Nombre del Item */
    private String itemName;
    
    /** Descripcion del Item */
    private String itemDescription;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

}
