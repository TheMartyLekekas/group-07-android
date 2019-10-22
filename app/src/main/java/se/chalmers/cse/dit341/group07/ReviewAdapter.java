package se.chalmers.cse.dit341.group07;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import se.chalmers.cse.dit341.group07.model.Review;


/*
 * {@link ProductAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Product} objects.
 * */
public class ReviewAdapter extends ArrayAdapter<Review> implements Serializable {
private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemListener(OnItemClickListener listner){
        mListener=listner;
    }
    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context    The current context. Used to inflate the layout file.
     * @param reviews   A List of Product objects to display in a list
     */
    public ReviewAdapter(Activity context, ArrayList<Review> reviews) {                          //, description, price, seller, category, reviews
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, reviews);
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
                    R.layout.review_item, parent, false);
        }

        Review currentReview = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID product_name,
        // product_number, list_item_icon and set them to respective views
        TextView textTextView = listItemView.findViewById(R.id.review_text);
        textTextView.setText(currentReview.getText());

        TextView ratingTextView = listItemView.findViewById(R.id.review_rating);
        ratingTextView.setText(String.valueOf(currentReview.getRating()));

        TextView dateTextView = listItemView.findViewById(R.id.review_date);
        dateTextView.setText(String.valueOf(currentReview.getDate()));

//        TextView imageTextView = listItemView.findViewById(R.id.product_image_id);
//        imageTextView.setText(String.valueOf(currentProduct.getImageResourceId()));



        return listItemView;
    }
}