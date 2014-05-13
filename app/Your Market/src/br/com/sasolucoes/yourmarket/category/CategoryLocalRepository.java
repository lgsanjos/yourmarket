package br.com.sasolucoes.yourmarket.category;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class CategoryLocalRepository {

	private ArrayList<Category> persisted = new ArrayList<Category>();
	
	public List<Category> selectAll() {
		return persisted;
	}

	public void update(List<Category> categories) {
		List<Category> localCategories = selectAll();
		
		HashSet<Category> merged = new HashSet<Category>(localCategories);
		merged.addAll(categories);
		
		persisted.clear();
		persisted.addAll(merged);
	}

}
