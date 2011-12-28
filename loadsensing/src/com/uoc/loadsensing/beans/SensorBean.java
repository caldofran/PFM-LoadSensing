package com.uoc.loadsensing.beans;


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
	
<<<<<<< HEAD
	private String networkId = "";
	/**
	 * @uml.property  name="latitude"
	 */
	private float latitude = 0;
	/**
	 * @uml.property  name="longitude"
	 */
	private float longitude = 1;
	
=======
	private int serial_number 	= 0;
	private int max_load		= 0;
	private int sensitivity		= 0;
	private int offset			= 0;
	private int alarm			= 0;
	
	
	public int getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(int serial_number) {
		this.serial_number = serial_number;
	}
	public int getMax_load() {
		return max_load;
	}
	public void setMax_load(int max_load) {
		this.max_load = max_load;
	}
	public int getSensitivity() {
		return sensitivity;
	}
	public void setSensitivity(int sensitivity) {
		this.sensitivity = sensitivity;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getAlarm() {
		return alarm;
	}
	public void setAlarm(int alarm) {
		this.alarm = alarm;
	}
>>>>>>> 7e5637bb546f7b90c4d25711e5975ebe86ea1aa6
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
