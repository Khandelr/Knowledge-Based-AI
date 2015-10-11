package com.example2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.example.ParseFile;

public class ReadFromFile {
	/*
	 * This function is used to read the cases from the case base and store the same in 
	 * the hashmap
	 */
	public static void readFile(String filename){
		BufferedReader br = null;
		 
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(filename));
			br.readLine();
			while ((sCurrentLine = br.readLine()) != null) {
				String arr[]=sCurrentLine.split(",");
				HashMap<String,String> data=new HashMap<String,String>();
				data.put("enroll_school",arr[1] );
				data.put("course", arr[2]);
				data.put("location", arr[3]);
				data.put("unemployed", arr[4]);
				data.put("salary", arr[5]);
				data.put("credit_score", arr[6]);
				data.put("course_performance", arr[7]);
				data.put("no_payment_due", arr[8]);
				data.put("family_income", arr[9]);
				data.put("max_family_credit_score", arr[10]);
				data.put("filled_for_bankrupcy", arr[11]);
				ParseData.meanSalary+=Integer.parseInt(arr[9]);
				ParseData.meanStudentSalary+=Integer.parseInt(arr[5]);
				ParseData.studentData.put(arr[0],data);
			
			}
			System.out.println();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}  

	}
}
