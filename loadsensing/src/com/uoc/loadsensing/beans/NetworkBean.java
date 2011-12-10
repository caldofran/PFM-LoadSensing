package com.uoc.loadsensing.beans;


public class NetworkBean {
	
	/**
	 * @uml.property  name="name"
	 */
	private String name = "";
	/**
	 * @uml.property  name="description"
	 */
	private String description = "";
	/**
	 * @uml.property  name="num_of_sensors"
	 */
	private int num_of_sensors = 0;
	/**
	 * @uml.property  name="latitude"
	 */
	private int latitude = 0;
	/**
	 * @uml.property  name="longitude"
	 */
	private int longitude = 1;
	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return
	 * @uml.property  name="description"
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description
	 * @uml.property  name="description"
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return
	 * @uml.property  name="num_of_sensors"
	 */
	public int getNum_of_sensors() {
		return num_of_sensors;
	}
	/**
	 * @param num_of_sensors
	 * @uml.property  name="num_of_sensors"
	 */
	public void setNum_of_sensors(int num_of_sensors) {
		this.num_of_sensors = num_of_sensors;
	}
	/**
	 * @return
	 * @uml.property  name="latitude"
	 */
	public int getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude
	 * @uml.property  name="latitude"
	 */
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return
	 * @uml.property  name="longitude"
	 */
	public int getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude
	 * @uml.property  name="longitude"
	 */
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	
	

}
