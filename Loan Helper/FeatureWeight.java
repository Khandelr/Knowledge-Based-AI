package com.example2;

import java.util.HashMap;

public class FeatureWeight {
	static HashMap<String,Double> featureWeight=new HashMap<String,Double>();
	/*
	 * This block is used to assign default weights to the features. This weight is then adjusted based on the 
	 * input features provided.
	 */
	static{
		featureWeight.put("location", 0.25);
		featureWeight.put("unemployed", 0.05);
		featureWeight.put("salary", 0.05);
		featureWeight.put("course_performance",0.35 );
		featureWeight.put("family_income",0.15 );
		featureWeight.put("max_family_credit_score",0.15 );
		
		
	}
}
