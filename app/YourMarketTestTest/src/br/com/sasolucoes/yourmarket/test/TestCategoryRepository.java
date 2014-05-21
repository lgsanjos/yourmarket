package br.com.sasolucoes.yourmarket.test;

import java.util.List;

import junit.framework.TestCase;
import android.test.suitebuilder.annotation.SmallTest;
import br.com.sasolucoes.yourmarket.business.category.Category;
import br.com.sasolucoes.yourmarket.network.JsonUtils;

public class TestCategoryRepository extends TestCase {
	
	private final String JSON_CATEGORY = "[{'id':1,'name':'cate1','description':'category 1'},{'id':2,'name':'cate2','description':'category 2'}]";
	
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
	
}
