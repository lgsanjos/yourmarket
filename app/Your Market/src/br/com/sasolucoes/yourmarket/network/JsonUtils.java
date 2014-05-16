package br.com.sasolucoes.yourmarket.network;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtils<T> {
	
	private static <T> JsonUtils<T> create(T t) {
		return new JsonUtils<T>();
	}
	
	public static <T> JsonUtils<T> get(Class clazz) {
		try {
			Class obj = (Class) clazz.newInstance();
			return (JsonUtils<T>) create(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public List<T> jsonToObject(String json) {
		Gson gson = new Gson();
		Type type = new TypeToken<ArrayList<T>>() {	}.getType();
		
		return gson.fromJson(json, type);
	}
	
}
