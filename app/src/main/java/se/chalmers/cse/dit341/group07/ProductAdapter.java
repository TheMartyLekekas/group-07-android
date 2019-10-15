package se.chalmers.cse.dit341.group07;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import se.chalmers.cse.dit341.group07.model.Product;


/*
 * {@link ProductAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Product} objects.
 * */
public class ProductAdapter extends ArrayAdapter<Product> {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context    The current context. Used to inflate the layout file.
     * @param products   A List of Product objects to display in a list
     */
    public ProductAdapter(Activity context, ArrayList<Product> products) {                          //, description, price, seller, category, reviews
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, products);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position in the list of data that should be displayed in the list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Product currentProduct = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID product_name,
        // product_number, list_item_icon and set them to respective views
        TextView nameTextView = listItemView.findViewById(R.id.product_name);
        nameTextView.setText(currentProduct.getProductName());

        TextView numberTextView = listItemView.findViewById(R.id.product_number);
        numberTextView.setText(String.valueOf(currentProduct.getPrice()));

        ImageView iconView = listItemView.findViewById(R.id.list_item_icon);
        iconView.setImageResource(currentProduct.getImageResourceId());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}