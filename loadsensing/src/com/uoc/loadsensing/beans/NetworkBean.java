package com.uoc.loadsensing.beans;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author  armisael
 */
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
	private float latitude = 0;
	/**
	 * @uml.property  name="longitude"
	 */
	private float longitude = 1;
	
	private ArrayList<String> arrayPathsToImages = new ArrayList<String>();
	
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
	
	public ArrayList<String> getArrayPathsToImages() {
		return arrayPathsToImages;
	}
	
	public void setArrayPathsToImages(ArrayList<String> array) {
		this.arrayPathsToImages = array;
	}
	
	public void addStringToArrayPathsToImages(String path) {
		this.arrayPathsToImages.add(path);
	}
	
	public void printImagesPaths() {
		Iterator iteratorItem = this.arrayPathsToImages.iterator();
		while(iteratorItem.hasNext()) {
			System.out.println("Path to Image: " + iteratorItem.next());
		}
	}

}
