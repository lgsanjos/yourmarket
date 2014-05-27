package br.com.sasolucoes.yourmarket.business.category.ui;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.com.sasolucoes.yourmarket.R;
import br.com.sasolucoes.yourmarket.business.category.Category;

public class CategoryArrayAdapter extends ArrayAdapter<Category> {

	private Context context;
	private List<Category> categories;
	
	public CategoryArrayAdapter(Context context, int resource, List<Category> categories) {
		super(context, resource, categories);
		this.context = context;
		this.categories = categories;
	}
	
    public View getView(int position, View convertView, ViewGroup parent) {
    	ViewHolder holder;
		
        if (convertView == null) {
        	LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.category_item, null);
            holder = createHolder(convertView);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        
        
        holder.categoryName.setText(categories.get(position).name);
        return convertView;
    }
        
    private ViewHolder createHolder(View convertView) {
    	ViewHolder holder = new ViewHolder();
    	holder.categoryName = (TextView) convertView.findViewById(R.id.category_name);
       	return holder;
    }
	
	private class ViewHolder {
		TextView categoryName;
	}

}