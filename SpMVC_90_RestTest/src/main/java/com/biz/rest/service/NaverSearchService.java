package com.biz.rest.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.rest.config.NaverConfig;
import com.biz.rest.domain.NaverBooksVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

@Service
public class NaverSearchService {

	private final String naver_book_url = "https://openapi.naver.com/v1/search/book.json";
	
	public List<NaverBooksVO> getBooksList(String search) {

		String responseString = this.getSearchBooks(search);
		
		JsonElement jsonElement = JsonParser.parseString(responseString);
		
		JsonArray jItems = (JsonArray) jsonElement.getAsJsonObject().get("items");
		
		Gson gson = new Gson();
		
		TypeToken<List<NaverBooksVO>> token = new TypeToken<List<NaverBooksVO>>() {
			
		};
		List<NaverBooksVO> searchBookList = gson.fromJson(jItems, token.getType());
		
		searchBookList.get(0).setTotal(jsonElement.getAsJsonObject().get("total").getAsString());
		
		return searchBookList;
		
	}

	private String getSearchBooks(String search) {

		URL url ;
		HttpURLConnection httpConnection;
		String queryText = null;
		
		try {
			queryText = URLEncoder.encode(search,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		queryText = this.naver_book_url + "?query=" + queryText;
		queryText += "&display=20";
		queryText += "&start=20";
		
		try {
			
			url = new URL(queryText);
			
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("GET");
			
			httpConnection.setRequestProperty("X-Naver-Client-Id", NaverConfig.NaverClientId);
			httpConnection.setRequestProperty("X-Naver-Client-Secret", NaverConfig.NaverClientSecret);
			
			int responseCode = httpConnection.getResponseCode();
			
			if(responseCode == 200) {
				InputStreamReader is = new InputStreamReader(httpConnection.getInputStream());
				
				BufferedReader buffer = new BufferedReader(is);
				
				String responseString = "";
				String reader = new String();
				
				while(true) {
					reader = buffer.readLine();
					if(reader == null) {
						break;
					}
					
					responseString += reader;
				}
				buffer.close();
				
				return responseString;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
