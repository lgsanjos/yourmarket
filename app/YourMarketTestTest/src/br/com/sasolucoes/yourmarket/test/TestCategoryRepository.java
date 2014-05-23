package br.com.sasolucoes.yourmarket.test;

import java.util.List;

import junit.framework.TestCase;
import android.test.suitebuilder.annotation.SmallTest;
import br.com.sasolucoes.yourmarket.business.category.Category;
import br.com.sasolucoes.yourmarket.business.category.Subcategory;
import br.com.sasolucoes.yourmarket.network.JsonUtils;

public class TestCategoryRepository extends TestCase {
	
	private final String JSON_CATEGORY = "[{'id':1,'name':'cate1','description':'category 1'},{'id':2,'name':'cate2','description':'category 2'}]";
	private final String JSON_SUBCATEGORY = "[{'id':1,'name':'cate1','description':'subcategory 1', 'category_id' : 1},{'id':2,'name':'cate2','description':'subcategory 2', 'category_id' : 4}]";
	
	@SmallTest
	public void testConversionOfJsonToCategory() {
		List<Category> categories = JsonUtils.convertCategory(JSON_CATEGORY);
		assertEquals(Integer.valueOf(2), Integer.valueOf(categories.size()));
		
		Category category1, category2;
		category1 = categories.get(0);
		category2 = categories.get(1);
		
		assertEquals(Integer.valueOf(1), category1.id);
		assertEquals("cate1", category1.name);
		assertEquals("category 1", category1.description);
		
		assertEquals(Integer.valueOf(2), category2.id);
		assertEquals("cate2", category2.name);
		assertEquals("category 2", category2.description);
	}
	
	@SmallTest
	public void testConversionOfJsonToSubcategory() {
		List<Subcategory> categories = JsonUtils.convertSubcategory(JSON_SUBCATEGORY);
		assertEquals(Integer.valueOf(2), Integer.valueOf(categories.size()));
		
		Subcategory sub1, sub2;
		sub1 = categories.get(0);
		sub2 = categories.get(1);
		
		assertEquals(Integer.valueOf(1), sub1.id);
		assertEquals("cate1", sub1.name);
		assertEquals("subcategory 1", sub1.description);
		assertEquals(Integer.valueOf(1), sub1.categoryId);
		
		assertEquals(Integer.valueOf(2), sub2.id);
		assertEquals("cate2", sub2.name);
		assertEquals("subcategory 2", sub2.description);
		assertEquals(Integer.valueOf(4), sub2.categoryId);		
	}
	
}
