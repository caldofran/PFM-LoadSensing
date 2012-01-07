package com.uoc.loadsensing.beans;

/**
 * 
 * UOC - Universitat Oberta de Catalunya
 * Proyecto Final Master Software Libre
 * Septiembre 2011
 * 
 * LoadSensing para WorldSensing
 * 
 * @authors
 * 		Ruben Mendez Puente
 * 		Jesus Sanchez-Migallon Perez
 * 
 * Licensed to the Apache Software Foundation (ASF) under one 
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 *       
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * 
 */


import java.util.ArrayList;
import java.util.Iterator;

public class NetworkBean {
	/**
	 * @uml.property  name="id"
	 */
	private int id = 0;
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
	 * @uml.property  name="id"
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * @param id
	 * @uml.property  name="id"
	 */
	public void setId(int _id) {
		this.id = _id;
	}
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
