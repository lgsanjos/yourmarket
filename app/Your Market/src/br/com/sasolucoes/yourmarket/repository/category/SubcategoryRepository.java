package br.com.sasolucoes.yourmarket.repository.category;

import static br.com.sasolucoes.yourmarket.network.ServerUtils.createUrl;
import static br.com.sasolucoes.yourmarket.network.ServerUtils.getServerUrl;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.sasolucoes.yourmarket.repository.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SubcategoryRepository extends Repository {

	private final static String getAllService = "get_subcategories";
	
	private List<Subcategory> subcategories = new ArrayList<Subcategory>();

	public void update() {
		URL url = getURL();
		String result = executeService(url);
		List<Subcategory> newSubcategories = convertFromJson(result);
		setSubcategories(newSubcategories);
	}
	
	public List<Subcategory> getAll() {
		if (subcategories.isEmpty())
			update();
		
		return subcategories;
	}
	
	public List<Subcategory> getByCategory(Integer idCategory) {
		ArrayList<Subcategory> result = new ArrayList<Subcategory>();
		for (Subcategory sc : getAll()) {
			if (sc.categoryId.equals(idCategory))
				result.add(sc);
		}
		return result;
	}
	
	private void setSubcategories(List<Subcategory> value) {
		if (value == null)
			return;
		
		this.subcategories = value;
	}

	private List<Subcategory> convertFromJson(String result) {
		Type collectionType = new TypeToken<ArrayList<Subcategory>>(){}.getType();
		
		Gson gson = new Gson();
		List<Subcategory> fromJson = gson.fromJson(result, collectionType);
		
		return fromJson;
	}

	private URL getURL() {
		URL serverUrl = getServerUrl();
		serverUrl = createUrl(serverUrl + getAllService); 
		return serverUrl; 
	}
}
