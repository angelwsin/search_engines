package org.Elasticsearch;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonTool {
	
	
	public static String  toJson(String name){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		StringBuilder bud  = new StringBuilder();
		try(InputStream is = classLoader.getResourceAsStream(name);
			BufferedReader  reader = new BufferedReader(new InputStreamReader(is));) {
			String content  = null;
			while((content=reader.readLine())!=null){
				bud.append(content);
			}
		} catch (Exception e) {
			
		}
		return bud.toString();
		   
		
	}
	
	public static void main(String[] args) {
		System.out.println(toJson("twitter.json"));
	}

}
