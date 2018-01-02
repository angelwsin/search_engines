package org.Elasticsearch;

import java.io.InputStream;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

/**
 * https://www.elastic.co/guide/en/elasticsearch/reference/current/removal-of-types.html
 * 
 * 
 * 主要的几个概念https://www.elastic.co/guide/en/elasticsearch/reference/current/_basic_concepts.html
 * 1.Index
 * 2.Type mapping types 
 * 3.Document
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	try(RestClient restClient = RestClient.builder(
    	        new HttpHost("localhost", 9200, "http"),
    	        new HttpHost("localhost", 9201, "http")).build();) {
    		Header[] headers = new Header[]{new BasicHeader("Content-Type", "application/json")};
    		listAllIndicesedit(restClient,"/_cat/indices?v",headers);
    		//mappingsType(restClient,"twitter",headers,JsonTool.toJson("twitter.json"));
    		//mappingsType(restClient,"twitter/doc/user-kimchy",headers,JsonTool.toJson("kimchy.json"));
    		//mappingsType(restClient,"twitter/doc/tweet-1",headers,JsonTool.toJson("tweet.json"));
    		search(restClient,"twitter/_search",headers,JsonTool.toJson("_search.json"));
    		//delete(restClient, "/twitter", headers);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	
    }
    
    //List All Indicesedit
    
    public static void  listAllIndicesedit(RestClient restClient,String endpoint,Header[] headers)throws Exception{
    	Response resp = 	restClient.performRequest("GET",endpoint, headers);
		InputStream is = resp.getEntity().getContent();
		byte[] b= new byte[is.available()];
		is.read(b);
		System.out.println(new String(b));
    }
    
    public static void  search(RestClient restClient,String endpoint,Header[] headers,String content)throws Exception{
    	StringEntity entity = new StringEntity(content);
    	Response resp = 	restClient.performRequest("GET",endpoint,new HashMap<>(),entity, headers);
		InputStream is = resp.getEntity().getContent();
		byte[] b= new byte[is.available()];
		is.read(b);
		System.out.println(new String(b));
    }
    
    //each document has been stored in a single index and assigned a single mapping type. 
    //A mapping type was used to represent the type of document or entity being indexed,
    //https://www.elastic.co/guide/en/elasticsearch/reference/current/removal-of-types.html
    public static void document(){
    	
    }
    //在Elasticsearch 6.0.0或更高版本中创建的索引可能只包含一个映射类型。
    //使用多种映射类型在5.x中创建的索引将继续像以前一样在Elasticsearch 6.x中运行。 映射类型将在Elasticsearch 7.0.0中完全删除。
    //https://www.elastic.co/guide/en/elasticsearch/reference/current/removal-of-types.html
    public static void mappingsType(RestClient restClient,String endpoint,Header[] headers,String content)throws Exception{
    	StringEntity entity = new StringEntity(content);
    	Response resp = 	restClient.performRequest("PUT", endpoint,new HashMap<>(),entity ,headers);
    	System.out.println(resp);
    	InputStream is = resp.getEntity().getContent();
		byte[] b= new byte[is.available()];
		is.read(b);
		System.out.println(new String(b));
    	
    }
    
    //删除索引
    public static void delete(RestClient restClient,String endpoint,Header[] headers)throws Exception{
    	Response resp = 	restClient.performRequest("DELETE", endpoint,headers);
    	System.out.println(resp);
    	InputStream is = resp.getEntity().getContent();
		byte[] b= new byte[is.available()];
		is.read(b);
		System.out.println(new String(b));
    }
    
}
