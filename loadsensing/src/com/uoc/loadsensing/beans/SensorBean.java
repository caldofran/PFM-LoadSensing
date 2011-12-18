package com.uoc.loadsensing.beans;

/**
 * @author  armisael
 */
public class SensorBean {

	/**
	 * @uml.property  name="id"
	 */
	private int id = 0;
	/**
	 * @uml.property  name="sensor"
	 */
	private String sensor = "";
	/**
	 * @uml.property  name="type"
	 */
	private String type = "";
	/**
	 * @uml.property  name="description"
	 */
	private String description = "";
	
	private String networkId = "";
	/**
	 * @uml.property  name="latitude"
	 */
	private float latitude = 0;
	/**
	 * @uml.property  name="longitude"
	 */
	private float longitude = 1;
	
	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id
	 * @uml.property  name="id"
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return
	 * @uml.property  name="sensor"
	 */
	public String getSensor() {
		return sensor;
	}
	/**
	 * @param sensor
	 * @uml.property  name="sensor"
	 */
	public void setSensor(String sensor) {
		this.sensor = sensor;
	}
	/**
	 * @return
	 * @uml.property  name="type"
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type
	 * @uml.property  name="type"
	 */
	public void setType(String type) {
		this.type = type;
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
	
	public String getNetworkId() {
		return this.networkId;
	}
	
	public void setNetworkId(String _networkId) {
		this.networkId = _networkId;
	}
	
	public float getLatitude() {
		return latitude;
	}
	/**
	 * @param f
	 * @uml.property  name="latitude"
	 */
	public void setLatitude(float f) {
		this.latitude = f;
	}
	/**
	 * @return
	 * @uml.property  name="longitude"
	 */
	public float getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude
	 * @uml.property  name="longitude"
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
}
