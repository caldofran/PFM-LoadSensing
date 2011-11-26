package com.uoc.loadsensing.beans;

public class NetworkBean {
	
	private String name = "";
	private String description = "";
	private int num_of_sensors = 0;
	private int latitude = 0;
	private int longitude = 1;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNum_of_sensors() {
		return num_of_sensors;
	}
	public void setNum_of_sensors(int num_of_sensors) {
		this.num_of_sensors = num_of_sensors;
	}
	public int getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public int getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	
	

}
