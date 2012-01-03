package com.uoc.loadsensing.beans;

/**
 * UOC - Universitat Oberta de Catalunya
 * Proyecto Final Máster Software Libre
 * Septiembre 2011
 * 
 * LoadSensing para WorldSensing
 * 
 * @authors
 * 		Rubén Méndez Puente
 * 		Jesús Sánchez-Migallón Pérez
 * 
 */

public class Users {
	private String name;
	private String password;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String _name) {
		this.name = _name;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String _password) {
		this.password = _password;
	}

}
