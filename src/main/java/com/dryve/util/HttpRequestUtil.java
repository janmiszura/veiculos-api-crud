package com.dryve.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestUtil {

	public static HttpResponseDTO makeGet(String url) throws Exception {
		
		URL obj = new URL(url);
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// optional default is GET
		con.setRequestMethod("GET");
		
		int responseCode = con.getResponseCode();
		
		InputStream inputStream = null;
		
		if( responseCode >= 400 ) { 
			inputStream = con.getErrorStream(); 
		} else { 
			inputStream = con.getInputStream(); 
		}
		
		String response = readInputStream(inputStream);
		
		return new HttpResponseDTO(responseCode, response);
	}

	private static String readInputStream(InputStream inputStream) throws IOException {
		StringBuffer response = new StringBuffer();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		return response.toString();
	}
	
//	public static void main(String[] args) throws Exception {
//		
//		String json = HttpRequestUtil.makeGet("https://6048bdf1fb5dcc0017968e3f.mockapi.io/api/v1/kbb/prices/1");
//		System.out.println(json);
//		
//	}
}