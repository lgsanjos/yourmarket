package br.com.sasolucoes.yourmarket;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import br.com.sasolucoes.yourmarket.business.category.Category;
import br.com.sasolucoes.yourmarket.business.category.CategoryRepository;
import br.com.sasolucoes.yourmarket.business.category.subcategory.Subcategory;
import br.com.sasolucoes.yourmarket.business.category.subcategory.SubcategoryRepository;
import br.com.sasolucoes.yourmarket.business.category.ui.CategoryListActivity;
import br.com.sasolucoes.yourmarket.business.seller.SellerActivity;

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

		switch(id) {
			case R.id.sync_categories:
				syncCategories();
				return true;
			case R.id.sync_subcategories:
				syncSubcategories();
				return true;
			case R.id.seller_activity:
				startActivity(new Intent(this, SellerActivity.class));
				return true;
			case R.id.open_categories:
				Intent intent = new Intent(this, CategoryListActivity.class);
				startActivity(intent);
				return true;
				
		}
		
		return super.onOptionsItemSelected(item);
	}


}
