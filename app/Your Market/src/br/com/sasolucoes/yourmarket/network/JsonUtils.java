package br.com.sasolucoes.yourmarket.network;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.sasolucoes.yourmarket.business.category.Category;
import br.com.sasolucoes.yourmarket.business.category.Subcategory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("unchecked")
public class JsonUtils {
	
	@SuppressWarnings("rawtypes")
	private static List convert(final String json, Type type) {
		Gson gson = new Gson();
		return gson.fromJson(json, type);
	}
	
	public static List<Category> convertCategory(final String json) {
		Type collectionType = new TypeToken<ArrayList<Category>>(){}.getType();
		return convert(json, collectionType);
	}

	public static List<Subcategory> convertSubcategory(final String json) {
		Type collectionType = new TypeToken<ArrayList<Subcategory>>(){}.getType();
		return convert(json, collectionType);
	}
}