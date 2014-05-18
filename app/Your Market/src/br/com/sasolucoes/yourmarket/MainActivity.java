package br.com.sasolucoes.yourmarket;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import br.com.sasolucoes.yourmarket.repository.category.Category;
import br.com.sasolucoes.yourmarket.repository.category.CategoryRepository;
import br.com.sasolucoes.yourmarket.repository.category.Subcategory;
import br.com.sasolucoes.yourmarket.repository.category.SubcategoryRepository;


public class MainActivity extends Activity {

	private CategoryRepository categoryRepository = new CategoryRepository();
	private TextView listCategorias;
	private TextView listSubcategories;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listCategorias = (TextView) findViewById(R.id.list_categorias);
		listSubcategories = (TextView) findViewById(R.id.list_subcategories);
	}

	private void syncCategories() {
		try {
			List<Category> categories = categoryRepository.selectAll();
			
			String text = "";
			for (Category cat : categories)
				text += cat.name + " - " + cat.description + "\n"; 
			
			listCategorias.setText(text);
		} catch (Exception e) {
			e.printStackTrace();
			listCategorias.setText(e.getMessage());
		}
	}
	
	private void syncSubcategories() {
		SubcategoryRepository repository = new SubcategoryRepository();
		repository.update();
		
		String text = "";
		for (Subcategory sc : repository.getAll()) {
			text += sc.categoryId + " - " + sc.id + " - " + sc.name + " - " + sc.description + "\n";
		}
		
		listSubcategories.setText(text);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.sync_categories) {
			syncCategories();
			return true;
		}
		
		
		if (id == R.id.sync_subcategories) {
			syncSubcategories();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}


}
