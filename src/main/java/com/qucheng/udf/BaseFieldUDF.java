package com.qucheng.udf;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.exec.UDFMethodResolver;
import org.json.JSONException;
import org.json.JSONObject;

public class BaseFieldUDF extends UDF {

	public String evaluate(String line, String key) throws JSONException {
		
		String[] log = line.split("\\|");
		
		if (log.length != 2 || StringUtils.isBlank(log[1].trim())) {
			return "Not OK";
			}	
		
			JSONObject json = new JSONObject(log[1].trim());
			
			String result = "";
			
			if ("st".equals(key)) {
				return log[0].trim();
			
			}else if ("et".equals(key)) {
				
				if (json.has("et")) {
					result = json.getString("et");
				}}else {
					
					JSONObject cm = json.getJSONObject("cm");
					if (cm.has(key)) {
						result = cm.getString(key);
					}
				}
			
			return result;
	}
	
	public static void main(String[] args) throws JSONException {
		
		String line = "111222333444555|{\"cm\":{\"ln\":\"-48.5\",\"sv\":\"V2.5.7\",\"os\":\"8.0.9\",\"g\":\"6F76AVD5@gmail.com\",\"mid\":\"0\",\"nw\":\"4G\",\"l\":\"pt\",\"vc\":\"3\",\"hw\":\"750*1134\",\"ar\":\"MX\",\"uid\":\"0\",\"t\":\"15996622286\",\"la\":\"-52.9\",\"md\":\"sumsung-18\",\"vn\":\"1.2.4\",\"ba\":\"Sumsung\",\"sr\":\"V\"}}";
		
		String result = new BaseFieldUDF().evaluate(line, "sv");
		
		System.out.println(result);
		
	}
	
	
}
