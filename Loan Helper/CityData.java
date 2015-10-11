package com.example2;

import java.util.HashMap;

public class CityData {
	static HashMap<String,Integer> cityData=new HashMap<String,Integer> ();
	/*
	 * It contains the city data where key is the name of the city 
	 * and value represents the job opportunities int he city.
	 */
	static{
		cityData.put("Houston",1);
		cityData.put("Indianapolis",3);
		cityData.put("Chicago",5);
		cityData.put("Ohio",2);
		cityData.put("New York",5);
		cityData.put("San Fransisco",5);
		cityData.put("Reston",3);
		cityData.put("Kansas City",2);
		cityData.put("Columbus",1);
		cityData.put("Detroit",1);
		cityData.put("Salt Lake City",3);
		cityData.put("Seattle",4);
		cityData.put("Cincinatti",1);
		cityData.put("Rome",1);
		cityData.put("Henderson",1);
		cityData.put("San Jose",5);
		cityData.put("California",5);
	}
}
