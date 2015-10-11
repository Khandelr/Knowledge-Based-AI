package com.example2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

public class ParseData {
	static HashMap<String, HashMap<String, String>> studentData = new HashMap<String, HashMap<String, String>>();
	static TreeMap<Double, ArrayList<String>> result = new TreeMap<Double, ArrayList<String>>();
	static HashMap<String, String> testData = new HashMap<String, String>();
	static HashMap<String, Double> adjustedFeatureWeight = new HashMap<String, Double>();
	static int meanSalary = 0;   // it stores the average of the salary
	static int meanStudentSalary = 0; // it stores the average of the family income
	/*
	 * This static block is used to initialize the test data.
	 */
	static {
		testData.put("enroll_school", "USC");
		testData.put("course", "Biology");
		testData.put("location", "Reston");
		testData.put("unemployed", "0");
		testData.put("salary", "8000");
		testData.put("credit_score", "1");
		testData.put("course_performance", "3");
		testData.put("no_payment_due", "1");
		testData.put("family_income", "180000");
		testData.put("max_family_credit_score", "2");
	}
/*This function is used to adjust the feature weight based on the 
 * proportion of the other features in the set.
 *
 */
	public static void adjustFeatureWeight() {
		double wt = 0;
		for (String key : FeatureWeight.featureWeight.keySet()) {
			if (testData.get(key) == null) {
				wt += FeatureWeight.featureWeight.get(key);
			} else {
				adjustedFeatureWeight.put(key,
						FeatureWeight.featureWeight.get(key));
			}
		}
		// System.out.println(wt);
		// System.out.println(adjustedFeatureWeight);
		for (String key : FeatureWeight.featureWeight.keySet()) {
			if (adjustedFeatureWeight.get(key) != null) {
				double scr = ((adjustedFeatureWeight.get(key) / 1) * wt)
						+ adjustedFeatureWeight.get(key);
				adjustedFeatureWeight.put(key, scr);
			}
		}
		// System.out.println(FeatureWeight.featureWeight);
		// System.out.println(adjustedFeatureWeight);

	}
	/*This function is used to calculate the similarity score of the test data 
	 * with all the cases in the case base.
	 * This is then stored in the HashMap<String, ArrayLis<>>
	 * where key represents the similarity score and value is the list of student id
	 */
	public static void compareResult() {
		System.out.println(adjustedFeatureWeight);

		if (Integer.parseInt(testData.get("no_payment_due")) == 0) {

			System.out.println("previous loan due---");
			return;
		}
		
		for (String key : studentData.keySet()) {
			System.out.println(key);
			HashMap<String, String> temp = studentData.get(key);

			double scr = 0;
			for (String featureKey : temp.keySet()) {
				if ((!featureKey.equalsIgnoreCase("enroll_school"))
						&& (!featureKey.equalsIgnoreCase("course"))
						&& !featureKey.equalsIgnoreCase("no_payment_due")
						&& !featureKey.equalsIgnoreCase("credit_score")
						&& !featureKey.equalsIgnoreCase("filled_for_bankrupcy")) {
					if (featureKey.equalsIgnoreCase("location")) {
						double f1 = CityData.cityData.get(testData
								.get(featureKey));
						double f2 = CityData.cityData.get(temp.get(featureKey));
						scr += adjustedFeatureWeight.get(featureKey)
								* Math.pow(f1 - f2, 2);
					} else if (featureKey.equalsIgnoreCase("salary")) {
						double f1 = 0;
						double f2 = 0;
						if (Double.parseDouble(testData.get(featureKey)) > meanStudentSalary) {
							f1 = 1;
						} else {
							f1 = 0;
						}
						if (Double.parseDouble(temp.get(featureKey)) > meanStudentSalary) {
							f2 = 1;
						} else {
							f2 = 0;
						}
						double sal = (f1 - f2) / 1000;
						scr += adjustedFeatureWeight.get(featureKey)
								* Math.pow(sal, 2);
					} else if (featureKey.equalsIgnoreCase("family_income")) {
						double f1 = 0;
						double f2 = 0;
						if (Double.parseDouble(testData.get(featureKey)) > meanSalary) {
							f1 = 1;
						} else {
							f1 = 0;
						}
						if (Double.parseDouble(temp.get(featureKey)) > meanSalary) {
							f2 = 1;
						} else {
							f2 = 0;
						}
						double sal = (f1 - f2) / 10000;
						scr += adjustedFeatureWeight.get(featureKey)
								* Math.pow(sal, 2);
					}

					else {
						double f1 = Double
								.parseDouble(testData.get(featureKey));
						double f2 = Double.parseDouble(temp.get(featureKey));
						scr += adjustedFeatureWeight.get(featureKey)
								* Math.pow(f1 - f2, 2);

					}
				}
			}
			scr = Math.sqrt(scr);
			if (result.get(scr) == null) {
				ArrayList<String> t = new ArrayList<String>();
				t.add(key);
				result.put(scr, t);
			} else {
				ArrayList<String> t = result.get(scr);
				t.add(key);
			}
		}

	}
	/*
	 * This function prints the results of the HashMap<String, ArrayLis<>> 
	 * where key represents the similarity score and value is the list of student id
	 */
	public static void printResult() {
		for (Double key : result.keySet()) {
			System.out.println(key + " : " + result.get(key));
		}
	}

	public static void main(String[] args) {
		ReadFromFile
				.readFile("C:/Users/Ramakant Khandel/Desktop/Course/knbai-dataset/dataset/knbai.csv");
		meanSalary = meanSalary / studentData.size();
		meanStudentSalary = meanStudentSalary / studentData.size();
		adjustFeatureWeight();
		compareResult();
		printResult();
	}

}
